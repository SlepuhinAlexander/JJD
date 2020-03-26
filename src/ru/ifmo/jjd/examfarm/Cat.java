package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Cat extends DomesticatedAnimal {
    private static Random r = new Random(new Date().getTime());

    public Cat(String name) {
        super(name, 5 + r.nextInt(6), 7 + r.nextInt(4),
                10 + r.nextInt(11), 0);
    }

    public Cat(String name, int weight, int speed) {
        super(name, weight, speed, 10 + r.nextInt(11), 0);
    }

    public Cat(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed, maxHealth, resources);
    }
}
