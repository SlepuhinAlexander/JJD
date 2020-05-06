package ru.ifmo.jjd.lessons.l21serialize.messages;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private Connection connection;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        int port = 8080;
        Server server = new Server(port);
        /*
         * Домашнее задание:
         * - если сервер не запущен, клиент не должен падать с эксепшенами, а корректно обработать
         * - если клиент подключился и отключился, сервер тоже не должен пострадать
         * */
        try {
            server.start();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException, ClassNotFoundException {
        /*
         * сервер должен ждать в ожидании подключения клиента
         * */
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен...");
            while (true) {
                /*
                 * Чтобы установить соединение сервер должен восстановить сокет клиента.
                 * */
                Socket clientSocket = serverSocket.accept();
                connection = new Connection(clientSocket);
                System.out.println(connection.readMessage());
                /*
                 * Домашнее задание:
                 * реализовать реакцию от сервера на сообщения:
                 * - /help - набор доступных команд
                 * - /ping -
                 * - /count - вернуть количество совершённых подключений
                 * - unknown command
                 * */
                connection.sendMessage(SimpleMessage.getInstance("server", "Привет"));
            }
        }
    }
}
