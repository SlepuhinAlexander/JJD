package exercises.lesson02;

import java.util.Random;

/*
 * В переменной n хранится натуральное трёхзначное число.
 * Вывсети в консоль сумму цифр числа n.
 * */
public class Exercise01 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(900) + 100; // [100, 999]
        System.out.println("Сумма цифр числа " + n + " равна " + sumDigits(n));
    }

    static int sumDigits(long num) {
        if (num < 0) num = -num;
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
/*
 * output:
 * Сумма цифр числа 655 равна 16
 * */
