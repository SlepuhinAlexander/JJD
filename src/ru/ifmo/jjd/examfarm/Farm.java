package ru.ifmo.jjd.examfarm;

public class Farm {
    private DomesticatedAnimal[] animals = new DomesticatedAnimal[10];
    private WildAnimal[] wildAnimals = new WildAnimal[3];
    private Farmer farmer = new Farmer();

    public DomesticatedAnimal[] getAnimals() {
        return animals;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void addAnimal(DomesticatedAnimal animal) {
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] != null) animals[i] = animal;
        }
    }

    public void dayOnFarm() {
        System.out.print("Фермер проел 2 ресурса. ");
        farmer.spendResources();
        System.out.println("Осталось " + farmer.getResources());
    }
}
