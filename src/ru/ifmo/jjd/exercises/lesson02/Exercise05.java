package ru.ifmo.jjd.exercises.lesson02;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

import java.util.Random;

/*
 * Написать код, выводящий на экран случайно сгенерированное трёхзначное число и его наибольшую цифру.
 * Примеры работы программы:
 *   В числе 208 наибольшая цифра 8
 *   В числе 774 наибольшая цифра 7
 *   В числе 613 наибольшая цифра 6
 * */
public class Exercise05 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(900) + 100; // [100, 999]
        println("В числе " + n + " наибольшая цифра " + getLargestDigit(n));
    }

    static byte getLargestDigit(long num) {
        if (num < 0) num = -num;
        byte result = 0;
        while (num != 0) {
            if (num % 10 > result) result = (byte) (num % 10);
            num /= 10;
        }
        return result;
    }
}
/*
 * output:
 * В числе 762 наибольшая цифра 7
 * */
