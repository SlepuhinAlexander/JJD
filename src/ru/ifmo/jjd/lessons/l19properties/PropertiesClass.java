package ru.ifmo.jjd.lessons.l19properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesClass {
    public static void main(String[] args) {
        /*
         * Класс Properties позволяет хранить значения в паре ключ-значение
         * ключ - строка, и значение - строка
         * ориентирован на работу с properties-файлами
         * */
        Properties properties = new Properties();
        /*
         * Получаем ссылку на текущий класс.
         * Из неё получаем ссылку на загрузчик текущего файла.
         * Из загрузчика классов получили поток загрузки из файла ресурсов.
         * */
        try (InputStream input = PropertiesClass.class.getClassLoader().
                getResourceAsStream("example.properties")) {
            /*
             * открыли файл example.properties на чтение.
             * */
            properties.load(input);
            /*
             * загрузили в объект properties все свойства из файла настроек
             * все настройки сохранены в мапе, соответсвтенно в key и value
             * */
        } catch (IOException e) {
            e.printStackTrace();
        }
        // получить значение по ключу
        System.out.println(properties.getProperty("key")); // value
        System.out.println(properties.getProperty("age")); // 578
        System.out.println(properties.getProperty("ads")); // null
        /*
         * как и с обычной картой, при запросе несуществующего свойства
         * */

        // добавить ещё свойство в объект properties
        properties.setProperty("ip", "97.167.23.9");
        /*
         * этот же метод обновит значение свойства по этому ключу, если этот ключ уже был в мапе
         * */

        /*
         * созданный объект properties существует только! в этом методе.
         * все загруженные настройки существую только в нём.
         * если будет создан другой объект properties в другом месте где-то - это будет отдельный независимый объект.
         * */


        // вызываем ConfigLoader
        ConfigLoader configLoader = ConfigLoader.getConfigLoader("example.properties");
        /*
         * когда метод getConfigLoader вызывается в первый раз, проверка есть ли созданный объект configLoader даст
         * false, будет сделан новый объект класса ConfigLoader, загрузятся свойства из .properties файла
         * когда метод getConfigLoader будет вызван во второй или в последующий раз, проверка есть ли созданный объект
         * даст true, будет возвращена ссылка на тот же самый объект, созданный при первом вызове
         * */
        System.out.println(configLoader.getProperties().getProperty("key")); // value
    }
}