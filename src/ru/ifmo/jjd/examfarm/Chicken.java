package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Chicken extends DomesticatedAnimal implements CanBeEaten, CanGiveResources {
    private static Random r = new Random(new Date().getTime());

    public Chicken(String name) {
        super(name, 5 + r.nextInt(6), 2 + r.nextInt(4),
                5 + r.nextInt(6), 1 + r.nextInt(2));
    }

    public Chicken(String name, int weight, int speed) {
        super(name, weight, speed,5 + r.nextInt(6), 1 + r.nextInt(2));
    }

    public Chicken(String name, int weight, int speed, int maxHealth, int resources) {
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
