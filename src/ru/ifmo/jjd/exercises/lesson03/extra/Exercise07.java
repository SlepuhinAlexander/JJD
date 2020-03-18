package ru.ifmo.jjd.exercises.lesson03.extra;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;
import static ru.ifmo.jjd.utils.ArraysHelper.*;

import java.util.Arrays;

/*
 * Создать двумерный массив из 6 строк по 7 столбцов в каждой из случайных целых чисел из отрезка [0;9].
 * Вывести массив в консоль.
 * Преобразовать массив таким образом, чтобы на первом месте в каждой строке стоял её наибольший элемент.
 * При этом изменять состав массива нельзя, а можно только переставлять элементы в рамках одной строки.
 * Порядок остальных элементов строки не имеет значения (т.е. можно соврешить только одну перестановку, а можно
 * отсортировать по убыванию каждую строку).
 * Вывести преобразованный массив в консоль.
 * */
public class Exercise07 {
    public static void main(String[] args) {
        int[][] someArr = new int[6][7];
        fillRandomly(someArr, 0, 10);
        println(Arrays.deepToString(someArr).replace("],", "],\n"));
        findHighestInRows(someArr);
        println("Преобразованный массив: ");
        println(Arrays.deepToString(someArr).replace("],", "],\n"));
    }

    private static void findHighestInRows(int[][] arr) {
        int highest;
        for (int[] ints : arr) {
            highest = 0;
            for (int j = 0; j < ints.length; j++) {
                highest = (ints[j] > ints[highest]) ? j : highest;
            }
            swap(ints, 0, highest);
        }
    }

    private static void swap(int[] arr, int one, int two) {
        try {
            int tmp = arr[two];
            arr[two] = arr[one];
            arr[one] = tmp;
        } catch (ArrayIndexOutOfBoundsException ex) {
            return;
        }
    }
}
/*
 * output:
 * [[4, 2, 5, 4, 8, 1, 2],
 *  [2, 1, 5, 5, 0, 8, 1],
 *  [8, 1, 1, 3, 5, 7, 8],
 *  [0, 6, 2, 9, 6, 9, 9],
 *  [0, 4, 5, 2, 3, 0, 1],
 *  [4, 9, 7, 6, 5, 1, 6]]
 * Преобразованный массив:
 * [[8, 2, 5, 4, 4, 1, 2],
 *  [8, 1, 5, 5, 0, 2, 1],
 *  [8, 1, 1, 3, 5, 7, 8],
 *  [9, 6, 2, 0, 6, 9, 9],
 *  [5, 4, 0, 2, 3, 0, 1],
 *  [9, 4, 7, 6, 5, 1, 6]]
 * */
