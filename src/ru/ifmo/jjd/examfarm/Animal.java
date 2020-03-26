package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

abstract public class Animal {
    private String name;
    private final int weight; // [5;100]
    private final int speed; // [2;10]

    public Animal(String name) {
        setName(name);
        Random r = new Random(new Date().getTime());
        weight = 5 + r.nextInt(96);
        speed = 2 + r.nextInt(9);
    }

    public Animal(String name, int weight, int speed) {
        setName(name);
        this.weight = Math.min(100, Math.max(5, weight));
        this.speed = Math.min(10, Math.max(2, speed));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !"".equals(name.replaceAll("[\\W&&[^А-Яа-я]]+", ""))) {
            this.name = name;
        }
    }

    public int getWeight() {
        return weight;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return name;
    }
}
