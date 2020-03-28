package ru.ifmo.jjd.exercises.lesson02.ex03;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Exercise03 {
    public static void main(String[] args) {
        int n = randomInt(), m = randomInt();
        int res = min(n, m);
        println("Из чисел " + m + " и " + n + " наименьшее - " + res);
    }

    static int min(int one, int two) {
        return Math.min(one, two);
    }
}
/*
 * output:
 * Из чисел 70911403 и -18019907 наименьшее - -18019907
 * */
