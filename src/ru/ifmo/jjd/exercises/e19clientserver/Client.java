package ru.ifmo.jjd.exercises.e19clientserver;

import ru.ifmo.jjd.utils.ConfigLoader;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.Socket;
import java.time.Duration;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.ConsoleHelper.readLine;
import static ru.ifmo.jjd.utils.StringHelper.isNullOrBlank;
import static ru.ifmo.jjd.utils.StringHelper.normalize;
import static ru.ifmo.jjd.utils.net.IPParser.parseIP;


public class Client {
    private int serverPort;
    private String serverIp;
    private int resendDelay;
    private int resendAttempts;


    public Client(int serverPort, String serverIp) {
        setServerPort(serverPort);
        setServerIp(serverIp);
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        ConfigLoader loader = ConfigLoader.getConfigLoader();
        int port;
        try {
            port = Integer.parseInt(ConfigLoader.getConfigLoader().getProperty(className, "serverPort").trim());
        } catch (NumberFormatException e) {
            port = -1;
        }
        Client client;
        try {
            client = new Client(port, ConfigLoader.getConfigLoader().getProperty(className, "serverIp").trim());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            client.setResendDelay(Integer.parseInt(loader.getProperty(className, "resendDelay").trim()));
        } catch (NumberFormatException e) {
            client.setResendDelay(1000);
        }
        try {
            client.setResendAttempts(Integer.parseInt(loader.getProperty(className, "resendAttempts").trim()));
        } catch (NumberFormatException e) {
            client.setResendAttempts(0);
        }
        client.start();
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        if (serverPort < 0 || serverPort >= 65536) throw new IllegalArgumentException("port " + serverPort +
                                                                                      " must be in [0,65536]");
        this.serverPort = serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        String parsedIp = parseIP(serverIp);
        if (parsedIp == null) throw new IllegalArgumentException("provided IP '" + serverIp + "' is invalid");
        this.serverIp = parsedIp;
    }

    private void setResendDelay(int resendDelay) {
        this.resendDelay = Math.min(Math.max(0, resendDelay), 60_000);
    }

    private void setResendAttempts(int resendAttempts) {
        this.resendAttempts = Math.min(Math.max(0, resendAttempts), 10);
    }

    private Message interact(Message message) {
        int attempt = 0;
        Message response = null;
        while (true) {
            Connection connection;
            try {
                connection = new Connection(getSocket());
                try (connection) {
                    connection.send(message);
                    println(message);
                    response = connection.receive();
                    if (response != null) {
                        println(response);
                        return response;
                    }
                }
                println("Failed to receive response from server");
            } catch (IOException e) {
                println("Failed to establish connection to server");
            }
            if (attempt >= resendAttempts) break;
            println("retry in " + resendDelay + " ms");
            attempt++;
            try {
                //noinspection BusyWait
                Thread.sleep(resendDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    public void start()  {
        String className = MethodHandles.lookup().lookupClass().getName();
        String name = ConfigLoader.getConfigLoader().getProperty(className, "senderName");
        while (isNullOrBlank(name)) {
            name = normalize(readLine("Please enter your name: "));
            ConfigLoader.getConfigLoader().setProperty(className, "senderName", name);
        }
        String text;
        while (true) {
            text = readLine("$>:");
            String[] command = text.split("\\s+");
            if (command.length == 0) continue;
            switch (command[0].toLowerCase()) {
                case "/name" -> {
                    if (command.length > 1) {
                        name = command[1];
                        ConfigLoader.getConfigLoader().setProperty(className, "senderName", name);
                    } else {
                        println(name);
                    }
                }
                case "/exit" -> {
                    return;
                }
                case "/help" -> interact(new Message(name, "/help"));
                case "/count" -> interact(new Message(name, "/count"));
                case "/ping" -> {
                    Message request = new Message(name, "/ping");
                    Message response = interact(request);
                    if (response != null) {
                        Duration duration = Duration.between(request.getDateTime(), response.getDateTime());
                        long elapsed = duration.getSeconds() * 1_000_000_000L + duration.getNano();
                        println("elapsed " + elapsed / 1_000_000 + "." + (elapsed % 1_000_000) / 1_000 + " ms");
                    }
                }
                default -> println("unknown command");
            }
        }
    }

    private Socket getSocket() throws IOException {
        /*
         * Socket() - пустой сокет, без подключения
         * Socket​(String host, int port) - сокет с попыткой подключения к указанному хосту-порту;
         *      бросает исключения при некорректных аргументах / невозможности подключения
         * Socket​(InetAddress address, int port) - аналогично
         *
         * connect​(SocketAddress endpoint) - подключает сокет к указанному адресу (к серверу)
         * getInetAddress() - удалённый адрес, к которому подключён сокет
         * getInputStream() - получает входящий поток сокета
         * getLocalAddress() - локальный адрес, к которому привязан сокет
         * getLocalPort() - локальный порт, к которому привязан сокет
         * getOutputStream() - получает исходящий поток сокета
         * getPort() - удалённый порт, к которому подключён сокет
         * getReceiveBufferSize() - размер буфера для входящего потока
         * setReceiveBufferSize​(int size) - установить размер буфера для входящего потока
         * getSendBufferSize() - размер буфера для исходящего потока
         * setSendBufferSize​(int size) - установить размер буфера для исходящего потока потока
         * isBound() - привязан ли сокет
         * isClosed() - закрыт ли сокет
         * isConnected() - подключен ли сокет
         * isInputShutdown() - закрыт ли входящий поток
         * shutdownInput() - перевести указатель входящего потока на конец потока (закрыть входящий поток)
         * isOutputShutdown() - закрыт ли исходящий поток
         * shutdownOutput() - закрыть исходящий поток
         * getOption​(SocketOption<T> name) - получить различные опции сокета (+ отдельные методы)
         * setOption​(SocketOption<T> name, T value) - установить различные опции сокета (+ отдельные методы)
         * supportedOptions() - набор доступных опций сокета
         * */
        return new Socket(serverIp, serverPort);
    }
}
