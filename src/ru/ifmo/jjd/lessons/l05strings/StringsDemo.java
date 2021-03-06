package ru.ifmo.jjd.lessons.l05strings;

import java.util.Arrays;

public class StringsDemo {
    public static void main(String[] args) {
        stringCreationDemo();
        stringEqualityDemo();
        stringCommonMethodsDemo();
        stringMethodsDemo();
        stringConcatenationDemo();
        stringBuilderDemo();
        stringFromToArrayDemo();
        stringCompareToDemo();
    }

    public static void stringCreationDemo() {
        /*
         * Строки - это ссылочный тип данных. Строка является объектом и создаётся на основе класса String.
         * до Java 8 (включительно) строки хранятся в как массив char - ов
         * т.е. один символ это char charVar = 'e';
         * а массив символов - это char[] chars = {'j', 'j', 'd'};
         *
         * При этом символы хранятся в кодировке UTF16 и каждый символ занимает 2 байта.
         * */

        /*
         * Строки (компактные строки) начиная с Java 9 хранятся как массив byte[]
         * private final byte[] value
         * И при этом вместе с массивом byte[] хранится в какой кодировке заданы значения.
         * private final byte coder;
         * с возможными значениями UTF16 или LATIN1
         * В случаях, когда символ помещается в 1 байт - используется кодировка LATIN1 (она использует 1 байт).
         * Иначе - используется кодировка UTF16 и символ занимает уже два байта (два смежных элемента в массиве value).
         * */

        // Способы задания строк:
        String someString = "jjd";
        /*
         * String - это указание на тип данных. Имя класса String.
         * someString - имя переменной
         * "jjd" - присвоенное строковое значение. Строковое значение всегда заключается в кавычки.
         * "Под капотом" объект someString хранит массив private final char[] value = {'j', 'j', 'd'};
         * */

        String someString2 = new String("Some String");
        /*
         * Через конструктор класса String.
         * Он полезен, когда необходимо создать строку из массива данных или иными нетипичными способами.
         * А использовать конструктор String() передавая в него строковое значение -- избыточно.
         * */
        /*
         * При создании строки через конструктор всегда создаётся новый (отдельный) объект).
         * Поэтому если создать несколько одинаковых объектов-строк (используя конструктор String()), то каждый раз
         * будет создан новый объект.
         *
         * Создание строки через присвоение строкового значения, используется пул строк.
         * Перед созданием новой строки java всегда будет проверять наличие такой строки в пуле строк.
         * И если таковой нет - записывать её в пул строк и возвращать ссылку на неё.
         * А если таковая строка есть - новую строку не создавать, и возвращать ссылку на неё.
         * */
    }

    public static void stringEqualityDemo() {
        // Сравнение строк:
        String string1 = "Some String";
        String string2 = "Some String";
        String string3 = new String("Some String");
        String string4 = new String("Some String");
        // сравнение ссылок на строки
        System.out.println("string1 == string2 : " + (string1 == string2)); // string1 == string2 : true
        System.out.println("string1 == string3 : " + (string1 == string3)); // string1 == string3 : false
        System.out.println("string3 == string4 : " + (string3 == string4)); // string3 == string4 : false
        // сравнение значений строк (посимвольное сравнение)
        System.out.println("string1.equals(string2) : " + string1.equals(string2)); // string1.equals(string2) : true
        System.out.println("string1.equals(string3) : " + string1.equals(string3)); // string1.equals(string3) : true
        System.out.println("string3.equals(string4) : " + string3.equals(string4)); // string3.equals(string4) : true
        // метод String.equals(), унаследованный от Object.equals() переопределён. Как раз для сравнения по значению.

        // метод intern()
        String newString = new String("new");
        String newString2 = newString.intern();
        /*
         * Помещает строку в пул строк.
         * Если там такой строки нет, он её помещает строку в пул строк и возвращает ссылку на пул строк.
         * Если в пуле строк такая строка есть, он просто возвращает ссылку на эту строку в пуле строк.
         * */
        System.out.println("string3 == string4 : " + (string3 == string4)); // string3 == string4 : false
        System.out.println("string3.intern() == string4.intern() : " + (string3.intern() == string4.intern()));
        // string3.intern() == string4.intern() : true
        System.out.println("string3 == string4 : " + (string3 == string4)); // string3 == string4 : false
        string3 = string3.intern();
        string4 = string4.intern();
        System.out.println("string3 == string4 : " + (string3 == string4)); // string3 == string4 : true


        // Строки в Java неизменяемы и потокобезопасны.
        /*
         * При любом изменении строки создаётся новая строка с новым значением и возвращается ссылка на новую строку.
         * */
    }

    public static void stringCommonMethodsDemo() {
        String str1 = "Some String";
        // Метод length()
        System.out.println("str1.length() = " + str1.length()); // str1.length() = 11
        /*
         * Метод length() возвращает количество символов (длину) вызывающей строки.
         * (Фактически, это длина массива, хранящего строку).
         * */

        // Метод isEmpty()
        String str2 = "";
        System.out.println("str2.isEmpty()? " + str2.isEmpty()); // str2.isEmpty()? true
        /*
         * Метод isEmpty() проверяет является ли строка пустой (состоящей из 0 символов).
         * */

        // Метод isBlank()
        String str3 = "   ";
        System.out.println("str3.isEmpty()? " + str3.isEmpty()); // str3.isEmpty()? false
        System.out.println("str3.isBlank()? " + str3.isBlank()); // str3.isBlank()? true
        /*
         * Метод isBlank() проверяет, состоит ли строка только из пробельных символов.
         * */

        // Метод toUpperCase()
        System.out.println("str1.toUpperCase() = " + str1.toUpperCase()); // str1.toUpperCase() = SOME STRING
        /*
         * Метод toUpperCase() возвращает новую строку, значение которой равно вызывающей строке, преобразованной к
         * верхнему регистру.
         * */

        // Метод toLowerCase()
        System.out.println("str1.toLowerCase() = " + str1.toLowerCase()); // str1.toLowerCase() = some string
        /*
         * Метод toLowerCase() возвращает новую строку, значение которой равно вызывающей строке, преобразованной к
         * нижнему регистру.
         * */

        // Метод charAt()
        System.out.println("str1.charAt(1) = " + str1.charAt(1)); // str1.charAt(1) = o
        /*
         * Метод charAt() возвращает символ, который вызывающая строка хранит на указанной позиции.
         * */

        // Метод valueOf()
        System.out.println("String.valueOf(123 + 456) = " + String.valueOf(123 + 456));
        // String.valueOf(123 + 456) = 579
        /*
         * Метод valueOf() - статический. Он принадлежит классу String в целом, а не какому-либо его экземпляру.
         * Метод valueOf() необходимо вызывать от класса String.
         * Метод возвращает строковое представление своего аргумента: это может быть любой тип данных - и примитивный,
         * и ссылочный.
         * */
    }

    public static void stringMethodsDemo() {
        String str1 = "Java";

        // Метод startsWith()
        System.out.println("str1.startsWith(\"Ja\") : " + str1.startsWith("Ja")); // str1.startsWith("Ja") : true
        /*
         * Метод startsWith() сравнивает с учётом регистра начало вызывающей строки со строкой, переданной в аргументе
         * и возвращает значение типа boolean
         * */

        // Метод endsWith()
        System.out.println("str1.endsWith(\"A\") : " + str1.endsWith("A")); // str1.endsWith("A") : false
        /*
         * Метод endsWith() сравнивает с учётом регистра конец вызывающей строки со строкой, переданной в аргументе
         * и возвращает значение типа boolean
         * */

        // Метод trim()
        str1 = "    Some \t String    ";
        System.out.println("str1.trim() = " + str1.trim()); // str1.trim() = Some 	 String
        /*
         * Метод trim() возвращает новую строку, значение которой равно вызывающей строке, из которой удалили все
         * предстоящие и завершающие пробелы.
         * Промежуточные пробелы не удаляются. Другие непробельные символы (например, табуляция, переносы строк) не
         * удаляются.
         * */

        // Метод replace()
        str1 = "Java";
        System.out.println("str1.replace(\"a\",\"AA\") = " + str1.replace("a", "AA"));
        // str1.replace("a","AA") = JAAvAA
        /*
         * Метод replace возвращает новую строку, значение которой равно вызывающей строке, в которой все вхождения
         * первого аргумента заменены на значения из второго аргумента
         * Аргументом метода replace() могут быть символы и строки
         * */

        // Метод replaceAll()
        System.out.println("str1.replaceAll(\"\\\\w\", \"Aa\") = " + str1.replaceAll("\\w", "Aa"));
        // str1.replaceAll("\\w", "Aa") = AaAaAaAa
        /*
         * Метод replaceAll() возвращает новую строку, значение которой равно вызывающей строке, в которой все
         * вхождения первого аргумента заменены на значения из второго аргумента
         * В первом аргументе принимаются регулярные выражения.
         * */

        // метод split()
        str1 = "Some string";
        System.out.println("Arrays.toString(str1.split(\"\\\\s\") = " + Arrays.toString(str1.split("\\s")));
        // str1.split() = [Some, string]
        /*
         * Метод split() разбивает переданную строку на массив строк по переданному разделителю.
         * В качестве разделителя принимается регулярное выражение.
         * Разделитель в результате не участвует.
         * Если совпадение с регулярным выражением не найдено, в результирующем массиве будет одна строка равная
         * вызывающей строке
         * */
    }

    public static void stringConcatenationDemo() {
        // Конкатенация (склеивание / объединение строк).
        String str1 = "строка 1";
        String str2 = "строка 2";
        String str3 = "строка 3";
        // Вариант 1: оператор +
        str1 = str1 + str2;
        System.out.println("str1 = " + str1); // str1 = строка 1строка 2
        /*
         * При этой операции в пуле строк:
         *  - строка "строка 1" остаётся, не меняется
         *  - в пуле строк создаётся новая строка со значением "строка 1строка 2"
         *  - ссылка str1 теперь указывает на строку "строка 1строка 2" в пуле строк.
         * */
        /*
         * Вообще говоря, операторов + в выражении с объединением строк может быть сколько угодно.
         * Если один из операндов оператора + является строкой, то оператор + работает как конкатенация строк, а не
         * как арифметический оператор.
         * */
        // Вариант 2: метод concat();
        str2 = str2.concat(str3).concat("Hello").concat("STR");
        System.out.println("str2 = " + str2); // str2 = строка 2строка 3HelloSTR
        /*
         * Метод concat делает ровно то же самое, что и оператор +, а именно: склеивает строки и возвращает ссылку на
         * новую объединённую строку.
         * */

        // Статический метод String.join()
        str3 = String.join(" :: ", str1, str2, str3);
        System.out.println("str3 = " + str3); // str3 = строка 1строка 2 :: строка 2строка 3HelloSTR :: строка 3
        /*
         * Метод String.join() объединяет несколько строк через разделитель.
         * Первый аргумент - разделитель (строка).
         * Второй аргумент - строки, которые необходимо объединить.
         * Используется аргумент переменной длины.
         * */

        /*
         * Каждый вызов оператора +, или метода concat(), или метода join () -- приводит к созданию и помещению новой
         * строки в пул строк.
         *
         * Более того, каждый раз при использовании текстового литерала (как "Hello" здесь), эта строка тоже
         * помещается в пул строк (но ссылка на неё не создаётся, т.к. мы ещё не создавали переменную на эту строку).
         * */

        str1 = "Строка";
        for (int i = 0; i < 10; i++) {
            str1 += " итерация " + i;
        }
        System.out.println("str1 = " + str1);
        // str1 = Строка итерация 0 итерация 1 итерация 2 итерация 3 итерация 4 итерация 5 итерация 6 итерация 7
        // итерация 8 итерация 9
    }

    public static void stringBuilderDemo() {
        /*
         * Операции +, concat(), join() -- тажеловесные операции.
         * При сложении больших строк или мнокоратном использовании сложения строк (например, в циклах) использовать
         * эти операци не рекомендуется.
         * Для таких целей рекомендуется использовать классы StringBuilder или StringBuffer (вместо string)
         * Эти два класса позволяют изменять имеющися объект вместо создания нового, тем самым повышая
         * произвоительность.
         *
         * StringBuilder -- работает быстрее, но непотокобезопасен.
         * StringBuffer -- работает медленнее, но при этом потокобезопасен.
         * */

        String str1 = "Строка";
        StringBuilder builder = new StringBuilder();
        /*
         * При создании можно в конструктор StringBuilder() передать строку, или строковый литерал, или выражение,
         * возвращающее строковое значение.
         * Тогда объект builder уже будет хранить значение строки.
         * */
        // метод append()
        builder.append(str1).append("Hello");
        System.out.println("builder = " + builder); // str1 = СтрокаHello
        /*
         * Метод append() изменяет содержимое объекта builder: "приклеивает" к его строке переданный аргумент
         * И возвращает ссылку на этот же объект.
         * Поэтому метод append() можно использовать последовательно.
         *
         * При этом не происходит операций со строками в классическом понимании; новые объекты строк не создаются.
         * Работа ведётся с одним и тем же объектом builder.
         * */

        builder = new StringBuilder("Строка");
        for (int i = 0; i < 10; i++) {
            builder.append(" итерация ").append(i);
            // Во всём этом цикле новых объектов не создавалось. Использовался объект builder.
        }
        System.out.println(builder);
        // Строка итерация 0 итерация 1 итерация 2 итерация 3 итерация 4 итерация 5 итерация 6 итерация 7 итерация 8
        // итерация 9
        /*
         * При вызове печати println() от объекта неявно вызывается его метод toString() для преобразования объекта в
         * строку.
         * Если необходимо получить строку из объекта StringBuilder | StringBuffer для дальнейшей работы, то
         * необходимо использовать его метод toString()
         * */
        str1 = builder.toString();
        System.out.println("str1 = " + str1);
        // str1 = Строка итерация 0 итерация 1 итерация 2 итерация 3 итерация 4 итерация 5 итерация 6 итерация 7
        // итерация 8 итерация 9
    }

    public static void stringFromToArrayDemo() {
        // Так же в конструктор строк можно передать char[], byte[]
        char[] charsArr = {'q', 'w', 'e'};
        String charStr = new String(charsArr);
        System.out.println("charStr = " + charStr); // charStr = qwe
        byte[] bytesArr = {111, 112, 113};
        String byteStr = new String(bytesArr);
        System.out.println("byteStr = " + byteStr); // byteStr = opq

        // И сами строки можно преобразовывать в массив char[] или массив byte[]
        char[] charsFromString = charStr.toCharArray();
        System.out.println("charsFromString = " + Arrays.toString(charsFromString)); // charsFromString = [q, w, e]
        byte[] bytesFromString = byteStr.getBytes();
        System.out.println("bytesFromString = " + Arrays.toString(bytesArr)); // bytesFromString = [111, 112, 113]
    }

    public static void stringCompareToDemo() {
        // Сравнение строк
        String str1 = "Java";
        String str2 = "java";
        // Метод equals()
        System.out.println("str1.equals(str2) : " + str1.equals(str2)); // str1.equals(str2) : false
        /*
         * Метод equals() сравнивает значения строк посимвольно с учётом регистра и возвращает значение типа boolean
         * */
        // сравнение без учёта регистра
        System.out.println("str1.equalsIgnoreCase(str2) : " + str1.equalsIgnoreCase(str2));
        // str1.equalsIgnoreCase(str2) : true
        /*
         * Метод equalsIgnoreCase() сравнивает значения строк посимвольно без учёта регистра и возвращает значение
         * типа boolean
         * */

        // Метод compareTo()
        System.out.println("str1.compareTo(str2) = " + str1.compareTo(str2)); // str1.compareTo(str2) = -32
        /*
         * Метод compareTo() сравнивает значения строк посимвольно с учётом регистра, до первого расхождения и
         * возвращает числовое значение:
         * - 0, если строки равны
         * - отрицательное число, если вызывающая строка меньше, чем переданный аргумент
         *     (т.е. вызывающая строка в алфавитном порядке следует раньше, чем переданный аргумент)
         * - положительное число, если вызывающая строка больше, чем переданный аргумент
         *     (т.е. вызывающая строка в алфавитном порядке следует позже, чем переданный аргумент)
         * абсолютная величина возвращённого числа равно разнице между первыми встретившимися расходящимися символами
         * */

        // Метод compareToIgnoreCase()
        System.out.println("str1.compareToIgnoreCase(str2) = " + str1.compareToIgnoreCase(str2));
        // str1.compareToIgnoreCase(str2) = 0
        /*
         * Метод compareToIgnoreCase() сравнивает значения строк посимвольно без учёта регистра, до первого расхождения
         * и возвращает числовое значение:
         * - 0, если строки равны
         * - отрицательное число, если вызывающая строка меньше, чем переданный аргумент
         *     (т.е. вызывающая строка в алфавитном порядке следует раньше, чем переданный аргумент)
         * - положительное число, если вызывающая строка больше, чем переданный аргумент
         *     (т.е. вызывающая строка в алфавитном порядке следует позже, чем переданный аргумент)
         * абсолютная величина возвращённого числа равно разнице между первыми встретившимися расходящимися символами
         * */
    }
}