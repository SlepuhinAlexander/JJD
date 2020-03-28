package ru.ifmo.jjd.exams.farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

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
        System.out.print("Фермер собирает ресурсы с фермы. ");
        for (DomesticatedAnimal animal : farm.getAnimals()) {
            if (animal instanceof CanGiveResources && animal.getHealth() > 0) {
                collected += animal.getResources();
            }
        }
        setResources(resources + collected);
        System.out.println("Получно: " + collected + " ресурсов. Всего: " + resources);
        return collected > 0;
    }

    public void feedAnimals(Farm farm) {
        System.out.println("Фемрер кормит своих животных");
        for (DomesticatedAnimal animal : farm.getAnimals()) {
            if (animal != null) {
                feed(animal);
            }
        }
    }

    private void feed(DomesticatedAnimal animal) {
        animal.beFed();
    }

    public void eat(CanBeEaten animal) {
        if (animal != null) {
            int received = animal.beEaten();
            System.out.print("Фермер съел " + animal + " ");
            setResources(resources + received);
            System.out.println("Получено " + received + " ресурсов. Всего: " + resources);
        }
    }

    public boolean frighten(WildAnimal wildAnimal) {
        boolean isFrightened = false;
        if (randomBoolean()) {
            wildAnimal.beFrightened();
            isFrightened = true;
        }
        return isFrightened;
    }

    public void spendResources() {
        System.out.print("Фермер проел 2 ресурса. ");
        setResources(resources - 2);
        System.out.println("Осталось: " + resources);
    }
}
