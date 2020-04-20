package ru.ifmo.jjd.exercises.e14collections.messages;

import java.util.*;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

enum MessagePriority {
    LOW, MEDIUM, HIGH, URGENT;

    public static MessagePriority getPriority(int ord) {
        for (MessagePriority mp : values()) {
            if (ord == mp.ordinal()) {
                return mp;
            }
        }
        throw new AssertionError("Wrong ordinal: " + ord);
    }
}

public class Message {
    private int code;
    private MessagePriority priority;

    public Message(int code, MessagePriority priority) {
        this.code = code;
        this.priority = priority;
    }

    public static void main(String[] args) {
        List<Message> messages = MessageGenerator.generate(34);
        System.out.println(messages);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public MessagePriority getPriority() {
        return priority;
    }

    public void setPriority(MessagePriority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return code == message.code &&
                priority == message.priority;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, priority);
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", priority=" + priority +
                '}';
    }
}

class MessageGenerator {
    public static List<Message> generate(int num) {
        if (num <= 0) {
            return Collections.emptyList();
        }
        List<Message> messages = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            messages.add(new Message(randomInt(10),
                    MessagePriority.getPriority(randomInt(MessagePriority.values().length))));
        }
        return messages;
    }
}