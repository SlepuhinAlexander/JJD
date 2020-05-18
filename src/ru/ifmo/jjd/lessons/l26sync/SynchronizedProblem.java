package ru.ifmo.jjd.lessons.l26sync;

public class SynchronizedProblem {
    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " запущен...");
            synchronized (object1) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " работает с object1");
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + " работает с object2");
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " запущен...");
            synchronized (object2) {
                try {
                    Thread.sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " работает с object2");
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + " работает с object1");
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
/*
 * Thread-0 запущен...
 * Thread-1 запущен...
 * Thread-0 работает с object1
 * Thread-1 работает с object2
 * */

/*
 * Первый поток захватывает объект1
 * Параллельно второй поток захватывает объект2
 * К тому моменту, когда первому потоку потребуется поработать с объектом2, он всё ещё будет заблокирован
 * К тому моменту, когда второму потоку потребуется поработать с объектом1, он всё ещё будет заблокирован
 * Оба объекта останутся заблокированы бесконечно.
 * Такая ситуация называется deadlock - смертельная взаимная блокировка.
 * */