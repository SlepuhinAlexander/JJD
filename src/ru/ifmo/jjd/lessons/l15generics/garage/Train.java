package ru.ifmo.jjd.lessons.l15generics.garage;

public class Train extends Transport {
    private int carCount;

    public Train(String depStation, String arrStation, String num, int carCount) {
        super(depStation, arrStation, num);
        this.carCount = carCount;
    }

    public int getCarCount() {
        return carCount;
    }

    public void setCarCount(int carCount) {
        this.carCount = carCount;
    }

    @Override
    public void repair() {
        System.out.println("Ремонт поезда");
    }
}
