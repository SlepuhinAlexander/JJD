package ru.ifmo.jjd.lessons.l21serialize.messages;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SimpleMessage implements Serializable {
    private String sender;
    private String text;
    private LocalDateTime dateTime;

    public SimpleMessage() {
    }

    public SimpleMessage(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public void setDateTime() {
        dateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
               "sender='" + sender + '\'' +
               ", text='" + text + '\'' +
               ", dateTime=" + dateTime +
               '}';
    }

    public static SimpleMessage getInstance(String sender, String text) {
        return new SimpleMessage(sender, text);
    }
}
