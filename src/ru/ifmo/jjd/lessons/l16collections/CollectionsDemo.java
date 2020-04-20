package ru.ifmo.jjd.lessons.l16collections;

import java.util.*;

public class CollectionsDemo {
    public static Student student1 = new Student("Петр", "Алексеев", 18);
    public static Student student2 = new Student("Екатерина", "Еремина", 20);
    public static Student student3 = new Student("Денис", "Попов", 22);
    public static Student student4 = new Student("Петр", "Попов", 22);


    public static void main(String[] args) {
        linkedListDemo();
        arrayListDemo();
        hashSetDemo();
        linkedHashSetDemo();
        treeSetDemo();
        collectionsIterationDemo();
    }

    public static void linkedListDemo() {
        System.out.println("---LinkedList---");
        /*
         * Двунаправленный связный список
         * Особенности LinkedList:
         * - порядок хранения элементов соответствует порядку добавления
         * - позволяет хранить null
         * - допускает хранение дублирующихся элементов
         * */

        /*
         * Создадим объект типа LinkedList.
         * Поскольку коллекция - это generic, необходимо указать тип данных, который будет хранится в коллекции:
         * */
        LinkedList<Student> students = new LinkedList<>();
        students.add(student1); // добавление элемента в коллекцию: в случае LinkedList - добавление в конец списка;
        students.add(student2);
        students.add(student2);
        students.add(student1);
        /*
         * Метод add() в LinkedList - перегружен.
         * Ещё одна версия метода add() позволяет добавить элемент в LinkedList на указанную позицию (индекс)
         * */
        students.add(1, student3); // добавление элемента на указанный индекс (индексация начинается с нуля!)
        /*
         * при вставке элемента по индексу список перестраивается, ссылки у ближайших соседей перестраиваются на этот
         * добавленный элемент.
         * */
        // students.add(90, student3); // java.lang.IndexOutOfBoundsException: Index: 90, Size: 4
        /*
         * Если коллекция позволяет доступ к элементу по индексу, то при обращении к несуществующему индексу всегда
         * будет бросать исключение java.lang.IndexOutOfBoundsException
         * */
        students.addFirst(null); // добавление элемента в начало списка. Равносильно add(0,элемент).


        // вывод коллекции в консоль
        students.forEach(System.out::println);
        /*
         * null
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Денис', surname='Попов', age=22}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Петр', surname='Алексеев', age=18}
         * */

        // удаление элементов
        students.removeFirst(); // удаление первого элемента
        students.removeLast(); // удаление последнего элемента
        students.remove(1); // удаление элемента по индексу
        students.remove(student2); // удаление первого встретившегося элемента, равного (через equals) переданному
        System.out.println("после удаления:");

        students.forEach(System.out::println);
        /*
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * */

        // получение первого элемента
        System.out.println("students.getFirst() = " + students.getFirst());
        // students.getFirst() = Student{name='Петр', surname='Алексеев', age=18}

        // получение последнего элемента
        System.out.println("students.getLast() = " + students.getLast());
        // students.getLast() = Student{name='Екатерина', surname='Еремина', age=20}

        // получение элемента по индексу
        System.out.println("students.get(0) = " + students.get(0));
        // students.get(0) = Student{name='Петр', surname='Алексеев', age=18}
    }

    public static void arrayListDemo() {
        System.out.println("---ArrayList---");
        ArrayList<Student> students = new ArrayList<>();
        /*
         * Внутри ArrayList создаётся массив Student[].
         * По умолчанию, массив создаётся на 10 элементов (затем при необходимости он будет пересоздан на больший /
         * меньший).
         *
         * Можно указать изначальный размер создаваемого массив, тогда при создании ArrayList будет создан массив на
         * указанное количество элементов.
         * */
        students = new ArrayList<>(20); // указание ИЗНАЧАЛЬНОЙ ёмкости.

        // Количество элементов коллекции
        System.out.println("students.size(): " + students.size()); // students.size(): 0

        students.add(student1); // добавление элемента в конец списка
        students.add(student2); // добавление элемента в конец списка
        students.add(1, null); // добавление элемента на указанную позицию

        students.trimToSize(); // уменьшает внутренний массив до количества элементов в коллекции

        students.forEach(System.out::println);
        /*
         * Student{name='Петр', surname='Алексеев', age=18}
         * null
         * Student{name='Екатерина', surname='Еремина', age=20}
         * */
        System.out.println();
        // метод subList()
        /*
         * есть у всех List-ов. Позволяет взять часть списка, от начального до конечного индекса
         * начальный индекс включительно, конечный индекс не включительно.
         * */
        System.out.println("subList(0,2) = " + students.subList(0, 2)); // [0,1]; 2 не включается
        // subList(0,2) = [Student{name='Петр', surname='Алексеев', age=18}, null]

        System.out.println();

        // удаление по индексу
        students.remove(1); // удаление по индексу
        students.remove(student2); // удаление ссылки на объект

        students.forEach(System.out::println);
        /*
         * Student{name='Петр', surname='Алексеев', age=18}
         * */

        System.out.println();

        // получение элемента по индексу
        System.out.println(students.get(0));

        /*
         * В ArrayList нет методов ***First() и ***Last(), свойственных LinkedList.
         * Вместо них используется обращение по конкретному индексу.
         * */

        /*
         * Домашнее задание:
         * Создать ArrayList и LinkedList на одинаковое большое количество элементов (1000000)
         * Посмотреть скорость работы методов получения, добавления, удаления: из начала, из конца, из середины
         * */

        System.out.println();
        // создание списка из массива
        Student[] studentsArr = {student1, student2, student3};
        // метод Arrays.asList() преобразует массив в лист и возвращает List();
        List<Student> studentsList = Arrays.asList(studentsArr);
        // метод коллекции addAll() позволяет добавить в данную коллекцию ВСЕ элементы из переданной коллекции
        students.addAll(Arrays.asList(studentsArr));
        students.forEach(System.out::println);
        /*
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Екатерина', surname='Еремина', age=2
         * Student{name='Денис', surname='Попов', age=22}
         * */
    }

    public static void hashSetDemo() {
        System.out.println("---интерфейс Set---");
        System.out.println("---HashSet---");

        Student[] studentsArr = {student1, student2, student3, student1, student2, student3};

        // любая коллекция может быть создана на основе другой коллекции
        HashSet<Student> students = new HashSet<>(Arrays.asList(studentsArr));
        students.forEach(System.out::println);
        /*
         * Student{name='Денис', surname='Попов', age=22}
         * Student{name='Екатерина', surname='Еремина', age=2
         * Student{name='Петр', surname='Алексеев', age=18}
         * */

        // опробовать методы HashSet
        students.add(student1);
        System.out.println("students.contains(student4) = " + students.contains(student4));
        // students.contains(student4) = false
        System.out.println("students.contains(student1) = " + students.contains(student4));
        // students.contains(student1) = false

        students.remove(student4);
        students.remove(student3);
        System.out.println();
        students.forEach(System.out::println);
        /*
         * Student{name='Екатерина', surname='Еремина', age
         * Student{name='Петр', surname='Алексеев', age=18}
         * */

        HashSet<Student> students1 = (HashSet<Student>) students.clone();
        System.out.println("students1: ");
        students1.forEach(System.out::println);
        /*
         * students1:
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Петр', surname='Алексеев', age=18}
         * */
    }

    public static void linkedHashSetDemo() {
        System.out.println("---LinkedHashSet---");
        /*
         * Аналогичен HashSet, но дополнительно хранит порядок элементов.
         * */
        LinkedHashSet<Student> students = new LinkedHashSet<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.forEach(System.out::println);
        /*
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Денис', surname='Попов', age=22}
         * */
    }

    public static void treeSetDemo() {
        Student[] studentsArr = {student1, student2, student3, student1, student2, student3};

        // Класс Student теперь реализует интерфейс Comparable
        TreeSet<Student> students = new TreeSet<>(Arrays.asList(studentsArr));
        students.add(student1);
        students.add(student3);
        System.out.println("students in TreeSet");
        students.forEach(System.out::println);
        /*
         * students in TreeSet
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Денис', surname='Попов', age=22}
         * */
        /*
         * Объекты отсортированы по возрасту, потому что мы так определили в методе compareTo
         * */

        System.out.println("При использовании компаратора: ");
        // создадим объект компаратора
        Comparator<Student> studentComparator = new StudentNameComparator().thenComparing(new StudentAgeComparator());
        /*
         * метод thenComparing позволяет назначить следующий компаратор для случая равенства.
         *
         * Сначала будет использоваться StudentNameComparator
         * затем если он вернул равенство, будет использоваться StudentAgeComparator
         *
         * Цепочка компараторов может быть любой длины.
         * */

        TreeSet<Student> students1 = new TreeSet<>(studentComparator);
        students1.add(student1);
        students1.add(student2);
        students1.add(student3);
        students1.add(student4);
        students1.forEach(System.out::println);
        /*
         * При использовании компаратора:
         * Student{name='Денис', surname='Попов', age=22}
         * Student{name='Екатерина', surname='Еремина', age=20}
         * Student{name='Петр', surname='Алексеев', age=18}
         * Student{name='Петр', surname='Попов', age=22}
         * */
    }

    public static void collectionsIterationDemo() {
        Student[] studentsArr = {student1, student2, student3, student1, student2, student3};
        ArrayList<Student> students = new ArrayList<>(Arrays.asList(studentsArr));
        for (Student student : students) {
            System.out.println(student);
            System.out.println(student.getName());
            // удалить элемент из коллекции внутри forEach невозможно!
            // students.remove(student); // выбросит исключение java.util.ConcurrentModificationException
        }

        /*
         * Итератор - это объект, который перебирает коллекции.
         * У всех коллекций есть свой Iterator.
         * У листов ещё есть ListIterator.
         *
         * Обычный итератор умеет проверить, есть ли следующий элемент, и вернуть следующий элемент.
         * Лист итератор умеет проверить наличие и следующего, и предыдущего элемента.
         * */
        Iterator<Student> studentIterator = students.listIterator();
        while (studentIterator.hasNext()) {
            if (studentIterator.next().getName().equals("Пётр")) {
                studentIterator.remove();
            }
        }

        /*
         * Использование лямбда-выражений позволяет упростить синтаксис перебора, удаления, компараторов
         * Изучим позже.
         * */
    }
}
