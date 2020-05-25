package ru.ifmo.jjd.lessons.l27concurrent;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        // put -> [] -> get
        LinkedBlockingDeque<Signal> signals = new LinkedBlockingDeque<>();
        /*
         * Если использовать пустой конструктор, очередь не лимитирована. Записывающий поток может добавлять данные
         * неограниченно.
         * Если в конструктор передать число - оно и будет ограничением размера очереди.
         * */

        new Thread(new WriteSignals(signals)).start();
        new Thread(new ReadSignals(signals)).start();

        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<>(100);
        /*
         * Очередь ArrayBlockingQueue не может быть безлимитной, обязательно нужно указать размер очереди
         * */
        ArrayBlockingQueue<String> strings2 = new ArrayBlockingQueue<>(100, true);
        /*
         * Дополнительный "флаг справедливости" по умолчанию - false. Если true - то put потоки в очереди тоже
         * выстаиваются в очередь, и добавляют элементы в очередь - по очереди.
         * Аналогично, get-потоки будут забирать элементы по очереди.
         * В такой конструкции put потоки не будут простаивать, но ArrayBlockingQueue будет работать чуть медленнее.
         * */

        SynchronousQueue<Integer> integers;
        /*
         * В такой очереди каждая операция добавления ожидает операции получения (удаления).
         * */
    }
}

class WriteSignals implements Runnable {
    private final LinkedBlockingDeque<Signal> signals;

    public WriteSignals(LinkedBlockingDeque<Signal> signals) {
        this.signals = Objects.requireNonNull(signals);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Write " + Thread.currentThread().getState());
            try {
                //noinspection BusyWait
                Thread.sleep(5000);
                Signal signal = Signal.getSignal();
                signals.put(signal);
                /*
                 * метод put кладёт элемент в конец очереди.
                 * если лимит не задан, очередь ограничена Integer.MAX_VALUE;
                 * если лимит задан и очередь "забита" (достигла лимита), то метод put заставит данный поток ждать
                 * пока не освободится место в очереди.
                 * */
                System.out.println("Write Signal " + signal);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ReadSignals implements Runnable {
    private final LinkedBlockingDeque<Signal> signals;

    public ReadSignals(LinkedBlockingDeque<Signal> signals) {
        this.signals = Objects.requireNonNull(signals);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Read " + Thread.currentThread().getState());
            try {
                System.out.println("Read " + signals.take());
                /*
                 * Забирает элемент из очереди (удаляет его оттуда)
                 * Возвращает этот элемент в качестве результата
                 * Не отработает, если в очереди ничего нет - будет ждать, если в очереди ничего нет.
                 *
                 * Остальные методы (добавление/получение элемента в/из начала/конца очереди) либо вернут null,
                 * либо выбросят исключение.
                 * */
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}

class Signal {
    private Priority priority;
    private int code;

    public Signal(Priority priority, int code) {
        this.priority = priority;
        this.code = code;
    }

    public static Signal getSignal() {
        Random random = new Random();
        return new Signal(
                Priority.getPriority(random.nextInt(Priority.values().length)),
                random.nextInt(30)
        );
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Signal{" +
               "priority=" + priority +
               ", code=" + code +
               '}';
    }

    enum Priority {
        HIGH, MEDIUM, LOW;

        public static Priority getPriority(int ord) {
            for (Priority priority : values()) {
                if (ord == priority.ordinal()) {
                    return priority;
                }
            }
            throw new AssertionError("wrong ordinal");
        }
    }
}
