package exercises.lesson03;

import java.util.Arrays;
import java.util.Random;

/*
 * Дан массив целых чисел. Массив не отсортирован, числа могут повторяться.
 * Необходимо найти в данном массиве такие два числа n и m, чтобы их сумма была равна 7.
 * Например, 2 + 5 = 7, 6 + 1 = 7, -2 + 9 = 7.
 * Постарайтесь решить задачу наиболее оптимальным способом
 * */
public class Exercise02 {
    public static void main(String[] args) {
        int[] someArr = getRandomArray(10);
        System.out.println(Arrays.toString(someArr));
        findPair(someArr, 7);
    }

    static int[] getRandomArray(int bound) {
        if (bound < 1) {
            bound = 1;
        }
        Random r = new Random();
        int[] arr = new int[r.nextInt(bound) + bound]; // [bound; 2 * bound)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(bound * 2) - bound; // [-bound;bound)
        }
        return arr;
    }

    /**
     * Searches through array arr to find a pair of ifs elements whose sum equals to sum.
     * In case of success prints out the first found pair
     */
    static void findPair(int[] arr, int sum) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + sum);
                    return;
                }
            }
        }
        System.out.println("Не удалось найти подходящую пару, дающую " + sum + " в сумме.");
    }
}

/*
 * output:
 * [-4, -5, 2, 6, 3, 6, -5, 5, -7, -3, -2, -9, -9, 2, -7]
 * 2 + 5 = 7
 * */
