package ru.ifmo.jjd.lessons.l23lambdas;

@FunctionalInterface
interface Operation {
    /*
     * Пусть есть абстрактный метод без реализации.
     * Принимает две переменные типа double и возвращает результат double
     * */
    double execute(double a, double b);
}

public class LambdasDemo {
    public static void main(String[] args) {
        /*
         * Лямбда - это реализация метода интерфейса.
         * При это в интерфейсе должен быть ТОЛЬКО ОДИН абстрактный метод
         * default методов (с реализацией) может быть столько угодно.
         * */

        // Реализация метода с сохранением в переменную (с использованием лямбда-синтаксиса)
        // Operation operation = реализация абстрактного метода интерфейса

        /*
         * В скобках () указываются принимаемые аргументы метода
         *
         * затем через -> указывается реализация метода в {}
         * если в реализации только одно выражение, `{}` можно опустить.
         * */
        //noinspection Convert2MethodRef
        Operation plus = (a, b) -> a + b;

        Operation divide = (a, b) -> {
            if (b == 0) throw new IllegalArgumentException("div by 0");
            return a / b;
        };
        /*
         * В переменной plus сохранена одна реализация метода execute(double a, double b)
         * В переменной divide сохранена вторая реализация метода execute(double a, double b)
         * */


        /*
         * Правила работы с аргументами лямбда-выражения
         * - Количество аргументов в скобках, и в методе должно совпадать.
         * - Имена аргументов могут быть произвольные (не обязаны совпадать с именами аргументов в декларации метода в
         *      интерфейсе)
         * - Если у метода только один аргумент, заключать аргумент в () не обязательно.
         * - В остальных случаях скобки () должны быть. В том числе при нуле аргументов.
         * - Типы данных для аргументов можно не указывать, их типы данных берутся из контекста метода интерфейса.
         * */

        /*
         * За аргументами лямбда-выражения следует `->`. Всегда.
         * Затем следует тело лямбды.
         * */

        /*
         * Правила работы с телом лямбды:
         * - если реализация метода состоит только из одной инструкции и предполагается возвращать значение, то `{}`
         *      указывать не нужно. Тело будет иметь return по умолчанию.
         * - если инструкций больше чем одна, то {} использовать обязательно.
         *      в этом случае return по умолчанию - уже отсутствует; если метод должен возвращать значение - его
         *      нужно явно прописать в теле метода.
         * */

        // Использование лямбда-реализаций метода
        System.out.println(Calculator.calculate(3, 67, plus));
        System.out.println(Calculator.calculate(80, 2, divide));
        /*
         * В качестве реализации метода execute() будет подставлена реализация, написанная и сохранённая в переменную
         * plus.
         * */

        // Использования лямбда-выражения без сохранения в переменную
        /*
         * При необходимости, для однократного использования реализации, можно реализацию лямбды указать
         * непосредственно в используемом контексте. Например, в аргументе метода calculate.
         *
         * При этом должны использоваться все те же правила работы с лямбдами.
         * Для использования реализации лямбды неявно создаётся объект типа Operation
         * */
        System.out.println(Calculator.calculate(4, 4, (a, b) -> a - b));
    }

}


class Calculator {
    public static double calculate(double a, double b, Operation operation) {
        return operation.execute(a, b);
    }
}