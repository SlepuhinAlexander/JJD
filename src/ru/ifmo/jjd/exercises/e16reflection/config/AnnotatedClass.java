package ru.ifmo.jjd.exercises.e16reflection.config;

import ru.ifmo.jjd.lessons.l18reflection.annotations.ConfigClass;
import ru.ifmo.jjd.lessons.l18reflection.annotations.Required;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

enum SomeEnum {
    FIRST, SECOND
}

interface AnInterface {
    String INTERFACE_NAME = "AnInterface";

    void doNothing();
}

@ConfigClass(prefix = "")
public class AnnotatedClass extends Ancestor implements AnInterface {
    @Required
    private ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
    @Required
    private String text = "some text\n";

    private Map<Integer,Integer> map;

    public AnnotatedClass(int number, Boolean[] array) {
        super(number, array);
    }

    public ArrayList<ArrayList<Integer>> getLists() {
        return lists;
    }

    public String getText() {
        return text;
    }

    @Override
    public void doNothing() {
    }

    @Override
    public String toString() {
        return "AnnotatedClass{" + super.toString() +
                ", lists=" + lists +
                ", text='" + text + '\'' +
                ", map=" + map +
                '}';
    }
}

class Ancestor {
    @Required
    private final int number;
    @Required
    private final Boolean[] array;
    @Required
    private String string;

    SomeEnum someEnum;

    public Ancestor(int number, Boolean[] array) {
        this.number = number;
        this.array = array;
    }

    public int getNumber() {
        return number;
    }

    public Object[] getArray() {
        return array;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SomeEnum getSomeEnum() {
        return someEnum;
    }

    public void setSomeEnum(SomeEnum someEnum) {
        this.someEnum = someEnum;
    }

    @Override
    public String toString() {
        return "Ancestor{" +
                "number=" + number +
                ", array=" + Arrays.toString(array) +
                ", string='" + string + '\'' +
                ", someEnum=" + someEnum +
                '}';
    }
}
