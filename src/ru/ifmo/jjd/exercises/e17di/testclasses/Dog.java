package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.container.marks.Required;
import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;
import ru.ifmo.jjd.exercises.e17di.testclasses.config.DogConfig;

public class Dog extends Animal {
    @Required
    private DogConfig config;

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

    public void fight(Cat cat) {
        if (getStrength() < cat.getStrength()) {
            System.out.println(getName() + " could not beat " + cat.getName());
        } else {
            System.out.println(getName() + " has beaten up " + cat.getName());
        }
    }
}
