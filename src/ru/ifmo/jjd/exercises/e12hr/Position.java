package ru.ifmo.jjd.exercises.e12hr;

public enum Position {
    CEO("CEO", 6, 4000, 6000),
    CTO("CTO", 5, 3300, 4400),
    CIO("CIO", 5, 3000, 4000),
    IT_DIRECTOR("IT Director", 4, 2200, 3500),
    IT_MANAGER("IT Manager", 3, 1500, 2800),
    LEAD_DEVELOPER("Lead Developer", 4, 2000, 3000),
    SENIOR_DEVELOPER("Senior Developer", 3, 1400, 2500),
    DEVELOPER("Developer", 2, 1200, 1600),
    JUNIOR_DEVELOPER("Junior Developer", 1, 800, 1200),
    TRAINEE("Trainee", 0, 400, 600);

    final String name;
    final int seniority;
    final int lowerSalary;
    final int upperSalary;

    Position(String name, int seniority, int lowerSalary, int upperSalary) {
        this.name = name;
        this.seniority = seniority;
        this.lowerSalary = lowerSalary;
        this.upperSalary = upperSalary;
    }

    public static Position nameToValue(String name) {
        if ("".equals(name)) {
            return null;
        }
        for (Position position : Position.values()) {
            if (position.name.equalsIgnoreCase(name)) {
                return position;
            }
        }
        return null;
    }
}
