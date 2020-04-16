package ru.ifmo.jjd.exercises.e01syntax.ex04;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Exercise04 {
    public static void main(String[] args) {
        int n = randomInt(5, 123); // [5,122]
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
