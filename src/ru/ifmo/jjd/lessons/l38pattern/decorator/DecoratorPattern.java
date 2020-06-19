package ru.ifmo.jjd.lessons.l38pattern.decorator;

import ru.ifmo.jjd.exercises.e18io.encryption.EncryptedOutputStream;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDateTime;

// основная функциональность
interface ILogger {
    void write(String data);
}

public class DecoratorPattern {
    public static void main(String[] args) {
        ILogger logger = new Logger();
        logger.write("info"); // никакого декорирования нет.

        // Декораторами можно пользоваться как по отдельности, так и несколькими вместе, вложенно друг в друга.
        // В любом случае декоратор должен иметь объект фактически исполняющий работу (после декорирования)

        ILogger dateLogger = new DateTimeDecorator(logger);
        dateLogger.write("info");

        ILogger codeDateLogger = new CodeDecorator(new DateTimeDecorator(logger));
        codeDateLogger.write("info");
    }
}

// класс реализующий основную функциональность
class Logger implements ILogger {
    @Override
    public void write(String data) {
        System.out.println(data);
    }
}

// Пусть с данными, используемыми этой функциональностью, предварительно нужно сделать какие-то манипуляции.
// Это и делают декораторы

// Сначала создаётся базовый декоратор
// Обязательно реализует тот же интерфейс, что и класс с основной функциональностью
class LoggerDecorator implements ILogger {
    // Обязательно имеет ссылку на интерфейс
    private final ILogger logger;

    public LoggerDecorator(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void write(String data) {
        logger.write(data);
    }
}

// все декораторы наследуются от базового декоратора
class DateTimeDecorator extends LoggerDecorator {
    public DateTimeDecorator(ILogger logger) {
        super(logger);
    }

    @Override
    public void write(String data) {
        // тут происходит фактическое декорирование. Например:
        String newData = data + " date: " + LocalDateTime.now();
        super.write(newData);
    }
}

class CodeDecorator extends LoggerDecorator {
    public CodeDecorator(ILogger logger) {
        super(logger);
    }

    @Override
    public void write(String data) {
        String newData = data + " code: " + Math.random();
        super.write(newData);
    }
}