package ru.ifmo.jjd.lessons.lesson08.animals;

public class Wolf implements CanEat {
    @Override
    public void eat(CanBeEaten animal) {
        animal.beEaten();
    }
}
