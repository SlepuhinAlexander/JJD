package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Fox extends WildAnimal {
    private static Random r = new Random(new Date().getTime());

    public Fox() {
        super("Лисица", 20 + r.nextInt(21),
                4 + r.nextInt(2), 2 + r.nextInt(4));
    }

    public Fox(int weight, int speed) {
        super("Лисица", weight, speed, 2 + r.nextInt(4));
    }

    public Fox(int weight, int speed, int attack) {
        super("Лисица", weight, speed, attack);
    }
}
