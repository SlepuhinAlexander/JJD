package ru.ifmo.jjd.exercises.lesson11.battleships;

import ru.ifmo.jjd.exercises.lesson11.battleships.engine.Player;

import static ru.ifmo.jjd.exercises.lesson11.battleships.UserInteraction.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.print;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class Game {
    private static Player first;
    private static Player second;
    private static Player active;
    private static Player inactive;

    public static void start() {
        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            switch (selectMainMenu()) {
                case 0 -> initialize();
                case 1 -> help();
                case 2 -> {
                    return;
                }
            }
        }
    }

    private static void play() {
        active = first;
        inactive = second;
        Player lastActive = inactive;
        do {
            if (lastActive != active) {
                pause(1000);
                println(active.name + " takes the turn\n");
                active.display();
            }
            lastActive = active;
            if (active.isAI()) {
                active.AIShoot();
            } else {
                active.makeAMove();
            }
        } while (!checkWinningConditions());
    }

    private static void initialize() {

        print("""
                Setting up the first player
                """);
        boolean firstIsAI = selectAI();
        String firstName;
        if (firstIsAI) {
            firstName = "Computer" + randomInt(100, 1000);
        } else {
            firstName = selectName();
        }
        int[] firstField, firstFleet;
        while (true) {
            firstField = selectFieldSize();
            firstFleet = selectFleet();
            first = new Player(firstName, firstField[0], firstField[1], firstFleet);
            try {
                first.placeRandomly();
            } catch (IllegalArgumentException e1) {
                println(e1.getMessage());
                println("Try selecting larger battlefield or smaller fleet");
                continue;
            }
            break;
        }
        if (!firstIsAI) {
            placeFleet(first, firstFleet);
        }

        print("""
                Setting up the second player
                """);
        boolean secondIsAI = selectAI();
        String secondName;
        if (secondIsAI) {
            secondName = "Computer" + randomInt(100, 1000);
        } else {
            secondName = selectName();
        }
        int[] secondField, secondFleet;
        while (true) {
            secondField = selectFieldSize();
            secondFleet = selectFleet();
            second = new Player(secondName, secondField[0], secondField[1], secondFleet);
            try {
                second.placeRandomly();
            } catch (IllegalArgumentException e) {
                println(e.getMessage());
                println("Try selecting larger battlefield or smaller fleet");
                continue;
            }
            break;
        }
        if (!secondIsAI) {
            placeFleet(second, secondFleet);
        }

        first.selectEnemy(second);
        second.selectEnemy(first);
        first.getReady();
        second.getReady();
        if (firstIsAI) {
            first.enableAI();
        }
        if (secondIsAI) {
            second.enableAI();
        }
        if (firstIsAI && secondIsAI) {
            Settings.showAIField = true;
        }
        play();
    }

    public static void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void passTurn() {
        active = active == first ? second : first;
        inactive = active == first ? second : first;
    }

    private static boolean checkWinningConditions() {
        if (inactive.isDefeated()) {
            println(inactive.name + " is defeated! " + active.name + " wins!");
        }
        return inactive.isDefeated();
    }
}
