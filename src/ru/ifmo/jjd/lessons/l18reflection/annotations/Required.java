package ru.ifmo.jjd.lessons.l18reflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * Аннотация создаётся так же как интерфейс, только перед ключевым словом interface указывается символ '@'
 *
 * Правила именования аннотаций такие же как классов, интерфейсов и т.д.
 *
 * Для того чтобы указать к чему аннотацию можно применить, нужно использовать аннотацию @Target()
 * Если одна цель - можно без фигурных скобок, если несколько - нужно заключить в фигурные скобки через запятую.
 *
 * Аннотация @Retention указывает на то, КОГДА будет использована аннотация: только в исходном коде; в исходном коде
 * плюс при компиляции, но не во время выполнения; и в исходном коде, и при компиляции и во время выполнения
 * */
@Target({ElementType.FIELD, ElementType.METHOD}) // применима только к полям, методам
@Retention(RetentionPolicy.RUNTIME) // применима во время выполнения
public @interface Required {
}
