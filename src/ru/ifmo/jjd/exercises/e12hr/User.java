package ru.ifmo.jjd.exercises.e12hr;

import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class User {
    final String fullName;
    private int salary;
    private Position position;

    public User(String fullName, int salary, Position position) {
        this(fullName, salary, position.name);
    }

    public User(String fullName, int salary, String positionName) {
        String name = normalize(fullName);
        if (name.isBlank()) {
            throw new IllegalArgumentException("Given name '" + fullName + "' is invalid");
        }
        Position position = Position.nameToValue(positionName);
        if (position == null) {
            throw new IllegalArgumentException("Position name '" + positionName + "' is invalid or unknown");
        }
        this.fullName = fullName;
        this.salary = Math.min(Math.max(position.lowerSalary, salary), position.upperSalary);
        setPosition(position);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.min(Math.max(position.lowerSalary, salary), position.upperSalary);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null) {
            throw new NullPointerException("Position cannot be null");
        }
        this.position = position;
    }
}
