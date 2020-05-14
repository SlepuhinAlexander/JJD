package ru.ifmo.jjd.lessons.l24streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsDemo {
    public static void main(String[] args) {
         /*Stream API - набор методов для работы с данными, как с потоком
         позволяет представить различные наборы данных в виде потока
         И далее: сортировать их, фильтровать, осуществлять поиск по различным критериям,
         кроме этого позволяет создавать новые потоки, создавать коллекции и мапы на основе потока данных*/
        // Stream НЕ ХРАНИТ ДАННЫЕ Для сохранения данных из Stream нужно использовать специальные методы

         /*Для работы с потоками данных:
         1. получить данные в виде потока - объект типа Stream
         2. выполнить промежуточные операции с потоком данных
         (промежуточные операции обрабатывают данные и возвращают Stream объект)
         3. выполнить терминальную (конечную) операцию
         (терминальная операция обрабатывает данные и завершает работу стрима)
         Без терминальной операции промежуточные операции не начнут выполняться!!!
         Например, получили объект stream
         промежуточные операции
         stream.операция1() - вернет преобразованный объект stream
               .операция2() - вернет преобразованный объект stream
               .операция3()  - вернет преобразованный объект stream
               .терминальнаяОперация(); // запускает промежуточные операции, данные обрабатываются, стрим закрывается
         терминальные forEach / findFirst / findAny / xxxMatch / min / max / collect*/

         /*методы получения Stream объектов:
         из коллекций collection.stream();
         из массива Arrays.stream(arr);
         из файла Files.lines(path_to_file);
         из строки string.chars();
         используя builder:
         Stream.builder().add(obj1).add(obj2).add(objN).build();
         Stream.of(1, 4, 7); любой набор данных*/

        Stream<Integer> integerStream = Stream.of(6, -8, 90, -22, 0, -100);
        integerStream
                // метод filter()
                /*
                 * промежуточный метод
                 * принимает на вход Predicate
                 * оставим только отрицательные
                 * */
                .filter(num -> num < 0)
                // метод map()
                /*
                 * промежуточный метод
                 * принимает на вход Function
                 * используется для преобразования элемента
                 * метод может только заменить элемент в стриме на новый (если это объект, например)
                 * изменить свойства текущего объекта методом map нельзя
                 *
                 * умножим каждый оставшийся элемент на 10
                 * */
                .map(num -> num * 10)
                // метод limit()
                /*
                 * промежуточный метод
                 * принимает на вход long аргумент maxSize
                 * ограничивает размер стрима
                 * можно использовать для того чтобы взять первые n элементов стрима
                 * оставим только первые 2 элемента
                 * */
                .limit(2)
                // метод forEach()
                /*
                 * терминальный метод
                 * принимает на вход Consumer
                 * аналогичен методу forEach коллекций
                 * */
                .forEach(System.out::println);
        System.out.println();
        /*
         * -80
         * -220
         * */

        integerStream = Stream.of(3, 34, 44, 34, -12, 34, -12, 44, 500);
        integerStream
                // метод distinct()
                /*
                 * промежуточный метод
                 * оставит только уникальные элементы
                 * */
                .distinct()
                // метод sorted()
                /*
                 * промежуточный метод
                 * сортирует элементы в естественном порядке
                 * может принимать аргументом компаратор - тогда будет сделана сортировка по компаратору
                 * */
                .sorted()
                .forEach(System.out::println);
        System.out.println();
        /*
         * -12
         * 3
         * 34
         * 44
         * 500
         * */

        integerStream = Stream.of(7, 8, 90, -100, 0, -13);
        // методы anyMatch / allMatch / noneMatch
        /*
         * терминальные операции
         * проверяют стрим на соответствие условию (условиям). Возвращают true/false.
         * принимают на вход Predicate
         * anyMatch - вернёт true, если ХОТЯ БЫ ОДИН элемент соответствует условию.
         * allMatch - вернёт true только если ВСЕ элементы соответствуют условию.
         * noneMatch - вернёт true только если все элементы не соответствуют условию.
         * */
        System.out.println(integerStream.anyMatch(num -> num == 0)); // true
        System.out.println();

        integerStream = Stream.of(7, 8, 90, -100, 0, -13);
        System.out.println(integerStream.allMatch(num -> num > 100)); // false
        System.out.println();

        integerStream = Stream.of(7, 8, 90, -100, 0, -13);
        System.out.println(integerStream.noneMatch(num -> num > 100)); // true
        System.out.println();

        String[] strings = {"aa", "bb", "cc", "dd"};
        String s = Arrays.stream(strings)
                // метод findFirst()
                /*
                 * Берёт первый элемент стрима.
                 * Возвращает объект Optional<T>
                 * В данном случае, Optional<String>
                 * */
                .findFirst().get();
        System.out.println("s = " + s); // s = aa
        // метод findAny() - произвольный элемент стрима.

        // null safe container Optional<T>
        /*
         * Optional - это контейнер для хранимого внутри него любого типа данных.
         *
         * Если в контейнере есть какое-то значение, то метод get() вернёт само значение.
         * Если нет, вернёт null
         * */

        s = Arrays.stream(strings).findFirst()
                // метод orElse() класса Optional
                /*
                 * подставляет дефолтное значение, если содержимое контейнера равно null
                 * иначе просто возвращает содержимое контейнера
                 * */
                .orElse("default");
        System.out.println("s = " + s); // s = aa

        s = Arrays.stream(strings).findFirst()
                // метод orElseThrow() класса Optional
                /*
                 * выбрасывает исключение, если содержимое контейнера равно null
                 * иначе просто возвращает содержимое контейнера
                 * */
                .orElseThrow();
        System.out.println("s = " + s); // s = aa

        //noinspection ConstantConditions
        System.out.println(Arrays.stream(strings).findFirst()
                // метод isPresent() класса Optional
                /*
                 * проверяет содержимое на нулл
                 * */
                .isPresent());

        Arrays.stream(strings)
                // метод skip()
                /*
                 * принимает long аргумент - сколько первых элементов удалить (чтобы перейти к следующим)
                 * */
                .skip(2)
                .filter(str -> str.startsWith("c"))
                .forEach(System.out::println);
        System.out.println();
        /*
         * cc
         * */

        // из этой коллекции нужно собрать массив пользователей старше 25 лет
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("qwe", 34));
        users.add(new User("asd", 56));
        users.add(new User("zxc", 18));
        users.add(new User("qwe", 34));
        users.add(new User("zxc", 22));

        User[] usersArr = users
                // открыть поток из элементов коллекции
                .stream()
                // отобрать пользователей старше 25
                .filter(user -> user.getAge() > 25)
                // собрать в массив: метод toArray()
                /*
                 * по умолчанию возвращает массив Object[]
                 * чтобы собрать массив определённого типа данных, нужно использовать аргумент: как создать массив
                 * по сути, нужно передать ссылку на конструктор того массива, который хотим получить
                 * */
                .toArray(User[]::new);

        // соберём из коллекции лист пользователей младше 25 лет
        List<User> userList = users.stream()
                .filter(user -> user.getAge() < 25)
                // метод peek()
                /*
                 * Служит для преобразования элемента стрима. Например, для изменения свойства объекта
                 * принимает на вход Consumer
                 * */
                .peek(user -> user.setActive(true))
                // метод collect()
                /*
                 * собирает стрим в коллекцию / мапу
                 * ожидает аргумент типа Collector
                 * есть стандартные Коллекторы, которые можно получить Collectors.toList() / Collectors.toSet()
                 * / Collectors.toMap()
                 * */
                .collect(Collectors.toList());

        Set<User> userSet = users.stream()
                .filter(user -> user.getAge() < 25)
                .peek(user -> user.setAge(user.getAge() + 10))
                .collect(Collectors.toSet());

        ArrayList<User> userArrayList = users.stream()
                .distinct()
                .sorted(Comparator.comparing(User::getAge))
                // приводим к коллекции
                .collect(Collectors.toCollection(ArrayList::new));

        // методы min() / max()
        /*
         * терминальные методы
         * возвращают минимальный / максимальный элемент из стрима
         * требуют компаратор для определения минимального / максимального элемента
         * возвращают Optional объект
         * */
        User minUser = users.stream()
                .min(Comparator.comparing(User::getAge))
                .orElse(new User("default", 1));


        List<String> stringList = Arrays.asList("34", "78", "190");
        stringList.stream()
                // метод flatMap()
                /*
                 * принимает на вход функцию
                 * каждый элемент стрима преобразует в новый стрим, а потом собирает их в общий стрим.
                 * */
                .flatMap(num -> Stream.of(Integer.parseInt(num)))
                // был Stream<String>, стал Stream<Integer>
                .forEach(System.out::println);

        String[] strings1 = {"aaa", "cc", "cc", "eeeee", "s"};
        // соберём из этого массива карту: ключ - элемент, значение - длина строки
        Map<String, Integer> mapFromArr = Arrays.stream(strings1)
                // нужно снова вызвать метод collect и передать туда Коллектор
                /*
                 * метод toMap ожидает две функции keyMapper и valueMapper вычисляющие ключ и значение соответственно
                 * третий аргуемент mergeFunction - BinaryOperator - описывает правила формирования значения если из
                 * стрима формировались одинаковые ключи
                 * */
                .collect(Collectors.toMap(
                        // метод identity() возвращает аргумент функции в неизменном виде.
                        // В данном случае вернёт элемент стрима
                        Function.identity(), // ключи
                        String::length, // значения
                        (elem1, elem2) -> elem1, // что делать, если ключи одинаковые
                        // если нужна конкретная мапа, то последним аргументом указать ссылку на конструктор мапы
                        HashMap::new
                ));

        // Объединение двух стримов
        List<String> list1 = Arrays.asList("aaa", "bbb", "ccc");
        List<String> list2 = Arrays.asList("ddd", "eee", "fff");

        //noinspection UnusedAssignment
        Stream<String> stringStream = Stream.concat(list1.stream(), list2.stream());
        Stream<String> stringStream2 = Stream.of(list1, list2).flatMap(Collection::stream);

        // метод reduce()
        /*
         * терминальная операция
         * принимает на вход один аргумент - бинарный оператор
         * возвращает Optional
         * накапливает элементы стрима по правилу = бинарному оператору
         * */
        integerStream = Stream.of(1, 2, 3, 4);
        int res = integerStream.reduce((x, y) -> x * y).orElse(11);
        System.out.println("res = " + res); // res = 24;

        stringStream = Stream.of("Java", "Junior", "Developer");
        /*
         * второй вариант reduce()
         * с двумя аргументами: с чего начать; и как накапливать
         * */
        String stringRes = stringStream.reduce("Result", (x, y) -> x + " " + y);
        System.out.println(stringRes); // Result Java Junior Developer

        integerStream = Stream.of(1, -2, -3, 4);
        res = integerStream.reduce(0, (x, y) -> {
            if (x < 0 || y < 0) return 0;
            return x + y;
        }, Integer::sum);
        System.out.println("res = " + res); // res = 4
    }
}
