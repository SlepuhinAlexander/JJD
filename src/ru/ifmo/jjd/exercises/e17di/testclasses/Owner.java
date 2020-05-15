package ru.ifmo.jjd.exercises.e17di.testclasses;

import ru.ifmo.jjd.exercises.e17di.container.marks.Required;
import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;
import ru.ifmo.jjd.exercises.e17di.testclasses.config.OwnerConfig;

@RequiredClass
public class Owner extends Toon {
    @Required
    private OwnerConfig config;

    @Override
    public String getName() {
        return config.getName();
    }

    @Override
    public void setName(String name) {
        config.setName(name);
    }
}