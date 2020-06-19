package ru.ifmo.jjd.exercises.e17di.container;

import ru.ifmo.jjd.exercises.e17di.container.marks.ConfigClass;
import ru.ifmo.jjd.exercises.e17di.container.marks.Required;
import ru.ifmo.jjd.exercises.e17di.container.marks.RequiredClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static ru.ifmo.jjd.utils.StringHelper.uppercaseFirst;

public class DIContainer {
    private final Map<String, Object> configObjects = new HashMap<>();
    private final Map<String, Object> requiredObjects = new HashMap<>();
    private Path packageDirectory;
    private Path configDir;

    private DIContainer() {
    }

    public static DIContainer of(Path packageDirectory, Path configDir) throws IOException,
            ReflectiveOperationException {
        Objects.requireNonNull(packageDirectory);
        if (!Files.exists(packageDirectory)) throw new FileNotFoundException(packageDirectory + " does not exist");
        if (!Files.isDirectory(packageDirectory))
            throw new IllegalArgumentException(packageDirectory + " is not a directory");
        Objects.requireNonNull(configDir);
        if (!Files.exists(configDir)) throw new FileNotFoundException(configDir + " does not exist");
        if (!Files.isDirectory(configDir))
            throw new IllegalArgumentException(configDir + " is not a directory");
        DIContainer container = new DIContainer();
        container.packageDirectory = packageDirectory;
        container.configDir = configDir;
        container.initialize();
        return container;
    }

    private void initialize() throws IOException, ReflectiveOperationException {
        String classPath = System.getProperty("java.class.path");
        // fixing some weird debugger issue
        classPath = classPath.contains(";") ? classPath.substring(0, classPath.indexOf(';')) : classPath;
        final String classPathFinal = classPath; // fixing lambda expr now, after fixing debugger
        Map<String, ArrayList<Class<?>>> allClasses = Files.walk(packageDirectory)
                .filter(Files::isRegularFile)
                .filter(path -> path.toFile().getName().endsWith(".class"))
                .map(path -> Paths.get(classPathFinal).relativize(path).toString()
                        .replace('\\', '.').replace(".class", ""))
                .map((String className) -> {
                    try {
                        return Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(aClass -> aClass.getAnnotations().length > 0)
                .filter(aClass -> !Modifier.isAbstract(aClass.getModifiers()))
                .collect(Collectors.groupingBy(aClass -> {
                    if (aClass.isAnnotationPresent(ConfigClass.class)) return "ConfigClass";
                    if (aClass.isAnnotationPresent(RequiredClass.class)) return "RequiredClass";
                    return "Other";
                }, Collectors.toCollection(ArrayList::new)));
        Map<String, Class<?>> configClasses = allClasses.get("ConfigClass").stream()
                .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));
        Map<String, Class<?>> requiredClasses = allClasses.get("RequiredClass").stream()
                .collect(Collectors.toMap(Class::getSimpleName, Function.identity()));



        for (String className : configClasses.keySet()) {
            configObjects.put(className, instantiate(configClasses.get(className)));
        }
        for (String className : configObjects.keySet()) {
            Class<?> aClass = configClasses.get(className);
            ConfigClass annotation = Objects.requireNonNull(aClass.getAnnotation(ConfigClass.class));
            Path valuesFile = configDir.resolve(annotation.file());
            if (!Files.exists(valuesFile) || !Files.isRegularFile(valuesFile) || !Files.isReadable(valuesFile)) {
                throw new FileNotFoundException(valuesFile.toString());
            }
            String prefix = annotation.prefix();
            Properties values = new Properties();
            values.load(Files.newBufferedReader(valuesFile));
            List<Field> fields = new LinkedList<>();
            Class<?> parentClass = aClass;
            while (parentClass != null) {
                fields.addAll(Arrays.asList(parentClass.getDeclaredFields()));
                parentClass = parentClass.getSuperclass();
            }
            for (Field field : fields) {
                String key = prefix + "." + field.getName();
                String value = values.getProperty(key);
                if (value == null) {
                    throw new ReflectiveOperationException("Failed to find '" + key + "' key in '" + valuesFile + "'");
                }
                value = value.trim();
                Class<?> fieldType = field.getType();
                Object parsedValue = switch (fieldType.getSimpleName()) {
                    case "boolean", "Boolean" -> Boolean.parseBoolean(value);
                    case "char", "Character" -> value.charAt(0);
                    case "byte", "Byte", "short", "Short", "int", "Integer" -> Integer.parseInt(value);
                    case "long", "Long" -> Long.parseLong(value);
                    case "float", "Float", "double", "Double" -> Double.parseDouble(value);
                    case "String" -> value;
                    default -> throw new ReflectiveOperationException("Unexpected field type '"
                                                                      + field.getType() + "' in @ConfigClass class '"
                                                                      + aClass.getName() + "'");
                };
                Method setter;
                setter = Arrays.stream(aClass.getMethods())
                        .filter(method -> method.getName().equals("set" + uppercaseFirst(field.getName())))
                        .filter(method -> method.isAnnotationPresent(Required.class))
                        .findFirst().orElse(null);
                if (setter != null) {
                    setter.invoke(configObjects.get(className), parsedValue);
                } else {
                    field.setAccessible(true);
                    field.set(configObjects.get(className), parsedValue);
                }
            }
        }


        for (String className : requiredClasses.keySet()) {
            requiredObjects.put(className, instantiate(requiredClasses.get(className)));
        }
        for (String className : requiredObjects.keySet()) {
            Class<?> aClass = requiredClasses.get(className);
            List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Required.class))
                    .collect(Collectors.toList());
            for (Field field : fields) {
                Class<?> fieldType = field.getType();
                Object value = configObjects.get(fieldType.getSimpleName());
                if (value == null) value = requiredObjects.get(fieldType.getSimpleName());
                if (value == null) {
                    throw new ReflectiveOperationException("Failed to find an instantiated object for '"
                                                           + fieldType.getName() + "'");
                }
                Method setter;
                setter = Arrays.stream(aClass.getMethods())
                        .filter(method -> method.getName().equals("set" + uppercaseFirst(field.getName())))
                        .filter(method -> method.isAnnotationPresent(Required.class))
                        .findFirst().orElse(null);
                if (setter != null) {
                    setter.invoke(requiredObjects.get(className), value);
                } else {
                    field.setAccessible(true);
                    field.set(requiredObjects.get(className), value);
                }
            }
        }
    }

    private Object instantiate(Class<?> aClass) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<?> aConstructor = Objects.requireNonNull(Objects.requireNonNull(aClass).getConstructor());
        aConstructor.setAccessible(true);
        return Objects.requireNonNull(aConstructor.newInstance());
    }

    public Object get(Class<?> aClass) {
        return requiredObjects.get(aClass.getSimpleName());
    }

    public Object get(String name) {
        return requiredObjects.get(name);
    }
}
