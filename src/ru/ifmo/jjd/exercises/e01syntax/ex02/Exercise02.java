package ru.ifmo.jjd.exercises.e01syntax.ex02;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Exercise02 {
    public static void main(String[] args) {
        int n = randomInt();
        oddOrEven(n);
    }

    static void oddOrEven(long num) {
        print("Число " + num + " - ");
        if (num % 2 != 0) print("не");
        println("чётное");
    }
}
/*
 * output:
 * Число 1498558662 - чётное
 * */
