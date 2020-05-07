package ru.ifmo.jjd.exercises.e19clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;

public class Connection implements AutoCloseable {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    public boolean send(Message message) {
        message.setDateTime();
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            println("Failed to send message");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Message receive() {
        Message received = null;
        try {
            received = (Message) in.readObject();
        } catch (IOException e) {
            println("Failed to read object");
            e.printStackTrace();
        } catch (ClassNotFoundException | ClassCastException e) {
            println("Failed to deserialize object");
            e.printStackTrace();
        }
        return received;
    }

    @Override
    public void close() /*throws IOException*/ {
        try {
            in.close();
        } catch (IOException e) {
            println("failed to close connection input stream");
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            println("failed to close connection output stream");
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            println("failed to close connection");
            e.printStackTrace();
        }
    }
}
