package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.testclasses.config.AnimalConfig;

public abstract class Animal extends Toon {
    private AnimalConfig config;

    public abstract int getSpeed();

    public abstract void setSpeed(int speed);

    public abstract int getStrength();

    public abstract void setStrength(int strength);
}
