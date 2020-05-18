package ru.ifmo.jjd.lessons.l26sync;

import java.util.ArrayList;

public class Synchronized {
    public static void main(String[] args) {
        Counter counter = new Counter();
        ArrayList<IncrementThread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new IncrementThread(counter));
            // каждый поток ссылается на один и тот же counter
            // соответственно, каждый будет работать с тем же самым счётчиком
        }

        threads.forEach(IncrementThread::start);

        for (IncrementThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("counter = " + counter.getCounter());
        // при каждом запуске программы результат будет разный. И крайне вряд ли он будет ожидаемым 100000.
        /*
         * Все потоки работают с одной и той же кучей.
         * Часть потоков работают параллельно, они берут один и тот же объект, с одним и тем же значением, например 5
         * и увеличивают его на 1, до, например 6.
         *
         * Поэтому итоговый результат всегда будет меньше 100000
         *
         * Введём блок synchronized на объект counter в классе IncrementThread
         * После этого результат выполнения задачи теперь всегда будет 100000
         * */

    }
}

class Counter {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    // первый способ:
    // public void increment() {

    // второй способ: ключевое слово synchronized на методе.
    /*
     * при таком использовании монитор устанавливается в состояние заблокирован на объекте, у которого вызывается этот
     * метод
     * */
    /*
     * synchronized нельзя использовать со свойствами, и с конструкторами; только с методами
     * */
    public synchronized void increment() {
        counter++;
    }
}

class IncrementThread extends Thread {
    private final Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            // synchronized блок на объекте counter
            synchronized (counter) {
                /*
                 * У каждого объекта есть некий монитор, определяющий, заблокирован объект или нет.
                 * Если объект - synchronized то при входе в этот блок кода объект становится заблокирован.
                 * То есть никакой другой поток не может взять этот объект, только текущий поток, взявший этот объект
                 * может с ним работать.
                 * Объект остаётся заблокирован на протяжении всего блока кода.
                 * */
                counter.increment();
                /*
                 * По окончании блока кода объект будет разблокирован и какой-то другой поток сможет успеть захватить
                 * этот объект и объект снова станет заблокирован, а другие потоки не смогут с ним работать.
                 *
                 * В результате все операции с counter происходят последовательно: более 1 потока не сможет работать с
                 * ним. И никакой пользы от многопоточности не остаётся.
                 * Но зато результат данных корректный.
                 * */
            }
            /*
             * Поле counter не final.
             * Пока поток вызывает инкремент от counter, сама ссылка в counter может начинать ссылаться на другой
             * объект.
             * И тогда результат будет непредсказуем.
             * Поэтому synchronized имеет смысл использовать только с финализированными объектами.
             * */

            /*
             * Всё что описано в synchronized блоке будет выполняться потоками последовательно, друг за другом.
             * Поэтому в synchronized должно содержаться только то, что относится к работе с объектом, который нужно
             * синхронизировать.
             * Если весь код поместить в synchronized, то никакого толка от многопоточности не будет.
             *
             * */
        }
    }
}