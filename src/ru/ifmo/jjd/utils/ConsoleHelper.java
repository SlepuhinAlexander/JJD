package ru.ifmo.jjd.utils;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ConsoleHelper {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
    }

    public static void print() {
        System.out.print("");
    }

    public static void print(boolean b) {
        System.out.print(b);
    }

    public static void print(char c) {
        System.out.print(c);
    }

    public static void print(short s) {
        System.out.print(s);
    }

    public static void print(int i) {
        System.out.print(i);
    }

    public static void print(long l) {
        System.out.print(l);
    }

    public static void print(float f) {
        System.out.print(f);
    }

    public static void print(double d) {
        System.out.print(d);
    }

    public static void print(char[] s) {
        System.out.print(s);
    }

    public static void print(String s) {
        System.out.print(s);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    public static void println() {
        System.out.println();
    }

    public static void println(boolean b) {
        System.out.println(b);
    }

    public static void println(char c) {
        System.out.println(c);
    }

    public static void println(short s) {
        System.out.println(s);
    }

    public static void println(int i) {
        System.out.println(i);
    }

    public static void println(long l) {
        System.out.println(l);
    }

    public static void println(float f) {
        System.out.println(f);
    }

    public static void println(double d) {
        System.out.println(d);
    }

    public static void println(char[] s) {
        System.out.println(s);
    }

    public static void println(String s) {
        System.out.println(s);
    }

    public static void println(Object o) {
        System.out.println(o);
    }

    public static void printText(String text, int wrapAt) {
        if (text == null) {
            System.out.println((String) null);
            return;
        }
        wrapAt = Math.min(Math.max(40, wrapAt), 200);
        outer:
        while (text.length() > wrapAt) {
            int nl = text.indexOf('\n');
            if (nl >= 0 && nl <= wrapAt) {
                System.out.print(text.substring(0, nl + 1));
                text = text.substring(nl + 1);
                continue;
            }
            for (int i = wrapAt; i > 0; i--) {
                if (text.charAt(i) == ' ') {
                    System.out.println(text.substring(0, i + 1));
                    text = text.substring(i + 1);
                    continue outer;
                }
            }
            break;
        }
        System.out.println(text);
    }

    public static void printText(String text) {
        printText(text, 100);
    }

    public static void println(boolean[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(byte[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(short[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(int[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(long[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(float[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(double[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static void println(Object[] arr) {
        if (arr == null) {
            System.out.println((String) null);
            return;
        }
        print("[");
        if (arr.length <= 1) {
            print(arr[0]);
            println("]");
            return;
        }
        print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            print("\n ");
            print(arr[i]);
        }
        println("]");
    }

    public static <T> void println(Collection<T> col) {
        if (col == null) {
            println((String) null);
            return;
        }
        if (col.size() <= 1) {
            print("{");
            col.forEach(ConsoleHelper::print);
            println("}");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        col.forEach(e -> {
            builder.append(e);
            builder.append("\n ");
        });
        builder.setLength(builder.length() - 2); // crop last separator
        builder.append("}");
        println(builder);
    }

    public static <K, V> void println(Map<K, V> map) {
        println(map, "%k=%v");
    }

    public static <K, V> void println(Map<K, V> map, String pattern) {
        if (map == null) {
            println((String) null);
            return;
        }
        if (StringHelper.isNullOrBlank(pattern)) pattern = "%k=%v";
        pattern = pattern.replaceAll("%K", "%k").replaceAll("%V", "%v");
        if (!pattern.contains("%k") || !pattern.contains("%v")) throw new IllegalArgumentException("invalid pattern '" +
                                                                                                   pattern + "'");
        String finalPattern = pattern;
        if (map.size() <= 1) {
            print("{");
            map.forEach((k, v) -> println(finalPattern.replaceAll("%k", k.toString()).
                    replaceAll("%v", v.toString())));
            println("}");
            return;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        map.forEach((k, v) -> {
            builder.append(finalPattern.replaceAll("%k", k.toString()).
                    replaceAll("%v", v.toString()));
            builder.append("\n ");
        });
        builder.setLength(builder.length() - 2); // crop last separator
        builder.append("}");
        println(builder);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(Locale.ENGLISH, format, args);
    }

    public static void printf(Locale locale, String format, Object... args) {
        System.out.printf(locale, format, args);
    }

    public static String read(String message) {
        print(message);
        try {
            return scanner.next();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readLine(String message) {
        print(message);
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Boolean readBoolean(String message) {
        print(message);
        try {
            if (scanner.hasNextBoolean()) return scanner.nextBoolean();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Byte readByte(String message) {
        try {
            if (scanner.hasNextByte()) return scanner.nextByte();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Short readShort(String message) {
        print(message);
        try {
            if (scanner.hasNextShort()) return scanner.nextShort();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Integer readInteger(String message) {
        print(message);
        try {
            if (scanner.hasNextInt()) return scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Long readLong(String message) {
        print(message);
        try {
            if (scanner.hasNextLong()) return scanner.nextLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Float readFloat(String message) {
        print(message);
        try {
            if (scanner.hasNextFloat()) return scanner.nextFloat();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Double readDouble(String message) {
        print(message);
        try {
            if (scanner.hasNextDouble()) return scanner.nextDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Byte readByte(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextByte(radix)) return scanner.nextByte(radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Short readShort(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextShort(radix)) return scanner.nextShort(radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Integer readInteger(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextInt(radix)) return scanner.nextInt(radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static Long readLong(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextLong(radix)) return scanner.nextLong(radix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.nextLine();
        return null;
    }

    public static String read() {
        return read("");
    }

    public static String readLine() {
        return readLine("");
    }

    public static Boolean readBoolean() {
        return readBoolean("");
    }

    public static Byte readByte() {
        return readByte("");
    }

    public static Short readShort() {
        return readShort("");
    }

    public static Integer readInteger() {
        return readInteger("");
    }

    public static Long readLong() {
        return readLong("");
    }

    public static Float readFloat() {
        return readFloat("");
    }

    public static Double readDouble() {
        return readDouble("");
    }
}
