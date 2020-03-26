package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

abstract public class DomesticatedAnimal extends Animal implements CanRunAway, CanBeFed {
    private static Random r = new Random(new Date().getTime());

    private final int maxHealth; // [10; 40]
    private int health; // [0;maxHealth]
    private final int resources; // [1;3]

    public DomesticatedAnimal(String name) {
        super(name);
        maxHealth = 10 + r.nextInt(31);
        health = maxHealth;
        resources = 1 + r.nextInt(3);
    }

    public DomesticatedAnimal(String name, int weight, int speed) {
        super(name, weight, speed);
        maxHealth = 10 + r.nextInt(31);
        health = maxHealth;
        resources = 1 + r.nextInt(3);
    }

    public DomesticatedAnimal(String name, int weight, int speed, int maxHealth, int resources) {
        super(name, weight, speed);
        this.maxHealth = Math.min(40, Math.max(10, maxHealth));
        health = maxHealth;
        this.resources = Math.min(3, Math.max(1, resources));
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

    void checkHealth() {
        if (health == 0) {
            System.out.println(this + " убит.");
        }
    }

    @Override
    public void beAttacked(int damage) {
        System.out.print(this + " получает урон " + damage + ". ");
        setHealth(health - damage);
        System.out.println("Здоровье: " + getHealth());
        checkHealth();
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
