package ru.ifmo.jjd.lessons.lesson08;

public interface CanRest {
    void rest();

    default void runFromField() {
        System.out.println("Бежал с поля боя");
    }
}
