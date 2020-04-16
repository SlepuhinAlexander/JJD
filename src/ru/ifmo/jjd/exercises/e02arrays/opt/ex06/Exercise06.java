package ru.ifmo.jjd.exercises.e02arrays.opt.ex06;

import java.util.Arrays;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.ArraysHelper.*;

/*
 * Создать двумерный массив из 7 строк по 4 столбца в каждой из случайных целых чисел из отрезка [-5;5].
 * Вывести массив в консоль.
 * Определить и вывести на экран индекс строки с наибольшим по модулю произведением элементов.
 * Если таких строк несколько, то вывести индекс первой встретившейся из них.
 * */
public class Exercise06 {
    public static void main(String[] args) {
        int[][] someArr = new int[7][4];
        fillRandomly(someArr, -5, 6);
        println(Arrays.deepToString(someArr).replace("],", "],\n"));
        println("Строка с индексом " + findHighestRowProduct(someArr) +
                " содержит наибольшее по модулю произведение элементов");
    }

    static int findHighestRowProduct(int[][] arr) {
        int row = 0;
        long product, highest = 0;
        for (int i = 0; i < arr.length; i++) {
            product = 1;
            for (int j = 0; j < arr[i].length; j++) {
                product *= arr[i][j];
            }
            product = (product < 0) ? -product : product;
            if (product > highest) {
                row = i;
                highest = product;
            }
        }
        return row;
    }
}
/*
 * output:
 * [[-1, -4, -1, 3],
 *  [-3, 2, 2, -4],
 *  [-1, 5, -1, -4],
 *  [5, -3, 5, 0],
 *  [5, -3, -4, 4],
 *  [3, 0, -3, 5],
 *  [-4, 4, -3, -2]]
 * Строка с индексом 4 содержит наибольшее по модулю произведение элементов
 * */
