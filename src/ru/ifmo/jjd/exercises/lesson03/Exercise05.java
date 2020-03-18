package ru.ifmo.jjd.exercises.lesson03;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ArraysHelper.fillRandomly;
import static ru.ifmo.jjd.utils.ConsoleHelper.println;

/*
 * Создать двумерный массив из 5 строк по 8 столбцов в каждой из случайных целых чисел из отрезка [-99;99].
 * Вывести массив в консоль.
 * После на отдельной строке вывести в консоль значение максимального элемента этого массива.
 * */
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
