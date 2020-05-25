package ru.ifmo.jjd.lessons.l27concurrent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueDemo {
    public static void main(String[] args) {
        // DelayQueue
        /*
         * Просто так положить объекты в такую очередь нельзя.
         * Добавляемый объект должен реализовывать интерфейс Delayed.
         * */
        Random random = new Random();
        DelayQueue<Actions> actions = new DelayQueue<>();
        actions.put(new Actions(LocalDateTime.now().plusSeconds(random.nextInt(10)), () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("new");
        }));
        actions.put(new Actions(LocalDateTime.now().minusSeconds(random.nextInt(10)), () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("old");
        }));

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                new Thread(actions.take().getRunnable()).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Actions implements Delayed {
    /*
     * Объект Actions хранит задачу и время.
     * Ожидаем, что при извлечении из очереди будет запущена задача в указанное время.
     * Элементы в очереди
     * */
    private final LocalDateTime dateTime;
    private final Runnable runnable;

    public Actions(LocalDateTime dateTime, Runnable runnable) {
        this.dateTime = Objects.requireNonNull(dateTime);
        this.runnable = Objects.requireNonNull(runnable);
    }

    // для определения, можно получить элемент из очереди или нет
    /*
     * Если возвращает число <= 0 значит элемент извлекать можно.
     * Используется для того чтобы мог отработать метод take() очереди.
     * */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(Duration.between(LocalDateTime.now(), dateTime).toSeconds(), TimeUnit.SECONDS);
    }

    // для сортировки элементов в очереди
    @Override
    public int compareTo(Delayed o) {
        return dateTime.compareTo(((Actions) o).dateTime);
    }

    public Runnable getRunnable() {
        return runnable;
    }
}