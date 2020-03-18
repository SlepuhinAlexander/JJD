package ru.ifmo.jjd.exercises.lesson02;

import static ru.ifmo.jjd.utils.ConsoleHelper.*;

import java.util.Formatter;

/*
 * Электронные часы показывают время в формате от 00:00 до 23:59. Подсчитать сколько раз за сутки случается так, что
 * слева от двоеточия показывается симметричная комбинация для той, что справа от двоеточия
 * (например, 02:20, 11:11 или 15:51).
 * */
public class Exercise12 {
    public static void main(String[] args) {
        int counter = 0;
        Formatter f = new Formatter();
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                if (i % 10 * 10 + i / 10 == j) {
                    f.format("%02d:%02d\n", i, j);
                    counter++;
                }
            }
        }
        println("В течение суток симметричное время показывается " + counter + " раз");
        println(f.toString());
    }
}
/*
 * output:
 * В течение суток симметричное время показывается 16 раз
 * 00:00
 * 01:10
 * 02:20
 * 03:30
 * 04:40
 * 05:50
 * 10:01
 * 11:11
 * 12:21
 * 13:31
 * 14:41
 * 15:51
 * 20:02
 * 21:12
 * 22:22
 * 23:32
 * */
