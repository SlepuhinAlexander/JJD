package ru.ifmo.jjd.lessons.l26sync;

public class YieldDemo {
    public static void main(String[] args) {
        new Thread(new PrintThread()).start();
        new Thread(new PrintThread()).start();
    }
}

class PrintThread implements Runnable {
    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                //noinspection BusyWait
                Thread.sleep(300);
                /*
                 * Поток засыпает на указанное количество миллисекунд.
                 * Но просыпается он не ровно через указанное время, а только когда его запустит шедулер.
                 * По истечении указанного таймаута поток переходит в состояние RUNNABLE - т.е. указывает шедулеру, что
                 * он готов продолжить работу.
                 * */
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Имя потока: " + Thread.currentThread().getName());
            Thread.yield();
            /*
             * Метод yield указывает шедулеру, что поток готов уступить управление другому потоку. Какому-то другому.
             * Указать какому конкретно потоку передать управление - в принципе невозможно.
             * Это только рекомендация шедулеру. Шедулер может проигнорировать это указание, а может учесть.
             * */
        }
    }
}