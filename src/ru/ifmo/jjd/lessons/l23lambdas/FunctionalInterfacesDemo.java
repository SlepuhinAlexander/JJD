package ru.ifmo.jjd.lessons.l23lambdas;

import java.util.function.Function;
import java.util.function.Predicate;

// некоторые готовые функциональные интерфейсы в Java API
public class FunctionalInterfacesDemo {
    public static void main(String[] args) {
        // 1. Интерфейс Predicate<T>
        /*
         * Содержит метод
         *      boolean test(T t);
         * Принимает на вход любой тип данных и возвращает булевское значение.
         * Предполагает проверку t на соответствие какому-либо условию.
         *
         * Содержит default методы:
         *      boolean or (Predicate<? super T> target) - или
         *      boolean and (Predicate<? super T> target) - и
         *      boolean negate (Predicate<? super T> target) - не
         * */

        Predicate<Integer> pos = num -> num > 0;
        System.out.println("pos.test(11) = " + pos.test(11)); // pos.test(11) = true
        Predicate<Integer> neg = num -> num < 0;
        System.out.println("neg.test(11) = " + neg.test(11)); // neg.test(11) = false
        Predicate<Integer> even = num -> num % 2 == 0;
        System.out.println("even.test(11) = " + even.test(11)); // even.test(11) = false

        /*
         * Например, в коллекциях есть метод removeIf
         * Он принимает на вход аргумент типа Predicate<E>
         * */
        System.out.println("pos.and(even).test(45) = " + pos.and(even).test(45)); // pos.and(even).test(45) = false
        System.out.println("neg.or(even).test(22) = " + neg.or(even).test(22)); // neg.or(even).test(22) = true

        // 2. Интерфейс Function<T, R>
        /*
         * Содержит абстрактный интерфейс
         *      R apply(T t)
         * Принимает аргумент одного типа, что-то с ним делает, возвращает аргумент второго типа.
         * T и R могут быть одинаковыми, могут быть разными.
         *
         * Содержит default методы
         *      andThen(Function<? super R, ? extends V> after)
         *      compose(Function<? super V, ? extends T> before)
         * */

        Function<Integer, Double> tenPercent = num -> num * 0.1;
        System.out.println("tenPercent.apply(15) = " + tenPercent.apply(15)); // tenPercent.apply(111) = 1.5
        Function<Integer, Integer> plusTen = num -> num + 10;
        System.out.println("plusTen.apply(34) = " + plusTen.apply(34)); // plusTen.apply(34) = 44
        Function<Integer, Integer> plusFive = num -> num + 5;
        System.out.println("plusFive.apply(43) = " + plusFive.apply(43)); // plusFive.apply(43) = 48

        System.out.println("plusTen.andThen(plusFive).andThen(tenPercent).apply(16) = " +
                           plusTen.andThen(plusFive).andThen(tenPercent).apply(16));
        // plusTen.andThen(plusFive).andThen(tenPercent).apply(16) = 3.1

        Function<Integer, Integer> two = num -> num * 2;
        Function<Integer, Integer> sqr = num -> num * num;
        System.out.println("two.andThen(sqr).apply(3) = " + two.andThen(sqr).apply(3));
        // two.andThen(sqr).apply(3) = 36

        // действие с #compose выполняется до основного действия
        int intRes = two // #4
                .compose(sqr) // #1
                .andThen(plusFive) //#4
                .compose(sqr) // #2
                .apply(3);
        System.out.println("intRes = " + intRes); // intRes = 167
        // (3 ^ 2) ^ 2 * 2 + 5 = 9 ^ 2 * 2 + 5 = 81 * 2 + 5 = 162 + 5 = 167

        Function<Integer, String> dollar = num -> num + "$";

        /*
         * Дан Predicate condition и две функции ifTrue и ifFalse
         * Написать метод getFunction, который принимает предикат condition и две функции ifTrue, ifFalse
         * Метод getFunction в зависимости от значения предиката возвращает функцию ifTrue или ifFalse
         * */

        Predicate<Integer> cond = num -> num > 0;
        Function<Integer, Integer> func = getFunction(cond, plusFive, plusTen);
        System.out.println(func.apply(5));
    }

    private static Function<Integer, Integer> getFunction (Predicate<Integer> condition,
                                                           Function<Integer,Integer> ifTrue,
                                                           Function<Integer,Integer> ifFalse) {
        return num -> condition.test(num) ? ifTrue.apply(num) : ifFalse.apply(num);
    }
}
