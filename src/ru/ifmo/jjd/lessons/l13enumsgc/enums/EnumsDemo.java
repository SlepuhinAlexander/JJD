package ru.ifmo.jjd.lessons.l13enumsgc.enums;

import java.util.Arrays;

enum Country {
    /*
     * Именя значений пишутся с большой буквы и внутри JAVA являются константами
     * */
    UK, USA, AUSTRALIA
    /*
     * если испльзуюстя только значения, то достаточно перечислить через запятую. ";" не нужна.
     * */
}

/*
 * Enum - это логически связанный набор констант. Перечисление.
 * Для объявления enum нужно указать ключевое слово enum, плюс название
 * Далее в {} через запятую пишем допустимые значения.
 *
 * */

enum Priority {
    HIGH(10), MIDDLE(5), LOW(1); // если в энуме появляется что-то кроме элементов, их нужно отделить ;
    private int code; // Хотим привязать к каждому элементу свой код
    // свойство code принадлежит каждому из экземпляров энума.

    // определим дополнительный конструктор
    Priority(int code) {
        this.code = code;
    }
    // теперь элементы приориетов могут быть созданы только с кодом.

    // добавим геттеры и сеттеры

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /*
     * реализация всех методов сейчас одинаковая для всех элементов энума
     * Но при желании, можно сделать свою реализацию для некоторых из элементов
     * */
}

// каждый элемент enum может иметь свою реализаию общего метода
enum Operation {
    SUM {
        @Override
        public int action(int a, int b) {
            return a + b;
        }
    },
    MULTI {
        @Override
        public int action(int a, int b) {
            return a * b;
        }
    };

    /*
     * сам метод делаем абстрактным (без реализации).
     * а для каждого конкретного элемента делаем имплементацию этого метода.
     * */
    public abstract int action(int a, int b);

    /*
     * если переопределять реализацию требуется только в конкретном элементе энума - то общий метод делаем не
     * абстрактным, делаем реализацию по умолчанию, и в нужных элементах энума переопределить его.
     * */
}

enum Planet {
    MERCURY("Меркурий", 10, 1000),
    VENUS("Венера", 40, 30000),
    EARTH("Земля", 41, 35000),
    MARS("Марс", 20, 25000),
    JUPITER("Юпитер", 220, 500000),
    SATURN("Сатурн", 200, 400000),
    URANUS("Уран", 100, 100000),
    NEPTUNE("Нептун", 95, 90000);

    final String name;
    final int size;
    final int mass;

    Planet(String name, int size, int mass) {
        this.name = name;
        this.size = size;
        this.mass = mass;
    }
}

/*
 * enum могут создаваться с дополнительными полями, конструкторами и методами
 * элементы в энуме обязательны. всё остальное - опционально.
 * */

public class EnumsDemo {
    /*
     * Хотим добавить в класс Article поле страна
     * Но мы хотим чтобы страну можно было выбирать из некого набора.
     *
     * Для задания набора констант используется тип enum - перечисления.
     * */
    public static void main(String[] args) {
        Article article = new Article("Путешествие по Австралли");
        article.setText("В Австралии - здорово!");
        /*
         * Обращение к эелементу enum идёт через НазваниеПеречисления.ЭЛЕМЕНТ
         * */
        article.setCountry(Country.AUSTRALIA);
        System.out.println(article);
        /*
         * Article{dateCreated=2020-04-13T15:25:48.468443600, title='Путешествие по Австралли',
         * text='В Австралии - здорово!', country=AUSTRALIA}
         * */

        Article ukArticle = new Article("Путешествие в Лондон");
        ukArticle.setCountry(Country.UK);
        System.out.println(ukArticle);
        /*
         * Article{dateCreated=2020-04-13T15:25:48.488390600, title='Путешествие в Лондон', text='', country=UK}
         * */

        // методы enum
        // получить массив элементов enum (массив констант) : метод values();
        System.out.println(Arrays.toString(Country.values()));
        // [UK, USA, AUSTRALIA]
        // Тип данных возращаемого массива: - ИимяЭнума[]
        Country[] countries = Country.values();
        System.out.println(Arrays.toString(countries));
        // [UK, USA, AUSTRALIA]
        /*
         * элементы энама упорядочены
         * всегда будут расположены в том порядке, в каком объявлены
         * в возвращаемом массиве элементы возвращаются в том же порядке
         * */

        // Получение индекса элемента: метод ordinal()
        // метод вызывается от элемента массива
        System.out.println(Country.AUSTRALIA.ordinal()); // 2

        // Получение константы enum по строке: метод valueOf()
        System.out.println(Country.valueOf("UK")); // UK
        /*
         * Если не найдено - выбросит IllegalArgumentException
         * */

        // наоборот: получение строкового представление энума
        System.out.println(Country.UK.name()); // UK

        // переберём все элементы энума
        for (Country country : Country.values()) {
            System.out.println(country.ordinal() + " : " + country);
        }
        /*
         * UK : 0
         * USA : 1
         * AUSTRALIA : 2
         * */
        // Порядок элементов всегда совпадает с порядком объявления.


        // посмотрим как получить коды приоритетов:
        int lowCode = Priority.LOW.getCode(); // 1
        System.out.println(1);
        /*
         * Метод getCode() вернёт значение кода для приоритета LOW
         * */

        // попробуем поменять код:
        Priority middle = Priority.MIDDLE;
        middle.setCode(7);
        System.out.println(middle.getCode()); // 7
        /*
         * значение поменялось
         *
         * Геттеры используются гораздо чаще чем сеттеры.
         * Лучше сеттеры вообще не создавать, если хотим чтобы поле было неизменным
         * */

        // проверяем как работают энумы с переопределёнными реализациями методов
        for (Operation operation : Operation.values()) {
            System.out.println(operation.action(2, 3));
        }

        // Задача: написать enum, который перечисляет планеты Солнечной системы, и возвращает массу планеты и её радиус
        for (Planet planet : Planet.values()) {
            System.out.println(planet.name + " : size = " + planet.size + "; mass = " + planet.mass);
        }
        /*
         * Меркурий : size = 10; mass = 1000
         * Венера : size = 40; mass = 30000
         * Земля : size = 41; mass = 35000
         * Марс : size = 20; mass = 25000
         * Юпитер : size = 220; mass = 500000
         * Сатурн : size = 200; mass = 400000
         * Уран : size = 100; mass = 100000
         * Нептун : size = 95; mass = 90000
         * */
    }
}
