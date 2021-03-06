## Многомерные массивы

1. [Двумерные массивы](#2d-arrays)
    1. [Объявление двумерного массива](#declaration)
    1. [Инициализация двумерного массива](#initialization)
    1. [Печать многомерного массива](#to-deep-string)
    1. [Обращение к элементу двумерного массива](#elements)
    1. [Перебор элементов двумерного массива](#iterating)
    1. [Сравнение многомерных массивов](#deep-equals)
1. [Трёхмерные массивы](#3d-arrays)

Элементами массива могут быть любые типы данных. В частности, элементами массива могут быть другие массивы.  
Такая конструкция, массив из массивов, называется **двумерным массивом**.  
Массивы большей размерности так же допустимы, но на практике используются редко. В целом, размерность массивов не 
ограничена.

### Двумерные массивы <a name="2d-arrays"></a>

#### Объявление двумерного массива <a name="declaration"></a> 

Синтаксис объявления двумерного массива:

    int[][] biDimArr;

То есть, нужно буквально указать, что это массив массивов (целых чисел, например).

#### Инициализация двумерного массива <a name="initialization"></a> 

Инициализация двумерного массива аналогична инициализации обычного массива: нужно указать размер внешнего массива, и 
размер каждого из внутренних массивов.

    biDimArr = new int[3][4];
    
Двумерный массив не обязан быть "прямоугольным" (т.е. иметь одинаковый размер внутренних массивов). Аналогично 
инициализации одномерного массива явными значениями, двумерный массив можно инициализировать явно:

    int[][] biDimArr2 = new int[][]{{1, 2, 3}, {4, 5}, {6}};

Аналогично одномерным массивам, при явной инициализации в момент объявления нотацию `new int[][]` можно пропустить.

Ещё один способ создать не-"прямоугольный" двумерный массив - это инициализировать его новыми одномерными массивами со 
значениями по умолчанию:

    int[][] biDimArr3 = {new int[3], new int[2], new int[1]};

#### Печать многомерного массива <a name="to-deep-string"></a>

Для преобразования многомерного массива в строку в классе `Arrays` есть подходящий похожий метод `toDeepString()`. Он 
аналогичен методу `Arrays.toString()`, только применяется к многомерным массивам.

[`MultiDimensionalArraysDemo.java`][MDArrays]

    twoDimensionalArraysDemo() {
        /* <code> */
        int[][] biDimArr = new int[3][4];
        int[][] biDimArr2 = {{1, 2, 3}, {4, 5}, {6}};
        int[][] biDimArr3 = {new int[3], new int[2], new int[1]};
        System.out.println("twoDimArr = " + Arrays.deepToString(twoDimArr)); 
        // biDimArr = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]
        System.out.println("biDimArr2 = " + Arrays.deepToString(biDimArr2));
        // biDimArr2 = [[1, 2, 3], [4, 5], [6]]
        System.out.println("biDimArr3 = " + Arrays.deepToString(biDimArr3));
        // biDimArr3 = [[0, 0, 0], [0, 0], [0]]
        /* <code> */
    }

#### Обращение к элементу двумерного массива <a name="elements"></a>

Для обращения к элементу двумерного массива необходимо указать два индекса, вместо одного: первый - индекс вложенного 
массива (индекс элемента из внешнего массива), второй - индекс элемента внутри вложенного массива.

[`MultiDimensionalArraysDemo.java`][MDArrays]

    twoDimensionalArraysDemo() {
        /* <code> */
        int[][] biDimArr = new int[3][4];
        biDimArr[1][1] = 1;
        biDimArr[2][2] = 2;
        System.out.println("biDimArr = " + Arrays.deepToString(biDimArr));
        // biDimArr = [[0, 0, 0, 0], [0, 1, 0, 0], [0, 0, 2, 0]]
        /* <code> */
    }
    
#### Перебор элементов двумерного массива <a name="iterating"></a>

Перебрать все элементы двумерного массива удобно с помощью двух вложенных циклов: `for` или `foreach`, в зависимости от 
цели.

[`MultiDimensionalArraysDemo.java`][MDArrays]

    twoDimensionalArraysDemo() {
        /* <code> */
        int[][] biDimArr = new int[3][4];
        for (int i = 0; i < biDimArr.length; i++) {
            for (int j = 0; j < biDimArr[i].length; j++) {
                biDimArr[i][j] = i + j;
            }
        }
        System.out.println("biDimArr = " + Arrays.deepToString(biDimArr));
        // biDimArr = [[0, 1, 2, 3], [1, 2, 3, 4], [2, 3, 4, 5]]
        /* <code> */
    }

#### Сравнение многомерных массивов <a name="deep-equals"></a>

Класс `Arrays` содержит метод `deepEquals()` для сравнения многомерных массивов. Его работа аналогична одномерному 
методу `Arrays.equals()`.

[`MultiDimensionalArraysDemo.java`][MDArrays]

    twoDimensionalArraysDemo() {
    /* <code> */
        int[][] biDimArr2 = new int[][]{{1, 2, 3}, {4, 5}, {6}};
        System.out.println("biDimArr2 = " + Arrays.deepToString(biDimArr2)); // biDimArr2 = [[1, 2, 3], [4, 5], [6]]
        int[][] biDimArr3 = new int[][]{new int[3], new int[2], new int[1]};
        System.out.println("biDimArr3 = " + Arrays.deepToString(biDimArr3)); // biDimArr3 = [[0, 0, 0], [0, 0], [0]]
        System.out.println("Arrays.deepEquals(biDimArr2, biDimArr3) : " + Arrays.deepEquals(biDimArr2, biDimArr3));
        // Arrays.deepEquals(biDimArr2, biDimArr3) : false
        int num = 0;
        for (int i = 0; i < biDimArr3.length; i++) {
            for (int j = 0; j < biDimArr3[i].length; j++) {
                biDimArr3[i][j] = ++num;
            }
        }
        System.out.println("biDimArr3 = " + Arrays.deepToString(biDimArr3)); // biDimArr3 = [[1, 2, 3], [4, 5], [6]]
        System.out.println("Arrays.deepEquals(biDimArr2, biDimArr3) : " + Arrays.deepEquals(biDimArr2, biDimArr3));
        // Arrays.deepEquals(biDimArr2, biDimArr3) : true
    /* <code> */

### Трёхмерные массивы <a name="3d-arrays"></a>

Работа с трёхмерными массивами полностью аналогична работе с двумерными, только участвует больше уровней вложенности.

[`MultiDimensionalArraysDemo.java`][MDArrays]

    threeDimensionalArraysDemo() {
        /* <code> */
        int[][][] threeDimArr = new int[2][3][4];
        System.out.println("threeDimArr = " + Arrays.deepToString(threeDimArr));
        // threeDimArr = [[[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]], [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]]
        int num = 0;
        for (int i = 0; i < threeDimArr.length; i++) {
            for (int j = 0; j < threeDimArr[i].length; j++) {
                for (int k = 0; k < threeDimArr[i][j].length; k++) {
                    threeDimArr[i][j][k] = ++num;
                }
            }
        }
        System.out.println("threeDimArr = " + Arrays.deepToString(threeDimArr));
        // threeDimArr = [[[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]], [[13, 14, 15, 16], [17, 18, 19, 20], 
        // [21, 22, 23, 24]]]
        /* <code> */
    }

<!--  ------------------------------  -->
[MDArrays]: MultiDimensionalArraysDemo.java