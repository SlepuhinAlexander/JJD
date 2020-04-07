package ru.ifmo.jjd.lessons.l08staticfinal.animals;

public class Mouse implements CanBeEaten {
    @Override
    public void beEaten() {
        System.out.println("Мышь съедена");
    }
}
