package exercises.lesson03;

import static utils.ConsoleHelper.*;
import static utils.ArraysHelper.*;

import java.util.Arrays;

/*
 * Пользователь вводит с клавиатуры натуральное число большее 3, которое сохраняется в переменную n.
 * Если пользователь ввёл не подходящее число, то программа должна просить пользователя повторить ввод.
 * Создать массив из n случайных целых чисел из отрезка [0;n] и вывести его на экран.
 * Создать второй массив только из чётных элементов первого массива, если они там есть, и вывести его в консоль
 * */
public class Exercise04 {
    public static void main(String[] args) {
        int num = getUserInput();
        int[] someArr = new int[num];
        fillRandomly(someArr, 0, num + 1);
        println(Arrays.toString(someArr));
        int[] evensArr = getEvensArr(someArr);
        if (evensArr.length != 0) {
            println("Чётные элементы:\n" + Arrays.toString(evensArr));
        }
    }

    static int getUserInput() {
        Integer result;
        while (true) {
            result = readInteger("Задайте размер масива: ");
            if (result == null) {
                println("Некорректный ввод, попробуйте ещё раз.");
            } else if (result < 3) {
                println("Слишком малый размер, рекомендуется взять число от 4");
            } else if (result > 1000) {
                println("Слишком большой размер, рекомендуется взять число до 1000");
            } else {
                break;
            }
        }
        return result;
    }

    static int[] getEvensArr(int[] arr) {
        int[] result = arr.clone();
        int effectiveSize = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] % 2 == 0) {
                result[effectiveSize++] = result[i];
            }
        }
        return Arrays.copyOf(result, effectiveSize);
    }
}
/*
 * output:
 * Задайте размер масива: 27
 * [13, 2, 2, 23, 26, 10, 9, 24, 10, 2, 12, 8, 14, 13, 20, 19, 25, 25, 14, 21, 20, 23, 20, 22, 15, 1, 8]
 * Чётные элементы:
 * [2, 2, 26, 10, 24, 10, 2, 12, 8, 14, 20, 14, 20, 20, 22, 8]
 * */
