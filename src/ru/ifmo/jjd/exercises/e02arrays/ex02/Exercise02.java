package ru.ifmo.jjd.exercises.e02arrays.ex02;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.RandomHelper.*;

public class Exercise02 {
    public static void main(String[] args) {
        int[] someArr = getRandomArray(10);
        println(Arrays.toString(someArr));
        findPair(someArr, 7);
    }

    static int[] getRandomArray(int bound) {
        if (bound < 1) {
            bound = 1;
        }
        int[] arr = new int[randomInt(bound, bound * 2)];
        fillRandomly(arr, -bound, bound);
        return arr;
    }

    /**
     * Searches through array arr to find a pair of ifs elements whose sum equals to sum.
     * In case of success prints out the first found pair
     */
    static void findPair(int[] arr, int sum) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    println(arr[i] + " + " + arr[j] + " = " + sum);
                    return;
                }
            }
        }
        println("Не удалось найти подходящую пару, дающую " + sum + " в сумме.");
    }
}

/*
 * output:
 * [-4, -5, 2, 6, 3, 6, -5, 5, -7, -3, -2, -9, -9, 2, -7]
 * 2 + 5 = 7
 * */
