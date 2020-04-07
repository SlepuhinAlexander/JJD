package ru.ifmo.jjd.lessons.l08staticfinal;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
         * Вызов статических методов осуществляется по имени класса.
         * */
        System.out.println("Введите тип первого юнита");
        BattleUnit unit1 = BattleUnit.getBattleUnit(scanner.nextLine());
        System.out.println("Введите тип второго юнита");
        BattleUnit unit2 = BattleUnit.getBattleUnit(scanner.nextLine());
        unit1.attack(unit2);


        /*
         * Чтобы создать объект класса можно указать их тип данных, а можем указать их общий тип.
         *
         * Например, если мы будем работать через тип BattleUnit, то созданным объектам будут доступны только те
         * методы, что объявлены в общем типе.
         * */


    }
}
