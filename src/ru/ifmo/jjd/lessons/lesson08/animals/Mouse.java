package ru.ifmo.jjd.lessons.lesson08.animals;

public class Mouse implements CanBeEaten {
    @Override
    public void beEaten() {
        System.out.println("Мышь съедена");
    }
}
