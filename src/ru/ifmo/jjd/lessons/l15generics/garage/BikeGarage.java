package ru.ifmo.jjd.lessons.l15generics.garage;

// наследование от класса c generic
public class BikeGarage extends Garage {
    /*
     * Класс Garage это generic класс.
     * Если не указать тип, то будет использоваться Garage<Object>.
     *
     * При наследовании от generic класса нужно либо указать конкретный тип либо ничего.
     * Использовать здесь generic тип вроде extends Garage<T> - нельзя.
     * */

    public void someVoid() {
        // getCarOnService вернёт объект типа Transport потому что им ограничен сам generic Garage
        System.out.println(this.getCarOnService().getNum());
    }
}

class BikeGarage2 extends Garage<Transport> {
    public void someVoid() {
        // getCarOnService вернёт объект типа Transport т.к. он указан в гараже
        System.out.println(this.getCarOnService().getDepStation());
    }
}

class BikeGarage3 extends Garage<Bus> {
    public void someVoid() {
        // getCarOnService вернёт объект типа Bus, т.к. он указан в гараже. И можно использовать методы Bus.
        System.out.println(this.getCarOnService().isWiFi());
    }
}