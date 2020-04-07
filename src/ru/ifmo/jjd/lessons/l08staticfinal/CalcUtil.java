package ru.ifmo.jjd.lessons.l08staticfinal;

// final классы
public final class CalcUtil {
    /*
     * С объявлением класса, свойства, метода или аргумента можно указать оператор final/
     *
     * Если указать final с классом, то запрещено наследование данного класса: нельзя создать дочерний для этого класса.
     * */

//    final double PI = 3.14;
    /*
     * Значения свойств, объявленных как final, должны быть либо сразу заданы в момент объявления.
     * Либо заданы в конструкторе класса.
     * Но не одновременно и там и там.
     * */

    // final свойства (поля)
    final double PI_VAR;
    /*
     * По соглашению об именованию, константы называют в uppercase; если несколько слов - задаются через `_`
     * */

    public CalcUtil() {
        PI_VAR = 3.14;
    }
    /*
     * Значения свойства, объявленного как final изменить нельзя.
     * */

    // final методы
    public final void doSomething() {

    }
    /*
     * Метод, объявленный как final - нельзя переопределить при наследовании (в дочерних классах).
     * */

    // final аргументы метода
    public void someVoid(final int num, final SomeClass sc, final int[] arr) {
        /*
         * final аргументы в методе доступны только для чтения;
         * Изменить значение final аргумента изменить нельзя.
         * */
        System.out.println(num); // можно
        // num = 90; // cannot assign value to final variable

        // final c аргументами ссылочного типа
        System.out.println(sc); // можно
        System.out.println(sc.name); // можно
        sc.name = "Новое имя"; // можно
        // sc = new SomeClass(); // нельзя

        /*
         * То есть нельзя присвоить ссылочной переменной новый объект (новую ссылку)
         * Но со свойстами объекта можно работать свободно.
         * */

        // аналогично, с массивами, т.к. массив тоже объект
        arr[4] = 34; // можно
        // arr = new int[5]; // нельзя

        // final c локальными переменными
        final int i = 6;
        System.out.println(i); // можно
        // i = 5; // нельзя
        /*
         * С локальными переменными действуют те же правила, что и с аргументами
         * */
    }

}

// class Child extends CalcUtils {} // cannot inherit final class

class SomeClass {
    String name;
}