package ru.ifmo.jjd.exercises.e10battleships.ai;

import ru.ifmo.jjd.exercises.e10battleships.engine.Cell;
import ru.ifmo.jjd.exercises.e10battleships.engine.Field;

import java.util.ArrayList;
import java.util.HashSet;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class AI {
    private final Field enemyField;
    private final ArrayList<Cell> availableTargets = new ArrayList<>();
    private final ArrayList<Cell> priorityTargets = new ArrayList<>();

    public AI(Field enemyField) {
        if (enemyField == null) {
            throw new NullPointerException("AI argument 'enemyField' cannot be null");
        }
        this.enemyField = enemyField;
        for (Cell[] row : enemyField.cells) {
            for (Cell cell : row) {
                if (cell.getState() == Cell.State.NONE) {
                    availableTargets.add(cell);
                }
                if (cell.getState() == Cell.State.HIT) {
                    priorityTargets.addAll(enemyField.getNearest(cell));
                }
            }
        }
        priorityTargets.retainAll(availableTargets);
    }

    public void update(Cell target, boolean hasSunk) {
        if (target == null) {
            throw new NullPointerException("Argument 'cell' cannot be null");
        }
        if (target.getState() == Cell.State.HIT && hasSunk) {
            enemyField.getNear(collectHits(target)).forEach(cell -> {
                cell.setState(Cell.State.EMPTY);
                priorityTargets.remove(cell);
                availableTargets.remove(cell);
            });
        } else if (target.getState() == Cell.State.HIT) {
            priorityTargets.addAll(enemyField.getNearest(target));
            priorityTargets.retainAll(availableTargets);
        }
        priorityTargets.remove(target);
        availableTargets.remove(target);
    }

    private HashSet<Cell> collectHits(Cell target) {
        if (target == null) {
            throw new NullPointerException("Target cell cannot be null");
        }
        HashSet<Cell> result = new HashSet<>();
        result.add(target);
        int prevSize;
        do {
            prevSize = result.size();
            enemyField.getNear(result).forEach(cell -> {
                if (cell.getState() == Cell.State.HIT) {
                    result.add(cell);
                }
            });
        } while (result.size() != prevSize);
        return result;
    }

    public Cell getNextTarget() {
        return !priorityTargets.isEmpty() ? priorityTargets.get(randomInt(priorityTargets.size())) :
                availableTargets.get(randomInt(availableTargets.size()));
    }
}
