package ru.ifmo.jjd.lessons.lesson11;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeDemo {
    public static void main(String[] args) {
        /*
         * Начиная с версии Java 8 в библитоеке классов платформы Java был добавлен ряд новых классов для работы с
         * датой и временем.
         * Классы расположены в пакете java.time.
         * Некторые из них:
         * - LocalDate - представляет только дату, без времени и временных зон (часовых поясов);
         * - LocalTime - представляет только время, без даты и временных зон;
         * - LocalDateTime - представлят дату и время, без учёта временных зон;
         * - ZonedDateTime - представляет дату и время с учётом временных зон;
         * - DateTimeFormatter - служебный класс для форматирования даты-времени: преобразует строки в дату-время и
         *     наоборот. Работает только для классов времени из пакета java.time;
         * - Instant - представляет момент времени, точку на временной оси; использует количество секунд, прошедших с
         *     момента Unix epoch time равного 1970-01-01T00:00:00Z (полночь 1 явнаря 1970 года по Гринвичу);
         * - Duration - представляет временной промежуток между двумя моментами времени: в секундах и наносекундах;
         * - Period - представляет временной промежуток между двумя датами: в годах, месяцах и днях;
         * - TemporalAdjusters - набор корректировщиков дат (к примеру, может получить дату следующего понедельника).
         * */
        localDateDemo();
        localTimeDemo();
        localDateTimeDemo();
        zonedDateTimeDemo();
    }

    // Работа только с датой в классе LocalDate. Без учёта времени и временных зон. (Примеры).
    public static void localDateDemo() {
        // Класс LocalDate позволяет работать только с датой. Если учёт времени не важен, этого класса достаточно.
        // Основные методы класса LocalDate:
        // Получение текущей даты:
        LocalDate currentDate = LocalDate.now();
        System.out.println("Текущая дата: " + currentDate); // Текущая дата: 2020-04-03
        /*
         * Дата берётся в текущей временной зоне.
         * Чтобы указать другую временную, нужно передать её ID в метод now().
         * */
        String timeZone = "Pacific/Auckland";
        currentDate = LocalDate.now(ZoneId.of(timeZone));
        System.out.println("Текущая дата в " + timeZone + ": " + currentDate);
        // Текущая дата в Pacific/Auckland: 2020-04-04

        // Установка определённой даты:
        LocalDate someDate = LocalDate.of(2018, 10, 20);
        System.out.println("Установленная дата: " + someDate); // Установленная дата: 2018-10-20
        /*
         * Метод of() ожидает передачу трёх целых чисел. При использовани некорректных значений метод выбросит
         * java.time.DateTimeException.
         *
         * Нумерация месяцев начинается с 1. В отличие от классов даты-времени более ранних версий Java: java.util.Date,
         * java.util.Calendar, где нумерация месяцев идёт с 0.
         *
         * Чтобы быть уверенным в выборе месяца можно использовать константы: класс-перечисление java.time.Month
         * содержит все месяцы и некоторые метоы работы с ними.
         * */
        someDate = LocalDate.of(2012, Month.FEBRUARY, 29);
        System.out.println("Новая становленная дата: " + someDate); // Новая установленная дата: 2012-02-29

        // Получение даты из строки:
        String dateString = "2017-05-14"; // стандартный формат даты
        LocalDate parseDate = LocalDate.parse(dateString);
        System.out.println("Объект LocalDate, полученный из строки \"" + dateString + "\": " + parseDate);
        // Объект LocalDate, полученный из строки "2017-05-14": 2017-05-14
        /*
         * По умолчанию метод parse() может распознать (распарсить) строку в формате "yyyy-MM-dd", где:
         * - yyyy - год,
         * - MM - месяц (числом),
         * - dd - день.
         * Если нужно распознать строку иного формата, то в метод parse() нужно передать второй аргумент: формат
         * переданной строки с датой. Например:
         * */
        dateString = "14 декабря 2017";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        /*
         * устанавливаем формат даты, где dd - день, MMMM - полное название месяца, yyyy - год.
         * */
        parseDate = LocalDate.parse(dateString, format);
        System.out.println("Объект LocalDate, полученный из строки \"" + dateString + "\": " + parseDate);
        // Объект LocalDate, полученный из строки "14 декабря 2017": 2017-12-14
        /*
         * Документация по синтаксису форматирования даты:
         * - https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html (пункт Date and Time Patterns).
         * - https://divancoder.ru/2017/12/simpledateformat/ - подробная таблица.
         * */

        // Сдвиг (увеличение / уменьшение) даты.
        /*
         * Дату можно сдвинуть (в большую или меньшую сторону) на определённое количество дней, недель, месяцев или лет.
         * - методы plus***() добавляют указанное количество выбранных единиц времени (отнимают, если аргумент
         *     отрицательный)
         * - методы minus***() отнимают указанное количество выбранных единиц времени (прибавляют, если значение
         *     отрицательное)
         * */
        currentDate = LocalDate.now();
        System.out.println("Текущая дата минус 1 год: " + currentDate.minusYears(1));
        // Текущая дата минус 1 год: 2019-04-03
        System.out.println("Текущая дата минус 2 месяца: " + currentDate.minusMonths(2));
        // Текущая дата минус 2 месяца: 2020-02-03
        System.out.println("Текущая дата минус 12 недель: " + currentDate.minusMonths(12));
        // Текущая дата минус 12 недель: 2019-04-03
        System.out.println("Текущая дата минус -400 дней: " + currentDate.minusDays(-400));
        // Текущая дата минус -400 дней: 2021-05-08
        System.out.println("Текущая дата плюс 11 лет: " + currentDate.plusYears(11));
        // Текущая дата плюс 11 лет: 2031-04-03
        System.out.println("Текущая дата плюс 22 месяца: " + currentDate.plusMonths(22));
        // Текущая дата плюс 22 месяца: 2022-02-03
        System.out.println("Текущая дата плюс 123 недели: " + currentDate.plusWeeks(123));
        // Текущая дата плюс 123 недели: 2022-08-12
        System.out.println("Текущая дата плюс -10 000 дней: " + currentDate.plusDays(-10_000));
        // Текущая дата плюс -10 000 дней: 1992-11-16
        /*
         * Каждый из методов plus***() и minus***() возвращает объект того же класса LocalDate.
         * Соответственно, методы можно вызывать по цепочке
         * */
        System.out.println("Текущая дата плюс 1 год, 2 месяца, 3 недели и 4 дня: " +
                currentDate.plusYears(1).plusMonths(2).plusWeeks(3).plusDays(4));
        // Текущая дата плюс 1 год, 2 месяца, 3 недели и 4 дня: 2021-06-28

        // Методы сравнения двух дат:
        /*
         * В классе LocalDate есть три метода для сравнения двух дат:
         * - isEqual() - возвращает true, если вызывающий объект и агрумент метода представляют одну и ту же дату.
         * - isAfter() - возвращает true, если вызывающий объект (дата) идёт по календарю после даты в аргументе.
         * - isBefore() - возвращает true, если вызывающий объект (дата) идёт по календарю перед датой в аргументе.
         * */
        LocalDate original = LocalDate.of(2018, Month.JANUARY, 2);
        LocalDate copy = LocalDate.of(2018, Month.JANUARY, 2);
        LocalDate different = LocalDate.of(2022, Month.FEBRUARY, 12);
        System.out.println("\"" + original + "\".isEqual(\"" + copy + "\") = " + original.isEqual(copy));
        // "2018-01-02".isEqual("2018-01-02") = true
        System.out.println("\"" + copy + "\".isEqual(\"" + original + "\") = " + copy.isEqual(original));
        // "2018-01-02".isEqual("2018-01-02") = true
        System.out.println("\"" + original + "\".isEqual(\"" + different + "\") = " + original.isEqual(different));
        // "2018-01-02".isEqual("2022-02-12") = false
        System.out.println("\"" + different + "\".isEqual(\"" + original + "\") = " + different.isEqual(original));
        // "2022-02-12".isEqual("2018-01-02") = false
        System.out.println("\"" + original + "\".isAfter(\"" + copy + "\") = " + original.isAfter(copy));
        // "2018-01-02".isAfter("2018-01-02") = false
        System.out.println("\"" + original + "\".isAfter(\"" + different + "\") = " + original.isAfter(different));
        // "2018-01-02".isAfter("2022-02-12") = false
        System.out.println("\"" + different + "\".isAfter(\"" + original + "\") = " + different.isAfter(original));
        // "2022-02-12".isAfter("2018-01-02") = true
        System.out.println("\"" + original + "\".isBefore(\"" + copy + "\") = " + original.isBefore(copy));
        // "2018-01-02".isBefore("2018-01-02") = false
        System.out.println("\"" + original + "\".isBefore(\"" + different + "\") = " + original.isBefore(different));
        // "2018-01-02".isBefore("2022-02-12") = true
        System.out.println("\"" + different + "\".isBefore(\"" + original + "\") = " + different.isBefore(original));
        // "2022-02-12".isBefore("2018-01-02") = false

        // Вычисление разницы между двумя датами:
        LocalDate first = LocalDate.of(2018, Month.JANUARY, 2);
        LocalDate second = LocalDate.of(2022, Month.FEBRUARY, 12);
        // С помощью ChronoUnit:
        System.out.println("Количество лет между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.YEARS.between(first, second)); // Количество лет между "2018-01-02" и "2022-02-12": 4
        System.out.println("Количество лет между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.YEARS.between(second, first)); // Количество лет между "2022-02-12" и "2018-01-02": -4
        System.out.println("Количество месяцев между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MONTHS.between(first, second)); // Количество месяцев между "2018-01-02" и "2022-02-12": 49
        System.out.println("Количество месяцев между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.MONTHS.between(second, first)); // Количество месяцев между "2022-02-12" и "2018-01-02": -49
        System.out.println("Количество недель между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.WEEKS.between(first, second)); // Количество недель между "2018-01-02" и "2022-02-12": 214
        System.out.println("Количество недель между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.WEEKS.between(second, first)); // Количество недель между "2022-02-12" и "2018-01-02": -214
        System.out.println("Количество дней между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.DAYS.between(first, second)); // Количество дней между "2018-01-02" и "2022-02-12": 1502
        System.out.println("Количество дней между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.DAYS.between(second, first)); // Количество дней между "2022-02-12" и "2018-01-02": -1502
        // ChronoUnit.SECONDS.between(first,second);
        /*
         * использование других единиц времени, не входящих в LocalDate (например секунд) приведёт к исключению
         * java.time.temporal.UnsupportedTemporalTypeException
         * */

        // С помощью Period:
        System.out.println("Разница лет между \"" + first + "\" и \"" + second + "\": " +
                Period.between(first, second).getYears()); // Разница лет между "2018-01-02" и "2022-02-12": 4
        System.out.println("Разница лет между \"" + second + "\" и \"" + first + "\": " +
                Period.between(second, first).getYears()); // Разница лет между "2018-01-02" и "2022-02-12": -4
        System.out.println("Разница месяцев между \"" + first + "\" и \"" + second + "\": " +
                Period.between(first, second).getMonths()); // Разница месяцев между "2018-01-02" и "2022-02-12": 1
        System.out.println("Разница месяцев между \"" + second + "\" и \"" + first + "\": " +
                Period.between(second, first).getMonths()); // Разница месяцев между "2018-01-02" и "2022-02-12": -1
        System.out.println("Разница дней между \"" + first + "\" и \"" + second + "\": " +
                Period.between(first, second).getDays()); // Разница дней между "2018-01-02" и "2022-02-12": 10
        System.out.println("Разница дней между \"" + second + "\" и \"" + first + "\": " +
                Period.between(second, first).getDays()); // Разница дней между "2018-01-02" и "2022-02-12": -10
        /*
         * ChronoUnit представляет общее количество желаемых единиц измерения времени между двумя моментами времени
         * (в данном случае датами).
         * Period хранит период времени между двумя датами в формате N лет + M месяцев + K дней. А методы get***()
         * возвращают значение соответствующего поля этого периода.
         * */

        // Форматированный вывод даты:
        currentDate = LocalDate.now();
        System.out.println("Текущая дата в формате по умолчанию: " + currentDate);
        // Текущая дата в формате по умолчанию: 2020-04-03
        /*
         * Форматирование даты в виде строки осуществляется методом format() объекта DateTimeFormatter, который
         * предвариетльно нужно создать с использованием интересующего шаблона форматирования.
         * При составлении шаблона форматирования необходимо использовать синтаксис форматирования даты и времени. Все
         * символы, не зарезервированные в синтаксисе, будут использованы как есть.
         * */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yy г.");
        System.out.println("Текущая дата в формате \"dd/MMM/yy г.\": " + formatter.format(currentDate));
        // Текущая дата в формате "dd/MMM/yy г.": 03/апр./20 г.
    }

    // Работа только с временем в классе LocalTime. Без учёта дат и временных зон. (Примеры).
    public static void localTimeDemo() {
        // Класс LocalTime позволяет работать только с датой. Если учёт времени не важен, этого класса достаточно.
        // Основные методы класса LocalTime:
        // Получение текущего времени:
        LocalTime currentTime = LocalTime.now();
        System.out.println("Текущее время: " + currentTime); // Текущее время: 21:17:55.547064
        /*
         * Время берётся в текущей временной зоне.
         * Чтобы указать другую временную зону, нужно передать её ID в метод now().
         * */
        String timeZone = "Pacific/Auckland";
        currentTime = LocalTime.now(ZoneId.of(timeZone));
        System.out.println("Текущее время в " + timeZone + ": " + currentTime);
        // Текущее время в Pacific/Auckland: 07:19:24.823596

        // Установка определённого времени:
        LocalTime someTime = LocalTime.of(20, 30);
        System.out.println("Установленнное время: " + someTime); // Установленнное время: 20:30
        /*
         * Фабричный метод of(), создающий объект класса LocalTime имеет несколько вариантов. Время можно составить из:
         * - часов и минут (используется 24-часовой формат);
         * - то же плюс секунды;
         * - то же плюс наносекунды;
         *
         * При использовани некорректных значений метод выбросит java.time.DateTimeException.
         *
         * Также можно использовать четвёртый аргумент - наносекунды:
         * */
        someTime = LocalTime.of(20, 30, 10, 123456789);
        System.out.println("Установленнное время: " + someTime); // Установленнное время: 20:30:10.123456789

        // Получение времени из строки:
        String timeString = "23:56:05"; // стандартный формат времени
        LocalTime parseTime = LocalTime.parse(timeString);
        System.out.println("Объект LocalTime, полученный из строки \"" + timeString + "\": " + parseTime);
        // Объект LocalTime, полученный из строки "23:56:05": 23:56:05
        /*
         * По умолчанию метод parse() может распознать (распарсить) строку в формате "HH:mm:ss", где:
         * - HH - часы (в 24-часовом формате),
         * - mm - минуты,
         * - ss - секунды.
         * Если нужно распознать строку иного формата, то в метод parse() нужно передать второй аргумент: формат
         * переданной строки со временем. Например:
         * */
        timeString = "в 23 часа 45 минут и 49 секунд";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("в HH часа mm минут и ss секунд");
        /*
         * устанавливаем формат времени, где HH - часы (в 24-часовом формате), mm - минуты, ss - секунды.
         * */
        parseTime = LocalTime.parse(timeString, format);
        System.out.println("Объект LocalTime, полученный из строки \"" + timeString + "\": " + parseTime);
        // Объект LocalTime, полученный из строки "в 23 часа 45 минут и 49 секунд": 23:45:49
        /*
         * Документация по синтаксису форматирования времени:
         * - https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html (пункт Date and Time Patterns).
         * - https://divancoder.ru/2017/12/simpledateformat/ - подробная таблица.
         * */

        // Сдвиг (увеличение / уменьшение) времени.
        /*
         * Дату можно сдвинуть (в большую или меньшую сторону) на определённое количество часов, минут, секунд или
         * наносекунд.
         * - методы plus***() добавляют указанное количество выбранных единиц времени (отнимают, если аргумент
         *     отрицательный)
         * - методы minus***() отнимают указанное количество выбранных единиц времени (прибавляют, если значение
         *     отрицательное)
         * */
        currentTime = LocalTime.now();
        System.out.println("Текущая дата минус 1 час: " + currentTime.minusHours(1));
        // Текущая дата минус 1 час: 20:40:46.021775400
        System.out.println("Текущее время минус 40 минут: " + currentTime.minusMinutes(40));
        // Текущее время минус 40 минут: 21:00:46.021775400
        System.out.println("Текущее время минус 1465 секунд: " + currentTime.minusSeconds(1465));
        // Текущее время минус 1465 секунд: 21:16:21.021775400
        System.out.println("Текущее время минус -123 456 789 наносекунд: " + currentTime.minusNanos(-123_456_789));
        // Текущее время минус -123 456 789 наносекунд: 21:40:46.145232189
        System.out.println("Текущее время плюс 11 часов: " + currentTime.plusHours(11));
        // Текущее время плюс 11 часов: 08:40:46.021775400
        System.out.println("Текущее время плюс 255 минут: " + currentTime.plusMinutes(22));
        // Текущее время плюс 255 минут: 22:02:46.021775400
        System.out.println("Текущее время плюс 9999 секунд: " + currentTime.plusSeconds(123));
        // Текущее время плюс 9999 секунд: 21:42:49.021775400
        System.out.println("Текущее время плюс -10 000 000 000 наносекунд: " + currentTime.plusNanos(-10_000_000_000L));
        // Текущее время плюс -10 000 000 000 наносекунд: 21:40:36.021775400
        /*
         * Каждый из методов plus***() и minus***() возвращает объект того же класса LocalTime.
         * Соответственно, методы можно вызывать по цепочке:
         * */
        System.out.println("Текущее время плюс 1 час, 22 минуты, 333 секунды и 4444 наносекунды: " +
                currentTime.plusHours(1).plusMinutes(22).plusSeconds(333).plusNanos(4444));
        // Текущее время плюс 1 час, 22 минуты, 333 секунды и 4444 наносекунды: 23:08:19.021779844

        // Методы сравнения двух моментов времени:
        /*
         * В классе LocalTime есть три метода для сравнения двух дат:
         * - equals() - возвращает true, если вызывающий объект и агрумент метода представляют одно и то же время.
         * - isAfter() - возвращает true, если вызывающий объект (время) идёт после времени в аргументе.
         * - isBefore() - возвращает true, если вызывающий объект (время) идёт до времени в аргументе.
         * */
        LocalTime original = LocalTime.of(20, 30, 10);
        LocalTime copy = LocalTime.of(20, 30, 10);
        LocalTime different = LocalTime.of(23, 10, 56);
        System.out.println("\"" + original + "\".equals(\"" + copy + "\") = " + original.equals(copy));
        // "20:30:10".equals("20:30:10") = true
        System.out.println("\"" + copy + "\".equals(\"" + original + "\") = " + copy.equals(original));
        // "20:30:10".equals("20:30:10") = true
        System.out.println("\"" + original + "\".equals(\"" + different + "\") = " + original.equals(different));
        // "20:30:10".equals("23:10:56") = false
        System.out.println("\"" + different + "\".equals(\"" + original + "\") = " + different.equals(original));
        // "23:10:56".equals("20:30:10") = false
        System.out.println("\"" + original + "\".isAfter(\"" + copy + "\") = " + original.isAfter(copy));
        // "20:30:10".isAfter("20:30:10") = false
        System.out.println("\"" + original + "\".isAfter(\"" + different + "\") = " + original.isAfter(different));
        // "20:30:10".isAfter("23:10:56") = false
        System.out.println("\"" + different + "\".isAfter(\"" + original + "\") = " + different.isAfter(original));
        // "23:10:56".isAfter("20:30:10") = true
        System.out.println("\"" + original + "\".isBefore(\"" + copy + "\") = " + original.isBefore(copy));
        // "20:30:10".isBefore("20:30:10") = false
        System.out.println("\"" + original + "\".isBefore(\"" + different + "\") = " + original.isBefore(different));
        // "20:30:10".isBefore("23:10:56") = true
        System.out.println("\"" + different + "\".isBefore(\"" + original + "\") = " + different.isBefore(original));
        // "23:10:56".isBefore("20:30:10") = false

        // Вычисление разницы между двумя моментами времени:
        LocalTime first = LocalTime.of(20, 30, 10, 123456789);
        LocalTime second = LocalTime.of(23, 10, 56, 987654321);
        // С помощью ChronoUnit:
        System.out.println("Количество часов между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.HOURS.between(first, second));
        // Количество часов между "20:30:10.123456789" и "23:10:56.987654321": 2
        System.out.println("Количество часов между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.HOURS.between(second, first));
        // Количество часов между "23:10:56.987654321" и "20:30:10.123456789": -2
        System.out.println("Количество минут между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MINUTES.between(first, second));
        // Количество минут между "20:30:10.123456789" и "23:10:56.987654321": 160
        System.out.println("Количество минут между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.MINUTES.between(second, first));
        // Количество минут между "23:10:56.987654321" и "20:30:10.123456789": -160
        System.out.println("Количество секунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.SECONDS.between(first, second));
        // Количество секунд между "20:30:10.123456789" и "23:10:56.987654321": 9646
        System.out.println("Количество секунд между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.SECONDS.between(second, first));
        // Количество секунд между "23:10:56.987654321" и "20:30:10.123456789": -9646
        System.out.println("Количество миллисекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MILLIS.between(first, second));
        // Количество миллисекунд между "20:30:10.123456789" и "23:10:56.987654321": 9646864
        System.out.println("Количество миллисекунд между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.MILLIS.between(second, first));
        // Количество миллисекунд между "23:10:56.987654321" и "20:30:10.123456789": -9646864
        System.out.println("Количество микросекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MICROS.between(first, second));
        // Количество микросекунд между "20:30:10.123456789" и "23:10:56.987654321": 9646864197
        System.out.println("Количество микросекунд между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.MICROS.between(second, first));
        // Количество микросекунд между "23:10:56.987654321" и "20:30:10.123456789": -9646864197
        System.out.println("Количество наносекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.NANOS.between(first, second));
        // Количество наносекунд между "20:30:10.123456789" и "23:10:56.987654321": 9646864197532
        System.out.println("Количество наносекунд между \"" + second + "\" и \"" + first + "\": " +
                ChronoUnit.NANOS.between(second, first));
        // Количество наносекунд между "23:10:56.987654321" и "20:30:10.123456789": -9646864197532
        // ChronoUnit.DAYS.between(first,second);
        /*
         * использование других единиц времени, не входящих в LocalTime (например дней) приведёт к исключению
         * java.time.temporal.UnsupportedTemporalTypeException
         * */

        // С помощью Duration:
        System.out.println("Количество часов между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toHours());
        // Количество часов между "20:30:10.123456789" и "23:10:56.987654321": 2
        System.out.println("Количество часов между \"" + second + "\" и \"" + first + "\": " +
                Duration.between(second, first).toHours());
        // Количество часов между "23:10:56.987654321" и "20:30:10.123456789": -2
        System.out.println("Количество минут между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toMinutes());
        // Количество минут между "20:30:10.123456789" и "23:10:56.987654321": 160
        System.out.println("Количество минут между \"" + second + "\" и \"" + first + "\": " +
                Duration.between(second, first).toMinutes());
        // Количество минут между "23:10:56.987654321" и "20:30:10.123456789": -160
        System.out.println("Количество секунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toSeconds());
        // Количество секунд между "20:30:10.123456789" и "23:10:56.987654321": 9646
        System.out.println("Количество секунд между \"" + second + "\" и \"" + first + "\": " +
                Duration.between(second, first).toSeconds());
        // Количество секунд между "23:10:56.987654321" и "20:30:10.123456789": -9647
        System.out.println("Количество миллисекунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toMillis());
        // Количество миллисекунд между "20:30:10.123456789" и "23:10:56.987654321": 9646864
        System.out.println("Количество миллисекунд между \"" + second + "\" и \"" + first + "\": " +
                Duration.between(second, first).toMillis());
        // Количество миллисекунд между "23:10:56.987654321" и "20:30:10.123456789": -9646864
        System.out.println("Количество наносекунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toNanos());
        // Количество наносекунд между "20:30:10.123456789" и "23:10:56.987654321": 9646864197532
        System.out.println("Количество наносекунд между \"" + second + "\" и \"" + first + "\": " +
                Duration.between(second, first).toNanos());
        // Количество наносекунд между "23:10:56.987654321" и "20:30:10.123456789": -9646864197532
        System.out.println("Разница секунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).getSeconds());
        // Разница секунд между "20:30:10.123456789" и "23:10:56.987654321": 9646
        System.out.println("Разница наносекунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).getNano());
        // Разница наносекунд между "20:30:10.123456789" и "23:10:56.987654321": 864197532
        /*
         * ChronoUnit представляет общее количество желаемых единиц измерения времени между двумя моментами времени.
         * Duration хранит период времени между двумя моментами времени в формате N секунд + M наносекунд + K дней. Его
         * методы get***() возвращают значение соответствующего поля этого периода, но в отличие от Period, класс
         * Duration умеет приводить хранимый диапазон к другим единицам изменения.
         * */

        // Форматированный вывод времени:
        currentTime = LocalTime.now();
        System.out.println("Текущее время в формате по умолчанию: " + currentTime);
        // Текущее время в формате по умолчанию: 22:11:47.888492
        /*
         * Форматирование времени в виде строки осуществляется методом format() объекта DateTimeFormatter, который
         * предвариетльно нужно создать с использованием интересующего шаблона форматирования.
         * При составлении шаблона форматирования необходимо использовать синтаксис форматирования даты и времени. Все
         * символы, не зарезервированные в синтаксисе, будут использованы как есть.
         * */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("в H ч. и mm мин.");
        System.out.println("Текущее время в формате \"в H ч. и mm мин.\": " + formatter.format(currentTime));
        // Текущее время в формате "в H ч. и mm мин.": в 22 ч. и 11 мин.
    }

    // Работа только с датой и временем в классе LocalDateTime. Без учёта временных зон. (Примеры).
    public static void localDateTimeDemo() {
        /*
         * Класс LocalDateTime позволяет работать с датой и временем, без учёта временных зон (часовых поясов).
         * Класс LocalDateTime является композицией классов LocalDate и LocalTime, для хранения, соответственно, даты и
         * времени. Поэтому в нём объединено всё, что было описано выше про эти два класса.
         * */

        // Получение текущей даты и времени
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Текущие дата и время: " + now); // Текущие дата и время: 2020-04-03T23:29:34.413360700
        /*
         * Используется текущая временная зона.
         * Чтобы указать другую временную зону, нужно передать её UD в метод now().
         * */
        String timeZone = "Pacific/Auckland";
        now = LocalDateTime.now(ZoneId.of(timeZone));
        System.out.println("Текущие дата и время в " + timeZone + ": " + now);
        // Текущие дата и время в Pacific/Auckland: 2020-04-04T09:29:34.414359600

        // Установка определённой даты и времени.
        LocalDateTime dateTime = LocalDateTime.of(2018, Month.OCTOBER, 20, 15, 45);
        System.out.println("Установленные дата и время: " + dateTime);
        // Установленные дата и время: 2018-10-20T15:45
        /*
         * Фабричный метод of() для создания объекта LocalDateTime имеет ряд вариантов. LocalDateTime можно собрать из:
         * - LocalDate и LocalTime
         * - года, месяца, дня, часа и минуты
         * - то же плюс секунды
         * - то же плюс наносекунды
         * В качестве месяца можно использовать и целое число и константу из класса Month. Лучше второй вариант.
         *
         * При использовании некорректных значений метод выбросит исключение java.time.DateTimeException
         * */
        dateTime = LocalDateTime.of(LocalDate.of(2019, Month.FEBRUARY, 11),
                LocalTime.of(12, 14, 44, 1));
        System.out.println("Установленные дата и время: " + dateTime);
        // Установленные дата и время: 2019-02-11T12:14:44.000000001

        // Получение даты и времени из строки:
        String dateTimeString = "2017-05-14T23:10:00"; // стандартный формат даты-времени
        System.out.println("Объект LocalDateTime, полученный из строки \"" + dateTimeString + "\": " +
                LocalDateTime.parse(dateTimeString));
        // Объект LocalDateTime, полученный из строки "2017-05-14T23:10:00": 2017-05-14T23:10
        /*
         * Заметьте, что незначимое количество секунд (00) было проигнорировано при выводе.
         * */
        /*
         * По умолчанию метод parse() может распознать (распарсить) строку в формате "yyyy-MM-ddTHH:mm:ss", где:
         * - yyyy - год,
         * - MM - месяц (числом),
         * - dd - день,
         * - T - разделитель даты и времени,
         * - HH - часы (в 24-часовом формате),
         * - mm - минуты,
         * - ss - секунды.
         * Если нужно распознать строку иного формата, то в метод parse() нужно передать второй аргумент: формат
         * переданной строки со временем. Например:
         * */
        dateTimeString = "22 декабря 2009 в 15 00";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH mm");
        /*
         * Устанавливаем формат даты и времени используя те же синтаксические конструкции. Все символы, не используемые
         * в синтаксисе DateTimeFormatter, при парсинге будут пропущены. Но для корректного парсинга в шаблоне формата
         * их указывать необходимо.
         * */
        System.out.println("Объект LocalDateTime, полученный из строки \"" + dateTimeString + "\": " +
                LocalDateTime.parse(dateTimeString, format));
        // Объект LocalDateTime, полученный из строки "22 декабря 2009 в 15 00": 2009-12-22T15:00
        /*
         * Документация по синтаксису форматирования даты и времени:
         * - https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html (пункт Date and Time Patterns).
         * - https://divancoder.ru/2017/12/simpledateformat/ - подробная таблица.
         * */

        // Сдвиг (увеличение/уменьшение) даты и времени.
        /*
         * Все методы сдвига plus***(), minus***(), аналогичные упомянутым методам классов LocalDate и LocalTime, также
         * определены в LocalDateTime. Аналогично, сдвинуть можно некоторое количество любой единицы измерения времени.
         * Аналогично, с учётом знака.
         *
         * Каждый из этих методов возвращает объект того же класса LocalDateTime, поэтому их можно вызывать по цепочке:
         * */
        System.out.println("Текущее время плюс 1 год, минус 2 месяца, плюс 6 недель, минус 24 дня, плюс 120 часов, " +
                "минус 720 минут, плюс 5 040 секунд, минус 40320 наносекунд: " +
                LocalDateTime.now().plusYears(1).minusMonths(2).plusWeeks(6).minusDays(24).
                        plusHours(120).minusMinutes(720).plusSeconds(5_040).minusNanos(40_320));
        /*
         * Текущее время плюс 1 год, минус 2 месяца, плюс 6 недель, минус 24 дня, плюс 120 часов, минус 720 минут,
         * плюс 5 040 секунд, минус 40320 наносекунд: 2021-02-26T13:31:52.059873380
         * */

        // Сравнение двух записей даты-времени
        /*
         * Метод isEqual() возвращает true, если совпадает и дата и время у вызывающего объекта и аргумента метода.
         * Метод isAfter() возвращает true, если вызывающий объект на шкале времени находится после аргумента метода.
         * Метод isBefore() возвращает true, если вызывающий объект на шкале времени находится перед аргументом метода.
         * */
        LocalDateTime before = LocalDateTime.of(2018, Month.DECEMBER, 20, 10, 20);
        LocalDateTime after = before.plusNanos(1);
        System.out.println("\"" + before + "\".isEqual(\"" + after + "\") = " + before.isEqual(after));
        // "2018-01-02".isEqual("2018-01-02") = true
        System.out.println("\"" + before + "\".isEqual(\"" + after.minusNanos(1) + "\") = " +
                before.isEqual(after.minusNanos(1)));
        // "2018-01-02".isEqual("2018-01-02") = true
        System.out.println("\"" + before + "\".isAfter(\"" + after + "\") = " + before.isAfter(after));
        // "2018-12-20T10:20".isAfter("2018-12-20T10:20:00.000000001") = false
        System.out.println("\"" + after + "\".isAfter(\"" + before + "\") = " + after.isAfter(before));
        // "2018-12-20T10:20:00.000000001".isAfter("2018-12-20T10:20") = true
        System.out.println("\"" + after.minusNanos(1) + "\".isAfter(\"" + before + "\") = " +
                after.minusNanos(1).isAfter(before));
        // "2018-12-20T10:20".isAfter("2018-12-20T10:20") = false
        System.out.println("\"" + before + "\".isBefore(\"" + after + "\") = " + before.isBefore(after));
        // "2018-12-20T10:20".isBefore("2018-12-20T10:20:00.000000001") = true
        System.out.println("\"" + before + "\".isBefore(\"" + after.minusNanos(1) + "\") = " +
                before.isBefore(after.minusNanos(1)));
        // "2018-12-20T10:20".isBefore("2018-12-20T10:20") = false

        // Вычисление разницы между двумя моментами времени:
        LocalDateTime first = LocalDateTime.of(1949, Month.JULY, 10, 10, 20);
        LocalDateTime second = LocalDateTime.of(2067, Month.MAY, 1, 22, 30, 44);
        /*
         * С использованием ChronoUnit вычисления аналогичны классам LocalDate и LocalTime. СhronoUnit представляет
         * общее количество желаемых единиц измерения времени между двумя моментами времени.
         * */
        System.out.println("Количество эр между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.ERAS.between(first, second));
        // Количество эр между "1949-07-10T10:20" и "2067-05-01T22:30:44": 0
        System.out.println("Количество тысячелетий между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MILLENNIA.between(first, second));
        // Количество тысячелетий между "1949-07-10T10:20" и "2067-05-01T22:30:44": 0
        System.out.println("Количество столетий между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.CENTURIES.between(first, second));
        // Количество столетий между "1949-07-10T10:20" и "2067-05-01T22:30:44": 1
        System.out.println("Количество десятилетий между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.DECADES.between(first, second));
        // Количество десятилетий между "1949-07-10T10:20" и "2067-05-01T22:30:44": 11
        System.out.println("Количество лет между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.YEARS.between(first, second));
        // Количество лет между "1949-07-10T10:20" и "2067-05-01T22:30:44": 117
        System.out.println("Количество месяцев между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MONTHS.between(first, second));
        // Количество месяцев между "1949-07-10T10:20" и "2067-05-01T22:30:44": 1413
        System.out.println("Количество недель между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.WEEKS.between(first, second));
        // Количество недель между "1949-07-10T10:20" и "2067-05-01T22:30:44": 6147
        System.out.println("Количество дней между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.DAYS.between(first, second));
        // Количество дней между "1949-07-10T10:20" и "2067-05-01T22:30:44": 43029
        System.out.println("Количество полу-дней между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.HALF_DAYS.between(first, second));
        // Количество полу-дней между "1949-07-10T10:20" и "2067-05-01T22:30:44": 86059
        System.out.println("Количество часов между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.HOURS.between(first, second));
        // Количество часов между "1949-07-10T10:20" и "2067-05-01T22:30:44": 1032708
        System.out.println("Количество минут между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MINUTES.between(first, second));
        // Количество минут между "1949-07-10T10:20" и "2067-05-01T22:30:44": 61962490
        System.out.println("Количество секунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.SECONDS.between(first, second));
        // Количество секунд между "1949-07-10T10:20" и "2067-05-01T22:30:44": 3717749444
        System.out.println("Количество миллисекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MILLIS.between(first, second));
        // Количество миллисекунд между "1949-07-10T10:20" и "2067-05-01T22:30:44": 3717749444000
        System.out.println("Количество микросекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.MICROS.between(first, second));
        // Количество микросекунд между "1949-07-10T10:20" и "2067-05-01T22:30:44": 3717749444000000
        System.out.println("Количество наносекунд между \"" + first + "\" и \"" + second + "\": " +
                ChronoUnit.NANOS.between(first, second));
        // Количество наносекунд между "1949-07-10T10:20" и "2067-05-01T22:30:44": 3717749444000000000

        /*
         * Для использования Period нужно привести объект LocalDateTime к типу LocalDate с помощью метода
         * toLocalDate(). Записи времени при этом будут отброшены, останется только дата.
         *
         * Далее можно использовать те же методы класса Period, что были показаны при демонстрации класса LocalDate.
         * */
        System.out.println("Разница лет между \"" + first.toLocalDate() + "\" и \"" + second.toLocalDate() + "\": " +
                Period.between(first.toLocalDate(), second.toLocalDate()).getYears());
        // Разница лет между "1949-07-10" и "2067-05-01": 117
        System.out.println("Разница месяцев между \"" + first.toLocalDate() + "\" и \"" + second.toLocalDate() + "\":" +
                " " +
                Period.between(first.toLocalDate(), second.toLocalDate()).getMonths());
        // Разница месяцев между "1949-07-10" и "2067-05-01": 9
        System.out.println("Разница дней между \"" + first.toLocalDate() + "\" и \"" + second.toLocalDate() + "\": " +
                Period.between(first.toLocalDate(), second.toLocalDate()).getDays());
        // Разница дней между "1949-07-10" и "2067-05-01": 21

        /*
         * Для использования Duration можно продолжать использовать объект LocalDateTime.
         * Можно использовать те же методы класса Duration, что были показаны при демонстрации класса LocalTime.
         * */
        System.out.println("Количество дней между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toDays());
        // Количество дней между "1949-07-10T10:20" и "2067-05-01T22:30:44": 43029
        System.out.println("Количество часов между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).toHours());
        // Количество часов между "1949-07-10T10:20" и "2067-05-01T22:30:44": 1032708
        System.out.println("Разница секунд между \"" + first + "\" и \"" + second + "\": " +
                Duration.between(first, second).getSeconds());
        // Разница секунд между "1949-07-10T10:20" и "2067-05-01T22:30:44": 3717749444

        /*
         * Если всё же требуетя привести LocalDateTime к LocalTime, то для этого есть соответствующий метод
         * toLocalTime(). Данные о дате при этом будут отброшены, останутся только данные о времени.
         * */
        System.out.println("Разница секунд между \"" + first.toLocalTime() + "\" и \"" + second.toLocalTime() +
                "\": " + Duration.between(first.toLocalTime(), second.toLocalTime()).getSeconds());
        // Разница секунд между "10:20" и "22:30:44": 43844


        // Форматированный вывод даты и времени
        now = LocalDateTime.now();
        System.out.println("Текущие дата и время в формате по умолчанию: " + now);
        // Текущие дата и время в формате по умолчанию: 2020-04-04T00:57:59.115897800
        /*
         * Форматирование даты и времени в виде строки осуществляется методом format() объекта DateTimeFormatter,
         * который предварительно нужно создать с использованием интересующего шаблона форматрирования.
         * При составлении шаблона форматрирования необходимо использовать синтаксис форматирования даты и времени. Все
         * символы, не зарезервированные в синтаксисе, будут использованы как есть.
         * */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy в HH:mm");
        System.out.println("Текущие дата и время в формате \"dd MMMM yyyy в HH:mm\": " + formatter.format(now));
        // Текущие дата и время в формате "dd MMMM yyyy в HH:mm": 04 апреля 2020 в 00:57
    }

    // Работа с временными зонами в классе ZonedDateTime. (Примеры)
    public static void zonedDateTimeDemo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("eeee, dd MMMM yyyy г. HH:mm:ss (O)");
        DateTimeFormatter localFormatter = DateTimeFormatter.ofPattern("eeee, dd MMMM yyyy г. HH:mm:ss");
        /*
         * Класс ZonedDateTime позволяет работать с датой и временем учитывая часовые пояса (временные зоны /
         * таймзоны).
         * Класс ZonedDateTime является композицией классов LocalDateTime и ZoneId, т.е. локальной даты-времени и
         * указателя часового пояса (таймзоны).
         * Класс ZoneId описывает часовой пояс, и идентифицируется по строковому представлению, например
         * "Europe/Moscow".
         * Чтобы просмотреть список всех существующих таймзон нужно выполнить следующий код:
         *     ZoneId.getAvailableZoneIds().forEach(System.out::println);
         * Метод getAvailableZoneIds() возвращает коллекцию Set<String>.
         * Любая коллекция итерируемая, поэтому у неё можно вызвать метод итерации по коллекции forEach.
         * А в него передаётся метод println который нужно применить для каждого элемента коллекции
         * */

        // Получить текущее время в некоторой таймзоне:
        String timeZoneNY = "America/New_York";
        String timeZoneMSK = "Europe/Moscow";
        ZonedDateTime now = ZonedDateTime.now(); // в таймзоне по умолчанию
        System.out.println("Сейчас: " + formatter.format(now));
        // Сейчас: суббота, 04 апреля 2020 г. 03:04:53 (GMT+3)
        now = ZonedDateTime.now(ZoneId.of(timeZoneNY));
        System.out.println("В " + timeZoneNY + " сейчас: " + formatter.format(now));
        // В America/New_York сейчас: пятница, 03 апреля 2020 г. 20:05:48 (GMT-4)
        /*
         * Чтобы привязать объект LocalDateTime к временной зоне необходимо использовать его метод atZone() с
         * соответствующим аргументом типа ZoneId
         * */
        LocalDateTime nowLocal = LocalDateTime.now();
        System.out.println("Текущее локальное время: " + localFormatter.format(nowLocal));
        // Текущее локальное время: суббота, 04 апреля 2020 г. 03:05:48
        ZonedDateTime nowMsk = nowLocal.atZone(ZoneId.of(timeZoneMSK));
        System.out.println("В " + nowMsk.getZone() + " сейчас: " + formatter.format(nowMsk));
        // В Europe/Moscow сейчас: суббота, 04 апреля 2020 г. 03:05:48 (GMT+3)
        /*
         * Чтобы определить ZonedDateTime в другом часовом поясе относительно существующего объекта ZonedDateTime
         * необходимо использовать метод withZoneSameInstant() с соответствующим аргументом типа ZoneId
         * */
        ZonedDateTime nowNY = nowMsk.withZoneSameInstant(ZoneId.of(timeZoneNY));
        System.out.println("В " + nowNY.getZone() + " сейчас: " + formatter.format(nowNY));
        // В America/New_York сейчас: пятница, 03 апреля 2020 г. 20:05:48 (GMT-4)

        /*
         * Класс ZonedDateTime поддерживает те же методы, что и LocalDateTime, продемонстрированные выше.
         * Например
         * */
        System.out.println("В " + timeZoneNY + " через 4.5 часа будет: " +
                nowNY.plusHours(4).plusMinutes(30).format(formatter));
        // В America/New_York через 4.5 часа будет: суббота, 04 апреля 2020 г. 00:38:52 (GMT-4)
    }
}
