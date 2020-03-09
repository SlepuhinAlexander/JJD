package exercises.lesson02;

import java.math.BigInteger;
import java.util.SortedMap;

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

/*
    // чудовищно неэффективный метод на сколько-нибудь больших числах.
    static String recursiveFibonacci(int n) {
        if (n < 0) n = -n;
        return switch (n) {
            case 0, 1 -> "1";
            default -> new BigInteger(recursiveFibonacci(n - 1)).add(new BigInteger(recursiveFibonacci(n - 2)))
            .toString();
        };
    }
*/
}
/*
 * output:
 * 1 1 2 3 5 8 13 21 34 55 89
 * */
