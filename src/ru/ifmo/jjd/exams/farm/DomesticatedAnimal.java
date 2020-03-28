package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

abstract public class DomesticatedAnimal extends Animal implements CanRunAway {
    private final int maxHealth; // [10; 40]
    private int health; // [0;maxHealth]
    private final int resources; // [1;3]

    public DomesticatedAnimal(String name) {
        super(name);
        maxHealth = randomInt(10, 41);
        health = maxHealth;
        resources = randomInt(1, 3);
    }

    public DomesticatedAnimal(String name, int weight, int speed) {
        super(name, weight, speed);
        maxHealth = randomInt(10, 41);
        health = maxHealth;
        resources = randomInt(1, 3);
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

    public void beFed() {
        if (health != 0) {
            System.out.print(this + " покормлен. ");
            setHealth(health + 1);
            System.out.println("Здоровье: " + getHealth());
        }
    }

    void checkHealth() {
        if (health == 0) {
            System.out.println(this + " убит.");
        }
    }

    @Override
    public void beAttacked(int damage) {
        System.out.print(this + " получает урон " + damage + " ");
        setHealth(health - damage);
        System.out.println("Здоровье: " + getHealth());
        checkHealth();
    }
}
