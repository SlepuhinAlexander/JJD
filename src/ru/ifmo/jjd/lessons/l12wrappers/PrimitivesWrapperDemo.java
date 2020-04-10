package ru.ifmo.jjd.lessons.l12wrappers;

public class PrimitivesWrapperDemo {
    public static void main(String[] args) {
        /*
         * Примитивные типы данных: byte, short, int, long, float, double, boolean, char
         *
         * Для каждого из примитивных типов данных в Java есть соответствующий класс-обёртка.
         * Класс-обёртка хранит значение примитивной величины и некоторые методы.
         *
         * byte - класс Byte
         * short - класс Short
         * int - класс Integer
         * long - класс Long
         * float - класс Float
         * double - класс Double
         * char - класс Character
         * boolean - класс Boolean
         * */

        /*
         * Везде где возможно - нужно использовать примитивы, т.к. это гораздо более экономично по памяти.
         *
         * Когда нужно использовать классы-обёртки:
         * - когда невозможно использовать примитивы:
         *     - в дженериках
         *     - в коллециях
         * - когда необходимы методы классов-обёрток.
         * */

        /*
         * Иерархия классов:
         * Object <- Boolean
         * Object <- Character
         * Object <- Number <- Byte
         * Object <- Number <- Short
         * Object <- Number <- Integer
         * Object <- Number <- Long
         * Object <- Number <- Float
         * Object <- Number <- Double
         * */

        int count = 267; // Создали переменную примитивного типа.
        // Создание объекта для примитивного типа.
        // Integer age = new Integer(200);
        /*
         * Через new объекты классов-обёрток создавать не принято (это устаревший способ создания).
         *
         * Лучше:
         * */
        Integer age = 200;


        // Автоупаковка и автораспаковка

        // Автоупаковка - процесс, когда переменной класса-обёртки присваивается значение примитива.
        Integer num = count; // примитивное значение присвоено объекту
        Integer someInt = 12; // примитивное значение присвоено объекту

        // Автораспаковка - процесс, когда переменной примитивного типа присваивается значение объекта-обёртки.
        double price;
        Double someDouble = 45.12; // произошла автоупаковка
        price = someDouble; // произошла самораспаковка

        /*
         * Автоупаковка и автораспаковка просходят автоматически, никаких дополнительных манипуляций не требуется.
         *
         * Но есть некоторые правила:
         *
         * При упаковке тип примитива и обёртки должны строго соответствовать.
         * */
        byte one = 1;
        // Integer byteToInteger = one; //incompatible types: byte cannot be converted to java.lang.Integer
        // Integer byteToInteger = (Integer) one; //incompatible types: byte cannot be converted to java.lang.Integer
        /*
         * Если примитив не соответствует класу-обёртке, упаковка не сработает.
         * Если необходимо, используем приведение типов.
         * */
        Integer byteToInteger = (int) one;
        /*
         * Так автоупаковка работает: сначала проедено приведение типа к нужному примитиву, потом автоматически
         * сработала автоупаковка.
         * */

        /*
         * С автораспаковкой такого строгого соответствия не требуется:
         * */
        Byte someByte = 12; // произошла автоупаковка
        int byteToInt = someByte;
        /*
         * Сначала произошла автораспаковка: а затем примитив byte привёлся к int
         * */

        // типы-оболочки напрямую друг к другу не приводимы
        Integer i = 12;
        Byte b = 5;
        // i = b; // нельзя, несмотря на то, что byte в int помещается.

        // b = (Byte) i; // incompatible types: java.lang.Integer cannot be converted to java.lang.Byte
        // i = (Integer) b; // incompatible types: java.lang.Byte cannot be converted to java.lang.Integer

        // Параметры методов тоже подлежат правилам автоупаковки и автораспаковки
        int c = 4;
        sqr(c);

        // автоупаковка и автораспаковка не работает с массивами
        double[] doubles = {1.1, 2.2, 3.3, 4.4};
        // printDoubleArr(doubles); // incompatible types: double[] cannot be converted to java.lang.Double[]


        // Наиболее часто используемые методы классов-обёрток
        // методы по приведению, возвращающие byte, int, long, double и т.д. (справедливо для наследников Number)
        Integer intI = 12;
        byte byteFromInt = intI.byteValue(); // приводит значение переменной intI к примитиву. возвращает примитив.
        /*
         * Методы
         *
         * shortValue(),
         * intValue(),
         * longValue(),
         * floatValue(),
         * doubleValue().
         *
         * Аналогичны
         * */

        // Методы преобразования строки-с-числом к числу.
        /*
         * метод parseXXX(String s); - возвращает примитив.
         * метод valueOf(String s); - возвращает объект.
         * */

        System.out.println(Byte.parseByte("2")); // возвращает примитив byte
        System.out.println(Byte.valueOf("2")); // возвращает объект типа Byte

        System.out.println(Integer.parseInt("32")); // возвращает примитив int
        System.out.println(Integer.valueOf("32")); // возвращает объект типа Integer

        /*
         * Если в строке содержится не только число, то выбросит NumberFormatException
         * */

        // Сравнение целочисленных значений (Integer)
        /*
         * Объекты всегда сравниваются через equals() или compareTo()
         * */
        // сравнение через == для значений в диапазоне [-128;127]
        Integer first = 45;
        Integer second = 45;
        System.out.println("45 == 45 : " + (first == second)); // 45 == 45 : true
        /*
         * Здесь происходило сравнение объектов. Распаковки до примитивов не происходило.
         * Результат true говорит нам о том, что ссылки first и second -- ссылаются на один и тот же объект.
         * Для чисет в этом диапазоне создаётся кеш использованных чисел, новые объекты не создаются, если такое число
         * уже есть.
         * */

        // сравнение через == для значений вне диапазона [-128;127]
        Integer third = 155;
        Integer fourth = 155;
        System.out.println("155 == 155 : " + (third == fourth)); // 155 == 155 : false
        /*
         * Размер кэша - настраиваемый. Его можно изменить в аргументах запускаемой программы.
         * Кэш используется для всех целочисленных классов-обёрток.
         * */

        // сравнение через compareTo() | compare()
        Integer fifth = 12;
        Integer sixth = 12;
        System.out.println("fifth.compareTo(sixth): " + fifth.compareTo(sixth));
        // fifth.compareTo(sixth): 0
        // по факту, внутри себя метод compare()
        System.out.println("Integer.compare(fifth, sixth): " + Integer.compare(fifth, sixth));
        // Integer.compare(fifth, sixth): 0
        /*
         * Метод compare возвращает 0, -1, 1 (см. реализацию метода)
         * */

        // сравнение через equals
        System.out.println("third.equals(fifth): " + third.equals(fifth)); // third.equals(fifth): false

        // Константы
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE); // Integer.MIN_VALUE = -2147483648
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE); // Integer.MAX_VALUE = 2147483647

        System.out.println("Double.NEGATIVE_INFINITY = " + Double.NEGATIVE_INFINITY);
        // Double.NEGATIVE_INFINITY = -Infinity
        System.out.println("Double.TYPE = " + Double.TYPE); // Double.TYPE = double

    }

    /*
     * Создать приватный статический метод, который принимает на вход число типа Integer и выводит в консоль значение
     * этого элемента в квадрате
     * */
    private static void sqr(Integer num) {
        System.out.println(num * num);
    }

    /*
     * Создать приватный статический метод, который принимает на вход массив типа Double[] double
     * и выводит в консоль каждыйего элемент.
     * */
    private static void printDoubleArr(Double[] doubles) {
        for (Double d : doubles) {
            System.out.print(" " + d);
        }
    }
}
