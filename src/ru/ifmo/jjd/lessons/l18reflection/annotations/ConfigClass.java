package ru.ifmo.jjd.lessons.l18reflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // цель - класс
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigClass {
    // создаём дополнительную информацию об аннотации. По сути, это методы.
    String prefix();
    /*
     * при использовании аннотации необходимо будет указать значение для prefix
     * */

    long version() default 1L; //
    /*
     * при использовании аннотации можно будет не указывать значение для version - тогда будет использовано значение
     * по умолчанию.
     * */

    /*
     * В качестве типов данных можно использовать только строки, примитивы и enum-ы
     * */
}
