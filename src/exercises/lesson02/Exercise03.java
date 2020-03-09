package exercises.lesson02;

import java.util.Random;

/*
 * Даны два целых числа n и m.
 * Наименьшее из них сохранить в переменную res и вывести ее на экран.
 * */
public class Exercise03 {
    public static void main(String[] args) {
        Random r = new Random();
        int n = r.nextInt(), m = r.nextInt();
        int res = min(n, m);
        System.out.println("Из чисел " + m + " и " + n + " наименьшее - " + res);
    }

    static int min(int one, int two) {
        return (one < two) ? one : two; // can be replaced with Math.min() of course.
    }
}
/*
 * output:
 * Из чисел 70911403 и -18019907 наименьшее - -18019907
 * */
