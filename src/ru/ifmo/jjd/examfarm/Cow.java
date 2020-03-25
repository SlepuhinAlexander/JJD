package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Cow extends DomesticatedAnimal implements CanBeEaten, CanGiveResources {
    private static Random r = new Random(new Date().getTime());

    public Cow(String name) {
        super(name, 50 + r.nextInt(51), 1 + r.nextInt(2),
                20 + r.nextInt(21), 3 + r.nextInt(3));
    }

    public Cow(String name, int weight, int speed) {
        super(name, weight, speed, 20 + r.nextInt(21), 3 + r.nextInt(3));
    }

    public Cow(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed, maxHealth, resources);
    }

    @Override
    public int beEaten() {
        int resources = 0;
        if (getHealth() > 0) {
            setHealth(0);
            resources = getWeight();
        }
        return resources;
    }
}
