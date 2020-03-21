package ru.ifmo.jjd.lessons.lesson08.animals;

public class Cat implements CanEat, CanBeEaten {
    @Override
    public void beEaten() {
        System.out.println("Кот съеден");
    }

    @Override
    public void eat(CanBeEaten animal) {
        animal.beEaten();
    }
}
