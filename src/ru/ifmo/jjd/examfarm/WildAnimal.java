package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

abstract public class WildAnimal extends Animal implements CanBeFrightened, CanAttack{
    private final int attack; // [2;10]
    private int timesFrightened;

    public WildAnimal(String name) {
        super(name);
        Random r = new Random(new Date().getTime());
        attack = 2 + r.nextInt(9);
    }

    public WildAnimal(String name, int weight, int speed) {
        super(name, weight, speed);
        Random r = new Random(new Date().getTime());
        attack = 2 + r.nextInt(9);
    }

    public WildAnimal(String name, int weight, int speed, int attack) {
        super(name, weight, speed);
        this.attack = Math.min(10, Math.max(2, attack));
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public boolean isFrightened() {
        return timesFrightened > 3;
    }

    @Override
    public void beFrightened() {
        timesFrightened++;
    }

    @Override
    public void attack(CanRunAway prey) {
        if (getSpeed() > prey.getSpeed()) {
            System.out.println(this + " атакует " + prey);
            prey.beAttacked(getAttack());
        }
    }
}
