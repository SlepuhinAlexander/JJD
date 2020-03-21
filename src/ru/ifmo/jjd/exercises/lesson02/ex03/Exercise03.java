package ru.ifmo.jjd.exercises.lesson02.ex03;

import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class Exercise03 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(), m = r.nextInt();
        int res = min(n, m);
        println("Из чисел " + m + " и " + n + " наименьшее - " + res);
    }

    static int min(int one, int two) {
        return (one < two) ? one : two; // can be replaced with Math.min() of course.
    }
}
/*
 * output:
 * Из чисел 70911403 и -18019907 наименьшее - -18019907
 * */
