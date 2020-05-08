package ru.ifmo.jjd.lessons.l22nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathsDemo {
    public static void main(String[] args) {
        /*
         * NIO для работы с файлами использует классы
         * Path
         * Paths
         * Files
         *
         * Path - это путь:
         * - создание пути
         * - сравнение путей
         * - получение информации с путями
         * используется с методами класса Files
         * */

        /*
         * В классе Paths есть только метод get для того чтобы вернуть объект типа Path.
         * */
        Path path1 = Paths.get("C:\\Users\\Superuser\\IdeaProjects\\JJD\\src\\ru\\ifmo\\jjd" +
                               "\\lessons\\l22nio\\README.md");
        /* Путь может быть несуществующим */

        System.out.println(path1.getFileSystem().getRootDirectories()); //[C:\, D:\, E:\, F:\]

        // приведение объекта Path к объекту File
        System.out.println(path1.toFile());

        Path path2 = Paths.get("");
        System.out.println(path2.toAbsolutePath()); // C:\Users\Superuser\IdeaProjects\JJD
        /*
         * toAbsolutePath() возвращает Path, который является полным путём.
         * Относительный путь установлен в ноль, значит вернёт текущую рабочую директорию проекта
         * */
        System.out.println(path2.toAbsolutePath().getParent()); // C:\Users\Superuser\IdeaProjects
        System.out.println(path2.toAbsolutePath().getRoot()); // C:\

        System.out.println(path1.toAbsolutePath().startsWith(path2.toAbsolutePath()));
        System.out.println(path1.endsWith("md"));

        Path path3 = Paths.get("C:\\Users\\Superuser\\IdeaProjects\\JJD\\src\\ru\\ifmo\\jjd\\lessons\\l20io\\filepath" +
                               ".txt");

        System.out.println("path1.relativize(path3)=" + path1.relativize(path3));
        // path1.relativize(path3)=..\..\l20io\filepath.txt
        /*
         * вычисляет путь одного пути относительно другого
         * */

        System.out.println(path1.compareTo(path3));
    }
}
