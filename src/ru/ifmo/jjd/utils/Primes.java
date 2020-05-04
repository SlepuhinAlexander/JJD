package ru.ifmo.jjd.utils;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import static ru.ifmo.jjd.utils.ArraysHelper.unwrap;

public class Primes {
    private static final int[] PRIMES;
    private static final File FILE = new File("src" + File.separator +
                                              Primes.class.getPackageName().replace(".", File.separator) +
                                              File.separator + "Primes.txt");

    static {
        if (!FILE.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                FILE.createNewFile();
            } catch (IOException e) {
                try {
                    throw new IOException("could not create file", e);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        LinkedList<Integer> primes = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                primes.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            try {
                throw new IOException("could not read from file", e);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        int last;
        try {
            last = primes.getLast();
        } catch (NoSuchElementException e) {
            last = 0;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            if (last == 0) {
                primes.add(2);
                writer.write(Integer.toString(2));
                writer.newLine();
                primes.add(3);
                writer.write(Integer.toString(3));
                writer.newLine();
                last = 3;
            }
            boolean flag = true;
            int sqrt;
            outer:
            for (int i = last; i < 16_777_216 /* 2^24 */; i += 2) {
                if (flag) i += 2;
                flag = !flag;
                sqrt = (int) Math.sqrt(i);
                for (int prime : primes) {
                    if (prime > sqrt) break;
                    if (i % prime == 0) continue outer;
                }
                primes.add(i);
                writer.write(Integer.toString(i));
                writer.newLine();
            }
        } catch (IOException e) {
            try {
                throw new IOException("could not write to file " + FILE, e);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        Integer[] primesArr = new Integer[primes.size()];
        primes.toArray(primesArr);
        PRIMES = unwrap(primesArr);
    }

    public static int[] getPrimes() {
        return PRIMES.clone();
    }

    public static int[] getPrimes(int num) {
        if (num <= 0) throw new IllegalArgumentException();
        return Arrays.copyOf(PRIMES, Math.min(num, PRIMES.length));
    }

    public static int length() {
        return PRIMES.length;
    }

    public static int getBiggest() {
        return PRIMES[PRIMES.length - 1];
    }

    public static boolean isPrime(int num) {
        if (num <= 1) throw new IllegalArgumentException("number " + num + " must be greater than 1");
        if (num > PRIMES[PRIMES.length - 1]) throw new IllegalArgumentException("given number " + num + " is too big");
        return Arrays.binarySearch(PRIMES, num) >= 0;
    }

    public static int upperPrime(int num) {
        if (num <= 1) throw new IllegalArgumentException("number " + num + " must be greater than 1");
        if (num > PRIMES[PRIMES.length - 2]) throw new IllegalArgumentException("given number " + num + " is too big");
        int pos = Arrays.binarySearch(PRIMES, num);
        if (pos >= 0) return PRIMES[pos + 1];
        return PRIMES[-pos - 1];
    }

    public static int lowerPrime(int num) {
        if (num <= 2) throw new IllegalArgumentException("number " + num + " must be greater than 2");
        if (num > PRIMES[PRIMES.length - 1]) throw new IllegalArgumentException("given number " + num + " is too big");
        int pos = Arrays.binarySearch(PRIMES, num);
        if (pos >= 0) return PRIMES[pos - 1];
        return PRIMES[-pos - 2];
    }
}
