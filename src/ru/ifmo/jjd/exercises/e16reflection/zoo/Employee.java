package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Employee extends Person {
    private Position position;
    private int salary;

    public Employee(Person person, Position position, int salary) {
        super(person.getName(), person.getAge());
        setPosition(position);
        setSalary(salary);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        if (position == null) throw new NullPointerException();
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.min(Math.max(0, salary), 10000);
    }
}
