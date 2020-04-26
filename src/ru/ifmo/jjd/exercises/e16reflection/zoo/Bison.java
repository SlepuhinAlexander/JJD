package ru.ifmo.jjd.exercises.e16reflection.zoo;

public class Bison extends Bovine {
    private int age;
    private Aviary aviary;

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

    public Aviary getAviary() {
        return aviary;
    }

    public void setAviary(Aviary aviary) {
        if (aviary == null) throw new NullPointerException();
        if (this.aviary != null) this.aviary.getInhabitants().remove(this);
        aviary.getInhabitants().add(this);
        this.aviary = aviary;
    }

    @Override
    public void graze() {
        System.out.println(name + " grazes");
    }
}

