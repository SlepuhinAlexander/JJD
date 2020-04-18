package ru.ifmo.jjd.lessons.l15generics.garage;

public abstract class Transport implements Repairing {
    private String depStation;
    private String arrStation;
    private String num;

    public Transport(String depStation, String arrStation, String num) {
        this.depStation = depStation;
        this.arrStation = arrStation;
        this.num = num;
    }

    public Transport() {
    }

    public String getDepStation() {
        return depStation;
    }

    public String getArrStation() {
        return arrStation;
    }

    public String getNum() {
        return num;
    }

    public void setDepStation(String depStation) {
        this.depStation = depStation;
    }

    public void setArrStation(String arrStation) {
        this.arrStation = arrStation;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
