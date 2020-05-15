package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;
import ru.ifmo.jjd.exercises.e17di.testclasses.config.ToonConfig;

@RequiredClass
public abstract class Toon {
    private ToonConfig config;

    public abstract String getName();

    public abstract void setName(String name);
}
