package ru.ifmo.jjd.exercises.lesson02.ex10;

import java.math.BigInteger;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise10 {
    public static void main(String[] args) {
        int num = getUserInput();
        println(num + "! = " + factorial(num));
//        println(num + "! = " + recursiveFactorial(num)); // альтернативное решение через рекурсию
    }

    static String factorial(int n) {
        if (n == 0) {
            return "1";
        } else if (n < 0) {
            n = -n;
        }
        BigInteger result = new BigInteger("1");
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result.toString();
    }

    static String recursiveFactorial(int n) {
        if (n == 0) {
            return "1";
        } else {
            if (n < 0) {
                n = -n;
            }
        }
        BigInteger result = BigInteger.valueOf(n);
        return result.multiply(new BigInteger(recursiveFactorial(n - 1))).toString();
    }

    static int getUserInput() {
        Integer result;
        while (true) {
            result = readInteger("Введите натуральное число: ");
            if (result == null) {
                println("Некорректный ввод, попробуйте ещё раз");
            } else if (result < 0) {
                println("Некорректный ввод, попробуйте ещё раз");
            } else if (result > 1000) {
                println("Число слишком больше, рекомендуется взять число до 1000");
            } else {
                break;
            }
        }
        return result;
    }
}
/*
 * output:
 * Введите натуральное число: 42
 * 42! = 1405006117752879898543142606244511569936384000000000
 * */
