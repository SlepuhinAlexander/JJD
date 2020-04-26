package ru.ifmo.jjd.exercises.e16reflection.zoo;

public abstract class Feline extends Animal implements Carnivore {

    public static final int TEETH = 30;

    public Feline(String name, String species) {
        super(name, "Mammal", "Feline", species);
    }
}
