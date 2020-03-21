package ru.ifmo.jjd.exercises.lesson02.ex05;

import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

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
