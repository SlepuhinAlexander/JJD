package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Bear extends WildAnimal {
    private static Random r = new Random(new Date().getTime());

    public Bear() {
        super("Медведь", 80 + r.nextInt(21),
                3 + r.nextInt(4), 12 + r.nextInt(9));
    }

    public Bear(int weight, int speed) {
        super("Медведь", weight, speed, 12 + r.nextInt(9));
    }

    public Bear(int weight, int speed, int attack) {
        super("Медведь", weight, speed, attack);
    }
}
