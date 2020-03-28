package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Cow extends DomesticatedAnimal implements CanBeEaten, CanGiveResources {
    public Cow(String name) {
        super(name, randomInt(50, 101), randomInt(1, 3),
                randomInt(20, 41), randomInt(2, 4));
    }

    public Cow(String name, int weight, int speed) {
        super(name, weight, speed, randomInt(20, 41), randomInt(2, 4));
    }

    public Cow(String name, int weight, int speed, int maxHealth, int resources) {
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
