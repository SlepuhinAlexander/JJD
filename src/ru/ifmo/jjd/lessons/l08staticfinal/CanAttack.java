package ru.ifmo.jjd.lessons.l08staticfinal;

public interface CanAttack {

    void attack(BattleUnit enemy);

    default void runFromField() {
        System.out.println("Бежал с поля боя");
    }
}
