package ru.ifmo.jjd.lessons.l16collections.comparing;

import java.util.ArrayList;
import java.util.Arrays;

public class ComparingDemo {
    public static void main(String[] args) {
        Car blackOpel = new Car("black", "opel", 2000);
        Car redOpel = new Car("red", "opel", 2500);
        Car yellowMazda = new Car("yellow", "mazda", 3000);
        Car greenMazda = new Car("green", "mazda", 3000);

        /*
         * Объекты добавить в ArrayList
         * Отсортировать (с использованием компаратора) со следующим порядком сортировки: brand, price, color
         * */

        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(blackOpel, redOpel, yellowMazda, greenMazda));
        cars.forEach(System.out::println);

        cars.sort(new CarBrandComparator().thenComparing(new CarPriceComparator()).
                thenComparing(new CarColorComparator()));

        System.out.println("после сортировки");
        cars.forEach(System.out::println);

        /*
         * Car{color='black', brand='opel', price=2000}
         * Car{color='red', brand='opel', price=2500}
         * Car{color='yellow', brand='mazda', price=3000}
         * Car{color='green', brand='mazda', price=3000}
         * после сортировки
         * Car{color='green', brand='mazda', price=3000}
         * Car{color='yellow', brand='mazda', price=3000}
         * Car{color='black', brand='opel', price=2000}
         * Car{color='red', brand='opel', price=2500}
         * */
    }
}
