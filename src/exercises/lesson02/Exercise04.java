package exercises.lesson02;

/*
 * Написать код, который будет проверять попало ли случайно сгенерированное целое число из отрезка [5;122]
 * в интервал (25;100) и выводить результат в консоль.
 * Примеры работы программы:
 *   Число 113 не содержится в интервале (25,100)
 *   Число 72 содержится в интервале (25,100)
 * */

import java.util.Random;

public class Exercise04 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(118) + 5; // [5,122]
        checkIntervalHit(n);
    }

    static void checkIntervalHit(int target) {
        System.out.print("Число " + target + " ");
        if (target < 25 || target > 100) System.out.print("не ");
        System.out.println("содержится в интервале (25, 100)");
    }
}
/*
*
* */
