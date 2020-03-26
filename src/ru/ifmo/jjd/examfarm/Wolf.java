package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Wolf extends WildAnimal {
    private static Random r = new Random(new Date().getTime());

    public Wolf() {
        super("Волк", 30 + r.nextInt(21),
                5 + r.nextInt(6), 7 + r.nextInt(9));
    }

    public Wolf(int weight, int speed) {
        super("Волк", weight, speed, 7 + r.nextInt(9));
    }

    public Wolf(int weight, int speed, int attack) {
        super("Волк", weight, speed, attack);
    }
}
