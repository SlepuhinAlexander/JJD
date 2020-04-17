package ru.ifmo.jjd.exercises.e13exceptions.ex02;

public class Exceptions {
    final Exception[] exceptions = new Exception[9];

    public void add(Exception e) {
        for (int i = 0; i < exceptions.length; i++) {
            if (exceptions[i] == null && e != null) {
                exceptions[i] = e;
                break;
            }
        }
    }
}
