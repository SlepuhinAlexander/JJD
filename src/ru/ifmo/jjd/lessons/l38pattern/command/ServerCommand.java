package ru.ifmo.jjd.lessons.l38pattern.command;

abstract public class ServerCommand {
    protected Server server;

    public ServerCommand(Server server) {
        this.server = server;
    }

    // фабричный метод
    public static ServerCommand getCommand(String type, Server server) {
        ServerCommand command = new DefaultCommand(server);
        // вместо if использовать switch case
        // вместо строк "time", "connections" и тд использовать
        // константы, enum,
        // в некоторых случаях properties файл
        // можно использовать
        // Class cls = Class.forName(type) для создания объектов через рефлексию
        if ("time".equalsIgnoreCase(type)) {
            command = new TimeCommand(server);
        } else if ("connections".equalsIgnoreCase(type)) {
            command = new ConnectionsCountCommand(server);
        }
        return command;
    }

    // должен быть реализован каждой конкретной командой
    abstract public void execute();
}