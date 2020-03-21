package ru.ifmo.jjd.lessons.lesson08;

public interface CanAttack {

    void attack(BattleUnit enemy);

    default void runFromField() {
        System.out.println("Бежал с поля боя");
    }
}
