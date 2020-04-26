package ru.ifmo.jjd.exercises.e16reflection.zoo;

public abstract class Bovine extends Animal implements Herbivore {
    public static final int HORNS = 2;

    public Bovine(String name, String species) {
        super(name, "Mammal", "Bovine", species);
    }
}
