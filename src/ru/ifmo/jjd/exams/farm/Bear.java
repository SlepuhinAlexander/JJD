package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Bear extends WildAnimal {

    public Bear() {
        super("Медведь", randomInt(80, 101),
                randomInt(3, 7), randomInt(12, 21));
    }

    public Bear(int weight, int speed) {
        super("Медведь", weight, speed, randomInt(12, 21));
    }

    public Bear(int weight, int speed, int attack) {
        super("Медведь", weight, speed, attack);
    }
}
