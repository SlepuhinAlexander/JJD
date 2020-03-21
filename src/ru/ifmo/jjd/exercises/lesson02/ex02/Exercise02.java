package ru.ifmo.jjd.exercises.lesson02.ex02;

import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise02 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt();
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
