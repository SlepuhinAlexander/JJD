package ru.ifmo.jjd.lessons.l28executors;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Callable<Unit> unitCallable = new UnitCreator();
        ArrayList<Future<Unit>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<Unit> future = service.submit(unitCallable);
            /*
             * Пул принимает задачу. Метод submit() сразу возвращает объект типа Future. Тот, который ассоциирован с
             * этой задачей.
             * Пока задача не выполнена, этот объект будет пустой. Данные там появятся, когда поток из пула
             * отработает задачу.
             * */
            futures.add(future);
        }

        service.shutdown();

        for (Future<Unit> future : futures) {
            // Ожидание данных. До получения результата работы потока.
/*
            System.out.println("Waiting Future: ");
            try {
                System.out.println("Unit " + future.get());
                */
            /*
             * Получение результата работы потока - происходит последовательно. Пока предыдущий get не вернёт
             * результат, следующий get будет ждать.
             * *//*

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
*/
            // Альтернативно: ожидание данных в течение ограниченного промежутка времени.
            System.out.println("Waiting future: ");
            try {
                System.out.println("Unit " + future.get((long) (Math.random() * 3000), TimeUnit.MILLISECONDS));
            } catch (Exception e) {
                System.out.println("--- не смог дождаться юнита -- ");
            }
            /*
             * Если за отведённый таймаут метод так и не получит данные, будет выброшен TimeoutException
             * */

            // пусть есть список из задач
            List<Callable<Unit>> taskList = new ArrayList<>();
            taskList.add(unitCallable);
            taskList.add(unitCallable);
            taskList.add(unitCallable);

            ExecutorService service2 = Executors.newFixedThreadPool(2);
            try {
                List<Future<Unit>> futureList = service2.invokeAll(taskList);
                /*
                 * Метод используется для выполнения набора задач (вместе).
                 * Метод берёт список задач на выполнение.
                 * Задачи все выполняются.
                 * Когда задачи будут выполнены, данные появятся в объектах типа Future
                 * */
                /*
                 * Ожидание происходит здесь. Ожидается пока все задачи не отработают.
                 * Аналогично методу get(), можно указать таймаут ожидания.
                 * Если не дождётся, будет TimeoutException.
                 * */
                for (Future<Unit> future2 : futureList) {
                    // future2.cancel(true) -- прерывает текущую задачу
                    // future2.isCancelled() -- проверяет, была ли текущая задача отменена
                    // future2.isDone() -- проверяет, была ли задача выполнена (завершилась нормально).
                    System.out.println("Unit from service2 " + future.get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            service2.shutdown();
        }
    }
}

class UnitCreator implements Callable<Unit> {
    /*
     * В качестве generic-параметра указывается тип данных, который ожидается вернуть из потока.
     *
     * */

    @Override
    public Unit call() {
        Random random = new Random(new Date().getTime());
        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Unit unit = new Unit();
        unit.setName(Thread.currentThread().getName());
        unit.setHealth(random.nextInt(20) + 1);
        unit.setAttackScore(random.nextInt(15) + 1);
        return unit;
    }
}

class Unit {
    private String name;
    private int health;
    private int attackScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackScore() {
        return attackScore;
    }

    public void setAttackScore(int attackScore) {
        this.attackScore = attackScore;
    }

    @Override
    public String toString() {
        return "Unit{" +
               "name='" + name + '\'' +
               ", health=" + health +
               ", attackScore=" + attackScore +
               '}';
    }
}
