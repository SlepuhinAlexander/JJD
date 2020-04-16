package ru.ifmo.jjd.exercises.e10battleships.engine;

import ru.ifmo.jjd.exercises.e10battleships.Game;
import ru.ifmo.jjd.exercises.e10battleships.Settings;
import ru.ifmo.jjd.exercises.e10battleships.UserInteraction;
import ru.ifmo.jjd.exercises.e10battleships.ai.AI;

import java.util.ArrayList;
import java.util.HashSet;

import static ru.ifmo.jjd.utils.ConsoleHelper.print;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomBoolean;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.StringHelper.uppercaseFirst;

public class Player {
    public final String name;
    private final Field field;
    private final ArrayList<Ship> fleet = new ArrayList<>();
    private final int[] shipLengths;
    private Mode mode = Mode.SHOOT;
    private Player enemy;
    private Field enemyField;
    //    private boolean isShooting = true;
    private AI ai;

    public Player(String name, int height, int width, int... shipLengths) {
        String nameN = uppercaseFirst(normalize(name));
        if (nameN.isBlank()) {
            throw new IllegalArgumentException("Given player name '" + name + "' is invalid or too short");
        }
        this.name = nameN;
        this.field = new Field(height, width);
        this.shipLengths = shipLengths == null ? new int[0] : shipLengths;
    }

    public Player(String name, int height, int width) {
        this(name, height, width, Settings.SHIP_LENGTHS_DEFAULT);
    }

    public Player(String name, int... shipLengths) {
        this(name, Settings.FIELD_HEIGHT_DEFAULT, Settings.FIELD_WIDTH_DEFAULT, Settings.SHIP_LENGTHS_DEFAULT);
    }

    public Player(String name) {
        this(name, Settings.SHIP_LENGTHS_DEFAULT);
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        if (mode == null) {
            throw new NullPointerException("Mode cannot be null");
        }
        this.mode = mode;
    }

    public void selectEnemy(Player enemy) {
        if (enemy == null) {
            throw new NullPointerException("Enemy cannot be null");
        }
        if (enemy == this) {
            throw new IllegalArgumentException("You cannot be enemy for yourself");
        }
        this.enemy = enemy;
        enemyField = new Field(enemy.field.height, enemy.field.width);
    }

    public void placeRandomly() {
        if (shipLengths == null || shipLengths.length == 0) {
            return;
        }
        clearField();
        Cell base;
        boolean direction;
        ArrayList<Cell> cellCandidates = new ArrayList<>();
        for (int length : shipLengths) {
            cellCandidates.clear();
            for (Cell[] row : field.cells) {
                for (Cell c : row) {
                    if (c.getState() == Cell.State.NONE) {
                        cellCandidates.add(c);
                    }
                }
            }
            while (true) {
                if (cellCandidates.isEmpty()) {
                    field.clear();
                    throw new IllegalArgumentException("Cannot place all battleships to the field");
                }
                base = cellCandidates.get(randomInt(cellCandidates.size()));
                direction = randomBoolean();
                if (placeShip(base, length, direction).isEmpty()) {
                    break;
                } else if (placeShip(base, length, !direction).isEmpty()) {
                    break;
                } else {
                    cellCandidates.remove(base);
                }
            }
        }
    }

    public HashSet<Cell> placeShip(Cell base, int length, boolean isVertical) {
        verifyArguments(base, false);
        if (length < Settings.SHIP_LENGTH_MIN || length > Settings.SHIP_LENGTH_MAX) {
            throw new IllegalArgumentException("Battleship length must be in [" +
                    Settings.SHIP_LENGTH_MIN + ";" + Settings.SHIP_LENGTH_MAX + "]");
        }
        int count = 0;
        int x = base.x;
        int y = base.y;
        HashSet<Cell> shipCells = new HashSet<>();
        while (count < length && x < field.height && y < field.width) {
            shipCells.add(field.cells[x][y]);
            if (isVertical) x++;
            else y++;
            count++;
        }
        if (shipCells.size() == length) {
            HashSet<Cell> invalidCells = new HashSet<>();
            for (Cell shipCell : shipCells) {
                for (Cell cell : field.getNear(shipCell)) {
                    if (cell.getState() == Cell.State.BUSY) {
                        invalidCells.add(cell);
                    }
                }
            }
            if (invalidCells.isEmpty()) {
                Ship b = new Ship(shipCells);
                fleet.add(b);
                shipCells.forEach(cell -> cell.setState(Cell.State.BUSY));
                field.getNear(b).forEach(cell -> cell.setState(Cell.State.EMPTY));
            }
            return invalidCells;
        }
        return shipCells;
    }

    public HashSet<Cell> placeShip(int row, int col, int length, boolean isVertical) {
        verifyArguments(row, col, false);
        return placeShip(field.cells[row][col], length, isVertical);
    }

    public void AIShoot() {
        if (!isAI()) {
            throw new NullPointerException("AI is null or disabled");
        }
        Cell target = ai.getNextTarget();
        Game.pause(1000);
        shoot(target.x, target.y);
    }

    public void shoot(Cell target) {
        verifyArguments(target, true);
        print(name + " shoots " + enemy.name + " at " + target + ": ");
        boolean hasSunk = false;
        if (target.getState() == Cell.State.NONE || target.getState() == Cell.State.EMPTY) {
            Cell.State result = enemy.checkHit(target.x, target.y);
            if (result != null) {
                switch (result) {
                    case HIT -> {
                        Game.pause(1000);
                        target.setState(Cell.State.HIT);
                        hasSunk = enemy.checkHasSunk();
                        if (hasSunk) {
                            println("Sunk!");
                        } else {
                            println("Hit!");
                        }
                        println(name + " can make one more move");
                    }
                    case MISS -> {
                        Game.pause(1000);
                        println("Miss!");
                        target.setState(Cell.State.MISS);
                        Game.passTurn();
                    }
                }
                display();
            } else {
                println("Nothing changed: you have already shot there");
            }
        } else {
            println("You have already shot there");
        }
        if (isAI()) {
            ai.update(target, hasSunk);
        }
    }

    public void shoot(int row, int col) {
        verifyArguments(row, col, true);
        shoot(enemyField.cells[row][col]);
    }

    public void mark(Cell target) {
        verifyArguments(target, true);
        if (target.getState() == Cell.State.NONE) {
            target.setState(Cell.State.EMPTY);
        }
        display();
    }

    public void mark(int row, int col) {
        verifyArguments(row, col, true);
        mark(enemyField.cells[row][col]);
    }

    public void unMark(Cell target) {
        verifyArguments(target, true);
        if (target.getState() == Cell.State.EMPTY) {
            target.setState(Cell.State.NONE);
        }
        display();
    }

    public void unMark(int row, int col) {
        verifyArguments(row, col, true);
        unMark(enemyField.cells[row][col]);
    }

    public Cell.State checkHit(Cell target) {
        verifyArguments(target, false);
        if (target.getState() == Cell.State.NONE || target.getState() == Cell.State.EMPTY) {
            target.setState(Cell.State.MISS);
            return target.getState();
        }
        if (target.getState() == Cell.State.BUSY) {
            target.setState(Cell.State.HIT);
            return target.getState();
        }
        return null;
    }

    public Cell.State checkHit(int row, int col) {
        verifyArguments(row, col, false);
        return checkHit(field.cells[row][col]);
    }

    private boolean checkHasSunk() {
        for (Ship ship : fleet) {
            if (ship.checkIsSunk()) {
                fleet.remove(ship);
                return true;
            }
        }
        return false;
    }

    public void clearField() {
        field.clear();
    }

    public boolean isDefeated() {
        return fleet.isEmpty();
    }

    public void display() {
        if (isAI() && !Settings.showAIField) {
            return;
        }
        Game.pause(1000);
        String[] currentS = field.toStringArr();
        String[] enemyS = enemyField.toStringArr();
        String nameBuf = " ".repeat(currentS[0].length() - name.length() + 5);
        print(name);
        print(nameBuf);
        println(enemy.name);
        String buf = enemyField.height < field.height ? "" : " ".repeat(currentS[0].length());
        for (int i = 0; i <= Math.max(field.height, enemyField.height); i++) {
            println(String.join("     ",
                    i < currentS.length ? currentS[i] : buf,
                    i < enemyS.length ? enemyS[i] : ""));
        }
        println();
    }

    public void displayField() {
        println(name);
        for (String row : field.toStringArr()) {
            println(row);
        }
        println();
    }

    public void enableAI() {
        ai = new AI(enemyField);
    }

    public void disableAI() {
        ai = null;
    }

    public boolean isAI() {
        return ai != null;
    }

    public void getReady() {
        for (int i = 0; i < field.height; i++) {
            for (int j = 0; j < field.width; j++) {
                if (field.cells[i][j].getState() == Cell.State.EMPTY) {
                    field.cells[i][j].setState(Cell.State.NONE);
                }
            }
        }
    }

    public void makeAMove() {
        println("Selected mode: " + mode.desc);
        int[] target;
        while (true) {
            try {
                target = UserInteraction.selectTarget("Select target: ");
                if (target == null) {
                    setMode(UserInteraction.selectMode());
                    display();
                    return;
                }
                switch (mode) {
                    case SHOOT -> shoot(target[0], target[1]);
                    case MARK -> mark(target[0], target[1]);
                    case UNMARK -> unMark(target[0], target[1]);
                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                println("Invalid target. Please try again");
                continue;
            }
            break;
        }

    }

    private void verifyArguments(Cell target, boolean isEnemy) {
        if (target == null) {
            throw new NullPointerException("Target cell cannot be null");
        }
        if (isEnemy) {
            if (enemy == null || enemy == this || enemyField == null) {
                throw new NullPointerException("Enemy is null or improperly initialized");
            }
            if (!enemyField.contains(target)) {
                throw new IllegalArgumentException("Invalid link to target");
            }
        } else if (!field.contains(target)) {
            throw new IllegalArgumentException("Invalid link to target");
        }
    }

    private void verifyArguments(int row, int col, boolean isEnemy) {
        if (isEnemy) {
            if (enemy == null || enemy == this || enemyField == null) {
                throw new NullPointerException("Enemy is null or improperly initialized");
            }
            if (row < 0 || row >= enemyField.height) {
                throw new IndexOutOfBoundsException("Target row " + row +
                        " must be in [0;" + (enemyField.height - 1) + "]");
            }
            if (col < 0 || col >= enemyField.width) {
                throw new IndexOutOfBoundsException("Target col " + col +
                        " must be in [0;" + (enemyField.width - 1) + "]");
            }
        } else {
            if (row < 0 || row >= field.height) {
                throw new IndexOutOfBoundsException("Target row " + row +
                        " must be in [0;" + (field.height - 1) + "]");
            }
            if (col < 0 || col >= field.width) {
                throw new IndexOutOfBoundsException("Target col " + col +
                        " must be in [0;" + (field.width - 1) + "]");
            }
        }
    }

    public enum Mode {
        SHOOT("Shooting"), MARK("Marking"), UNMARK("Un-Marking");

        public final String desc;

        Mode(String desc) {
            this.desc = desc;
        }
    }
}
