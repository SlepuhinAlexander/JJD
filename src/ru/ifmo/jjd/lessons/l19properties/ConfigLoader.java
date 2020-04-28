package ru.ifmo.jjd.lessons.l19properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/*
 * Создадим возможность делать только один объект Properties на всю программу.
 *
 * Будем считать, что класс ConfigLoader будет ответственен за загрузку и хранение настроек нашей программы.
 * Сделаем так, что объект класса ConfigLoader можно было создавать только один на всю программу.
 * */
public class ConfigLoader {
    /*
     * создаём одну статическую ссылку на объект ConfigLoader
     * она будет использоваться для хранения единственного экземпляра класса ConfigLoader
     * */
    private static ConfigLoader configLoader;

    private Properties properties;
    private String propertiesFileName;

    /*
     * Конструктор закрытый, значит объект этого класса вне этого класса создать не можем.
     * */

    private ConfigLoader(String fileName) {
        this.properties = new Properties();
        setPropertiesFileName(fileName);
        loadProperties();
    }
    // создаём статический метод, возвращающий объект ConfigLoader

    private void setPropertiesFileName(String propertiesFileName) {
        if (propertiesFileName == null) throw new NullPointerException();
        this.propertiesFileName = propertiesFileName;
    }

    public Properties getProperties() {
        return properties;
    }

    public static ConfigLoader getConfigLoader(String fileName) {
        if (fileName == null) throw new NullPointerException();
        if (configLoader == null) {
            configLoader = new ConfigLoader(fileName);
        }
        return configLoader;
    }

    /*
     * Загружает свойства из файла propertiesFileName
     * в объект this.properties
     * */
    private void loadProperties() {
        try (InputStream input = PropertiesClass.class.getClassLoader().
                getResourceAsStream(propertiesFileName)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
