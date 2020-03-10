package exercises.lesson03.extra;

import java.util.Arrays;
import java.util.Random;

/*
 * Создайте массив из 15 случайных целых чисел из отрезка [0;9]. Выведите массив в консоль.
 * Подсчитайте сколько в массиве чётных элементов и выведете это количество в консоль.
 * */
public class Exercise02 {
    public static void main(String[] args) {
        int[] someArr = new int[15];
        fillRandomly(someArr, 0, 10);
        System.out.println(Arrays.toString(someArr));
        System.out.println("Массив содержит " + countEvenElements(someArr) + " чётных элементов.");
    }

    /**
     * Method fills the given one-dimensional integer array with random integers in range from bottom (inclusively)
     * to top (exclusively)
     *
     * @param arr the array to fill
     * @param bottom lower border for int value to assign, inclusive
     * @param top upper border for int value to assign, exclusive
     */
    static void fillRandomly(int[] arr, int bottom, int top) {
        if (arr == null || top <= bottom) {
            return;
        }
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(top - bottom) + bottom;
        }
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
