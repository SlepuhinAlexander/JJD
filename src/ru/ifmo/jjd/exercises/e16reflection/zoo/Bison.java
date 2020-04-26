package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Bison extends Bovine {
    private int age;

    public Bison(String name, int age) {
        super(name, "Bison Bison");
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.max(0, age);
    }

    @Override
    public void graze() {
        System.out.println(name + " grazes");
    }
}

