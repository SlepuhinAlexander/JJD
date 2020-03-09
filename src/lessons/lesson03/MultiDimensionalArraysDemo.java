package lessons.lesson03;

import java.util.Arrays;

public class MultiDimensionalArraysDemo {
    public static void main(String[] args) {
        biDimensionalArraysDemo();
        triDimensionalArraysDemo();
    }

    private static void biDimensionalArraysDemo() {
        /*
         * Элементами массива могут быть любые объекты. В частности, элементами массива могут быть другие массивы.
         * Такая конструкиця, массив из массивов, называется двумерным массивом.
         * */

        // Синтаксис объявления двумерного массива
        int[][] biDimArr; // т.е. нужно буквально указать, что это массив массивов целых чисел.ы

        // Инициализация двумерного массива значениями по умолчанию
        biDimArr = new int[3][4];
        /*
         * Т.е. необходимо указать размер внешнего массива, и размер каждого из внутренних массивов.
         * */

        /*
         * Метод печати многомерных массивов. Метод Arrays.deepToString().
         * Метод аналогичен Arrays.toString, но только применим к многомерным массивам.
         * */
        System.out.println("biDimArr = " + Arrays.deepToString(biDimArr));
        // biDimArr = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]

        // Обращеие к элементу двумерного массива
        biDimArr[1][1] = 1;
        biDimArr[2][2] = 2;
        System.out.println("biDimArr = " + Arrays.deepToString(biDimArr));
        // biDimArr = [[0, 0, 0, 0], [0, 1, 0, 0], [0, 0, 2, 0]]

        /*
         * Перебрать все элементы двумерного массива можно с помощью двух циклов for вложенных друг в друга
         * */
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                biDimArr[i][j] = i + j;
            }
        }
        System.out.println("biDimArr = " + Arrays.deepToString(biDimArr));
        // biDimArr = [[0, 1, 2, 3], [1, 2, 3, 4], [2, 3, 4, 5]]

        /*
         * Двумерный массив не обязан иметь одинаковый размер вложенных (внутренних) массивов,
         * т.е. быть "прямоугольным".
         * С помощью, например, явной инициализации, можно задать двумерный массив произвольной конфигурации
         * */
        int[][] biDimArr2 = new int[][]{{1, 2, 3}, {4, 5}, {6}};
        // Аналогично одномерным массивам, при инициализации в момент объявления нотацию new int[][] можно пропустить.
        System.out.println("biDimArr2 = " + Arrays.deepToString(biDimArr2));
        // biDimArr2 = [[1, 2, 3], [4, 5], [6]]

        /*
         * Сравнение многомерных массивов. Метод Arrays.deepEquals()
         * Метод аналогичен методу Arrays.equals(), только применяется для многомерных массивов.
         * */
        int[][] biDimArr3 = new int[][]{new int[3], new int[2], new int[1]};
        // ещё один способ создать не "прямоугольный" двумерный массив
        System.out.println("biDimArr3 = " + Arrays.deepToString(biDimArr3));
        // biDimArr3 = [[0, 0, 0], [0, 0], [0]]
        System.out.println("Arrays.deepEquals(biDimArr2, biDimArr3) : " + Arrays.deepEquals(biDimArr2, biDimArr3));
        // Arrays.deepEquals(biDimArr2, biDimArr3) : false
        int num = 0;
        for (int i = 0; i < biDimArr3.length; i++) {
            for (int j = 0; j < biDimArr3[i].length; j++) {
                biDimArr3[i][j] = ++num;
            }
        }
        System.out.println("biDimArr3 = " + Arrays.deepToString(biDimArr3));
        // biDimArr3 = [[1, 2, 3], [4, 5], [6]]
        System.out.println("Arrays.deepEquals(biDimArr2, biDimArr3) : " + Arrays.deepEquals(biDimArr2, biDimArr3));
        // Arrays.deepEquals(biDimArr2, biDimArr3) : true
    }

    private static void triDimensionalArraysDemo() {
        /*
         * Работа трёхмерными массивами и массивами большей мерности аналогична работе с двумерными массивами.
         * Только больше уровней вложенности участвует.
         * */
        int[][][] triDimArr = new int[2][3][4];
        System.out.println("triDimArr = " + Arrays.deepToString(triDimArr));
        // triDimArr = [[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]]
        int num = 0;
        for (int i = 0; i < triDimArr.length; i++) {
            for (int j = 0; j < triDimArr[i].length; j++) {
                for (int k = 0; k < triDimArr[i][j].length; k++) {
                    triDimArr[i][j][k] = ++num;
                }
            }
        }
        System.out.println("triDimArr = " + Arrays.deepToString(triDimArr));
        // triDimArr =
        // [[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]], [[13, 14, 15, 16], [17, 18, 19, 20], [21, 22, 23, 24]]]
    }
}
