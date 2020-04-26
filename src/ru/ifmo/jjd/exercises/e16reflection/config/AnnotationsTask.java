package ru.ifmo.jjd.exercises.e16reflection.config;

import ru.ifmo.jjd.lessons.l18reflection.annotations.ConfigClass;
import ru.ifmo.jjd.lessons.l18reflection.annotations.Required;

import java.lang.reflect.*;
import java.util.ArrayList;

import static ru.ifmo.jjd.utils.StringHelper.uppercaseFirst;

public class AnnotationsTask {
    public static void main(String[] args) {
        Object o = configure(AnnotatedClass.class);
    }

    public static Object configure(Class<?> c) {
        if (c == null) return null;
        if (!c.isAnnotationPresent(ConfigClass.class)) return null;
        Object result = null;
        try {
            result = getInstance(c, true);
            setRequiredFields(result);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        if (result != null) {
            try {
                System.out.println(c.getMethod("toString").invoke(result));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static void setRequiredFields(Object o) {
        if (o == null) return;
        Class<?> c = o.getClass();
        ArrayList<Field> fields = new ArrayList<>();
        while (c != null) {
            Field[] classFields = c.getDeclaredFields();
            for (Field field : classFields) {
                if (field.isAnnotationPresent(Required.class)) {
                    fields.add(field);
                }
            }
            c = c.getSuperclass();
        }
        c = o.getClass();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            Method setter = null;
            try {
                setter = c.getMethod("set" + uppercaseFirst(fieldName), fieldType);
            } catch (NoSuchMethodException ignored) {
            }
            try {
                if (setter != null) {
                    setter.setAccessible(true);
                    setter.invoke(o, getInstance(fieldType));
                } else {
                    field.setAccessible(true);
                    field.set(o, getInstance(fieldType));
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private static Object getInstance(Class<?> c) throws ReflectiveOperationException {
        return getInstance(c, false);
    }

    private static Object getInstance(Class<?> c, boolean setRequiredFields) throws ReflectiveOperationException {
        if (c == null) return null;
        if (c == boolean.class || c == Boolean.class) return false;
        if (c.isPrimitive() || c == Character.class || c.getSuperclass() == Number.class) return 0;
        if (c == String.class) return "Dummy String";
        if (c.isArray()) {
            Class<?> componentType = c.getComponentType();
            Object[] array = (Object[]) Array.newInstance(componentType, 1);
            array[0] = getInstance(componentType, setRequiredFields);
            return array;
        }
        if (c.isEnum()) {
            return c.getEnumConstants()[0];
        }
        Constructor<?>[] constructors = c.getConstructors();
        if (constructors.length == 0) throw new ReflectiveOperationException("No constructor found");
        Constructor<?> availableConstructor = null;
        for (Constructor<?> constructor : constructors) {
            try {
                constructor.setAccessible(true);
                availableConstructor = constructor;
                break;
            } catch (InaccessibleObjectException | SecurityException ignored) {
            }
        }
        if (availableConstructor == null) throw new ReflectiveOperationException("Cannot get an available constructor");
        Class<?>[] parameterTypes = availableConstructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = getInstance(parameterTypes[i], setRequiredFields);
        }
        Object result = availableConstructor.newInstance(parameters);
        if (setRequiredFields) setRequiredFields(result);
        return result;
    }
}

/*
 * AnnotatedClass{Ancestor{number=0, array=[false], string='Dummy String', someEnum=null}, lists=[],
 * text='Dummy String', map=null}
 * */
