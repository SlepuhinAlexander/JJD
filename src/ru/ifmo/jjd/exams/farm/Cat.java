package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Cat extends DomesticatedAnimal {

    public Cat(String name) {
        super(name, randomInt(5, 11), randomInt(7, 11),
                randomInt(10, 21), 0);
    }

    public Cat(String name, int weight, int speed) {
        super(name, weight, speed, randomInt(10, 20), 0);
    }

    public Cat(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed, maxHealth, resources);
    }
}
