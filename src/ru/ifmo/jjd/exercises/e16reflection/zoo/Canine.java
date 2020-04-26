package ru.ifmo.jjd.exercises.e16reflection.zoo;

public abstract class Canine extends Animal implements Carnivore{
    public static final int TEETH = 30;

    public Canine(String name, String species) {
        super(name, "Mammal", "Canine", species);
    }
}
