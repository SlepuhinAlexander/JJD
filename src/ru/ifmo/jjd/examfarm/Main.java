package ru.ifmo.jjd.examfarm;

public class Main {
    public static void main(String[] args) {
        Farm farm = new Farm();
        WildAnimal[] wildAnimals = {};
        farm.addAnimal(new Cat("Васька"));
        farm.addAnimal(new Cow("Бурёнка"));
        farm.addAnimal(new Cow("Зорька"));
        farm.addAnimal(new Rabbit("Ушастик"));
        farm.addAnimal(new Rabbit("Зубастик"));
        farm.addAnimal(new Rabbit("Трусишка"));
        farm.addAnimal(new Chicken("Рыжая"));
        farm.addAnimal(new Chicken("Чёрная"));
        farm.addAnimal(new Chicken("Белая"));
        farm.addAnimal(new Chicken("Серая"));
        while (farm.getFarmer().getResources() > 2) {
            farm.dayOnFarm();

        }
        System.out.println("Фермер разорён");
    }
}
