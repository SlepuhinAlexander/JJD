package ru.ifmo.jjd.lessons.l08staticfinal.animals;

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
