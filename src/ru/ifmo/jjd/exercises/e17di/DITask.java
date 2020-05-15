package ru.ifmo.jjd.exercises.e17di;

import ru.ifmo.jjd.exercises.e17di.container.DIContainer;
import ru.ifmo.jjd.exercises.e17di.testclasses.Cat;
import ru.ifmo.jjd.exercises.e17di.testclasses.Dog;
import ru.ifmo.jjd.exercises.e17di.testclasses.Mouse;
import ru.ifmo.jjd.utils.ConfigLoader;

import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.ifmo.jjd.utils.ConsoleHelper.println;
import static ru.ifmo.jjd.utils.RandomHelper.randomInt;

public class DITask {
    public static void main(String[] args) {
        String classPath = System.getProperty("java.class.path");
        // fixing some weird debugger issue
        classPath = classPath.contains(";") ? classPath.substring(0, classPath.indexOf(';')) : classPath;
        String pack = ConfigLoader.getConfigLoader()
                .getProperty(MethodHandles.lookup().lookupClass().getName(), "testPackage");
        String configs = ConfigLoader.getConfigLoader()
                .getProperty(MethodHandles.lookup().lookupClass().getName(), "testConfigs");
        Path testPackage = Paths.get(classPath + "\\"
                                     + pack.replace(".", "\\"));
        Path configsDir = Paths.get(System.getProperty("user.dir") + "\\" + configs);
        println("working with test package '" + pack + "'");
        println("and configs in " + configsDir);
        DIContainer container;
        try {
            container = DIContainer.of(testPackage, configsDir);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        println("------------------------------------------------------------");
        for (int i = 1; i <= 30; i++) {
            println("\nday" + i);
            ((Cat) container.get("Cat")).catchMouse((Mouse) container.get(Mouse.class));
            ((Dog) container.get("Dog")).fight((Cat) container.get(Cat.class));
            ((Mouse) container.get(Mouse.class))
                    .setSpeed(((Mouse) container.get("Mouse")).getSpeed() + randomInt(-4, 5));
            ((Cat) container.get(Cat.class))
                    .setSpeed(((Cat) container.get("Cat")).getSpeed() + randomInt(-4, 5));
            ((Cat) container.get("Cat"))
                    .setStrength(((Cat) container.get(Cat.class)).getStrength() + randomInt(-4, 5));
            ((Dog) container.get("Dog"))
                    .setStrength(((Dog) container.get(Dog.class)).getStrength() + randomInt(-4, 5));
        }
    }
}
/*
 * working with test package 'ru.ifmo.jjd.exercises.e17di.testclasses'
 * and configs in C:\Users\Superuser\IdeaProjects\JJD\sources\ru\ifmo\jjd\exercises\e17di
 * ------------------------------------------------------------
 *
 * day1
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day2
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day3
 * Tom has caught Jerry
 * Spike has beaten up Tom
 *
 * day4
 * Tom has caught Jerry
 * Spike has beaten up Tom
 *
 * day5
 * Tom has caught Jerry
 * Spike has beaten up Tom
 *
 * day6
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day7
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day8
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day9
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day10
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day11
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day12
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day13
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day14
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day15
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day16
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day17
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day18
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day19
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day20
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day21
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day22
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day23
 * Tom has caught Jerry
 * Spike has beaten up Tom
 *
 * day24
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day25
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day26
 * Jerry ran away form Tom
 * Spike has beaten up Tom
 *
 * day27
 * Jerry ran away form Tom
 * Spike could not beat Tom
 *
 * day28
 * Tom has caught Jerry
 * Spike could not beat Tom
 *
 * day29
 * Tom has caught Jerry
 * Spike has beaten up Tom
 *
 * day30
 * Jerry ran away form Tom
 * Spike could not beat Tom
 * */