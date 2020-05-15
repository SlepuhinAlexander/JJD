package ru.ifmo.jjd.exercises.e17di.testclasses.config;

import ru.ifmo.jjd.exercises.e17di.container.marks.ConfigClass;

@ConfigClass(file = "Toons.properties", prefix = "animal.dog")
public class DogConfig extends AnimalConfig {
}
