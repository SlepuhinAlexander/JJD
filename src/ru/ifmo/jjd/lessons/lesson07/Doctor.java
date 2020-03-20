package ru.ifmo.jjd.lessons.lesson07;

public class Doctor extends BattleUnit {
    private int medicineScore;

    public Doctor(int healthScore, int speed, int attackScore, int medicineScore) {
        super(healthScore, speed, attackScore);
        setMedicineScore(medicineScore);
    }

    public int getMedicineScore() {
        return medicineScore;
    }

    public void setMedicineScore(int medicineScore) {
        this.medicineScore = medicineScore;
    }

    @Override
    /*
     * Аннотация @Override говорит о том, что переопределяется родительский метод:
     * - либо это задание реализации метода интерфейса
     * - или переопределение метода родительского класса
     *
     * Без этой аннотации программа тоже будет работать, ничего с ней плохого не произойдёт, но это ещё и указатель
     * компилятору, что мы переопределяем метод: не меняем его название, его сигнатуру, возвращаемое значение
     * */
    public void attack(BattleUnit enemy) {
        System.out.println("Doctor attacks " + enemy);
    }

    @Override
    public void rest() {
        System.out.println("Doctor rests");
    }
}
