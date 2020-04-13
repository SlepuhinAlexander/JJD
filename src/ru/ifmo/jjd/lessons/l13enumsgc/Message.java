package ru.ifmo.jjd.lessons.l13enumsgc;

public class Message {
    public final static String TYPE;
    private String text;
    private int code;

    static {
        TYPE = "Simple text message";
        System.out.println("Class Message initialized");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}