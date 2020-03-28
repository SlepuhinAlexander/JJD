package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Fox extends WildAnimal {
    public Fox() {
        super("Лисица", randomInt(20, 41),
                randomInt(4, 8), randomInt(5, 11));
    }

    public Fox(int weight, int speed) {
        super("Лисица", weight, speed, randomInt(5, 11));
    }

    public Fox(int weight, int speed, int attack) {
        super("Лисица", weight, speed, attack);
    }
}
