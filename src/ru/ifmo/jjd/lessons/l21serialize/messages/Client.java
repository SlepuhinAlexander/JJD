package ru.ifmo.jjd.lessons.l21serialize.messages;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    // клиентская программа должна знать IP и порт сервера, к которому нужно подключиться.
    private int port;
    private String ip;
    private Scanner scanner;

    public Client(int port, String ip) {
        this.port = port;
        this.ip = ip;
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println("Программа клиент");
        /*
         * Домашнее задание:
         * port и ip получать из .properties файла
         * */
        int port = 8080;
        String ip = "127.0.0.1";
        try {
            new Client(port, ip).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        /*
         * Домашнее задание:
         * Реализовать команды:
         * - /help - набор доступных команд
         * - /exit - для завершения работы
         * - /ping
         * - /count - количество подключавшихся клиентов
         *
         * Текст сообщений может быть только таким. Другие сообщения передавать нельзя (уведомить пользователя о
         * некорректном вводе)
         * */
        String name = scanner.nextLine();
        String messageText;
        //noinspection InfiniteLoopStatement
        while (true) {
            System.out.println("Введите сообщение");
            messageText = scanner.nextLine();
            sendAndPrintMessage(SimpleMessage.getInstance(name, messageText));
        }
    }

    private void sendAndPrintMessage(SimpleMessage message) throws Exception {
        try (Connection connection = new Connection(getSocket())) {
            connection.sendMessage(message);
            SimpleMessage fromServer = connection.readMessage();
            System.out.println("Ответ от сервера: " + fromServer);
        }
    }

    private Socket getSocket() throws IOException {
        //noinspection UnnecessaryLocalVariable
        Socket socket = new Socket(ip, port);
        /*
         * Домашнее задание:
         * По документации Socket изучить какие есть способы установки соединения;
         * какую информацию можно получить по этому объекту.
         *
         * */
        return socket;
    }
}
