package ru.ifmo.jjd.lessons.l18reflection.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@ConfigClass(prefix = "text", version = 34)
public class AnnotationsDemo {
    @Required
    private String stringData;

    public static void main(String[] args) {
        Class<AnnotationsDemo> testClass = AnnotationsDemo.class;

        // получение всех аннотаций класса
        Annotation[] annotations = testClass.getAnnotations();
        System.out.println(Arrays.toString(annotations));
        // [@ru.ifmo.jjd.lessons.l18reflection.annotations.ConfigClass(version=34, prefix="text")]

        // проверка наличия конкретной аннотации у класса
        if (testClass.isAnnotationPresent(ConfigClass.class)) {
            System.out.println("аннотация ConfigClass"); // аннотация ConfigClass
            // получение ссылки на аннотацию
            ConfigClass configClass = testClass.getAnnotation(ConfigClass.class);
            // получение данных из аннотации
            System.out.println(configClass.prefix()); // text
            System.out.println(configClass.version()); // 34
        }

        // получение аннотаций полей
        Field[] fields = testClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            if (field.isAnnotationPresent(Required.class)) {
                System.out.println("поле с аннотацией Required"); // поле с аннотацией Required.
            }
        }

        // получение аннотаций методов
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getDeclaredAnnotations();
            if (method.isAnnotationPresent(Required.class)) {
                System.out.println("метод с аннотацией Required");
            }
        }
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    @Override
    public String toString() {
        return "AnnotationsDemo{" +
                "stringData='" + stringData + '\'' +
                '}';
    }
}
