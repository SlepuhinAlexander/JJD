package ru.ifmo.jjd.exercises.e19clientserver;

import ru.ifmo.jjd.utils.ConfigLoader;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.ServerSocket;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.StringHelper.normalize;

public class Server {
    private int port;
    private Connection connection;
    private String name;
    private int connectionsCount;
    private int resendDelay;
    private int resendAttempts;

    public Server(int port, String name) {
        setPort(port);
        setName(name);
    }

    public static void main(String[] args) {
        String className = MethodHandles.lookup().lookupClass().getName();
        ConfigLoader loader = ConfigLoader.getConfigLoader();
        int port;
        try {
            port = Integer.parseInt(loader.getProperty(className, "serverPort").trim());
        } catch (NumberFormatException e) {
            port = -1;
        }
        Server server;
        try {
            server = new Server(port, "Main Server");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            server.setResendDelay(Integer.parseInt(loader.getProperty(className, "resendDelay").trim()));
        } catch (NumberFormatException e) {
            server.setResendDelay(1000);
        }
        try {
            server.setResendAttempts(Integer.parseInt(loader.getProperty(className, "resendAttempts").trim()));
        } catch (NumberFormatException e) {
            server.setResendAttempts(0);
        }
        server.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        if (port < 0 || port >= 65536) throw new IllegalArgumentException("port " + port + " must be in [0,65536]");
        this.port = port;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String nameNorm = normalize(name);
        if (nameNorm.isBlank()) throw new IllegalArgumentException("provided name '" + name +
                                                                   "' is invalid or too short");
        this.name = nameNorm;
    }

    private void setResendDelay(int resendDelay) {
        this.resendDelay = Math.min(Math.max(0, resendDelay), 60_000);
    }

    private void setResendAttempts(int resendAttempts) {
        this.resendAttempts = Math.min(Math.max(0, resendAttempts), 10);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server '" + name + "' started");
            //noinspection InfiniteLoopStatement
            while (true) {
                try {
                    connection = new Connection(serverSocket.accept());
                } catch (IOException e) {
                    println("Failed to establish connection with client");
                    e.printStackTrace();
                    continue;
                }
                connectionsCount++;
                interact();
            }
        } catch (IOException e) {
            println("Failed to establish a server socket");
            e.printStackTrace();
        }
    }

    private void interact() {
        Message request = connection.receive();
        if (request == null) return;
        println(request);
        String[] command = request.getText().split("\\s+");
        String responseText;
        if (command.length == 0) {
            responseText = "unknown command";
        } else {
            responseText = switch (command[0].toLowerCase()) {
                case "/help" -> """
                                                                
                                /help this help message
                                /ping check connection to server
                                /count get total amount of connections to server made
                                /name get current sender name (client-side)
                                /name [Name] set current sender name (client-side) 
                                /exit stop and close application""";
                case "/ping" -> "available";
                case "/count" -> "" + connectionsCount;
                default -> "unknown command";
            };
        }
        Message response = new Message(name, responseText);
        int attempt = 0;
        while (!connection.send(response) && attempt < resendAttempts) {
            println("retry in " + resendDelay + " ms");
            attempt++;
            try {
                //noinspection BusyWait
                Thread.sleep(resendDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        println(response);
    }
}
