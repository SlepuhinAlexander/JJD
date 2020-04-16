package ru.ifmo.jjd.exercises.e02arrays.opt.ex03;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise03 {
    public static void main(String[] args) {
        int[] someArr = new int[4];
        fillRandomly(someArr, 10, 100);
        println(Arrays.toString(someArr));
        print("Массив ");
        if (!isStrictlyIncreasing(someArr)) {
            print("не ");
        }
        println("является строго возрастающей последовательностью.");
    }

    static boolean isStrictlyIncreasing(int[] arr) {
        return switch (arr.length) {
            case 0, 1 -> false;
            default -> {
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] <= arr[i - 1]) {
                        yield false;
                    }
                }
                yield true;
            }
        };
    }
}
/*
 * output:
 * [71, 21, 90, 19]
 * Массив не является строго возрастающей последовательностью.
 * */
