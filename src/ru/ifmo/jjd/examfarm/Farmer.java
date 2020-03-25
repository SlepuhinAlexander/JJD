package ru.ifmo.jjd.examfarm;

import java.util.Date;
import java.util.Random;

public class Farmer {
    private int resources = 5;

    public int getResources() {
        return resources;
    }

    public void setResources(int resources) {
        this.resources = Math.max(0, resources);
    }

    public boolean collectResources(Farm farm) {
        int collected = 0;
        for (DomesticatedAnimal animal : farm.getAnimals()) {
            if (animal instanceof CanGiveResources && animal.getHealth() > 0) {
                collected += animal.getResources();
            }
        }
        setResources(resources + collected);
        return collected > 0;
    }

    public void feedAnimals(Farm farm) {
        for (DomesticatedAnimal animal : farm.getAnimals()) {
            feed(animal);
        }
    }

    private void feed(CanBeFed animal) {
        animal.beFed();
    }

    public void eat(CanBeEaten animal) {
        if (animal != null) {
            setResources(resources + animal.beEaten());
        }
    }

    public boolean frighten(CanBeFrightened wildAnimal) {
        boolean isFrightened = false;
        Random r = new Random(new Date().getTime());
        if (r.nextBoolean()) {
            wildAnimal.beFrightened();
            isFrightened = true;
        }
        return isFrightened;
    }

    public void spendResources() {
        setResources(resources - 2);
    }
}
