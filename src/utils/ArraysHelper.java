package utils;

import java.util.Random;

public class ArraysHelper {
    /**
     * Method fills the given one-dimensional integer array with random integers in range from bottom (inclusively)
     * to top (exclusively)
     *
     * @param arr    the array to fill
     * @param bottom lower border for int value to assign, inclusive
     * @param top    upper border for int value to assign, exclusive
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
    }


    public static boolean[] revertArray(boolean[] arr) {
        boolean[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            boolean swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static char[] revertArray(char[] arr) {
        char[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            char swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static byte[] revertArray(byte[] arr) {
        byte[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            byte swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static short[] revertArray(short[] arr) {
        short[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            short swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static int[] revertArray(int[] arr) {
        int[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static long[] revertArray(long[] arr) {
        long[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            long swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static float[] revertArray(float[] arr) {
        float[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            float swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static double[] revertArray(double[] arr) {
        double[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            double swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static void revertArray(Object[] arr) {
        Object[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            Object swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
    }
}
