package ru.ifmo.jjd.exercises.e06farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Wolf extends WildAnimal {

    public Wolf() {
        super("Волк", randomInt(30, 51),
                randomInt(5, 11), randomInt(7, 16));
    }

    public Wolf(int weight, int speed) {
        super("Волк", weight, speed, randomInt(7, 16));
    }

    public Wolf(int weight, int speed, int attack) {
        super("Волк", weight, speed, attack);
    }
}
