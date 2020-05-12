package ru.ifmo.jjd.lessons.l23lambdas;

import java.util.HashMap;

public class LambdasInMapsDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Сидней", 200);
        map.put("Лондон", 260);
        map.put("Париж", 376);

        // метод forEach
        /*
         * для Map метод forEach принимает на вход интерфейс BiConsumer<T, U>
         *
         * он имеет абстрактный метод
         *      void accept(T t, U u)
         * который принимает два аргумента, что-то с ними делает, и ничего не возвращает
         * */
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 260
         * Париж : 376
         * Сидней : 200
         * */
        System.out.println("---");
        /*
         * внутри метода forEach (в лямбде) изменять можно только value. Ключ в мапе изменять нельзя
         * */
        map.forEach((key, value) -> map.replace(key, value * 2));
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 520
         * Париж : 752
         * Сидней : 400
         * */
        System.out.println("---");

        // метод compute()
        /*
         * метод V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction)
         * принимает ключ key и интерфейс BiFunction в качестве аргумента
         * BiFunction<T,​U,​R> - это дженерик-интерфейс с тремя типами данных:
         * - T - первый аргумент функции
         * - U - второй аргумент функции
         * - R - возвращаемое значение функции
         * BiFunction интерфейс имеет default метод andThen​(Function<? super R,​? extends V> after)
         * который имеет ещё один дженерик аргумент V, и позволяет текущую би-функцию продолжить следующей би-функцией,
         * которая берёт результат типа R, вместе с новым аргументов V и возвращает ещё какое-то значение
         *
         * Метод compute вычисляет (новое) значение, которое нужно в мапе проассоциировать с текущим ключом
         * Если ключ не найден, то новая запись помещается в карту.
         * Если ключ найден, то вычислить новое значение для ключа и обновить в карте.
         * */
        map.compute("Лондон", (key, value) -> value != null ? value % 10 == 0 ? value / 10 : value * 10 : null);
        map.compute("Берлин", (key, value) -> key.length() * 10);
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 52
         * Париж : 752
         * Берлин : 60
         * Сидней : 400
         * */
        System.out.println("---");

        // метод computeIfPresent()
        /*
         * аналогичен предыдующему, только проверяет на наличие значения, привязанного к ключу.
         * Если ключ найден, и к нему привязано не-null значение - вычислить новое значение и заменить в мапе.
         * Если текущее значение, привязанное к ключу, равно null или ключ не найден - ничего не делать.
         * */
        map.put("Москва", null);
        map.computeIfPresent("Москва", (key, value) -> key.length() + value);
        map.computeIfPresent("Минск", (key, value) -> key.length() + value);
        map.computeIfPresent("Лондон", (key, value) -> key.length() + value);
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 58
         * Париж : 752
         * Берлин : 60
         * Москва : null
         * Сидней : 400
         * */
        System.out.println("---");

        // метод computeIfAbsent()
        /*
         * похож на предыдущий, но наоборот:
         * если для ключа задано не-null значение, то не делать ничего.
         * если же ключ не найден, или ключу привязано null значение, то вычислить новое значение по переданной функции
         * и положить в карту.
         * Принимает аргументом обычную функцию, не би-функцию
         * */
        map.computeIfAbsent("Москва", key -> key.length() * key.length());
        map.computeIfAbsent("Лондон", key -> key.length() * key.length());
        map.computeIfAbsent("Нью-Йорк", key -> key.length() * key.length());
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 58
         * Париж : 752
         * Берлин : 60
         * Москва : 36
         * Сидней : 400
         * Нью-Йорк : 64
         * */
        System.out.println("---");

        // метод merge()
        /*
         * merge​(K key, V value, BiFunction<? super V,​? super V,​? extends V> remappingFunction)
         * если ключ не найден в карте, или ему привязано null значение, то установить переданное значение
         * если же ключ найден и у него есть не-null значение, то вычислить новое значение и положить в карту
         * новое значение вычисляется по старому значению и переданному значению.
         * */
        map.merge("Санкт-Петербург", 17, (oldVal, newVal) -> oldVal * newVal);
        map.merge("Москва", 17, (oldVal, newVal) -> oldVal * newVal);
        map.forEach((key, value) -> System.out.println(key + " : " + value));
        /*
         * Лондон : 58
         * Париж : 752
         * Берлин : 60
         * Москва : 612
         * Санкт-Петербург : 17
         * Сидней : 400
         * Нью-Йорк : 64
         * */
    }
}
