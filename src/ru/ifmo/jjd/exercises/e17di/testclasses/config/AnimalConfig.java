package ru.ifmo.jjd.exercises.e17di.testclasses.config;

import ru.ifmo.jjd.exercises.e17di.container.marks.ConfigClass;
import ru.ifmo.jjd.exercises.e17di.container.marks.Required;

@ConfigClass()
public abstract class AnimalConfig extends ToonConfig {
    private int speed;
    private int strength;

    public int getSpeed() {
        return speed;
    }

    @Required
    public void setSpeed(int speed) {
        this.speed = Math.min(Math.max(0, speed), 100);
    }

    public int getStrength() {
        return strength;
    }

    @Required
    public void setStrength(int strength) {
        this.strength = strength;
    }
}
