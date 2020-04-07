package ru.ifmo.jjd.lessons.l01basesyntax;

import java.util.Scanner;

public class BasicIODemo {
    public static void main(String[] args) {
        basicConsoleOutputDemo();
        basicConsoleInputDemo();
    }

    private static void basicConsoleOutputDemo() {
        /*
         * Базовый вывод в консоль использует 3 метда:
         *  - System.out.print();
         *      Выводит переданный аргумент (строку, число, любой другой тип в его стандартном представлении).
         *      Не переводит каретку на новую строку.
         *  - System.out.println();
         *      Аналогичен print().
         *      Отличается тем, что после вывода переданного аргумента переводит каретку на начало новой строки.
         *  - System.out.printf();
         *      Аналогичен print(), но способен форматировать переданные аргументы согласно заданному шаблону.
         *      Поддерживает гибкий набор ключей для формирования шаблона.
         * */
        System.out.print("Вывод без переноса строки. ");
        System.out.print("Перевод строки можно добавить принудительно символом \\n\n");
        System.out.println("Вывод текста \nпосле которого будет перенос строки");
        System.out.printf("Вывод %s %s текста\n", "гибко", "отформатированного");
        /*
         * Вывод без переноса строки. Перевод строки можно добавить принудительно символом \n
         * Вывод текста
         * после которого будет перенос строки
         * Вывод гибко отформатированного текста
         * */

        /*
         * %s --- для вставки строки
         *      %12s  --- при вставке для значения будет отведено не менее 12 символов
         * %d --- для вставки целочисленного значения
         *      %12d  --- при вставке для значение будет отведено не менее 12 символов
         *      %-12d --- аналогично, но выравнивание по левому краю
         *      %012d --- аналогично, но предстоящие незанятые символы заполнятся нулями, вместо пробелов
         *      %,d   --- использовать разделители разрядов
         * %f --- для вставки дробных чисел
         *      перечисленные выше ключи так же применимы с аналогичным результатом
         *      %.2d  --- дробная часть числа будет приведена к ровно 2 знакам
         *                  (лишние - округлить, недостающие - заполнить нулями)
         * */
        System.out.printf("Перед %12s после\n", "вставка");
        System.out.printf("Перед %12d после\n", 123);
        System.out.printf("Перед %012d после\n", 123);
        System.out.printf("Перед %-12d после\n", 123456);
        System.out.printf("Перед %,12d после\n", 123456789);
        System.out.printf("Перед %012f после\n", 123d / 456d);
        System.out.printf("Перед %12.2f после\n", 123d / 456d);
        System.out.printf("Перед %12.2f после\n", 1d / 10d);
        /*
         * Перед      вставка после
         * Перед          123 после
         * Перед 000000000123 после
         * Перед 123456       после
         * Перед  123 456 789 после
         * Перед 00000,269737 после
         * Перед         0,27 после
         * Перед         0,10 после
         * */
    }

    private static void basicConsoleInputDemo() {
        /*
         * Получение данных, введённых пользователем, из консоли можно реализовать с помощью служебного класса
         * java.util.Scanner
         * */
        Scanner scanner = new Scanner(System.in);
        // Создан объект типа Scanner прослушивающий поток System.in, которым по умолчанию является консоль.
        System.out.print("Введите число: ");
        int n = scanner.nextInt();
        System.out.printf("Квадрат вашего числа равен %,d\n", n * n);
    }
}