package ru.ifmo.jjd.lessons.l08staticfinal;

public interface CanRest {
    void rest();

    default void runFromField() {
        System.out.println("Бежал с поля боя");
    }
}
