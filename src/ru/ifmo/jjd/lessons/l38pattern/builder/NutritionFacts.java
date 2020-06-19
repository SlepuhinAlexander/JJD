package ru.ifmo.jjd.lessons.l38pattern.builder;

public class NutritionFacts {
    private final int servings;
    private final int calories;
    private final int fat;

    // вложенный класс для создания объекта внешнего класса.
    public static class Builder {
        // обязательные параметры
        // (отмечаются final)
        private final int servings;

        // необязательные параметры
        // (не отмечаются final() и могут получать значения свойств по умолчанию)
        private int calories = 1;
        private int fat;

        // Исключительно обязательные параметры собираем в конструктор
        public Builder(int servings) {
            this.servings = servings;
        }

        // Для остальных создаются сеттеры
        // Сеттер устанавливает значение свойства и возвращает ссылку на текущий же объект (Builder) для возможности
        // дальнейшего построения.
        public Builder calories(int caloriesVal) {
            calories = caloriesVal;
            return this;
        }

        public Builder fat(int fatVal) {
            fat = fatVal;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
    }
}
