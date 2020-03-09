package exercises.lesson02;

/*
 * Создайте программу, выводящую на экран все неотрицательные элементы последовательности
 *   90 85 80 75 70 65 60 ...
 * */
public class Exercise08 {
    public static void main(String[] args) {
        generateCountdown(90, -1, 5);
    }

    static void generateCountdown(int start, int finish, int step) {
        while (start > finish) {
            System.out.print(start + " ");
            start -= step;
        }
    }
}
/*
 * output:
 * 90 85 80 75 70 65 60 55 50 45 40 35 30 25 20 15 10 5 0
 * */
