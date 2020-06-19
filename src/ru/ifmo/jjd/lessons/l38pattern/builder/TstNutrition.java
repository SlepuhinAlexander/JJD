package ru.ifmo.jjd.lessons.l38pattern.builder;

public class TstNutrition {
    public static void main(String[] args) {
        NutritionFacts apple = new NutritionFacts.Builder(4)
                .fat(2)
                .calories(200)
                .build();
    }
}
