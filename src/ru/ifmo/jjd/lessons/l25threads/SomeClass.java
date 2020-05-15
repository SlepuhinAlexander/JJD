package ru.ifmo.jjd.lessons.l25threads;

import java.util.ArrayList;
import java.util.Scanner;

interface EventListener {
    void greenEvent(int code);

    void yellowEvent(int code);

    void redEvent(int code);
}

public class SomeClass {
    public static void main(String[] args) {
        // Анонимные классы
        /*
         * Анонимные классы - это классы без имени
         * Анонимные классы могут быть созданы на основе интерфейса или абстрактного класса
         * */
        EventListener firstListener = new /* создание объекта этого анонимного класса */ EventListener()
                /* начало анонимного класса */ {
            @Override
            public void greenEvent(int code) {
                System.out.println("реакция firstListener на " + code);
            }

            @Override
            public void yellowEvent(int code) {
                System.out.println("реакция firstListener на " + code);
            }

            @Override
            public void redEvent(int code) {
                System.out.println("реакция firstListener на " + code);
            }
        }/* конец анонимного класса */;

        EventListener secondListener = new EventListener() {
            @Override
            public void greenEvent(int code) {
                System.out.println("реакция secondListener на " + code);
            }

            @Override
            public void yellowEvent(int code) {
                System.out.println("реакция secondListener на " + code);
            }

            @Override
            public void redEvent(int code) {
                System.out.println("реакция secondListener на " + code);
            }
        };
        System.out.println(firstListener.getClass().getName()); // ru.ifmo.jjd.lessons.l25threads.SomeClass$1
        System.out.println(secondListener.getClass().getName()); // ru.ifmo.jjd.lessons.l25threads.SomeClass$2

        StateClass stateClass = new StateClass();
        stateClass.addListener(firstListener);
        stateClass.addListener(secondListener);

        Scanner scanner = new Scanner(System.in);
        String s;
        while (true) {
            System.out.print("введите статус или exit для выхода: ");
            s = scanner.nextLine();
            if ("exit".equalsIgnoreCase(s)) break;
            stateClass.changeState(s);
        }
    }
}

class StateClass {
    // список тех, кому StateClass должен рассылать уведомления
    private ArrayList<EventListener> eventListeners = new ArrayList<>();

    public void addListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    private void okNotify(int code) {
        eventListeners.forEach(listener -> listener.greenEvent(code));
    }

    private void warnNotify(int code) {
        eventListeners.forEach(listener -> listener.yellowEvent(code));
    }

    private void errorNotify(int code) {
        eventListeners.forEach(listener -> listener.redEvent(code));
    }

    // допустим, мы хотим реагировать на статусы ok / warn / error:
    // мы хотим, чтобы какие-то объекты, не связанные с объектом StateClass реагировали на изменение статуса
    public void changeState(String value) {
        if ("ok".equalsIgnoreCase(value)) okNotify(1);
        if ("warn".equalsIgnoreCase(value)) warnNotify(5);
        if ("error".equalsIgnoreCase(value)) errorNotify(10);
    }
}