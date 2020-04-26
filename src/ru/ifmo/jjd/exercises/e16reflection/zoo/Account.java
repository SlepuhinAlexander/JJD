package ru.ifmo.jjd.exercises.e16reflection.zoo;

import java.util.Objects;

public class Account {
    private final String login;
    private String password;
    private final Employee employee;

    public Account(String login, String password, Employee employee) {
        if (login == null || password == null || employee == null) throw new NullPointerException();
        if (login.isEmpty()) throw new IllegalArgumentException();
        this.login = login;
        setPassword(password);
        this.employee = employee;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        if (newPassword == null) throw new NullPointerException();
        if (password == null) password = newPassword;
    }

    public boolean setPassword(String oldPassword, String newPassword) {
        if (oldPassword == null || newPassword == null) throw new NullPointerException();
        if (password == null) {
            password = newPassword;
            return true;
        }
        if (checkPassword(oldPassword)) {
            password = newPassword;
            return true;
        }
        return false;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return login.equals(account.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
