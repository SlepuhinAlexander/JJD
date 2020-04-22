package ru.ifmo.jjd.lessons.l17maps;

import java.util.Objects;

public class User {
    private String login;
    private String pwd;
    private Role role;

    public User(String login, String pwd, Role role) {
        this.login = login;
        this.pwd = pwd;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pwd='" + pwd + '\'' +
                ", role=" + role +
                '}';
    }
}
