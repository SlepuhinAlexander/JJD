package ru.ifmo.jjd.exercises.lesson02.ex04;

import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise04 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(118) + 5; // [5,122]
        checkIntervalHit(n);
    }

    static void checkIntervalHit(int target) {
        print("Число " + target + " ");
        if (target < 25 || target > 100) print("не ");
        println("содержится в интервале (25, 100)");
    }
}
/*
 * output:
 * Число 46 содержится в интервале (25, 100)
 * */
