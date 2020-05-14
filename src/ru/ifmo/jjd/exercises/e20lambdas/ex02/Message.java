package ru.ifmo.jjd.exercises.e20lambdas.ex02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class Message {
    private int code;
    private Priority priority;

    public Message(int code, Priority priority) {
        this.code = code;
        this.priority = priority;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return code == message.code && priority == message.priority;
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

    enum Priority {
        LOW, MEDIUM, HIGH, URGENT;

        public static Priority getPriority(int ord) {
            for (Priority priority : Priority.values()) {
                if (ord == priority.ordinal()) return priority;
            }
            throw new IllegalArgumentException("Wrong ordinal: " + ord);
        }
    }

    static class Generator {
        public static List<Message> generate(int amount) {
            if (amount <= 0) return Collections.emptyList();
            List<Message> messages = new ArrayList<>(amount);
            int length = Priority.values().length;
            for (int i = 0; i < amount; i++) {
                messages.add(new Message(randomInt(10, 50), Priority.getPriority(randomInt(length))));
            }
            return messages;
        }
    }
}
