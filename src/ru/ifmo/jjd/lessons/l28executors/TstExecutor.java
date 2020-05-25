package ru.ifmo.jjd.lessons.l28executors;

import java.util.concurrent.*;

// собственная реализация пула потоков.
public class TstExecutor {
    public static void main(String[] args) {
        /*
         * Конструктор ожидает:
         * - начальное количество потоков
         * - максимальное количество потоков
         *      при необходимости, количество потоков будет увеличиваться до указанного максимума
         * - максимальная продолжительность простоя
         * - единица измерения
         *      если поток бездействует дольше указанного времени, он будет убран из пула.
         *      надо будет - вернётся.
         * - очередь для задач для этого пула потоков
         * */
        ExecutorService pool = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }
}

class SomeExecutor extends ThreadPoolExecutor {

    public SomeExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        System.out.println("опять работать? :(");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("дело сделано ^^");
    }
}