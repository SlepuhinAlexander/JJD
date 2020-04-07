package ru.ifmo.jjd.lessons.l08staticfinal.animals;

public class Wolf implements CanEat {
    @Override
    public void eat(CanBeEaten animal) {
        animal.beEaten();
    }
}
