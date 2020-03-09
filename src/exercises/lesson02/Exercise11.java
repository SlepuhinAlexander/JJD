package exercises.lesson02;

import java.math.BigInteger;
/*
 * Выведите на экран первые 11 членов последовательности Фибоначчи.
 * */
public class Exercise11 {
    public static void main(String[] args) {
        generateFibonacci(11);
    }

    static void generateFibonacci(int count) {
        if (count <= 0) {
            System.out.println("Некорректное значение длины последовательности");
            return;
        }
        switch (count) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("1 1");
            default -> {
                BigInteger previous = BigInteger.valueOf(1);
                BigInteger current = BigInteger.valueOf(1);
                BigInteger swap;
                System.out.print(previous + " " + current);
                for (int i = 3; i <= count; i++) {
                    swap = previous.add(current);
                    previous = current;
                    current = swap;
                    System.out.print(" " + current);
                }
            }
        }
    }
}
/*
 * output:
 * 1 1 2 3 5 8 13 21 34 55 89
 * */
