package ru.ifmo.jjd.exercises.e02arrays.opt.ex02;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise02 {
    public static void main(String[] args) {
        int[] someArr = new int[15];
        fillRandomly(someArr, 0, 10);
        println(Arrays.toString(someArr));
        println("Массив содержит " + countEvenElements(someArr) + " чётных элементов.");
    }

    static int countEvenElements(int[] arr) {
        int result = 0;
        for (int n : arr) {
            if (n % 2 == 0) {
                result++;
            }
        }
        return result;
    }
}
/*
 * [0, 0, 6, 3, 9, 2, 9, 9, 9, 8, 0, 9, 4, 7, 5]
 * Массив содержит 7 чётных элементов.
 * */
