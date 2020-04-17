package ru.ifmo.jjd.exercises.e13exceptions.ex02;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Exceptions exceptionsList = new Exceptions();
        try { // ArithmeticException
            int a = 17;
            a = a / (a - a);
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // ArrayStoreException
            Object[] x = new String[2];
            x[0] = 1;
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // ClassCastException
            Object x = 1;
            System.out.println((String) x);
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // NegativeArraySizeException
            int[] x = new int[-1];
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // ArrayIndexOutOfBoundsException
            int[] n = new int[2];
            n[-1] = 1;
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // StringIndexOutOfBoundsException
            String s = "123";
            System.out.println(s.charAt(12));
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // NullPointerException
            String s = null;
            System.out.println(s.isBlank());
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // DateTimeException
            LocalDateTime dateTime = LocalDateTime.of(2000, -1, 0, 22, 20);
        } catch (Exception e) {
            exceptionsList.add(e);
        }
        try { // NumberFormatException
            Integer d = Integer.parseInt("12.2");
        } catch (Exception e) {
            exceptionsList.add(e);
        }


        for (Exception exception : exceptionsList.exceptions) {
            if (exception != null) {
                System.out.println(exception.toString());
            } else {
                break;
            }
        }
    }
}
/*
 * java.lang.ArithmeticException: / by zero
 * java.lang.ArrayStoreException: java.lang.Integer
 * java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String (java.lang.Integer
 *     and java.lang.String are in module java.base of loader 'bootstrap')
 * java.lang.NegativeArraySizeException: -1
 * java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 2
 * java.lang.StringIndexOutOfBoundsException: String index out of range: 12
 * java.lang.NullPointerException
 * java.time.DateTimeException: Invalid value for MonthOfYear (valid values 1 - 12): -1
 * java.lang.NumberFormatException: For input string: "12.2"
 * */