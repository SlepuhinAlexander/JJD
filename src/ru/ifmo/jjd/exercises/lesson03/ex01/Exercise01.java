package ru.ifmo.jjd.exercises.lesson03.ex01;

import java.util.Arrays;
import java.util.Random;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise01 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(Integer.MAX_VALUE) + 1; // [1, max int]
        int m = r.nextInt(Integer.MAX_VALUE) + 1; // понятие НОД имеет смысл для натуральных чисел.
//        println("НОД чисел " + n + " и " + m + " равен " + getGCF(n, m));
//        println("НОД чисел " + n + " и " + m + " равен " + getGCFPrimes(n, m));
        println("НОД чисел " + n + " и " + m + " равен " + getGFCEuler(n, m));
    }

    /*
     * Вариант 1: Перебор
     * pros: простая реализация
     * cons: очень неэффективная на больших числах
     * */
    static int getGCF(int one, int two) { // GCF = Greatest Common Factor
        for (int i = Math.min(one, two); i > 1; i--) {
            if (one % i == 0 && two % i == 0) {
                return i;
            }
        }
        return 1;
    }

    /*
     * Вариант 2: Разложение на простые множители
     * pros: соответствует смыслу НОД;
     *       должно быть значительно быстрее способа 1,
     *       особенно если список простых чисел заранее известен.
     * cons: громоздкая реализация.
     * */
    static int getGCFPrimes(int one, int two) {
        int[] primes = getPrimeNumbers();
        int[] powers1 = factorizeToPrimes(primes, one);
        int[] powers2 = factorizeToPrimes(primes, two);
        int result = 1;
        for (int i = 0; i < primes.length; i++) {
            if (powers1[i] != 0 && powers2[i] != 0) {
                result *= Math.pow(primes[i], Math.min(powers1[i], powers2[i]));
            }
        }
        return result;
    }

    /*
     * Вариант 3. Алгоритм Эйлера.
     * pros: всем прекрасен; прост и максимально быстр.
     * cons: до него ещё нужно догадаться.
     * */
    static int getGFCEuler(int one, int two) {
        int dividend = Math.max(one, two);
        int divisor = Math.min(one, two);
        int remainder = dividend % divisor;
        while (remainder != 0) {
            dividend = divisor;
            divisor = remainder;
            remainder = dividend % divisor;
        }
        return divisor;
    }

    /**
     * Returns the array containing int prime numbers from 2 to sqrt(max int).
     * Used in factoring int number to primes.
     */
    static int[] getPrimeNumbers() {
        int sqrt = (int) Math.sqrt(Integer.MAX_VALUE);
        int[] result = new int[sqrt];
        int effectiveSize = 0;
        outer:
        for (int i = 2; i < sqrt; i++) {
            for (int j = 0; j < effectiveSize; j++) {
                if (i % result[j] == 0) {
                    continue outer;
                }
            }
            result[effectiveSize++] = i;
        }
        return Arrays.copyOf(result, effectiveSize + 2); // +2 это костыль :(
        // придётся оставить 2 запасных места, если у любого из двух чисел найдётся простой делитель больший чем sqrt
        // так всё же лучше, чем перебирать _все_ int.
    }

    /**
     * Factorizes the given num to powers of given primes
     *
     * @param primes is assumed to be the sorted array of unique prime numbers
     * @param num    the int num to factorize to primes
     * @return the resultant array of powers of given primes for given num
     */
    static int[] factorizeToPrimes(int[] primes, int num) {
        if (primes == null) {
            return new int[0];
        } else if (primes.length == 0 || num < 1) {
            return new int[0];
        }
        int[] result = new int[primes.length];
        outer:
        while (num != 1) {
            for (int i = 0; i < primes.length; i++) {
                if (primes[i] == 0) { // прошли все простые и не встретили делителей num. Значит сам num простой
                    primes[i] = num;
                    result[i]++;
                    break outer;
                }
                if (num % primes[i] == 0) {
                    result[i]++;
                    num /= primes[i];
                    continue outer;
                }
            }
        }
        return result;
    }
}
/*
 * output:
 * НОД чисел 216632264 и 203048507 равен 3847
 * */
