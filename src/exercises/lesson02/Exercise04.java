package exercises.lesson02;

import static utils.ConsoleHelper.*;

import java.util.Random;

/*
 * Написать код, который будет проверять попало ли случайно сгенерированное целое число из отрезка [5;122]
 * в интервал (25;100) и выводить результат в консоль.
 * Примеры работы программы:
 *   Число 113 не содержится в интервале (25,100)
 *   Число 72 содержится в интервале (25,100)
 * */
public class Exercise04 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(118) + 5; // [5,122]
        checkIntervalHit(n);
    }

    static void checkIntervalHit(int target) {
        print("Число " + target + " ");
        if (target < 25 || target > 100) print("не ");
        println("содержится в интервале (25, 100)");
    }
}
/*
 * output:
 * Число 46 содержится в интервале (25, 100)
 * */
