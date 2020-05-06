package ru.ifmo.jjd.lessons.l21serialize.serialization;

import java.io.*;
import java.lang.invoke.MethodHandles;

public class Main {
    public static void main(String[] args) {
        Pupil pupil1 = new Pupil();
        pupil1.setAge(7);
        pupil1.setName("pupil1");
        pupil1.setGroup(new Group("Химия", 12));
        pupil1.learn();

        Pupil pupil2 = new Pupil();
        pupil2.setAge(8);
        pupil2.setName("pupil2");
        pupil2.setGroup(new Group("Биология", 22));
        pupil2.learn();

        Director director = new Director("Super Speech");
        director.setAge(44);
        director.setName("director");
        director.setRating(2);

        Class<?> thisClass = MethodHandles.lookup().lookupClass();
        File classFolder = new File(thisClass.getClassLoader().
                getResource(thisClass.getName().replace(".", "/") + ".class").
                getFile()).getParentFile();
        File file = new File(classFolder + File.separator + "pupil.bin");
        System.out.println(pupil1);
        try {
            objectToFile(file, pupil1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(pupil2);
        try {
            objectToFile(file, pupil2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pupil pupilFromFile = null;
        try {
            pupilFromFile = (Pupil) getObjFromFile(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(pupilFromFile);
        System.out.println(pupil1.equals(pupilFromFile));

        Pupil pupilFromFile2 = null;
        try {
            pupilFromFile2 = (Pupil) getObjFromFile(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(pupilFromFile2);
        System.out.println(pupil2.equals(pupilFromFile2)); // false
        // здесь прочитан первый объект из файла
        // нужно чтобы метод чтения либо последовательно перебирал объекты из файла; либо возвращал коллекцию объектов
        // удобнее всего сохранять и восстанавливать один большой объект, в котором содержится всё остальное.

        File fileDirector = new File(classFolder + File.separator + "director.bin");
        System.out.println(director);
        try {
            objectToFile(fileDirector, director);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Director direcrorFromFile = null;
        try {
            direcrorFromFile = (Director) getObjFromFile(fileDirector);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(direcrorFromFile);
        System.out.println(director.equals(direcrorFromFile));
        /*
         * Director{speech='Super Speech', rating=2, name='director', age=44}
         * Director{speech='Super Speech', rating=2, name='Человек', age=44}
         * true
         * */
    }

    // метод записи объекта в файл (сериализация)
    private static void objectToFile(File file, Object obj) throws IOException {
        try (FileOutputStream fileOutput = new FileOutputStream(file, true);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(obj);
            /*
             * objectOutput преобразует объект в набор байт
             * и передаёт его в тот OutputStream, который с ним связан, т.е. в файл
             * как результат, объект будет записан в файл
             * */
        }
    }

    // метод получения объекта из файла (десериализация)
    private static Object getObjFromFile(File file) throws IOException, ClassNotFoundException {
        Object obj;
        try (FileInputStream fileInput = new FileInputStream(file);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            obj = objectInput.readObject();
            return obj;
            /*
             * objectInput получает набор байт из InputStream (в данном случае из файла)
             * преобразует в объект
             * и возвращает
             *
             * В классе восстанавливаемого объекта должен быть конструктор без параметров, иначе он не сможет собрать
             * объект.
             * */
        }
    }
}
