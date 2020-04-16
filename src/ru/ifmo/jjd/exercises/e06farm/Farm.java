package ru.ifmo.jjd.exercises.e06farm;

import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Farm {
    private DomesticatedAnimal[] animals = new DomesticatedAnimal[10];
    private WildAnimal[] wildAnimals = new WildAnimal[5];
    private Farmer farmer = new Farmer();

    public DomesticatedAnimal[] getAnimals() {
        return animals;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void addAnimal(DomesticatedAnimal animal) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] == null) {
                animals[i] = animal;
                break;
            }
        }
    }

    public void addWildAnimal(WildAnimal animal) {
        for (int i = 0; i < wildAnimals.length; i++) {
            if (wildAnimals[i] == null) {
                wildAnimals[i] = animal;
                break;
            }
        }
    }

    public boolean dayOnFarm() {
        farmer.spendResources();
        if (farmer.getResources() == 0) {
            return false;
        }
        if (canSelectHunter()) {
            WildAnimal hunter = selectHunter();
            System.out.println("На ферму пробрался " + hunter);
            if (!farmer.frighten(hunter)) {
                if (canSelectPrey()) {
                    hunter.attack(selectPrey());
                } else {
                    System.out.println("А поживиться-то и нечем.");

                }
            }
        }
        farmer.feedAnimals(this);
        if (!farmer.collectResources(this)) {
            if (canSelectEdible()) {
                farmer.eat(selectEdible());
            }
        }
        return true;
    }

    private WildAnimal selectHunter() {
        WildAnimal hunter;
        do {
            hunter = wildAnimals[randomInt(wildAnimals.length)];
        } while (hunter == null || hunter.isFrightened());
        return hunter;
    }

    private boolean canSelectHunter() {
        for (WildAnimal animal : wildAnimals) {
            if (animal != null && !animal.isFrightened()) {
                return true;
            }
        }
        return false;
    }

    private DomesticatedAnimal selectPrey() {
        DomesticatedAnimal prey;
        do {
            prey = animals[randomInt(animals.length)];
        } while (prey == null || prey.getHealth() == 0);
        return prey;
    }

    private boolean canSelectPrey() {
        for (DomesticatedAnimal animal : animals) {
            if (animal != null && animal.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    private CanBeEaten selectEdible() {
        while (true) {
            int ind = randomInt(animals.length);
            if (animals[ind] instanceof CanBeEaten && animals[ind].getHealth() > 0) {
                return ((CanBeEaten) animals[ind]);
            }
        }
    }

    private boolean canSelectEdible() {
        for (DomesticatedAnimal animal : animals) {
            if (animal instanceof CanBeEaten && animal.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }
}
