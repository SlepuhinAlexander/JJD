package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Tiger extends Feline {
    private int age;

    public Tiger(String name, int age) {
        super(name, "Panthera Tigris");
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
