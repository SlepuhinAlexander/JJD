package ru.ifmo.jjd.utils;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class ArraysHelper {
    /**
     * Method fills the given one-dimensional integer array with random integers in range from lower (inclusively)
     * to upper (exclusively)
     *
     * @param arr   the array to fill
     * @param lower lower border for int value to assign, inclusive
     * @param upper upper border for int value to assign, exclusive
     */
    public static void fillRandomly(int[] arr, int lower, int upper) {
        if (arr == null || upper <= lower) return;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = randomInt(lower, upper);
        }
    }

    /**
     * Method fills the given two-dimensional integer array with random integers in range from lower (inclusively)
     * to upper (exclusively)
     *
     * @param arr   the array to fill
     * @param lower lower border for int value to assign, inclusive
     * @param upper upper border for int value to assign, exclusive
     */
    public static void fillRandomly(int[][] arr, int lower, int upper) {
        if (arr == null || upper <= lower) return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = randomInt(lower, upper);
            }
        }
    }

    public static boolean[] revert(boolean[] arr) {
        if (arr == null) return null;
        boolean[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            boolean swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static char[] revert(char[] arr) {
        if (arr == null) return null;
        char[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            char swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static byte[] revert(byte[] arr) {
        if (arr == null) return null;
        byte[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            byte swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static short[] revert(short[] arr) {
        if (arr == null) return null;
        short[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            short swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static int[] revert(int[] arr) {
        if (arr == null) return null;
        int[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static long[] revert(long[] arr) {
        if (arr == null) return null;
        long[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            long swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static float[] revert(float[] arr) {
        if (arr == null) return null;
        float[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            float swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static double[] revert(double[] arr) {
        if (arr == null) return null;
        double[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            double swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
        return clone;
    }

    public static void revert(Object[] arr) {
        if (arr == null) return;
        Object[] clone = arr.clone();
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            Object swap = clone[i];
            clone[i] = clone[j];
            clone[j] = swap;
        }
    }

    public static Boolean[] wrap(boolean[] arr) {
        if (arr == null) return null;
        Boolean[] result = new Boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }


    public static Character[] wrap(char[] arr) {
        if (arr == null) return null;
        Character[] result = new Character[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Byte[] wrap(byte[] arr) {
        if (arr == null) return null;
        Byte[] result = new Byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Short[] wrap(short[] arr) {
        if (arr == null) return null;
        Short[] result = new Short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Integer[] wrap(int[] arr) {
        if (arr == null) return null;
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Long[] wrap(long[] arr) {
        if (arr == null) return null;
        Long[] result = new Long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Float[] wrap(float[] arr) {
        if (arr == null) return null;
        Float[] result = new Float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static Double[] wrap(double[] arr) {
        if (arr == null) return null;
        Double[] result = new Double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static boolean[] unwrap(Boolean[] arr) {
        if (arr == null) return null;
        boolean[] result = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static char[] unwrap(Character[] arr) {
        if (arr == null) return null;
        char[] result = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static byte[] unwrap(Byte[] arr) {
        if (arr == null) return null;
        byte[] result = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static short[] unwrap(Short[] arr) {
        if (arr == null) return null;
        short[] result = new short[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] unwrap(Integer[] arr) {
        if (arr == null) return null;
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static long[] unwrap(Long[] arr) {
        if (arr == null) return null;
        long[] result = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static float[] unwrap(Float[] arr) {
        if (arr == null) return null;
        float[] result = new float[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }

    public static double[] unwrap(Double[] arr) {
        if (arr == null) return null;
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) throw new NullPointerException();
            result[i] = arr[i];
        }
        return result;
    }
}
