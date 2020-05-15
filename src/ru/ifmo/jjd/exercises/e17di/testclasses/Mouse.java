package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.container.marks.Required;
import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;
import ru.ifmo.jjd.exercises.e17di.testclasses.config.MouseConfig;

public class Mouse extends Animal {
    @Required
    private MouseConfig config;

    @Override
    public String getName() {
        return config.getName();
    }

    @Override
    public void setName(String name) {
        config.setName(name);
    }

    @Override
    public int getSpeed() {
        return config.getSpeed();
    }

    @Override
    public void setSpeed(int speed) {
        config.setSpeed(speed);
    }

    @Override
    public int getStrength() {
        return config.getStrength();
    }

    @Override
    public void setStrength(int strength) {
        config.setStrength(strength);
    }
}
