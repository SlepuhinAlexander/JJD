package ru.ifmo.jjd.lessons.lesson08.school;

public class Main {
    public static void main(String[] args) {
        Teacher headTeacher = new Teacher("Антон Владимирович", "Селиванов", 44);
        School school = new School("Школа № 12", Director.appointDirector(headTeacher));
        school.addStudent(new Student("Владимир", "Петров", 7, "Физика"));
        school.addStudent(new Student("Петр", "Сергеев", 8, "Химия"));
        school.addStudent(new Student("Сергей", "Иванов", 9, "История"));
        school.addStudent(new Student("Иван", "Николаев", 10, "География"));
        school.addStudent(new Student("Николай", "Васильев", 11, "Математика"));
        school.addStudent(new Student("Василий", "Макаров", 12, "Труд"));
        school.addStudent(new Student("Макар", "Владимиров", 13, "Биология"));
        school.addStudent(new Student("Светлана", "Иванова", 8, "Музыка"));
        school.addStudent(new Student("Мария", "Николаева", 11, "Математика"));
        school.addStudent(new Student("Екатерина", "Васильева", 14, "Физика"));
        school.addStudent(new Student("Анастасия", "Макарова", 12, "Астрономия"));
        school.addStudent(new Student("Евгения", "Владимирова", 7, "Биология"));
        school.addStudent(new Student("Ирина", "Петрова", 10, "Литература"));
        school.addStudent(new Student("Галина", "Сергеева", 10, "История"));

        school.addTeacher(new Teacher("Марина Геннадьевна", "Семенюк", 43, "Литература"));
        school.addTeacher(new Teacher("Леонид Петрович", "Каган", 27, "Физика"));
        school.addTeacher(new Teacher("Семён Альбертович", "Штольц", 33, "Математика"));
        school.addTeacher(new Teacher("Анастасия Ивановна", "Поплавская", 47, "Биология"));
        school.addTeacher(new Teacher("Виктория Сергеевна", "Кузнецова", 49, "История"));
        school.addTeacher(new Teacher("Изольда Витальевна", "Вельдович", 43, "Химия"));
        school.addTeacher(new Teacher("Марина Геннадьевна", "Семенюк", 43, "Музыка"));
        school.addTeacher(new Teacher("Федор Григорьевич", "Земский", 56, "Труд"));

        school.academicPerformance();
        for (int i = 0; i < 20; i++) {
            school.schoolDay();
        }
        school.academicPerformance();
    }
}
