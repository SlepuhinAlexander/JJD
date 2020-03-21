package ru.ifmo.jjd.exercises.lesson03.optional.ex05;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.*;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise05 {
    public static void main(String[] args) {
        int[][] someArr = new int[8][5];
        fillRandomly(someArr, 10, 100);
        println(Arrays.deepToString(someArr).replace("],", "],\n"));
    }
}
/*
 * output:
 * [[71, 76, 98, 40, 16],
 *  [81, 42, 20, 34, 65],
 *  [79, 60, 38, 82, 53],
 *  [98, 70, 62, 67, 85],
 *  [12, 52, 65, 12, 22],
 *  [26, 95, 75, 92, 25],
 *  [47, 62, 99, 46, 17],
 *  [60, 53, 30, 57, 93]]
 * */
