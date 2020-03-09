package lessons.lesson01;

public class DataTypesDemo {
    // Однострочный комментарий
    // Распространяется от символов '//' до перевода строки
    // Горячие клавиши: Ctrl + /

    /*
     * Многострочный комментарий
     * Распространяется на все строки от открывающей до закрывающей комбинаций символов
     * Горячие клавиши: Ctrl + Shift + /
     * */

    // public static void main(String[] args) {} -- точка входа в программу.
    // При запуске программы выполняется именно метод main (и всё его содержимое построчно).
    // Сокращение 'main' или 'psvm' вставляет шаблон для создания метода main()

    public static void main(String[] args) {
        // Для демонстрации работы отдельных частей кода можно вызывать отдельные методы
        // (закомментировав вызов остальных)
        variablesDemo();
        dataTypesDemo();
        typesCastDemo();
    }

    private static void variablesDemo() {
        /*
         * Все перемеенные должны быть объявлены перед тем как будут использоваться.
         * Объявление переменной состоит из указания типа данных и имени переменной.
         * По имени переменной осуществляется доступ к данным, которые в ней хранятся.
         * Имя переменной должно (по требованиям языка и по соглашениям):
         *  - состоять из букв латинского алфавита и цифр;
         *  - начинаться с буквы (прописной);
         *  - использовать lowerCamelCase стиль. Например: weirdRidiculouslyLongVariableName;
         *  - быть уникальным в пределах своей видимости;
         * */

        byte byteVar; // просто объявление переменной, выделение области памяти под неё;
        // значение переменной не определено.
        byte byteVar1, byteVar2; // объявлять можно одновременно несколько переменных одного типа
        byteVar = 10; // присваивание значения ранее объявленной переменной
        byteVar = 12; // присваивание значения переменной вновь; переопределение значения
        byte byteVar3 = 35; // можно присваивать значение переменной и в момент её объявления.
        byte byteVar4 = 20, byteVar5 = 17; // для нескольких переменных это тоже верно.
        byte byteVar6 = byteVar; // можно присвоить значение другой переменной того же (или совместимого) типа

        // System.out.println(); -- вызов метода печати данных в консоль.
        // В качестве одного аргмумента принимает строку либо любые другие типы данных.
        // Сокращение 'sout' вставляет шаблон для вызова метода println()
        System.out.println("byteVar: " + byteVar); // byteVar: 12
        // Вывести значение переменных byteVar1, byteVar2 невозможно:
        //   они не инициализированы (им не присвоены значения)
        System.out.println("byteVar3: " + byteVar3); // byteVar3: 35
        System.out.println("byteVar4: " + byteVar4); // byteVar4: 20
        System.out.println("byteVar5: " + byteVar5); // byteVar5: 17
        System.out.println("byteVar6: " + byteVar6); // byteVar6: 12
    }

    private static void dataTypesDemo() {
        /*
         * Все типы данных в Java делятся на две категории: примитивные и ссылочные.
         * От принадлежности переменной к примитивным или ссылочным типам данных зависит способ её хранения и способы
         * работы с ней.
         *
         * К примитивным типам относятся:
         *  - целочисленные типы: byte, short, int, long
         *  - числа с плавающей точкой: float, double
         *  - символьный тип: char
         *  - булевский тип: boolean
         *
         * К ссылочным типам относятся все остальные типы данных. В частности:
         *  - строки: String
         *  - массивы
         *  - классы и интерфейсы, реализованные в языке и его библиотеках
         *  - любые другие типы данных, созданные пользователем (так же являются классами или интерфейсами)
         * */

        /*
         * Тип byte.
         * Хранит целое число в диапазоне от -128 до 127. Значение по умолчанию = 0.
         * Занимает 1 байт (8 бит).
         * */
        byte byteVar = 35, byteVar1 = -17;
        System.out.println("byteVar: " + byteVar); // byteVar: 35
        System.out.println("byteVar1: " + byteVar1); // byteVar: -17

        /*
         * Тип short
         * Хранит целое число в диапазоне от -32 768 до 32 767. Значение по умолчанию = 0.
         * Занимает 2 байта (16 бит)
         * */
        short shortVar = 300, shortVar1 = 200;
        System.out.println("shortVar: " + shortVar); // shortVar: 300
        System.out.println("shortVar1: " + shortVar1); // shortVar1: 200

        /*
         * Тип int
         * Хранит целое число в диапазоне от -2 147 483 648 до 1 124 483 647. Значение по умолчанию = 0.
         * Занимает 4 байта (32 бита).
         * */
        int intVar = 1000;
        int intVar1 = 1_000_000; // Начиная с Java 7 в численных литералах можно использовать символ '_' для
        // визуального удобства разделения групп разрядов. Java эти символы игнорирует.
        System.out.println("intVar: " + intVar); // intVar: 1000
        System.out.println("intVar1: " + intVar1); // intVar1: 1000000

        // byte bigByte = 1_000_000;
        // Ошибка компиляции: incompatible types: possible lossy conversion from int to byte
        // short bigShort = 1_000_000;
        // Ошибка компиляции: incompatible types: possible lossy conversion from int to short
        // Любое численное значение, заданное таким образом (численный литерал) интерпретируется Java как тип данных int
        // значение, выходящее за диапазон значений типа данных нельзя просто так присвоить.

        // byte byteSum = byteVar + byteVar1;
        // Ошибка компиляции: incompatible types: possible lossy conversion from int to byte
        // short shortSum = shortVar + shortVar1;
        // Ошибка компиляции: incompatible types: possible lossy conversion from int to short
        // Любые арифметические операции с типами byte и short производятся с помощью 32-битной арифметики,
        // как с типом int. И результатом операции тоже будет тип int.
        int byteSum = byteVar + byteVar1;
        System.out.println("byteSum: " + byteSum); // byteSum: 18
        int shortSum = shortVar + shortVar1;
        System.out.println("shortSum: " + shortSum); // shortSum: 500

        /*
         * Тип long
         * Хранит целое число в диапазоне от -9 223 372 036 854 775 808 до 9 223 372 036 854 775 807.
         * Значение по умолчанию = 0.
         * Занимает 8 байт (64 бита).
         * */

        // long longVar = 3_000_000_000; // ошибка компиляции: integer number too large
        // Численный литерал интерпретируется как тип данных int.
        // Чтобы указать компилятору, что должен использоваться именно тип данных long,
        // необходимо добавить символ 'l' или 'L' в конце литерала.
        // Лучше использовать 'L', т.к. 'l' легко спутать с '1'.
        long longVar = 3_000_000_000L;
        System.out.println("longVar: " + longVar); // longVar: 3000000000

        /*
         * Для всех целочисленных типов арифметические операции дают целочисленный результат.
         * Операция деления для целочисленных данных так же даёт целый результат:
         * возвращается целая часть от деления (неполное частное), а остаток от деления отбрасывается
         * */
        System.out.println("8 / 3 = " + 8 / 3); // 8 / 3 = 2
        System.out.println("-11 / 7 = " + -11 / 7); // -11 / 7 = -1
        // System.out.println("8 / 0 = " + 8 / 0); // ошибка времени исполнения:
        // ArithmeticException: / by zero
        // Целочисленное деление на ноль приводит к ошибке (не определено).

        // Для работы с большими числами, для которых не подойдут примитивные типы, рекомендуется использовать служебный
        // класс java.math.BigInteger

        /*
         * Тип float
         * Хранит дробное число (десятичную дробь) в формате числа с плавающей точкой.
         * Диапазон значений от 1.4E-45 до 3.4E38 без учёта знака (такой же диапазон для отрицательных значений)
         * Так же может принимать значения +0.0, -0.0, Positive Infinity, Negative Infinity, NaN (not a number).
         * Значение по умолчанию = 0.0.
         * Занимает 4 байта (32 бита).
         * */
        // float floatVar = 1;
        // Ошибка компиляции: incompatible types: possible lossy conversion from int to float
        // float floatVar = 1.0;
        // Ошибка компиляции: incompatible types: possible lossy conversion from double to float
        // Численный литерал интерпретируется как тип данных int.
        // Численный литерал в виде десятичной дроби интерпретируется как тип данных double.
        // Чтобы указать компилятору, что должен использоваться именно тип данных float,
        // необходимо добавить символ 'f' или 'F' в конце литерала.
        float floatVar = 1f, floatVar1 = 5.7f;
        float floatVar2 = -2.3e3F, floatVar3 = 0.044E-5F; // можно использовать экспоненциальную запись,
        // используя символ 'e' или 'E', за которым следует значение экспоненты
        System.out.println("floatVar: " + floatVar); // 1.0
        System.out.println("floatVar1: " + floatVar1); // 5.7
        System.out.println("floatVar2: " + floatVar2); // -2300.0
        System.out.println("floatVar3: " + floatVar3); // 4.4E-7
        // Числа с плавающей точкой хранятся в нормализованном виде (как в последнем примере).

        /*
         * Тип double
         * Хранит дробное число (десятичную дробь) в формате числа с плавающей точкой.
         * Диапазон значений от 4.9E-324 до 1.79E308 без учёта знака (такой же диапазон для отрицательных значений)
         * Так же может принимать значения +0.0, -0.0, Positive Infinity, Negative Infinity, NaN (not a number).
         * Значение по умолчанию 0.0.
         * Занимает 8 байт (64 бита).
         * */
        double doubleVar = 2.0, doubleVar1 = 2.7E3; // можно использовать экспоненциальную запись.
        // Для указания компилятору, что должен использоваться тип double можно указать символ 'd' или 'D'
        // в конце литерала, но это не обязательно.
        double doubleVar2 = 1d, doubleVar3 = -0.033E-3D;
        System.out.println("doubleVar: " + doubleVar); // 2.0
        System.out.println("doubleVar1: " + doubleVar1); // 2700.0
        System.out.println("doubleVar2: " + doubleVar2); // 1.0
        System.out.println("doubleVar3: " + doubleVar3); // -3.3E-5
        // Числа с плавающей точкой хранятся в нормализованном виде (как в последнем примере).

        // Для типов float и double арифметические операции дают соответствующий результат: float или double
        System.out.println("floatVar1 + floatVar3 = " + (floatVar1 + floatVar3)); // floatVar1 + floatVar3 = 5.7000003
        System.out.println("doubleVar * doubleVar1 = " + doubleVar * doubleVar1); // doubleVar * doubleVar1 = 5400.0
        System.out.println("doubleVar2 / doubleVar = " + doubleVar2 / doubleVar); // doubleVar2 / doubleVar = 0.5

        // Деление на ноль в числах с плавающей точкой допустимо:
        double zero = 0D;
        System.out.println("doubleVar / zero = " + doubleVar / zero); // doubleVar / zero = Infinity
        System.out.println("doubleVar3 / zero = " + doubleVar3 / zero); // doubleVar3 / zero = -Infinity
        // Деление нуля на ноль даёт неопределённость NaN (not a number)
        System.out.println("zero / zero = " + zero / zero); //zero / zero = NaN

        // Арифметические операции числами float и double дают ПРИБЛИЗИТЕЛЬНЫЙ результат.
        System.out.println("2.0 - 1.1 = " + (2.0 - 1.1)); // 2.0 - 1.1 = 0.8999999999999999
        System.out.println("0.1 + 0.1 + 0.2 + 0.2 = " + (0.1 + 0.1 + 0.2 + 0.2)); // 0.1 + 0.1 + 0.2 + 0.2 =
        // 0.6000000000000001
        // Для ТОЧНЫХ вычислений эти типы данных использовать некорректно.
        // Для работы с точными вычислениями рекомендуется использовать служебный класс java.math.BigDecimal

        /*
         * Тип char
         * Хранит один Unicode-символ (в кодировке UTF-16). Фактически, хранит численный код символа.
         * Диапазон значений от 0 до 65536 (от '\u0000' до '\uFFFF').
         * Значение по умолчаниню = 0.
         * Занимает 2 байта (16 бит).
         * */
        char charVal = 74; // можно присваивать код символа
        char charVal1 = 'a'; // можно присваивать непосредственно сам символ
        char charVal2 = '\u0076'; // можно присваивать код символа в шестнадцатеричном представлении
        // в escape-последовательности:
        //  - \\u - означает Unicode-символ
        //  - далее следует 4-значное число в 16-ричной системе счисления, равное коду символа
        //  - вся последовательность заключается в одинарные квычки как символ.
        System.out.println("" + charVal + charVal1 + charVal2 + charVal1); // Java

        // поскольку char фактически хранит целое число, с ним допустимы целочисленные арифметические операции:
        char java = 'J' + 'a' + 'v' + 'a';
        System.out.println("java = " + java); // java = Ƃ
        char character = 'c' + 'h' + 'a' + 'r' + 'a' + 'c' + 't' + 'e' + 'r';
        System.out.println("character = " + character); // character = έ

        /*
         * Тип boolean
         * Логический тип. Хранит одно из двух значений: false (ложь) или true (истина).
         * Это не 0 и 1, а именно отдельные значения false и true.
         * Занимаемое количество памяти не фиксировано, зависит от реализации JVM.
         * */
        boolean isActive = true;
        boolean isClosed = false;
        System.out.println("Is active? " + isActive); // Is active? true
        System.out.println("Is closed? " + isClosed); // Is closed? false
        // Поскольку не является численным типом, не поддерживает арифметические операции
        // и не совместим с другими примитивными типами.
    }

    private static void typesCastDemo() {
        /*
         * Совместимые типы данных возможно приводить один к другому.
         * Существует 2 варианта приведения типов:
         *  - автоматическое приведение
         *  - явное (принудительное) приведение
         * */

        /*
         * Автоматическое приведение типов осуществляется если оба типа совместимы
         * и целевой тип имеет большую длину, чем исходный
         * */
        byte byteVal = 15;
        short shortVal = byteVal; // значение типа byte переменной byteVal автоматически приведено
        // к типу short присвоением переменной shortVal
        int intVal = byteVal, intVal1 = shortVal; // значение типа byte (short) автоматически приведенр к int
        long longVal = byteVal, longVal1 = shortVal, longVal2 = intVal; // значение автоматически приведено к long
        double doubleVal = intVal; // значение автоматически приведено к double
        // такие преобразования просходят без потери данных.
        System.out.println("byte = " + byteVal + "; byte to short = " + shortVal + "; byte to int = " + intVal +
                "; byte to long = " + longVal + "; byte to double = " + doubleVal);
        // byte = 15; byte to short = 15; byte to int = 15; byte to long = 15; byte to double = 15.0

        // автоматическое приведение с возможной потерей точности данных:
        intVal = 1_234_567_890;
        longVal = 123_456_789_000L;
        float floatVal = intVal, floatVal1 = longVal;
        double doubleVal2 = longVal;
        System.out.println(intVal + " to float = " + floatVal); // 1234567890 to float = 1.23456794E9
        System.out.println(longVal + " to float = " + floatVal1); // 123456789000 to float = 1.23456791E11
        System.out.println(longVal + " to double = " + doubleVal2); //123456789000 to double = 1.23456789E11

        /*
         * Для явного приведения типов необходимо указать в скобках целевой тип перед переменной, значением или
         * выражением
         * */
        longVal = 4_294_968_407L;
        intVal = (int) longVal;
        System.out.println(longVal + " to int = " + intVal); // 4294968407 to int = 1111

        // нецелые типы приводятся к целым отбрасыванием дробной части (округления не производится).
        doubleVal = 3.9485;
        longVal = (long) doubleVal;
        System.out.println(doubleVal + " to long = " + longVal); // 3.9485 to long = 3

        // boolean не совместим ни с одним из примитивных типов.
    }
}