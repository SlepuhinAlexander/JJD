package exercises.lesson02;

import java.util.Random;

/*
 * Проверить является ли целое число записанное в переменную n, чётным либо нечётным.
 * Результат вывсети в консоль.
 * */
public class Exercise02 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt();
        oddOrEven(n);
    }

    static void oddOrEven(long num) {
        System.out.print("Число " + num + " - ");
        if (num % 2 != 0) System.out.print("не");
        System.out.println("чётное");
    }
}
/*
 * output:
 * Число 1498558662 - чётное
 * */
