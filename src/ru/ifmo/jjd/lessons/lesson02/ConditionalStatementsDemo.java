package ru.ifmo.jjd.lessons.lesson02;

public class ConditionalStatementsDemo {
    public static void main(String[] args) {
        ifElseDemo();
        switchDemo();
    }

    private static void ifElseDemo() {
        /*
         * Базовый синтаксис условного оператора if:
         * if (условие) {
         *     код, который выполнится, только если условие истинно
         * }
         * код, который выполнится после if в любом случае
         * */

        // Если случайное двузначное целое число - чётное, вывести этот факт в консоль
        int n = (int) (Math.random() * 100); // генерируем случайное целое число от 0 до 99
        if (n % 2 == 0) {
            System.out.println("Число " + n + " - чётное.");
        }
        // Число 68 - чётное.

        /*
         * Условный оператор if может содержать необязательный блок else. Синтаксис:
         * if (условие) {
         *     код, который выполнится, только если условие истинно
         * } else {
         *     код, который выполнится, только если условие ложно
         * }
         * код, который выполнится после if в любом случае
         * */

        // Определить, чётным или нечётным является случайное двузначное целое число. Вывезти результат в консоль.
        n = (int) (Math.random() * 100);
        if (n % 2 == 0) {
            System.out.println("Число " + n + " - чётное.");
        } else {
            System.out.println("Число " + n + " - нечётное.");
        }
        // Число 32 - чётное.

        // Если блок кода внутри конструкции if или else состоит из только одной инструкции,
        // заключать его в {} не обязательно.
        // Тот же пример более компактно:
        n = (int) (Math.random() * 100);
        System.out.print("Число " + n + " - ");
        if (n % 2 != 0) System.out.print("не");
        System.out.println("чётное.");
        // Число 15 - нечётное.

        /*
         * Оператор if допускает последовательную вложенность.
         * Используется, когда необходимо добавить новые варианты условий.
         * Каждое новое условие будет проверяться, если предыдущие условия ложны.
         * Синтаксис:
         * if (условие1) {
         *     код, который выполнится, если условие1 истинно.
         *             // далее программа переходит к строке //##
         * } else if (условие2) {
         *     код, который выполнится, если условие1 ложно, а условие2 истинно
         *             // далее программа переходит к строке //##
         * } else if (условие3) {
         *     код, который выполнится, если и условие1 ложно и условие2 ложно, а условие3 истинно
         *             // далее программа переходит к строке //##
         * } else {
         *     код, который выполнится, если все три условия ложны
         *             // секция else так же необязательна
         *             // далее программа переходит к строке //##
         * }
         * //##
         * код, который выполнится после if в любом случае
         * */

        // В переменной minute предположительно лежит число от 0 до 59 (хранящее число минут).
        // Определить в какую четверть часа попадает данное число и ответ вывести в консоль
        //  0-14 => "первая четверть"
        // 15-29 => "вторая четверть"
        // 30-44 => "третья четверть"
        // 45-59 => "четвёртая четверть"
        int minute = (int) (Math.random() * 100) - 10;
        System.out.print(minute + " мин. - это ");
        if (minute >= 0 && minute <= 14) {
            System.out.println("первая четверть.");
        } else if (minute >= 15 && minute <= 29) {
            System.out.println("вторая четверть.");
        } else if (minute >= 30 && minute <= 44) {
            System.out.println("третья четверть.");
        } else if (minute >= 45 && minute <= 59) {
            System.out.println("четвертая четверть.");
        } else {
            System.out.println("некорректное значение.");
        }
        // 4 мин. - это первая четверть.

        // альтернативное и несколько более оптимальное решение
        minute = (int) (Math.random() * 100) - 10;
        System.out.print(minute + " мин. - это ");
        if (minute < 0 || minute >= 60) {
            System.out.println("некорректное значение.");
        } else if (minute < 15) {
            System.out.println("первая четверть.");
        } else if (minute < 30) {
            System.out.println("вторая четверть.");
        } else if (minute < 45) {
            System.out.println("третья четверть.");
        } else {
            System.out.println("четвёртая четверть.");
        }
        // 55 мин. - это четвёртая четверть.

        // Проверить, является ли случайное целое число от -999 до 999 положительным или отрицательным; для
        // положительных чисел определить является ли число чётным или нечётным.
        // Результат вывести в консоль.
        int num = (int) (Math.random() * 1999) - 999; // [-999; 999]
        System.out.print("Число " + num + " - ");
        if (num > 0) {
            System.out.print("положительное");
            if (num % 2 == 0) {
                System.out.println(" чётное.");
            } else {
                System.out.println(" нечётное.");
            }
        } else if (num < 0) {
            System.out.println("отрицательное.");
        } else {
            System.out.println("ноль.");
        }
        // Число -665 - отрицательное
    }

    private static void switchDemo() {
        /*
         * Конструкция switch позволяет делать ветвление кода на произвольное количество альтернатив.
         * Синтаксис:
         * switch (выражение) {
         *     case значение1:
         *         // <code>
         *         break;
         *     case значение2:
         *         // <code>
         *         break;
         *     case значение3:
         *         // <code>
         *     case значение4:
         *         // <code>
         *         break;
         *     default:
         *         // <code>, который выполняется если не один case не совпал.
         * }
         *
         * Все варианты значения в case-ах должны быть уникальны.
         *
         * Конструкция switch вычисляет значение выражения и проверяет его на равенство с указанными вариантами значений
         * (случаями / cases).
         * Если соответствие установлено, код внутри switch начинает выполняться от точки соответствующего совпавшего
         * case и далее пока не встретится break; или конец switch.
         *
         * Если ни один случай не совпал, выполняется вариант default (если он задан).
         * */

        // Игральный автомат выдаёт случаное трёхзначное число.
        // Если выпало 111 или 222 или 333 - игрок получает малый приз
        // Если выпало 444 или 555 - игрок получает средний приз
        // Если выпало 777 - игрок получает крупный приз
        int num = ((int) (Math.random() * 9) + 1) * 111; // для простоты проверки сделаем все три цифры одинаковыми
        switch (num) {
            case 111:
            case 222:
            case 333:
                System.out.println(num + " малый приз");
                break;
            case 444:
            case 555:
                System.out.println(num + " средний приз");
                break;
            case 777:
                System.out.println(num + " крупный приз");
                break;
            default:
                System.out.println(num + " попробуйте ещё раз");
        }
        // 666 попробуйте ещё раз

        /*
         * В версиях Java 12 и 13 конструкцию switch стало возможно использовать в более компактном виде.
         * Синтаксис:
         * switch (выражение) {
         *     case значение1, значение2, значение3 -> {код, который нужно выполнить при любом из этих трёх случаев};
         *     case значение4, значение5 -> {код, который нужно выполнить при любом из этих двух случаев};
         *     case значение6 -> {код, который нужно выполнить если выражение == значение6};
         *     default -> {код, который нужно выполнить если не один case не совпал}
         * }
         * */
        // тот же пример
        num = ((int) (Math.random() * 9) + 1) * 111;
        switch (num) {
            case 111, 222, 333 -> System.out.println(num + " малый приз");
            case 444, 555 -> System.out.println(num + " средний приз");
            case 777 -> System.out.println(num + " крупный приз");
            default -> System.out.println(num + " попробуйте ещё раз");
        }
        // 111 малый приз

        /*
         * В версиях Java 12 и 13 конструкция switch может возвращать значение, которое можно в дальнейшем использовать
         * */
        // тот же пример.
        num = ((int) (Math.random() * 9) + 1) * 111;
        String result = switch (num) {
            case 111, 222, 333 -> num + " малый приз";
            case 444, 555 -> num + " средний приз";
            case 777 -> num + " крупный приз";
            default -> num + " попробуйте ещё раз";
        };
        System.out.println(result);
        // 777 крупный приз
    }
}
