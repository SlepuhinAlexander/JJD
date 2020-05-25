package ru.ifmo.jjd.lessons.l28executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadsPool {
    public static void main(String[] args) {
        /*
         * При создании объекта пула потоков используются фабричные методы класса Executors.
         * */
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);
        /*
         * newFixedThreadPool() - Фиксированное количество потоков в пуле. Не меняется после создания.
         * */

        /*
         * Чтобы передать задачу на выполнение используется метод execute().
         * Это не значит, что задача будет выполнена незамедлительно, она будет добавлена в очередь.
         * */
        for (int i = 0; i < 10; i++) {
            fixedPool.execute(new RunnableTask("fixedPool на 2 потока"));
        }
        /*
         * Пул обработает все задачи и будет продолжать ожидать новых задач. Работа программы не прекратится.
         * Чтобы остановить ожидание новых задач, нужно пул - выключить. Это делает метод shutdown().
         * */
        fixedPool.shutdown();
        /*
         * После выполнения метода shutdown() пул не будет принимать новых задач (будет бросать исключение).
         * Далее пул завершает выполнение всех задач из своей очереди и прекращает работу.
         * */

        // List<Runnable> runnables = fixedPool.shutdownNow();
        /*
         * Метод shutdownNow() прерывает выполнение задач, возвращает список задач, которые пул не успел выполнить
         * И затем завершает работу.
         * */

        ExecutorService singleThread = Executors.newSingleThreadExecutor();
        /*
         * Создаёт пул на один поток.
         * Складывает задачи в очереди, выполняет последовательно.
         * */
        singleThread.execute(new RunnableTask("singleThread"));
        singleThread.execute(new RunnableTask("singleThread task 2"));
        singleThread.shutdown();

        // отложенное выполнение
        /*
         * Есть экзекьюторы, которые могут запускать задачи по расписанию: через какой-то таймаут; или раз в какой-то
         * конкретный интервал времени.
         * */
        ScheduledExecutorService dService = Executors.newSingleThreadScheduledExecutor();

        // выполнение через указанное время
        dService.schedule(new RunnableTask("dService"), 5, TimeUnit.SECONDS);
        /*
         * Аналогично, экзекьютор добавит задачу в очередь, только тут используется DelayedQueue, чтобы брать задачи
         * из очереди в нужное время.
         * */
        dService.shutdown();

        // выполнение с указанной частотой
        ScheduledExecutorService everyFiveSecondService = Executors.newSingleThreadScheduledExecutor();
        everyFiveSecondService.scheduleAtFixedRate(new RunnableTask("everyFiveSecondService"),
                0, 5, TimeUnit.SECONDS);
        /*
         * Метод ожидает задачу, первичную задержку (перед первым запуском задачи), затем периодичность и единицу
         * измерения.
         *
         * Задержка между запусками всегда считается от начала запуска последней задачи.
         * Время выполнения задачи никак не учитывается.
         *
         * Если предыдущая задача не успела завершиться, пул ждёт (если в пуле один поток).
         * */

        ScheduledExecutorService oneSecondTimeout = Executors.newSingleThreadScheduledExecutor();
        oneSecondTimeout.scheduleWithFixedDelay(new RunnableTask("oneSecondTimeout"),
                0, 1, TimeUnit.SECONDS);
        /*
         * Метод ожидает задачу, первичную задержку, задержку между задачами и единицу измерения.
         * В данном методе период времени до запуска нового потока уже считается от завершения предыдущего запуска.
         * */
    }
}

/*
 * Задачи для пула потоков должны реализовывать интерфейс Runnable
 * В пул потоков передаются инструкции (задачи), а не потоки. Т.е. для создания задачи нельзя наследоваться от Thread.
 * */
class RunnableTask implements Runnable {
    private final String poolName;

    public RunnableTask(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public void run() {
        System.out.println("Поток " + Thread.currentThread().getName() + " из пула " + poolName);
    }
}