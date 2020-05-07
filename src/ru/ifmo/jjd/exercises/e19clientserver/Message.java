package ru.ifmo.jjd.exercises.e19clientserver;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Message implements Serializable {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("(MMM, d HH:mm:ss) ",
            Locale.ENGLISH);

    private String sender;
    private String text;
    private LocalDateTime dateTime;

    public Message() {
    }

    public Message(String sender, String text) {
        setSender(sender);
        setText(text);
    }

    public void setDateTime() {
        dateTime = LocalDateTime.now();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return dateTime == null ? sender + ": " + text :
                dateTime.format(FORMAT) + sender + ": " + text;
    }
}
