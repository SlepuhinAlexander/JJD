package ru.ifmo.jjd.exercises.e14collections.experiment;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class Main {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("[HH:mm:ss.SSS]: ");
    private static final int SIZE = 1000000;
    private static final int AMOUNT = 1000;
    private static LocalTime begin;
    private static Duration elapsed;
    private static LinkedList<Integer> ll = new LinkedList<>();
    private static ArrayList<Integer> al = new ArrayList<>();


    public static void main(String[] args) {
        creationTest();
        readingTest(Position.FIRST);
        readingTest(Position.MIDDLE);
        readingTest(Position.LAST);
        addingTest(Position.FIRST);
        addingTest(Position.MIDDLE);
        addingTest(Position.LAST);
        removalTest(Position.FIRST);
        removalTest(Position.MIDDLE);
        removalTest(Position.LAST);
    }

    public static void creationTest() {
        println("---creationTest---");
        begin("creating a LinkedList with " + SIZE + " elements");
        ll = new LinkedList<>();
        for (int i = 0; i < SIZE; i++) {
            ll.add(randomInt());
        }
        end();
        avg(SIZE);
        begin("creating an ArrayList with " + SIZE + " elements");
        al = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            al.add(randomInt());
        }
        end();
        avg(SIZE);
    }

    public static void readingTest(Position position) {
        if (position == null) return;
        restart();
        println("---readingTest---");
        int pos = ll.size() / 2;
        Integer value;
        begin("Receiving " + position.getName() + " element form LinkedList of " + ll.size() + " elements " +
                AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            switch (position) {
                case FIRST -> value = ll.getFirst();
                case MIDDLE -> value = ll.get(pos);
                case LAST -> value = ll.getLast();
            }
        }
        end();
        avg(AMOUNT);
        pos = switch (position) {
            case FIRST -> 0;
            case MIDDLE -> al.size() / 2;
            case LAST -> al.size() - 1;
        };
        begin("Receiving " + position.getName() + " element form ArrayList of " + al.size() + " elements " +
                AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            value = al.get(pos);
        }
        end();
        avg(AMOUNT);
    }

    public static void addingTest(Position position) {
        if (position == null) return;
        restart();
        println("---addingTest---");
        int pos = ll.size() / 2;
        begin("Adding element to " + position.getName() + " position in LinkedList of " + ll.size() + " elements " +
                AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            switch (position) {
                case FIRST -> ll.addFirst(randomInt());
                case MIDDLE -> ll.add(pos, randomInt());
                case LAST -> ll.addLast(randomInt());
            }
        }
        end();
        avg(AMOUNT);
        pos = switch (position) {
            case FIRST -> 0;
            case MIDDLE -> al.size() / 2;
            case LAST -> al.size() - 1;
        };
        begin("Adding element to " + position.getName() + " position in ArrayList of " + al.size() + " elements " +
                AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            if (position == Position.LAST) {
                al.add(randomInt());
            } else {
                al.add(pos, randomInt());
            }
        }
        end();
        avg(AMOUNT);
    }

    public static void removalTest(Position position) {
        if (position == null) return;
        restart();
        println("---removalTest---");
        int pos = ll.size() / 2;
        begin("Removing element from " + position.getName() + " position in LinkedList of " + ll.size() +
                " elements " + AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            switch (position) {
                case FIRST -> ll.removeFirst();
                case MIDDLE -> ll.remove(pos);
                case LAST -> ll.removeLast();
            }
        }
        end();
        avg(AMOUNT);
        pos = switch (position) {
            case FIRST -> 0;
            case MIDDLE -> al.size() / 2;
            case LAST -> al.size() - 1;
        };
        begin("Removing element from " + position.getName() + " position in ArrayList of " + al.size() +
                " elements " + AMOUNT + " times");
        for (int i = 0; i < AMOUNT; i++) {
            if (position == Position.LAST) {
                al.remove(al.size() - 1);
            } else {
                al.remove(pos);
            }
        }
        end();
        avg(AMOUNT);
    }

    public static void begin(String message) {
        begin = LocalTime.now();
        println(begin.format(FORMAT) + message);
    }

    public static void begin() {
        begin("started");
    }

    public static void end(String message) {
        LocalTime end = LocalTime.now();
        println(end.format(FORMAT) + message);
        elapsed = Duration.between(begin, end);
        println("Elapsed: " + elapsed.getSeconds() + "." + "0".repeat(9 - ("" + elapsed.getNano()).length()) +
                elapsed.getNano() + " s.");
    }

    public static void end() {
        end("finished");
    }

    public static void avg(int amount) {
        long avg = (elapsed.getSeconds() * 1_000_000_000L + elapsed.getNano()) / amount;
        println("Average: " + avg + " nanos per operation");
    }

    private static void restart() {
        ll = new LinkedList<>();
        al = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            ll.add(randomInt());
            al.add(randomInt());
        }
    }

    private enum Position {
        FIRST("first"), MIDDLE("middle"), LAST("last");

        private String name;

        Position(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
