package ru.ifmo.jjd.lessons.l10random;

import java.util.Date;
import java.util.Random;

public class RandomDemo {
    public static void main(String[] args) {
        /*
         * Класс Random - служебный класс из пакета java.util для работы со случайными величинами.
         *
         * У класса Random есть два конктруктора:
         * - без параметров (конструктор по умолчанию)
         * - с параметром seed
         *
         * Генерация случайных чисел начинается с некой отправной точки seed.
         * Принято передавать в него текущее время: new Date().getTime();
         * */
        Random random1 = new Random();
        Random random2 = new Random(new Date().getTime());

        // методы класса Random:
        System.out.println(random2.nextBoolean());
        /*
         * Возвращает случайное значение true или false.
         * */
        System.out.println(random2.nextInt());
        /*
         * Генерирует случайное int число.
         * */
        System.out.println(random2.nextInt(34));
        /*
         * Генерирует случайное int число в пределах [0;34)
         * Левая граница включена, правая граница не включена.
         * */
        System.out.println(random2.nextFloat());
        /*
         * Генерирует случайное float число
         * */
        System.out.println(random2.nextDouble());
        /*
         * Генерирует случайно double число
         * */
        random2.nextBytes(new byte[5]);
        /*
         * Наполняет переданный массив байт случайными значениями.
         * */

        // Алгоритм получеиня случайного числа из диапазона
        // Например хотим получить случайное число из диапазона [10;100] включительно.
        int min = 10;
        int max = 100;
        int diff = max - min;
        int randomInt = random2.nextInt(diff + 1) + min;
    }
}
