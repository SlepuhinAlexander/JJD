package ru.ifmo.jjd.lessons.l08staticfinal;

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
    public void attack(BattleUnit enemy) {
        System.out.println("Doctor attacks " + enemy);
    }

    @Override
    public void rest() {
        System.out.println("Doctor rests");
    }
}
