package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Wolf extends Canine {
    private int age;

    public Wolf(String name, int age) {
        super(name, "Canis Lupus");
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = Math.max(0, age);
    }

    @Override
    public void hunt() {
        System.out.println(getName() + " hunts for prey");
    }
}
