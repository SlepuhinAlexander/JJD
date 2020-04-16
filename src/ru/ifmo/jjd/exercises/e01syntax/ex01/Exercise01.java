package ru.ifmo.jjd.exercises.e01syntax.ex01;

import static ru.ifmo.jjd.utils.RandomHelper.*;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise01 {
    public static void main(String[] args) {
        int n = randomInt(100, 1000); // [100, 999]
        println("Сумма цифр числа " + n + " равна " + sumDigits(n));
    }

    static int sumDigits(long num) {
        if (num < 0) num = -num;
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
/*
 * output:
 * Сумма цифр числа 655 равна 16
 * */
