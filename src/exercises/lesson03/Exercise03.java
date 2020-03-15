package exercises.lesson03;

import static utils.ConsoleHelper.*;
import static utils.ArraysHelper.*;

import java.util.Arrays;
import java.util.Random;

/*
 * Заполните массив на N элементов случайным числами и выведете максимальное, минимальное и среднее значение.
 * */
public class Exercise03 {
    public static void main(String[] args) {
        int[] someArr = getRandomArray(42);
        println(Arrays.toString(someArr));
        println("Максимальное значение: " + getMax(someArr));
        println("Миминальное значение: " + getMin(someArr));
        println("Среднее значение: " + getAverage(someArr));
    }

    static int[] getRandomArray(int bound) {
        if (bound < 2) {
            bound = 2;
        }
        Random r = new Random();
        int[] arr = new int[r.nextInt(bound - 1) + 1]; // [1;bound)
        fillRandomly(arr, -bound, bound);
        return arr;
    }

    static int getMin(int[] arr) {
        int result = arr[0];
        for (int num : arr) {
            if (num < result) {
                result = num;
            }
        }
        return result;
    }

    static int getMax(int[] arr) {
        int result = arr[0];
        for (int num : arr) {
            if (num > result) {
                result = num;
            }
        }
        return result;
    }

    static double getAverage(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return (double) sum / arr.length;
    }
}
/*
 * output:
 * [1, 30, 29, -42, -1, -9, -38, -21, 0, -18, -11, 13, 2, -11, -26, 41]
 * Максимальное значение: 41
 * Миминальное значение: -42
 * Среднее значение: -3.8125
 * */
