package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

abstract public class WildAnimal extends Animal implements CanBeFrightened, CanAttack {
    private static Random r = new Random(new Date().getTime());

    private final int attack; // [10;20]
    private int timesFrightened;

    public WildAnimal(String name) {
        super(name);
        attack = 10 + r.nextInt(11);
    }

    public WildAnimal(String name, int weight, int speed) {
        super(name, weight, speed);
        attack = 10 + r.nextInt(11);
    }

    public WildAnimal(String name, int weight, int speed, int attack) {
        super(name, weight, speed);
        this.attack = Math.min(20, Math.max(10, attack));
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public boolean isFrightened() {
        return timesFrightened > 2;
    }

    @Override
    public void beFrightened() {
        System.out.println(this + " испугался фермера и сбежал");
        timesFrightened++;
    }

    @Override
    public void attack(CanRunAway prey) {
        if (getSpeed() > prey.getSpeed()) {
            System.out.print(this + " атакует " + prey + ". ");
            prey.beAttacked(getAttack());
        } else {
            System.out.println(prey + " убежал от " + this);
        }
    }
}
