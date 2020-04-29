package ru.ifmo.jjd.lessons.l20io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class IODemo {
    public static void main(String[] args) {
        String path = "src" + File.separator + IODemo.class.getPackageName().replace(".", File.separator)
                + File.separator;
        File file = new File(path + "filepath.txt");
        /*
         * Указывается путь до файла. Можно указать полный путь; можно относительный.
         * Относительные пути считаются от корня проекта.
         * */
        /*
         * Методами класса File можно создавать/удалять файлы/директории, проверять наличие файла/директории
         * */

        try {
            System.out.println("file.createNewFile() : " + file.createNewFile()); // file.createNewFile() : true
            /*
             * Метод createNewFile() пытается создать новый файл с указанным путём и возвращает true|false
             * false если файл уже есть. Не пересоздаст.
             * */
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file.exists() : " + file.exists()); // file.exists() : true
        /*
         * проверяет, существует ли файл/директория
         * */
        System.out.println("file.isDirectory() : " + file.isDirectory()); // file.isDirectory() : false
        /*
         * проверяет, является ли директорией
         * */
        System.out.println("file.isFile() : " + file.isFile()); // file.isFile() : true
        /*
         * проверяет, является ли файлом
         * */
        System.out.println("file.canRead() : " + file.canRead()); // file.canRead() : true
        /*
         * проверяет, доступен ли для чтения
         * */
        System.out.println("file.canWrite() : " + file.canWrite()); // file.canWrite() : true
        /*
         * проверяет, доступен ли для записи
         * */
        System.out.println("file.canExecute() : " + file.canExecute()); // file.canExecute() : true
        /*
         * проверяет, доступен ли для исполнения
         * */

        // метод listFiles() выдаёт массив файлов/директорий в вызывающем файле
        System.out.println(Arrays.toString(new File("src/ru/ifmo/jjd/lessons/l20io").listFiles()));
        /*
         * [src\ru\ifmo\jjd\lessons\l20io\filepath.txt,
         *  src\ru\ifmo\jjd\lessons\l20io\IODemo.java,
         *  src\ru\ifmo\jjd\lessons\l20io\README.md]
         * */
        /*
         * Все методы File не рекурсивные: если нужны все файлы в поддиректориях, нужно вызывать метод рекурсивно.
         * */

        try {
            writeToFile(file, "hello", true);
            bufferedWriteToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            writeToFile(file, "hello", false);
            System.out.println(readByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byte[] bytes = imageToByteArray(new File(path + "ecology.png"));
            byteArrayToImage(new File(path + "copy_ecology.png"), bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // создадим простейший метод записи текста в файл
    /*
     * записывает строку data в файл file.
     * append флаг определяет дописывать в конец файла или перезаписывать файл.
     * */
    private static void writeToFile(File file, String data, boolean append) throws IOException {
        /*
         * сначала нужно открыть соединение с файлом (открыть файл для записи)
         *
         * синтаксис try-with-resources появился с Java 7
         * Когда выполнение блока try закончится успешно, либо когда в блоке try будет Exception,
         * - у объектов, созданных внутри () вызывается метод close() - т.е. закрытие ресурсов.
         *      класс этих объектов должен реализовывать интерфейс AutoCloseable
         *          (он гарантирует наличие метода close() у объектов)
         * - сначала закрываются ресурсы, потом обрабатывается ошибка.
         *      (в отличие от случая с использованием finally{} - в нём сначала произойдёт обработка ошибки из catch,
         *      а только потом закрытие ресурсов в finally{})
         * */
        try (FileOutputStream out = new FileOutputStream(file, append)) {
            byte[] bytes = data.getBytes(); // строка как массив байт
            out.write(bytes); // запись массива байт в файл
        }
    }

    // Буферизованная запись в файл
    private static void bufferedWriteToFile(File file) throws IOException {
        /*
         * Добавляется промежуточный буфер
         * file <--- [buffer(ссылка на fileOutput)] <--- application
         * Теперь данные, которые нужно будет записать в файл, предварительно будем складывать в буфер, а
         * непосредственно запись в файл будет происходить только при заполнении буфера.
         * */
        try (FileOutputStream fileOutput = new FileOutputStream(file);
             BufferedOutputStream out = new BufferedOutputStream(fileOutput)) {
            /*
             * С файлом работает именно FileOutputStream, только он будет записывать данные в файл.
             * BufferedOutputStream реализует буфер и передаёт собранные данные в FileOutputStream, когда это будет
             * необходимо (когда накопится буфер или будет явно вызвана запись)
             *
             * По умолчанию, размер буфера - 8192 байта. В конструкторе можно задать другой размер буфера.
             * */
            for (int i = 0; i < 1_000; i++) {
                out.write((i + " ").getBytes());
            }
        }
    }

    // Чтение из файла
    private static String readByteArray(File file) throws IOException {
        String result;
        try (FileInputStream in = new FileInputStream(file);
             ByteArrayOutputStream arrOut = new ByteArrayOutputStream()) {
            /*
             * ByteArrayOutputStream - не обёртка, он отдельно стоит.
             * Он используется когда данные нужно прочитать в массив байт.
             * */
            byte[] buf = new byte[512];
            int data;
            while ((data = in.read(buf)) > 0) {
                /*
                 * метод read() возвращает количество прочитанных байт.
                 * когда будет нечего читать, он вернёт -1, поэтому проверяем на > 0
                 * */
                System.out.println(data);
                System.out.println(Arrays.toString(buf));
                arrOut.write(buf, 0, data); // пишем в ByteArray всё что прочитали из буфера
                /*
                 * offset тут указывает на индекс от даты
                 * */
            }
            result = new String(arrOut.toByteArray());
        }
        return result;
    }

    // метод imageToByteArray преобразует изображение из file в массив байт bytes
    public static byte[] imageToByteArray(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        ByteArrayOutputStream arrOutput = new ByteArrayOutputStream();
        ImageIO.write(image, "png", arrOutput);
        return arrOutput.toByteArray();
    }


    /*
     * Передаём изображение из одной программы в другую. Передают они информацию как последовательность байт.
     * Для этого нужно разобрать изображение на последовательность байт, а потом собрать.
     *
     * Метод byteArrayToImage собирает массив байт bytes в изображение и записывает его в файл file
     * */
    public static boolean byteArrayToImage(File file, byte[] bytes) throws IOException {
        try (ByteArrayInputStream arrInput = new ByteArrayInputStream(bytes)) {
            BufferedImage image = ImageIO.read(arrInput);
            return ImageIO.write(image, "png", file);
        }
    }

    /*
     * Метод, для чтения из нескольких файлов в одну строку
     * */
    private static String readFromMultipleFiles(File... files) throws IOException {
        String result = null;

        if (files.length == 2) {
            // если два файла
            try (FileInputStream in = new FileInputStream(files[0]);
                 FileInputStream in1 = new FileInputStream(files[1]);
                 ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                SequenceInputStream sequenceInput = new SequenceInputStream(in, in1);
                byte[] buf = new byte[512];
                int data;
                while ((data = sequenceInput.read(buf)) > 0) {
                    bout.write(buf, 0, data);
                    /*
                     * SequenceInputStream осуществляет переключение с первого потока на второй, когда первый закончится
                     * Непосредственного взаимодействия с файлами не осуществляет.
                     * */
                }
                result = new String(bout.toByteArray());
            }
        } else {
            Vector<InputStream> sequence = new Vector<>();
            for (File file : files) {
                sequence.add(new FileInputStream(file));
            }
            SequenceInputStream sequenceInput = new SequenceInputStream(sequence.elements());
        }
        return result;
    }
}
