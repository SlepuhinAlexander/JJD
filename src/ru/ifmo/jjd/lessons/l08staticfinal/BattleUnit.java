package ru.ifmo.jjd.lessons.l08staticfinal;

import java.util.Random;

abstract public class BattleUnit extends Unit implements CanAttack {
    protected int attackScore;

    public BattleUnit(int healthScore, int speed, int attackScore) {
        super(healthScore, speed); // вызов конструктора родительского класса
        setAttackScore(attackScore);
    }

    public int getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(int attackScore) {
        this.attackScore = attackScore;
    }

    @Override
    public void runFromField() {
        System.out.println("Бежал с поля боя");
    }

    /*
     * Нужно создать метод, который принимает на вход строку: "knight", "doctor", "infantry"
     * В зависимости от переданной строки необходимо создать и вернуть объект соответствующего класса.
     * */
    // Для этого метод будет возвращать тип данных BattleUnit
    public static BattleUnit getBattleUnit(String type) {
        Random r = new Random();
        BattleUnit battleUnit = null;
        if ("knight".equalsIgnoreCase(type)) {
            // healthScore [2;12), speed [1;8), attackScore [1;9), horseSpeed [3;16)
            battleUnit = new Knight(r.nextInt(10) + 2, r.nextInt(7) + 1,
                    r.nextInt(8) + 1, r.nextInt(13) + 3);
        } else if ("doctor".equalsIgnoreCase(type)) {
            // healthScore [2;12), speed [1;8), attackScore [1;9), medicineScore [3;16)
            battleUnit = new Doctor(r.nextInt(10) + 2, r.nextInt(7) + 1,
                    r.nextInt(8) + 1, r.nextInt(13) + 3);
        } else if ("infantry".equalsIgnoreCase(type)) {
            // healthScore [2;12), speed [1;8), attackScore [1;9), armor [3;16)
            battleUnit = new Infantry(r.nextInt(10) + 2, r.nextInt(7) + 1,
                    r.nextInt(8) + 1, r.nextInt(13) + 3);
        }
        return battleUnit;
    }
    /*
     * В подобных конструкциях принято использовать переменную, присваивать ей значения, и только один return.
     * А не прерывать выполнение каждой ветки кода своим return-ом.
     * */
}
