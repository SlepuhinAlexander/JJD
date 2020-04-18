package ru.ifmo.jjd.lessons.l15generics.garage;

public class GarageTest {
    public static void main(String[] args) {
        Bus bus = new Bus("Спб", "Тверь", "234", false);
        Train train = new Train("Спб", "Москва", "23-Ф", 12);

        Garage<Bus> garage1 = new Garage<>();
        /*
         * При создании объекта garage1 его generic тип установлен как Bus, не Transport. Именно конкретный класс Bus.
         * */
        garage1.setCarOnService(bus);
        /*
         * При вызове методов garage1, в качестве generic аргумента они принимают уже тоже только Bus.
         * */
        System.out.println(garage1.getCarOnService().isWiFi());
        /*
         * Соответственно, при получении объектов, лежащих в гараже, будут возвращены объекты типа Bus.
         * Значит от них можно вызывать методы Bus.
         * */

        Garage<Train> garage2 = new Garage<>();
        /*
         * При создании объекта garage2 его generic тип установлен как Train, не Transport.
         * */
        garage2.setCarOnService(train);
        /*
         * При вызове методов garage2, в качестве generic аргумента они принимают уже тоже только Train.
         * */
        System.out.println(garage2.getCarOnService().getCarCount());

        /*
         * При создании объекта garage2 его generic тип установлен как Transport.
         * */
        Garage<Transport> garage3 = new Garage<>();
        garage3.setCarOnService(bus);
        // garage3.getCarOnService().isWiFi(); // метод не доступен
        System.out.println(((Bus) garage3.getCarOnService()).isWiFi()); // после приведения типа метод доступен.

        garage3.setCarOnService(train);
        // garage3.getCarOnService().getCarCount(); // метод не доступен
        System.out.println(((Train) garage3.getCarOnService()).getCarCount()); // после приведения типа метод доступен
    }
}
