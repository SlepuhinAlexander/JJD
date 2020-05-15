package ru.ifmo.jjd.exercises.e17di.testclasses.config;

import ru.ifmo.jjd.exercises.e17di.container.marks.ConfigClass;

@ConfigClass(file = "Toons.properties", prefix = "owner")
public class OwnerConfig extends ToonConfig {
}
