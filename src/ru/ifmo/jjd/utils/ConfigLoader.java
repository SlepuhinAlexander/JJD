package ru.ifmo.jjd.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class ConfigLoader {
    private static final String GLOBAL_CONFIG_PATH = System.getProperty("user.dir") + File.separator +
                                                     "sources" + File.separator + "global.properties";
    private static ConfigLoader configLoader;
    private static HashMap<File, Properties> configs;
    private static Properties paths;

    private ConfigLoader() {

    }

    public static ConfigLoader getConfigLoader() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
            configs = new HashMap<>();
            paths = new Properties();
            try (BufferedReader in = new BufferedReader(new FileReader(GLOBAL_CONFIG_PATH))) {
                paths.load(in);
                paths.stringPropertyNames().forEach(key -> {
                    File config = new File(paths.getProperty(key));
                    if (config.exists() && config.isFile() && config.canRead()) {
                        configs.put(config, new Properties());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configLoader;
    }

    public void loadConfig(File file) {
        if (!configs.containsKey(file)) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            configs.get(file).load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getConfig(File file) {
        if (!configs.containsKey(file)) return null;
        if (configs.get(file).size() == 0) loadConfig(file);
        return configs.get(file);
    }

    public Properties getConfig(String globalKey) {
        return getConfig(new File(paths.getProperty(globalKey, "")));
    }

    public File getConfigFile(String globalKey) {
        return new File(paths.getProperty(globalKey,""));
    }

    public String getProperty(File file, String key) {
        if (!configs.containsKey(file)) return null;
        if (configs.get(file).size() == 0) loadConfig(file);
        return configs.get(file).getProperty(key);
    }

    public String getProperty(String globalKey, String key) {
        return getProperty(new File(paths.getProperty(globalKey, "")), key);
    }

    public void saveConfig(File file) {
        if (!configs.containsKey(file)) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            configs.get(file).store(writer, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig(String globalKey) {
        saveConfig(new File(paths.getProperty(globalKey, "")));
    }

    public void setProperty(File file, String key, String newValue) {
        if (!configs.containsKey(file)) return;
        if (configs.get(file).size() == 0) loadConfig(file);
        configs.get(file).setProperty(key, newValue);
        saveConfig(file);
    }

    public void setProperty(String globalKey, String key, String newValue) {
        setProperty(new File(paths.getProperty(globalKey)), key, newValue);
    }
}
