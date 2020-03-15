package exercises.lesson02;

import static utils.ConsoleHelper.*;

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
        print("Число " + num + " - ");
        if (num % 2 != 0) print("не");
        println("чётное");
    }
}
/*
 * output:
 * Число 1498558662 - чётное
 * */
