package ru.ifmo.jjd.lessons.l22nio.nioserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 8090));

        new Thread(new Receiver(socket)).start();
        new Thread(new Sender(socket)).start();
    }

    private static class Receiver extends Worker {
        private InputStream in;
        private Socket socket;
        private byte[] buf;

        public Receiver(Socket socket) {
            this.socket = socket;
            buf = new byte[1024];
        }

        @Override
        protected void init() throws IOException {
            in = socket.getInputStream();
        }

        @Override
        protected void loop() throws IOException {
            int read = in.read(buf);
            System.out.println(new String(buf, 0, read));
        }

        @Override
        protected void stop() throws IOException {
            socket.close();
        }
    }

    private static class Sender extends Worker {
        private OutputStream out;
        private Socket socket;
        private Scanner scanner;

        public Sender(Socket socket) {
            this.socket = socket;
        }

        @Override
        protected void init() throws IOException {
            out = socket.getOutputStream();
            scanner = new Scanner(System.in);
        }

        @Override
        protected void loop() throws IOException {
            String message = scanner.nextLine();
            out.write(message.getBytes(StandardCharsets.UTF_8));
        }

        @Override
        protected void stop() throws IOException {
            socket.close();
        }

    }
}