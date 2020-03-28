package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Chicken extends DomesticatedAnimal implements CanBeEaten, CanGiveResources {

    public Chicken(String name) {
        super(name, randomInt(5, 11), randomInt(2, 6),
                randomInt(5, 11), randomInt(1, 3));
    }

    public Chicken(String name, int weight, int speed) {
        super(name, weight, speed, randomInt(5, 11), randomInt(1, 3));
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
