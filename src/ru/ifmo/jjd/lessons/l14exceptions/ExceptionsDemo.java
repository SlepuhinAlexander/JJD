package ru.ifmo.jjd.lessons.l14exceptions;

import java.io.IOException;

public class ExceptionsDemo {
    public static void main(String[] args) {
        // примеры RuntimeException
        // деление на ноль
        int a = 9, b = 0, res;
        // res = a / b;
        /*
         * Exception in thread "main" java.lang.ArithmeticException: / by zero
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:6)
         * */

        /*
         * Исключение ArithmeticException (деление на ноль)
         * в stacktrace указана последовательность вызовов методов. И номер конкретной строки в конкретном файле
         * */

        // обращение к несуществующему элементу массива: ArrayIndexOutOfBoundsException
        int[] arr = new int[3];
        // arr[5] = 3;
        /*
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 3
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:21)
         * */

        // обращение к несуществующему объекту: NullPointerException
        String s = null;
        // s.equals("hello");
        /*
         * Exception in thread "main" java.lang.NullPointerException
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:29)
         * */

        // ошибка приведения типов: ClassCastException
        Object someData = "123";
        // Integer someInteger = (Integer) someData;
        /*
         * Exception in thread "main" java.lang.ClassCastException: class java.lang.String cannot be cast to class
         * java.lang.Integer (java.lang.String and java.lang.Integer are in module java.base of loader 'bootstrap')
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:36)
         * */

        // обработка исключений
        try {
            /*
             * В блок try помещаем потенциально опасный код: код, который может привести к исключению
             * */
            System.out.println("before");
            res = a / b;
            System.out.println("after");
        } catch (
            /*
             * В скобках необходимо указать какой тип исключений необходимо обработать
             * */
                ArithmeticException e
            /*
             * Здесь ArithmeticException - это тип данных, а e - имя переменной.
             * В переменную e попадает объект класса ArithmeticException, если исключение произошло.
             * У этого объекта потом можно вызвать методы класса эксепшена, или его родителей (и интерфейса Throwable
             * */
        ) {
            /*
             * Блок кода, описывающий действия, при отлове перечисленного исключения.
             * */
            System.out.println();
            // методы эксепшенов
            System.out.println(e.getMessage()); // сообщение об исключении (например, чтобы записать его в лог)
            e.printStackTrace(); // напечатать в консоль
            res = 0;
        }
        System.out.println("after try catch");
        System.out.println("res = " + res);

        /*
         * Управление в блок catch сразу! при срабатывании исключения.
         * Никакой дальнейший код в try выполнен не будет.
         * */
        /*
         * Try перехватывает только указанный тип исключений (и его потомков).
         * Все остальные исключения обработаны не будут.
         *
         * Если, например, указать в catch тип Exception - то этот блок будет перехватывать все исключения, но это
         * затрудняет обработку исключения: невозможно понять какое именно исключение было перехвачено и определить
         * необходимые действия по исправлению.
         * */

        // объединение catch блоков. Если необходимо перехватить несколько исключений.
        // вариант 1: несколько блоков catch
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                Integer someInteger = (Integer) someData;
            } else {
                arr[33] = 90;
            }
            /*
             * В данном блоке try может выпасть 2 несвязанных типа исключений:
             * - ClassCastException
             * - ArrayIndexOutOfBoundsException
             * */
        } catch (ClassCastException e) {
            /*
             * перехватывает ClassCastException и всех его потомков.
             * */
            System.out.println("Проблема с приведением типов");
        } catch (ArrayIndexOutOfBoundsException ex) {
            /*
             * перехватывает ArrayIndexOutOfBoundsException и всех его потомков
             * */
            System.out.println("Проблема с массивом");
        }
        /*
         * Блоков catch может быть сколько угодно.
         * В единый момент времени будет только один эксепшен. Несколько сработать не смогут.
         * Последовательность блоков важна, если перехватываемые исключения являются родственниками.
         *
         * Плюс в том, что можно описать различные обработки для разных исключений.
         * Минус в том, что большой блок кода получается. И catch-ей может быть сколько угодно.
         * */

        // вариант 2: объединение нескольких исключений в одном блоке catch
        // все исключения будут обработаны единым способом
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                Integer someInteger = (Integer) someData;
            } else {
                arr[33] = 90;
            }
        } catch (
            /*
             * в скобках через | перечисляем все отлавливаемые типы исключений и задаём одно имя переменной
             * */
                ClassCastException | ArrayIndexOutOfBoundsException e
            /*
             * Перехватывает ClassCastException и всех его потомков + ArrayIndexOutOfBoundsException и всех его
             * потомков.
             * Но обработка будет единой.
             * */
        ) {
            System.out.println("Проблема с приведением типов или массивом");
        }
        /*
         * В этом варианте через ИЛИ нельзя перечислить типы-родственники (один является потомком другого)
         * */

        // вариант 3: через общего родителя (через общий тип данных)
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                Integer someInteger = (Integer) someData;
            } else {
                arr[33] = 90;
            }
        } catch (RuntimeException e) {
            /*
             * Блок catch будет перехватывать все RuntimeException
             * */
            System.out.println("Проблема с чем-то в Runtime");
        }
        /*
         * Плюс в том, что будут перехвачены все потомки
         * Минусы:
         * - нельзя обработать по-разному.
         * - нельзя понять какой именно эксепшен был -> правильно обработать его.
         * */

        // Предположим, мы хотим отлавливать все RuntimeException одним способом; а ClassCastException и
        // ArrayIndexOutOfBoundsException - иным образом.
        // Это можно сделать с помощью нескольких блоков catch (вариант 1), но тут уже важна последовательность:
        // сначала нужно указывать наиболее узкий класс. А наиболее широкий - в последнюю очередь.
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                Integer someInteger = (Integer) someData;
            } else {
                arr[33] = 90;
            }
        } catch (ClassCastException | ArrayIndexOutOfBoundsException e) {
            /*
             * Блок catch будет перехватывать ClassCastException всех его потомков + ArrayIndexOutOfBoundsException и
             * всех его потомков. Но не другие исключения
             * */
            System.out.println("Проблема с массивом или приведением типов");
        } catch (Throwable e) {
            /*
             * Блок catch будет перехватывать все RuntimeException.
             * Если их ещё не перехватили блоки catch выше.
             * */
            System.out.println("Проблема с чем-то в Runtime");
        }

        // блок finally
        /*
         * Блок finally не обязательный.
         * Начиная c Java 7 появилась функциональность try-with-resources, который в некоторых случаях позволяет
         * обойтись без finally.
         * */
        /*
         * finally - блок кода, который выполнится в случае любого исключения, даже если оно не перехватывается в блоке
         * catch.
         * Обычно блок finally используется для закрытия ресурсов, сетевых подключений, соединений с файлами и тому
         * подобного.
         * */
        System.out.println("finally");
        /*
         * Если никаких исключений в try не произошло, блок finally - не выполняется.
         * */

        voidWithUncheckedException(34);
//        voidWithUncheckedException(3);
        /*
         * Exception in thread "main" java.lang.IllegalArgumentException: Значение не может быть меньше 30
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.voidWithUncheckedException(ExceptionsDemo.java:216)
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:208)
         * */

        try {
            voidWithCheckedException("qwer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * Метод voidWithCheckedException может генерировать поверяемое исключение.
         * Поэтому его нужно либо обработать здесь же, либо пробросить выше (декларировать в сигнатуре вызывающего
         * метода: указать throws)
         * */

/*
        try {
            SomeMessage someMessage = new SomeMessage("Название", "Текст");
        } catch (ChatException e) {
            e.printStackTrace();
        }
*/
        /*
         * В такой реализации видимость переменной someMessage ограничена блоком try. Все try к этой переменной не
         * обратиться.
         * Нужно объявление переменной вынести за try/catch. И не забыть её инициализировать начальным значением.
         * Например null.
         * */

        SomeMessage someMessage = null;
        try {
            someMessage = new SomeMessage("Название", "text");
        } catch (ChatException e) {
            e.printStackTrace();
        }
        // если объект не будет создан, следующий код приведёт к NullPointerException
        // System.out.println(someMessage.getText());

        SomeMessage oneMoreMessage = null;
        try {
            oneMoreMessage = new SomeMessage("Н", "Текст");
        } catch (ChatException e) {
            try {
                throw new ChatException("some message").initCause(e);
                /*
                 * Здесь мы устанавливаем причину по которой произошёл изначальный ChatException
                 * */
            } catch (Throwable throwable) {
                System.out.println("Catch in catch " + throwable.getCause());
            }
            e.printStackTrace();
        }
        /*
         * ru.ifmo.jjd.lessons.l14exceptions.ChatException: Длина не может быть меньше 3 А-та-та!
         * 	at ru.ifmo.jjd.lessons.l14exceptions.SomeMessage.setTitle(SomeMessage.java:26)
         * 	at ru.ifmo.jjd.lessons.l14exceptions.SomeMessage.<init>(SomeMessage.java:8)
         * 	at ru.ifmo.jjd.lessons.l14exceptions.ExceptionsDemo.main(ExceptionsDemo.java:253)
         * Catch in catch ru.ifmo.jjd.lessons.l14exceptions.ChatException: Длина не может быть меньше 3 А-та-та!
         * */
    }

    /*
     * Исключения можно не только обрабатывать, но и выбрасывать самостоятельно.
     * */
    // метод с необрабатываемым исключением
    private static void voidWithUncheckedException(int a) {
        if (a < 30) {
            throw new IllegalArgumentException("Значение не может быть меньше 30");
            /*
             * new IllegalArgumentException - создание объекта исключения.
             * throw - выбрасывание этого исключения.
             * */
        }
    }
    /*
     * Поскольку метод voidWithUncheckedException выбрасывает непроверяемое исключение - его вызов *можно*, но
     * *не обязательно* заворачивать в try|catch
     * */

    // метод с обрабатываемым исключением
    private static void voidWithCheckedException(String s) throws IOException {
        if (s.endsWith(".txt")) {
            throw new IOException("Проблема с файлом");
            /*
             * генерируем и выбрасываем обрабатываемое исключение
             * */

            /*
             * Обрабатываемое исключение должно быть либо обработано здесь же (в try/catch) - что бессмысленно.
             * Либо объявлено в сигнатуре метода.
             * С помощью throws ТипИсключения.
             *
             * Объявление исключения в методе переносит ответственность за обработку исключения на тот участок кода, в
             * котором вызывается метод.
             * */
        }
    }
}
