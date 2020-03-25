package ru.ifmo.jjd.examfarm;

import org.w3c.dom.ls.LSOutput;

import java.util.Date;
import java.util.Random;

abstract public class DomesticatedAnimal extends Animal implements CanRunAway, CanBeFed {
    private final int maxHealth; // [10; 40]
    private int health; // [0;maxHealth]
    private final int resources; // [2;5]

    public DomesticatedAnimal(String name) {
        super(name);
        Random r = new Random(new Date().getTime());
        maxHealth = 10 + r.nextInt(31);
        health = maxHealth;
        resources = 2 + r.nextInt(4);
    }

    public DomesticatedAnimal(String name, int weight, int speed) {
        super(name, weight, speed);
        Random r = new Random(new Date().getTime());
        maxHealth = 10 + r.nextInt(31);
        health = maxHealth;
        resources = 2 + r.nextInt(4);
    }

    public DomesticatedAnimal(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed);
        this.maxHealth = Math.min(40, Math.max(10, maxHealth));
        health = maxHealth;
        this.resources = Math.min(5, Math.max(2, resources));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.min(maxHealth, Math.max(0, health));
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getResources() {
        return resources;
    }

    @Override
    public void beAttacked(int damage) {
        System.out.print(this + " получает урон " + damage + ". ");
        setHealth(health - damage);
        System.out.println("Здоровье: " + getHealth());
    }

    @Override
    public void beFed() {
        if (health != 0) {
            System.out.print(this + " покормлен. ");
            setHealth(health + 1);
            System.out.println("Здоровье: " + getHealth());
        }
    }
}
