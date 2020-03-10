package exercises.lesson03.extra;

import java.util.Arrays;
import java.util.Random;

/*
 * Создать двумерный массив из 8 строк по 5 столбцов в каждой из случайных целых чисел из отрезка [10;99].
 * Вывести массив в консоль.
 * */
public class Exercise05 {
    public static void main(String[] args) {
        int[][] someArr = new int[8][5];
        fillRandomly(someArr, 10, 100);
        System.out.println(Arrays.deepToString(someArr).replace("],", "],\n"));
    }

    /**
     * Method fills the given two-dimensional integer array with random integers in range from bottom (inclusively)
     * to top (exclusively)
     *
     * @param arr the array to fill
     * @param bottom lower border for int value to assign, inclusive
     * @param top upper border for int value to assign, exclusive
     */
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
