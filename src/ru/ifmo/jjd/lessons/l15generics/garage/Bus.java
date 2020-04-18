package ru.ifmo.jjd.lessons.l15generics.garage;

public class Bus extends Transport {
    private boolean wiFi;



    public Bus(String depStation, String arrStation, String num, boolean wiFi) {
        super(depStation, arrStation, num);
        this.wiFi = wiFi;
    }

    public boolean isWiFi() {
        return wiFi;
    }

    public void setWiFi(boolean wiFi) {
        this.wiFi = wiFi;
    }

    @Override
    public void repair() {
        System.out.println("Ремонт автобуса");
    }
}
