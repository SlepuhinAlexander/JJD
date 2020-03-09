package exercises.lesson02;

import java.math.BigInteger;
import java.util.Scanner;

/*
 * Создайте программу, вычисляющую факториал натурального числа n, которое пользователь введёт с клавиатуры.
 * */
public class Exercise10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                System.out.print("Введите натуральное число: ");
                num = scanner.nextInt();
                if (num < 0) {
                    System.out.println("Некорректный ввод, попробуйте ещё раз");
                } else if (num > 1000) {
                    System.out.println("Число слишком больше, вычисление не рекомендуется");
                } else break;
            } catch (Exception ex) {
                scanner.nextLine();
            }
        }
        System.out.println(num + "! = " + factorial(num));
//        System.out.println(num + "! = " + recursiveFactorial(num)); // альтернативное решение через рекурсию
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
        return result.multiply(new BigInteger(recursiveFactorial(n-1))).toString();
    }
}
/*
 * output:
 * Введите натуральное число: 42
 * 42! = 1405006117752879898543142606244511569936384000000000
 * */
