package ru.ifmo.jjd.utils;

import java.util.Date;
import java.util.Random;

public class RandomHelper {
    private static final Random R = new Random(new Date().getTime());

    public static void setRandomSeed(long seed) {
        R.setSeed(seed);
    }

    public static boolean randomBoolean() {
        return R.nextBoolean();
    }

    public static void randomBytes(byte[] bytes) {
        R.nextBytes(bytes);
    }

    public static double randomDouble() {
        return R.nextDouble();
    }

    public static double randomGaussian() {
        return R.nextGaussian();
    }

    public static int randomInt() {
        return R.nextInt();
    }

    public static int randomInt(int bound) {
        return R.nextInt(bound);
    }

    public static int randomInt(int lower, int upper) {
        return R.nextInt(upper - lower) + lower;
    }

    public static long randomLong() {
        return R.nextLong();
    }
}
