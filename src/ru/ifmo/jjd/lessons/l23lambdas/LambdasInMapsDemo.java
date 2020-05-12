package ru.ifmo.jjd.lessons.l23lambdas;

import java.util.HashMap;

public class LambdasInMapsDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Сидней", 200);
        map.put("Лондон", 260);
        map.put("Париж", 376);

        // метод forEach
        /*
         * для Map метод forEach принимает на вход интерфейс BiConsumer<T, U>
         *
         * он имеет абстрактный метод
         *      void accept(T t, U u)
         * который принимает два аргумента, что-то с ними делает, и ничего не возвращает
         * */
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        /*
         * внутри метода forEach (в лямбде) изменять можно только value. Ключ в мапе изменять нельзя
         * */

        /*
         * ДЗ: посмотреть работу методов
         * map.computeIfAbsent()
         * map.computeIfPresent()
         * map.merge()
         * */
    }
}
