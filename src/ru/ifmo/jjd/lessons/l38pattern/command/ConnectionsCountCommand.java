package ru.ifmo.jjd.lessons.l38pattern.command;

public class ConnectionsCountCommand extends ServerCommand {
    public ConnectionsCountCommand(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        System.out.println(server + " отправит количество " +
                           "подключений " + server.getConnectionCount());
    }
}
