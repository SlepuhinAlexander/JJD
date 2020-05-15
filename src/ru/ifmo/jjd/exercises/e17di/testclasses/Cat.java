package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.container.marks.Required;
import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;
import ru.ifmo.jjd.exercises.e17di.testclasses.config.CatConfig;

import java.util.ArrayList;

public class Cat extends Animal{
    @Required
    private CatConfig config;

    @Required
    private Owner owner;

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

    private final ArrayList<Mouse> mice = new ArrayList<>(20);

    public void catchMouse(Mouse mouse) {
        if (getSpeed() < mouse.getSpeed()) {
            System.out.println(mouse.getName() + " ran away form " + getName());
        } else {
            System.out.println(getName() + " has caught " + mouse.getName());
            mice.add(mouse);
        }
    }

    public ArrayList<Mouse> getCaughtMice() {
        return mice;
    }
}
