package ru.ifmo.jjd.lessons.lesson07;

abstract public class Unit implements CanRest{
    protected String name = "Unit";
    /*
     * Модификатор доступа protected указывает, что данное свойство доступно всем потомкам данного класса, и всем
     * классам в этом же пакете
     * */
    protected int healthScore;
    protected int speed;

    public Unit(int healthScore, int speed) {
        setHealthScore(healthScore);
        setSpeed(speed);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /*
     * Дополнительные методы, не гетеры и сеттеры, принято описывать после геттеров и сеттеров.
     * */

    public boolean isAlive() {
        return healthScore > 0;
    }

    public void run() {
        System.out.println(name + " двигается со скоростью " + speed);
    }
}
