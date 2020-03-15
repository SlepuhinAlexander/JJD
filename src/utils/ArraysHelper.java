package utils;

import java.util.Random;

public class ArraysHelper {
    /**
     * Method fills the given one-dimensional integer array with random integers in range from bottom (inclusively)
     * to top (exclusively)
     *
     * @param arr the array to fill
     * @param bottom lower border for int value to assign, inclusive
     * @param top upper border for int value to assign, exclusive
     */
    public static void fillRandomly(int[] arr, int bottom, int top) {
        if (arr == null || top <= bottom) {
            return;
        }
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(top - bottom) + bottom;
        }
    }

    /**
     * Method fills the given two-dimensional integer array with random integers in range from bottom (inclusively)
     * to top (exclusively)
     *
     * @param arr    the array to fill
     * @param bottom lower border for int value to assign, inclusive
     * @param top    upper border for int value to assign, exclusive
     */
    public static void fillRandomly(int[][] arr, int bottom, int top) {
        if (arr == null || top <= bottom) {
            return;
        }
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = r.nextInt(top - bottom) + bottom;
            }
        }
    }}
