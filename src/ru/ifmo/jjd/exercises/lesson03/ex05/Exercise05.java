package ru.ifmo.jjd.exercises.lesson03.ex05;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise05 {
    public static void main(String[] args) {
        int[][] someArr = new int[5][8];
        fillRandomly(someArr, -99, 100);
        println(Arrays.deepToString(someArr).replace("],", "],\n"));
        println(getMax(someArr) + " - максимальный элемент массива.");
    }

    static int getMax(int[][] arr) {
        int result = Integer.MIN_VALUE;
        for (int[] row : arr) {
            for (int num : row) {
                if (num > result) result = num;
            }
        }
        return result;
    }
}
/*
 * output:
 * [[-36, -32, 65, -2, -72, -3, -55, -9],
 *  [76, -76, -85, -50, 21, 93, -85, 27],
 *  [73, 5, 0, 73, -98, 63, -29, -71],
 *  [19, -58, 52, -67, -96, -69, 64, -90],
 *  [6, 14, -75, -64, -61, 81, -58, -66]]
 * 93 - максимальный элемент массива.
 * */

