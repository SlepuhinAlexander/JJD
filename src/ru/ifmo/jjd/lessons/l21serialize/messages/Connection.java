package ru.ifmo.jjd.lessons.l21serialize.messages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements AutoCloseable{
    private final Socket socket;
    private final ObjectInputStream input;
    private final ObjectOutputStream output;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.input = new ObjectInputStream(this.socket.getInputStream());
    }

    public void sendMessage (SimpleMessage message) throws IOException {
        message.setDateTime();
        output.writeObject(message);
        output.flush();
    }

    public SimpleMessage readMessage() throws IOException, ClassNotFoundException {
        return (SimpleMessage) input.readObject();
    }

    @Override
    public void close() throws Exception {
        input.close();
        output.close();
        socket.close();
    }
}
