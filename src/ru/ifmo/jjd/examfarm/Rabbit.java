package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Rabbit extends DomesticatedAnimal implements CanBeEaten {
    private static Random r = new Random(new Date().getTime());

    public Rabbit(String name) {
        super(name, 5 + r.nextInt(6), 5 + r.nextInt(6),
                5 + r.nextInt(11), 0);
    }

    public Rabbit(String name, int weight, int speed) {
        super(name, weight, speed, 5 + r.nextInt(11), 0);
    }

    public Rabbit(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed, maxHealth, resources);
    }

    @Override
    public int beEaten() {
        int resources = 0;
        if (getHealth() > 0) {
            setHealth(0);
            checkHealth();
            resources = getWeight();
        }
        return resources;

    }
}
