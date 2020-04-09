package ru.ifmo.jjd.lessons.l11datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateAndCalendar {
    public static void main(String[] args) throws ParseException {
        // классы Date и Calendar использовались до появления dateTime Api в java 8
        // они не являются безопасными для использования в многопоточных средах и имеют меньше возможностей, чем  классы dateTime Api
        // не смотря на это, Date и Calendar не перестали использовать в проектах окончательно


        // класс Date хранит дату в миллисекундах, которые прошли с 1 января 1970 года (Unix время)
//        https://docs.oracle.com/javase/7/docs/api/java/util/Date.html

        Date currentDate = new Date(-345268782); // принимает аргумент равный числу миллисекунд, истекших с полуночи 1 января 1970 г
        System.out.println(currentDate);
        currentDate = new Date(); // текущая дата и время в миллисекундах
        System.out.println(currentDate);

        // getTime() возвращает количество миллисекунд, прошедших с полуночи 1 января 1970 г
        System.out.println("getTime: " + currentDate.getTime());

        // Сравнение дат
        Date first = new Date(3000000000L); // 4 февраля 1970
        Date second = new Date(); // текущая дата
        // возвращает true, если вызывающая метод дата идет после той, что передается в аргументе и false, если нет
        System.out.println("4 февраля 1970 после Текущей даты: " + first.after(second));
        // возвращает true, если вызывающая метод дата идет до той, что передается в аргументе и false, если нет
        System.out.println("4 февраля 1970 до Текущей даты: " + first.before(second));
        // возвращает true, если даты равны и false, если нет
        System.out.println("4 февраля 1970 - текущая дата: " + first.equals(second));


        // Класс Calendar (GregorianCalendar) содержит методы для работы с датой
//        https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html

        // Основные возможности:
        // увеличить / уменьшить к текущей дату
        // получить отдельные компоненты даты (месяц, день и тд)

        // Создание григорианского календаря инициализированного текущей датой и временем
        GregorianCalendar currentCalendar = new GregorianCalendar();
        System.out.println("currentCalendar: " + currentCalendar);

        // Создание григорианского календаря инициализированного определенной датой и временем
        GregorianCalendar birthday = new GregorianCalendar(1995, Calendar.APRIL, 1);
        System.out.println("birthday: " + birthday);

        // Создание григорианского календаря на основе объекта Date
        Calendar calendarFromDate = new GregorianCalendar();
        calendarFromDate.setTime(currentDate);
        System.out.println("calendarFromDate: " + currentCalendar);

        // получить объект Date из Calendar:
        Date birthdayDate = birthday.getTime();
        System.out.println("birthday Date: " + birthdayDate);

        // методы Calendar для работы с датой
        Calendar someCalendar = new GregorianCalendar();

        // Посмотрите список констант для установки / получения дней, месяцев, лет и тд по документации
        // установить дату, например,
        // set(int field, int value)
        someCalendar.set(Calendar.DAY_OF_MONTH, 23); // установили 23 число
        someCalendar.set(Calendar.MONTH, 1); // установили 1 месяц
        System.out.println("someCalendar date: " + someCalendar.getTime());

        // set(int year, int month, int date)
        someCalendar.set(1900, Calendar.JANUARY, 10);
        System.out.println("someCalendar date: " + someCalendar.getTime());

        // set(int year, int month, int date, int hourOfDay, int minute, int second)
        someCalendar.set(1900, Calendar.JANUARY, 10, 15, 22, 10);
        System.out.println("someCalendar date: " + someCalendar.getTime());

        // получение отдельных компонентов даты
        System.out.println(someCalendar.get(Calendar.YEAR)); // получили значение года
        System.out.println(someCalendar.get(Calendar.HOUR_OF_DAY)); // получили час
        System.out.println(someCalendar.get(Calendar.DAY_OF_MONTH)); // получили число

        // увеличить / уменьшить дату
        someCalendar.add(Calendar.MONTH, 3);   // увеличили на месяца
        System.out.println("someCalendar date: " + someCalendar.getTime());
        someCalendar.add(Calendar.YEAR, 12);   // увеличили на 12 лет
        System.out.println("someCalendar date: " + someCalendar.getTime());
        someCalendar.add(Calendar.HOUR_OF_DAY, -20);   // уменьшили на 20 часов
        System.out.println("someCalendar date: " + someCalendar.getTime());

        // сравнение
        Calendar firstCalendar = someCalendar; // 9 апреля 1912
        Calendar secondCalendar = currentCalendar; // текущая дата
        // возвращает true, если вызывающая метод дата идет после той, что передается в аргументе и false, если нет
        System.out.println("9 апреля 1912 после Текущей даты: " + firstCalendar.after(secondCalendar));
        // возвращает true, если вызывающая метод дата идет до той, что передается в аргументе и false, если нет
        System.out.println("9 апреля 1912 до Текущей даты: " + firstCalendar.before(secondCalendar));
        // возвращает true, если даты равны и false, если нет
        System.out.println("9 апреля 1912 - текущая дата: " + firstCalendar.equals(secondCalendar));

        // получение объекта Date из строки / форматированный вывод даты (SimpleDateFormat)
        // создаем объект SimpleDateFormat с заданным шаблоном
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y-MM-dd HH:mm:ss");

        // можно прочитать дату из строки, соответсвующей шаблону
        String dateInString = "2020-06-03 22:08:10";

        Date dateFromString = simpleDateFormat.parse(dateInString);
        System.out.println("date From String: " + dateFromString);

        // можно преоразовать дату в строку по шаблону
        System.out.println("string from date: " + simpleDateFormat.format(currentDate));

        // из Date в DateTimeAPI и наоборот
        Date input = new Date();
        LocalDateTime date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        // начиная с java 9
//        date = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());

        LocalDateTime now = LocalDateTime.now();
        Date nowDate =  Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

    }
}
