package ru.ifmo.jjd.exercises.e17di.testclasses.config;

import ru.ifmo.jjd.exercises.e17di.container.marks.ConfigClass;
import ru.ifmo.jjd.exercises.e17di.container.marks.Required;

import java.util.Objects;

@ConfigClass()
public abstract class ToonConfig {
    private String name;

    public String getName() {
        return name;
    }

    @Required
    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }
}
