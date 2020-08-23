package ru.ifmo.jjd.exercises.e12hr;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.StringHelper.startsWithIgnoreCase;


public class Accounting {
    private final User[][] staff;

    public Accounting() {
        staff = new User[Position.values().length][100];
    }

    public static void main(String[] args) {
        Accounting accounting = new Accounting();
        for (int i = 0; i < 10; i++) {
            accounting.addUserPrompt();
        }
        accounting.printStuff();
    }

    public void addUser(String name, int salary, Position position) {
        User[] users = getUsers(position);
        if (users == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        if (users[users.length - 1] != null) {
            throw new IllegalStateException("Stuff accounts for " + position.name + " is full");
        }
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                User user;
                try {
                    user = new User(name, salary, position);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Cannot create a user: " + e.getMessage());
                }
                users[i] = user;
                break;
            }
        }

    }

    private void addUser(String fullName, Position position) {
        if (position == null) {
            throw new NullPointerException("Position cannot be null");
        }
        addUser(fullName, randomInt(position.lowerSalary, position.upperSalary), position);
    }

    public void addUserPrompt() {
        String name, position;
        while (true) {
            println("Please enter your name and job position");
            while (true) {
                name = normalize(readLine("Your name: "));
                if (name.isBlank()) {
                    println("Given name is invalid or too short");
                } else {
                    break;
                }
            }
            while (true) {
                position = normalize(readLine("Your job position: "));
                int count = 0;
                for (Position value : Position.values()) {
                    if (startsWithIgnoreCase(value.name, position)) {
                        count++;
                    }
                }
                switch (count) {
                    case 0:
                        System.out.println("Could not find such job position. Please try again");
                        continue;
                    case 1:
                        for (Position value : Position.values()) {
                            if (startsWithIgnoreCase(value.name, position)) {
                                position = value.name;
                                break;
                            }
                        }
                        break;
                    default:
                        print("Could not identify your job position. Did you mean: ");
                        StringBuilder builder = new StringBuilder();
                        for (Position value : Position.values()) {
                            if (startsWithIgnoreCase(value.name, position)) {
                                builder.append(value.name).append(", ");
                            }
                        }
                        builder.setLength(builder.length() - 2);
                        builder.append("?");
                        println(builder.toString());
                        continue;
                }
                break;
            }
            try {
                addUser(name, Position.nameToValue(position));
            } catch (NullPointerException | IllegalArgumentException e) {
                println("Could not add a user: " + e.getMessage());
                println("Please try again.");
                continue;
            } catch (IllegalStateException e) {
                println(e.getMessage());
                break;
            }
            System.out.println("Your account has been created successfully");
            break;
        }
    }

    public User[] getUsers(Position position) {
        if (position == null) {
            return null;
        }
        return staff[position.ordinal()];
    }

    public void printStuff() {
        println("Accounts:");
        for (int i = 0; i < staff.length; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(Position.values()[i].name).append(": ");
            for (int j = 0; j < staff[i].length; j++) {
                if (staff[i][j] != null) {
                    builder.append(staff[i][j].fullName).append(", ");
                } else {
                    break;
                }
            }
            if (builder.toString().endsWith(", ")) {
                builder.setLength(builder.length() - 2);
            }
            println(builder.toString());
        }
    }
}
/*
 * Accounts:
 * CEO: John Johnson
 * CTO: Clark Carlson
 * CIO: Will Wilson
 * IT Director:
 * IT Manager: Ramona Flowers
 * Lead Developer: Joseph Woo, Alex Wong
 * Senior Developer: Victoria Bell
 * Developer: Jack Rex
 * Junior Developer: Samantha Black
 * Trainee: James Swift
 * */

