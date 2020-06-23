package ru.ifmo.jjd.lessons.l38pattern.command;

import java.util.Random;

public class Server {
    private int connectionCount; // ссылка на контекст использования

    public int getConnectionCount() {
        return connectionCount;
    }

    public void start(){
        //noinspection InfiniteLoopStatement
        while (true){
            try {
                //noinspection BusyWait
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // подключение клиента
            connectionCount++;
            // получение запроса от клиента
            String clientTask = genClientReq();
            // выполнение задачи в зависимости от clientTask
            executeCommand(ServerCommand.getCommand(clientTask, this));
        }
    }

    public void executeCommand(ServerCommand command){
        command.execute();
        // можно добавить сохранение выполненных команд в список
        // и тп
    }

    // вместо клиента
    public static String genClientReq(){
        String[] strings = {"time", "connections", "unknown_command", "state"};
        Random random = new Random();
        return strings[random.nextInt(strings.length)];
    }

    public static void main(String[] args) {
        new Server().start();
    }

}