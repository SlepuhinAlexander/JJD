package ru.ifmo.jjd.lessons.l15generics;

public class GenericsDemo {
    public static void main(String[] args) {
        /*
         * Для создания объекта класса, использующего generic тип, при объявлении типа данных необходимо явно задать
         * используемый genetic тип;
         * При вызове конструктора достаточно указать <>, конкретный тип для generic типа подставляется автоматически.
         * */
        User<String> user1 = new User<>(); // для данного объекта тип T для свойства id всегда будет String;
        user1.setId("765");
        user1.setLogin("qwe");
        user1.setPwd("123456");

        /*
         * В generic типах нельзя использовать примитивные типы.
         * Вместо них необходимо использовать соответствующие классы-обёртки.
         * */
        User<Integer> user2 = new User<>(); // для данного объекта тип T для свойства id всегда будет Integer;
        user2.setId(12); // Здесь происходит автоупаковка
        user2.setLogin("rty");
        user2.setPwd("1212");

        User user3 = new User(); // не указанный generic тип означает, что выбран тип Object для generic

        System.out.println(user1); // User{id=765, login='qwe', pwd='123456'}
        System.out.println(user2); // User{id=12, login='rty', pwd='1212'}

        /*
         * Создаём объект PairContainer:
         * - тип данных key для container1 - String
         * - тип данных value для container1 - Integer
         * Порядок типов важен.
         * */
        PairContainer<String, Integer> container1 = new PairContainer<>("hello", 1);

        /*
         * Создаём другой объект PairContainer:
         * - тип данных key для container2 - Integer
         * - тип данных value для container2 - String
         * */
        PairContainer<Integer, String> container2 = new PairContainer<>(44, "cat");

        /*
         * Создаём ещё один объект PairContainer:
         * - тип данных key для container3 - Double
         * - тип данных value для container3 - User с любым generic типом (любым типом для id)
         * */
        PairContainer<Double, User> container3 = new PairContainer<>(3.3, user2);
        /*
         * В такой ситуации можно использовать в конструкторе и user1, и user2, и user3
         * */
        container3.getValue().setId("sdfsdf"); // здесь в setId можно передать что угодно (Object).
        container3.getValue().setId(123); // тоже object

        /*
         * Создаём ещё один объект PairContainer:
         * - тип данных key для container4 - Double
         * - тип данных value для container4 - User с типом String
         * */
        PairContainer<Double, User<String>> container4 = new PairContainer<>(3.3, user1);
        /*
         * В такой ситуации можно использовать в конструкторе только user1.
         * */

        /*
         * Наследование в generic типах не используется: ни в одну ни в другую сторону.
         * */
        // PairContainer<Double, User<Number>> container5 = new PairContainer<>(3.3, user2); // cannot infer arguments
        User<Number> nUser = new User<>();
        // PairContainer<Double, User<Integer>> container6 = new PairContainer<>(5.4, nUser); // cannot infer arguments
    }
}
