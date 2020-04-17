package ru.ifmo.jjd.exercises.e13exceptions.ex01;


import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.jar.JarException;

import static ru.ifmo.jjd.utils.RandomHelper.randomInt;
import static ru.ifmo.jjd.utils.ConsoleHelper.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            try {
                throwException(Status.values()[randomInt(Status.values().length)]);
            } catch (FileNotFoundException e) {
                println(e.getMessage() + " please check the file name");
            } catch (AccessDeniedException e) {
                println(e.getMessage());
                try {
                    throw new AccessDeniedException(null).initCause(e);
                } catch (Throwable t) {
                    System.out.println("Catch in catch " + t.getCause());
                }
            } catch (JarException e) {
                e.printStackTrace();
            }
        }
    }

    static void throwException(Status status) throws FileNotFoundException, AccessDeniedException, JarException {
        switch (status) {
            case FILE_NOT_FOUND -> throw new FileNotFoundException();
            case ACCESS_DENIED -> throw new AccessDeniedException(null);
            case JAR_ERROR -> throw new JarException();
        }
    }

    private enum Status {
        FILE_NOT_FOUND, ACCESS_DENIED, JAR_ERROR
    }
}
