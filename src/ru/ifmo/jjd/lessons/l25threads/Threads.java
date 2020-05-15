package ru.ifmo.jjd.lessons.l25threads;

public class Threads {
    public static void main(String[] args) {
        // получить текущий поток выполнения
        System.out.println("Основной поток: " + Thread.currentThread().getName()); // Основной поток: main

        // Варианты описания (создания потока):
        // 1. создать класс, который наследуется от класса Thread
        // 2. создать класс, который реализует интерфейс Runnable
        // 3. использовать лямбда-выражение, т.к. Runnable - функциональный интерфейс

        // вариант 1. создать класс-наследник Thread
        FirstThread firstThread = new FirstThread();
        /*
         * На данном моменте поток не запущен и не планирует запускаться.
         * */
        System.out.println("firstThread before start is " + firstThread.getState());
        // firstThread before start is NEW

        // метод start
        /*
         * Вызов метода start() - НЕ запуск потока, и НЕ вызов метода run()
         *
         * Вызов метода start() означает что поток готов к работе и дёт, когда его запустит шедулер.
         *
         * Шедулер (Scheduler) - это специальный объект, который управляет расписанием запуска потоков.
         * Только Шедулер определяет когда какой поток будет запущен или когда потоку будет передано управление.
         * */
        firstThread.start();

        // вариант 2: создание класса, имплементирующего Runnable
        Thread secondThread = new Thread(new SecondThread());
        secondThread.start();
        /*
         * Вызов метода start() имеет тот же смысл
         * */

        // вариант 3: реализация метода run() интерфейса Runnable через лямбда-выражение
        Thread thirdThread = new Thread(() -> System.out.println("thirdThread " + Thread.currentThread().getState()
                                                                 + " isAlive: " + Thread.currentThread().isAlive()));
        thirdThread.start();

        // Приоритеты потоков
        Thread threadOne = new Thread(new SecondThread());
        Thread threadTwo = new Thread(new SecondThread());
        Thread threadThree = new Thread(new SecondThread());

        // установка приориета
        threadOne.setPriority(Thread.MAX_PRIORITY); // 10
        threadOne.setPriority(9); // не может быть выше максимального допустимого потока группы.
        threadTwo.setPriority(Thread.MIN_PRIORITY); // 1
        threadThree.setPriority(Thread.NORM_PRIORITY); // 5
        System.out.println("Приоритет threadOne " + threadOne.getPriority()); // Приоритет threadOne 9

        // группы потоков
        /*
         * Все потоки принадлежат группам потоков.
         * Группы определяют настройки потоков. Например максимальный и минимальный приоритет.
         * По умолчанию все потоки принадлежат группе main.
         * */
        System.out.println("Группа потоков, к которой принадлежит threadOne " + threadOne.getThreadGroup());
        // Группа потоков, к которой принадлежит threadOne java.lang.ThreadGroup[name=main,maxpri=10]
        System.out.println("Максимальный приоритет группы main " + threadOne.getThreadGroup().getMaxPriority());
        // Максимальный приоритет группы main 10

        threadOne.start();
        threadTwo.start();
        threadThree.start();

        // метод join()
        /*
         * Допустим, нам нужно, чтобы основной поток (main) ждал, пока завершатся потоки threadOne / threadTwo /
         * threadThree
         * Для этого используется метод join()
         *
         * Вызывающий поток (в данном случае main) ожидает
         * кода указанный поток (в данном случае threadOne / threadTwo / threadThree присоединится к нему
         * (в вызывающему)
         *
         * метод может принимать аргумент в миллисекундах: как долго ждать завершения.
         * если без аргумента - будет ждать до конца потока.
         * */

        try {
            System.out.println("Основной поток ждёт завершения threadOne / threadTwo / threadThree");
            threadOne.join();
            threadTwo.join();
            threadThree.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
         * вызывающий поток - main
         * именно main будет ждать завершения threadOne и только потом продолжать работу
         * */

        System.out.println("Основной поток: " + Thread.currentThread().getName());

        // остановка потоков
        /*
         * Случаи остановки потоков:
         * 1. поток выполнил все инструкции и остановился, потому что делать ему больше нечего.
         * 2. в процессе работы потока было выброшено необработанное исключение или ошибка.
         * 3. остановилась JVM.
         * 4. если поток был daemon-потоком, и все не-daemon потоки завершили свою работу.
         * */

        // daemon-потоки (фоновые)
        FirstThread daemon = new FirstThread();
        daemon.setDaemon(true); // установка режима работы потока в daemon

        // механизм прерывания потоков
        /*
         * У потока есть boolean свойство interrupt: true/false
         * и есть метод interrupt(), который устанавливает значение на true
         * метод isInterrupted() проверяет этот флаг
         * */
        Thread actions = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("some actions...");
                try {
                    //noinspection BusyWait
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    /*
                     * При возникновении InterruptedException флаг interrupt меняется на обратный.
                     * (а он произошёл потому что извне было вызвано прерывание, когда этот поток спал)
                     * поэтому поток продолжит выполнение.
                     *
                     * Поэтому обязательно в catch нужно указывать Thread.currentThread().interrupt();
                     * */
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println("After the 'while'");
        });

        actions.start();
        try {
            // метод sleep()
            Thread.sleep(2000);
            /*
             * метод sleep() приостанавливает выполнение потока на указанное количество миллисекунд
             * */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        actions.interrupt();
        /*
         * из состояния isInterrupted() == true - вернуться нельзя.
         * поток выполнился (после цикла) и закончил свою работу (стал TERMINATED)
         *
         * завершённый поток перезапустить невозможно
         * */
    }
}

// первый вариант описания потока - наследование от Thread
/*
 * При создании объекта типа FirstThread будет создан новый поток.
 * Сколько будет создано объектов - столько потоков и будет создано.
 * */
class FirstThread extends Thread {
    /*
     * В методе run() должны быть описаны инструкции, которые должен выполнить этот отдельный поток.
     *
     * Когда будет вызван метод run инструкции начнут выполняться.
     * Вручную метод run() никогда не вызывается.
     * (не путать с вызовом метода start())
     * */
    @Override
    public void run() {
        // установим имя потоку
        Thread.currentThread().setName("FirstThread");
        System.out.println(Thread.currentThread().getName() + " is " + Thread.currentThread().getState());
        // FirstThread is RUNNABLE
    }
}

// второй вариант описания потока - имплементация Runnable
/*
 * Отличий в созданном потоке от первого варианта - никаких, это будет точно такой же поток.
 * Польза в том, что при имплементации Runnable остаётся свободным наследование: можно унаследоваться ещё от другого
 * класса.
 *
 * Имплементация от Runnable не значит создание потока. Поток - это всегда Thread.
 * Для создания потока этот Runnable нужно будет передать в Thread.
 * */
class SecondThread implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setName("SecondThread (Runnable)");
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}