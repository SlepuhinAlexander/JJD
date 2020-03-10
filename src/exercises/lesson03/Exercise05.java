package exercises.lesson03;

import java.util.Arrays;
import java.util.Random;

/*
 * Создать двумерный массив из 5 строк по 8 столбцов в каждой из случайных целых чисел из отрезка [-99;99].
 * Вывести массив в консоль.
 * После на отдельной строке вывести в консоль значение максимального элемента этого массива.
 * */
public class Exercise05 {
    public static void main(String[] args) {
        int[][] someArr = new int[5][8];
        fillRandomly(someArr, -99, 100);
        System.out.println(Arrays.deepToString(someArr).replace("],", "],\n"));
        System.out.println(getMax(someArr) + " - максимальный элемент массива.");
    }

    static void fillRandomly(int[][] arr, int bottom, int top) {
        if (arr == null || top <= bottom) {
            return;
        }
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = r.nextInt(top - bottom) + bottom;
            }
        }
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
