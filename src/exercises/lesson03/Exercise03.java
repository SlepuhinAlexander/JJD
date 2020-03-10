package exercises.lesson03;

import java.util.Arrays;
import java.util.Random;

/*
 * Заполните массив на N элементов случайным числами и выведете максимальное, минимальное и среднее значение.
 * */
public class Exercise03 {
    public static void main(String[] args) {
        int[] someArr = getRandomArray(42);
        System.out.println(Arrays.toString(someArr));
        System.out.println("Максимальное значение: " + getMax(someArr));
        System.out.println("Миминальное значение: " + getMin(someArr));
        System.out.println("Среднее значение: " + getAverage(someArr));
    }

    static int[] getRandomArray(int bound) {
        if (bound < 2) {
            bound = 2;
        }
        Random r = new Random();
        int[] arr = new int[r.nextInt(bound - 1) + 1]; // [1;bound)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(bound * 2) - bound; // [-bound;bound)
        }
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
