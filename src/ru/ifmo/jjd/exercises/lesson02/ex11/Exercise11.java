package ru.ifmo.jjd.exercises.lesson02.ex11;

import java.math.BigInteger;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Exercise11 {
    public static void main(String[] args) {
        generateFibonacci(11);
    }

    static void generateFibonacci(int count) {
        if (count <= 0) {
            println("Некорректное значение длины последовательности");
            return;
        }
        switch (count) {
            case 1 -> println("1");
            case 2 -> println("1 1");
            default -> {
                BigInteger previous = BigInteger.valueOf(1);
                BigInteger current = BigInteger.valueOf(1);
                BigInteger swap;
                print(previous + " " + current);
                for (int i = 3; i <= count; i++) {
                    swap = previous.add(current);
                    previous = current;
                    current = swap;
                    print(" " + current);
                }
            }
        }
    }
}
/*
 * output:
 * 1 1 2 3 5 8 13 21 34 55 89
 * */
