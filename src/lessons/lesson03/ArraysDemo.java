package lessons.lesson03;

import java.util.Arrays;

public class ArraysDemo {
    /*
     * Массив - это объект, представляющий некоторую непрерывную область памяти, хранящую набор данных (переменных)
     * определённого (одного) типа.
     * */
    public static void main(String[] args) {
        arrayDeclarationDemo();
        arrayInitializationDemo();
        accessingArrayElementsDemo();
        iteratingTroughArrayDemo();
        copyingArraysDemo();
        arraysClassDemo();
    }

    private static void arrayDeclarationDemo() {
        /*
         * Элементы массива - это каждая из переменных, хранящихся в данном массиве.
         * Все элементы массива имеют один и тот же тип и распроложены в неизменном порядке (занимают
         * ячейки в памяти последовательно, т.к. весь массив - непрерывная область памяти).
         * Элементы массива пронумерованы целыми числами, начиная с нуля: т.е. в массиве из 10 элементов, например,
         * первый элемент имеет номер 0, последний - номер 9.
         * Номер (позиция) элемента в массиве называется индексом этого элемента в массиве.
         * Индекс используется для доступа (чтения и записи) к конкретной переменной в массиве (элементу массива).
         * Индексы (нумерация) всегда начинается с нуля.
         * */

        // Объявление переменной типа массив
        int[] someArr;
        /*
         * Как и для других переменных объявление массива состоит из указания типа данных и имени переменной
         * Тип данных обозначается как xxx[], где xxx - тип данных, которые будут храниться в массиве (тип данных для
         * элементов массива).
         * */
        // int someArr1[]; // Альтернативный, работающий способ объявления массива. Унаследован от семейства языков С.
        // Не рекомендуется использовать.

        /*
         * В результате создана ссылочная перменная someArr, с типом int[]. Сам массив ещё не создан.
         * Значение переменной someArr == null.
         * */
    }

    private static void arrayInitializationDemo() {
        /*
         * Длиной (или размером) массива называется количество элементов в нём.
         * Размер массива - всегда целое неотрицательное число. Т.е. массивы длиной 0 - допустимы, в них не хранится
         * никаких данных.
         * Размер массива задаётся при его создании (выделении памяти под массив). После создания массива его размер
         * изменить невозможно. Можно лишь оперировать с его отдельными элементами.
         * Наиболее близкая операция к изменению размера массива - это создать новый массив нужной длины и скопировать
         * в него необходимые данные из старого массива; после чего использовать уже новый массив.
         *
         * Таким образом, для создания массива нужно, как минимум, явно указать его размер.
         * */

        // создание пустого массива
        int[] someArr = new int[7];
        /*
         * Массив, как и любой ссылочный тип создаётся с помощью ключегого слова new.
         * Затем указывается тип данных, который необходимо создать: например int[].
         * Затем необходимо использовать инициализатор массива.
         * Простейший инициализатор - указать внутри [] размер массива, например new int[7].
         *
         * Здесь объявляется переменная someArr c типом массив целых чисел;
         * создаётся пустой массив длиной 7 (выделяется непрерывная область памяти под хранение семи int переменных);
         * каждый элемент массива инициализируется значением по умолчанию;
         * и этот созданный массив присваивается переменной someArr.
         * */
        /*
         * В качестве аргумента, указывающего длину массива можно использовать не только явное число, но и любые
         * переменные или выражения, которые имеют целочисленное значение (int).
         * При попытке создать массив отрицательной длины будет выдана ошибка java.lang.NegativeArraySizeException и
         * исполнение программы прервётся. Это ошибка времени исполнения (runtime exception), она отлавливается не во
         * время компиляции, а уже во время исполнения программы.
         * */
        /*
         * Если элементы массива не инициализированы явно (см. ниже), то при создании массива они всегда заполняются
         * значениями по умолчанию в зависимости от их типа данных:
         *  - целочисленные типы данных заполняются значениями 0;
         *        (в том числе char, что равносильно значению '\u0000')
         *  - дробные числовые типы данных -- значениями 0.0;
         *  - boolean -- значениями false;
         *  - ссылочные типы данных (т.е. объекты; т.е. любые другие типы данных) -- значениями null.
         * */

        // Инициализация массива при создании (заполнение значениями не по умолчанию)
        int[] someArr2 = new int[]{37, 48, 125, 983, -23, 0, -112};
        /*
         * Т.е. вместо указания длины массива, явно указываются его элементы в {} через запятую.
         * Соответственно, длина созданного массива будет равна количеству переданных элементов.
         * Количество элементов, переданных в инициализатор может быть любым, в том числе нулевым: new int[]{};
         *
         * Частично инициализировать массив (т.е. задать только часть значений, а остальные оставить по умолчанию) в
         * момент создания - невозможно. Для этой задачи нужно создать пустой массив и затем, обратившись в нужным
         * его элементам присвоить им нужные значения.
         * */

        // Сокращённая запись
        int[] someArr3 = {37, 48, 125, 983, -23, 0, -112};
        /*
         * делает то же самое.
         * Т.е. при использовании явной инициализации в момент объявления массива, нотацию new типДанных[] можно
         * опустить.
         * */

        // Получение длины массива
        System.out.println("Длина массива someArr: " + someArr.length); // Длина массива someArr: 7
        System.out.println("Длина массива someArr2: " + someArr2.length); // Длина массива someArr2: 7
        /*
         * Получить длину массива можно просто обратившись к его атрибуту length
         * */

        // Печать массива
        System.out.println("someArr = " + Arrays.toString(someArr)); // someArr = [0, 0, 0, 0, 0, 0, 0]
        System.out.println("someArr2 = " + Arrays.toString(someArr2)); // someArr2 = [37, 48, 125, 983, -23, 0, -112]
        /*
         * Массив является объектом, поэтому вызов println(someArr) выдаст стандартное текстовое представление
         * объекта, а не содержимое массива, как хотелось бы. (Фактически, будет напечатан хеш-код объекта someArr).
         *
         * Для удобного преобразования содержимого массива в строку (например для вывода в консоль) необходимо
         * использовать служебный класс java.util.Arrays и его метод toString(), определённый для массивов из всех
         * примитивных и ссылочных типов
         * */

        // Повторная инициализация массива
        someArr = new int[]{-4, 12, 37, 48, 125};
        someArr2 = new int[5];
        /*
         * Т.е. создание нового массива и присвоение его имеющейся переменной того же типа.
         * Можно использовать любой из двух вариантов инициализации.
         * При повторной инициализации явными значениями использовать сокращённую запись уже нельзя:
         * someArr = {-4, 12, 37, 48, 125, 983, -23, 0, -112, 33}; // компилятор такую конструкцию не распознает.
         * */
        System.out.println("Длина массива someArr: " + someArr.length); // Длина массива someArr: 5
        System.out.println("Длина массива someArr2: " + someArr2.length); // Длина массива someArr2: 5
        System.out.println("someArr = " + Arrays.toString(someArr)); // someArr = [-4, 12, 37, 48, 125]
        System.out.println("someArr2 = " + Arrays.toString(someArr2)); // someArr2 = [0, 0, 0, 0, 0]
    }

    private static void accessingArrayElementsDemo() {
        int[] someArr = new int[7];
        int[] someArr2 = {37, 48, 125, 983, -23, 0, -112};
        /*
         * После создания массива, к любому из его элементов можно обратиться: прочитать его значение или изменить его.
         * Для обращения к элементу массива необходимо указать имя массива, и затем в [] индекс интересующего элемента.
         * В качестве индекса можно передать не только явное число, но и любую переменную или выражение, принимающие
         * целое значение (int).
         * */
        System.out.println("someArr[4] = " + someArr[4]); // someArr[4] = 0
        System.out.println("someArr2[7 / 2] = " + someArr2[7 / 2]); // someArr2[7 / 2] = 983
        someArr[1] = 11;
        someArr[5] = 17;
        someArr[someArr2[5]] = 22;
        System.out.println("someArr = " + Arrays.toString(someArr)); // someArr = [22, 11, 0, 0, 0, 17, 0]
        System.out.println("someArr2 = " + Arrays.toString(someArr2)); // someArr2 = [37, 48, 125, 983, -23, 0, -112]

        /*
         * При попытке доступа (для чтения или записи) к несуществующему элементу массива будет получена ошибка
         * времени исполнения (runtime exception) java.lang.ArrayIndexOutOfBoundsException и выполнение программы
         * прервётся.
         * Оращение someArr[i] вызовет ошибку при i < 0 || i >= someArr.length
         * */
    }

    private static void iteratingTroughArrayDemo() {
        /*
         * Задачу перебрать все элементы массива (чтобы прочитать или присвоить/изменить значения элементов) удобно
         * решать с помощью циклов.
         * Более удобно применять цикл for, т.к. заранее известно необходимое число итераций, равное длине массива.
         * */

        // создать массив целых чисел и заполнить его чётными числами начиная с нуля
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 2;
        }
        System.out.println("arr = " + Arrays.toString(arr)); // arr = [0, 2, 4, 6, 8, 10, 12, 14, 16, 18]

        // удвоить значения всех элементов массива целых чисел
        for (int i = 0; i < arr.length; i++) {
            arr[i] *= 2;
        }
        System.out.println("arr = " + Arrays.toString(arr)); // arr = [0, 4, 8, 12, 16, 20, 24, 28, 32, 36]

        // та же задача, но с использованием цикла while
        int i = 0;
        while (i < arr.length) {
            arr[i++] *= 2;
        }
        System.out.println("arr = " + Arrays.toString(arr)); // arr = [0, 8, 16, 24, 32, 40, 48, 56, 64, 72]

        // вывести квадраты всех элементов массива
        System.out.print("Квадраты элементов массива arr:");
        for (int n : arr) {
            n = n * n; // несмотря на изменение n, исходный массив не изменится
            System.out.print(" " + n);
        }
        System.out.println(); // Квадраты элементов массива arr: 0 64 256 576 1024 1600 2304 3136 4096 5184
        System.out.println("arr = " + Arrays.toString(arr)); // arr = [0, 8, 16, 24, 32, 40, 48, 56, 64, 72]
        /*
         * В отличие от перебора циклом for (или while) цикл foreach не использует явно индексы элементов массива.
         * Соответственно, циклом foreach пользователь не имеет возможности изменить массив.
         * В переменную int n в каждой итерации КОПИРУЕТСЯ текущий выбранный элемент массива, а сам массив остаётся
         * без изменений.
         * */
    }

    private static void copyingArraysDemo() {
        int[] original = {1, 2, 3, 4, 5};
        int[] copy = original;
        /*
         * Присваивание copy = original не означает, что будет создан новый массив идентичный original.
         * Написанное здесь означает, что бует создана ссылочная переменная типа int[] с именем copy и ей будет
         * присвоено значение равное значению переменной original (которая тоже является ссылочной переменной типа
         * int[]).
         * Другими словами, в переменную someArr2 будет скопирована ССЫЛКА на массив {1, 2, 3, 4, 5}. Сам же массив
         * останется в единственном экземпляре.
         * Обращаться к его элементам теперь можно будет через любое из имён original или copy.
         * Копированием массива такая операция не является.
         *
         * Скопировать массив означает получить новый массив, который имеет то же содержимое, что и исходный массив.
         * Но оба массива должны быть независимы (несвязаны).
         * */

        // Есть несколько способов копировать / клонировать массив или его часть.
        // Способ 1. Вручную с использованием цикла.
        original = new int[]{2, 4, 8, 16, 32};
        copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        System.out.println("original = " + Arrays.toString(original)); // original = [2, 4, 8, 16, 32]
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [2, 4, 8, 16, 32]
        /*
         * Разумеется, способ можно модифицировать, например для копирования части массива (какого-то диапазона
         * индексов)
         * */

        /*
         * Способ 2. Метод clone() у самого массива.
         * Метод clone() создаёт новый массив того же типа и той же длины, что и вызвавший объект. И копирует все
         * элементы в новый массив. В результате выполнения метод возвращает созданный новый массив.
         *
         * Другими словами, метод clone() создаёт полную копию исходного массива и возвращает её как результат своей
         * работы. Результат можно присвоить, например, другой переменой.
         *
         * Метод не гибкий: он позволяет создать только полную копию массива.
         * */
        original = new int[]{3, 6, 9, 12, 15};
        copy = original.clone();
        System.out.println("original = " + Arrays.toString(original)); // original = [3, 6, 9, 12, 15]
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [3, 6, 9, 12, 15]

        /*
         * Способ 3. Метод System.arraycopy();
         * Метод system.arraycopy() является статическим методом класса System и повзоляет гибко копироавть часть
         * элементов одного массива в другой. Оба массива, исходный и целевой, должны уже существовать и должны быть
         * переданы методу arraycopy() в качестве аргументов.
         * Метод arraycopy() имеет следующие 5 аргументов:
         *   Object src -- исходный объект; в частности, исходный массив
         *   int srcPos -- позиция в исходном объекте; индекс в исходном массиве, начиная с которого нужно брать данные
         *   Object dest -- целевой объект; в частности, целевой массив
         *   int destPos -- позиция в целевом объекте; индекс в целевом массиве, начаная с которого нужно вставлять
         *                  данные
         *   int length -- длина копируемой части массива (количество элементов, которые нужно взять начиняя с srcPos
         *                 из src и поместить в dest начиная с destPos)
         *
         * Метод может выбрасывать java.lang.ArrayIndexOutOfBoundsException при обращении к несуществующим индексам в
         * исходном и/или целевом массивах
         * */
        original = new int[]{23, 1, -10, 5};
        copy = new int[12];
        System.arraycopy(original, 1, copy, 3, 2);
        System.out.println("original = " + Arrays.toString(original)); // original = [23, 1, -10, 5]
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [0, 0, 0, 1, -10, 0, 0, 0, 0, 0, 0, 0]

        /*
         * Способ 4. Метод Arrays.copyOf()
         * Метод copyOf() является статическим методом служебного класса Arrays и позволяет скопироавть несколько
         * первых элементов массива или сделать полную копию.
         * Метод имеет два аргумента: исходный массив и желаемую длину целевого массива.
         * Метод создаёт новый массив желаемой длины, заполняет его копиями элементов исходного массива, начиная с
         * нулевого индекса и продолжая до тех пор пока не заполнит весь. Если длина желаемой копии меньше длины
         * оригинала, избыточные элементы массива-оригинала будут проигнорированы. Если, наоборот, длина жеаемой
         * копии больше длины оригинала, метод заполнит оставшиеся элементы массива-копии значениями по умолчанию.
         * (Следовательно, метод безопасен с точки зрения выхода за диапазон допустимых индексов).
         * В результате работы метод возвращает созданный массив, который можно, например, присвоить переменной.
         * */
        original = new int[]{1, 2, 3};
        copy = Arrays.copyOf(original, 2);
        System.out.println("original = " + Arrays.toString(original)); // original = [1, 2, 3]
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [1, 2]
        copy = Arrays.copyOf(original, 5);
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [1, 2, 3, 0, 0]

        /*
         * Способ 5. Метод Arrays.copyOfRange
         * Метод аналогичен предыдущему, только имеет 3 аргумента вместо двух:
         * - исходный массив
         * - начальную позицию (индекс from) в исходном массиве
         * - конечную позицию (индекс to), до которой необдимо копировать (не включая).
         *
         * Если аргумент to будет меньше, чем from, метод выдаст ошибку java.lang.IllegalArgumentException
         *
         * Полученный массив будет иметь длину равную to - from. Если позиция to выходит за границы исходного
         * массива, соответствующие элементы в массиве-копии будут заполнены значениями по умолчанию
         * */
        original = new int[]{12, 13, 14, 15, 16, 17, 18};
        copy = Arrays.copyOfRange(original, 2, 5);
        System.out.println("original = " + Arrays.toString(original)); // original = [12, 13, 14, 15, 16, 17, 18]
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [14, 15, 16]
        copy = Arrays.copyOfRange(original, 2, 10);
        System.out.println("copy = " + Arrays.toString(copy)); // copy = [14, 15, 16, 17, 18, 0, 0, 0]

    }

    private static void arraysClassDemo() {
        // Служебный класс Arrays имеет ешё ряд полезных статических методов для работы с массивами.
        // Сравнение массивов
        int[] arr1 = new int[10];
        int[] arr2 = new int[10];
        System.out.println("arr1 == arr2 : " + (arr1 == arr2)); // arr1 == arr2 : false
        System.out.println("arr1.equals(arr2) : " + (arr1.equals(arr2))); // arr1.equals(arr2) : false
        /*
         * Под сравнением массивов принято понимать сравнение их содержимого. Т.е. массивы равны, если они содержат
         * одинаковые данные.
         * Для сравнения массивов в таком конктексте оператор == и метод equals не подходят.
         * Оператор == сравнивает ссылки arr1 и arr2. Он вернёт true если обе ссылки ведут на один и тот же объект.
         * Из этого, конечно, следует, что массивы равны (т.к. это один и тот же массив). Но обратное не верно: два
         * разных массива с одинаковым содержимым будут иметь различные ссылки на них.
         *
         * Метод equals сравнивает объекты, ссылки на которые ведут из arr1 и arr2. Он вернёт true объекты равны. Из
         * этого, вообще говоря, не следует, что содержимое массивов равно. И обратное тоже не верно: два разных
         * массива с одинаковым содержимым опеделённо являются разными объектами.
         *
         * Для сравнения массивов по содержимому подходит метод Arrays.equals(). В качестве аргументов он принимает
         * два сравниваемых массива и возвращает true только если массивы имеют одинаковую длину и на равных позициях
         * хранят равные значения.
         * */
        System.out.println("Arrays.equals(arr1, arr2) : " + Arrays.equals(arr1, arr2));
        // Arrays.equals(arr1, arr2) : true

        /*
         * Наполнение массива данными. Метод Arrays.fill()
         * Метод принимает два аргумента: массив, который необходимо заполнить, и значение, которе нужно положить во
         * все элементы массива.
         * */
        int[] someArr = new int[5];
        Arrays.fill(someArr, 11);
        System.out.println("someArr = " + Arrays.toString(someArr)); // someArr = [11, 11, 11, 11, 11]

        /*
         * Сортировка массива. Метод Arrays.sort()
         * Метод принимает массив, значения которого необходимо отсортировать.
         * В результате выполнения метода переданный массив будет изменён: его элементы будут расположены в
         * естественном порядке.
         * Для числовых данных естественный порядок - порядок по возрастанию.
         * Для строк - алфавитный порядок по возрастанию.
         *
         * Для сортировки применяется алгоритм быстрой сортировки (quick sort). Сложность алгоритма О(n * log2 n).
         * */
        int[] someArr1 = {12, -10, 123, 6};
        Arrays.sort(someArr1);
        System.out.println("Отсортированный someArr1 = " + Arrays.toString(someArr1));
        // Отсортированный someArr1 = [-10, 6, 12, 123]

        /*
         * Сортировка части массива. Метод Arrays.sort()
         * Аналогичен предыдущему, только принимает ещё в качестве аргументов начальную и конечную позицию в массиве.
         * Метод сортирует только часть массива от начальной до конечной позиции, остальные элементы массива не трогает.
         * Начальная граница включается в сортировку, конечная - не включается.
         * */
        int[] someArr2 = {23, 1, -10, 5, 111, 24, 22, 1};
        Arrays.sort(someArr2, 1, 5); // сортировка части массива от индекса1 до индекса2
        System.out.println("Отсортированный someArr2 = " + Arrays.toString(someArr2));
        // Отсортированный someArr2 = [23, -10, 1, 5, 111, 24, 22, 1]

        /*
         * Поиск в массиве. Метод Arrays.binarySearch()
         * Метод имеет смысл вызывать только для отсортированного массива. Работа с несортированным массивом даст
         * непредсказуемый результат.
         * Метод принимает два аргумента: массив, в котором нужно искать, и ключ - объект, который ищется.
         * Если ключ найден в массиве, метод возвращает неотрицательное число -- индекс первого найденного в массиве
         * элемента, который совпадает с ключом.
         * Если ключ в массиве не найден, метод возврашает отрицательное число равное (-(insertion point) - 1), где
         * insertion point -- индекс массива, куда можно было бы вставить искомый элемент чтобы не нарушить сортировку.
         *
         * Метод использует алгоритм бинарного (двочного) поиска. Сложность алгоритма О(n * log2 n).
         * */
        someArr1 = new int[]{-10, 6, 12, 123};
        System.out.println("Searching for 6 in someArr1 : " + Arrays.binarySearch(someArr1, 6));
        // Searching for 6 in someArr1 : 1
        System.out.println("Searching for 15 in someArr1 : " + Arrays.binarySearch(someArr1, 15));
        // Searching for 15 in someArr1 : -4

        /*
         * Поиск в части массива. Метод Arrays.binarySearch()
         * Аналогичен предыдущему, только принимает ещё в качестве аргументов начальную и конечную позицию в массиве.
         * Метод ищет ключ только в части массива от начальной до конечной позиции, остальные элементы массива
         * игнорирует. Начальная граница включается в поиск, конечная - не включается.
         * Вычисление insertion point также учитывает заданные границы. Например, если искомый элемент будет больше,
         * чем все элементы в выбранной части массива, insertion point будет равен минус конечной позиции поиска.
         * */
        someArr2 = new int[]{-10, 1, 1, 5, 22, 23, 24, 111};
        System.out.println("Searching for 23 in someArr2 from 1 to 5 : " +
                Arrays.binarySearch(someArr2, 1, 5, 23));
        // Searching for 23 in someArr2 from 1 to 5 : -6
        System.out.println("Searching for 22 in someArr2 from 1 to 5 : " +
                Arrays.binarySearch(someArr2, 1, 5, 22));
        // Searching for 22 in someArr2 from 1 to 5 : 4
        System.out.println("Searching for 100 in someArr2 from 1 to 5 : " +
                Arrays.binarySearch(someArr2, 2, 5, 1000));
        // Searching for 100 in someArr2 from 1 to 5 : -6
    }
}

