package utils;

import java.util.Locale;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner;

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
            if (scanner.hasNextBoolean()) {
                return scanner.nextBoolean();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Byte readByte(String message) {
        try {
            if (scanner.hasNextByte()) {
                return scanner.nextByte();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Short readShort(String message) {
        print(message);
        try {
            if (scanner.hasNextShort()) {
                return scanner.nextShort();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Integer readInteger(String message) {
        print(message);
        try {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Long readLong(String message) {
        print(message);
        try {
            if (scanner.hasNextLong()) {
                return scanner.nextLong();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Float readFloat(String message) {
        print(message);
        try {
            if (scanner.hasNextFloat()) {
                return scanner.nextFloat();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Double readDouble(String message) {
        print(message);
        try {
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            scanner.nextLine();
            e.printStackTrace();
            return null;
        }
    }

    public static Byte readByte(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextByte(radix)) {
                return scanner.nextByte(radix);
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Short readShort(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextShort(radix)) {
                return scanner.nextShort(radix);
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Integer readInteger(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextInt(radix)) {
                return scanner.nextInt(radix);
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
    }

    public static Long readLong(String message, int radix) {
        print(message);
        try {
            if (scanner.hasNextLong(radix)) {
                return scanner.nextLong(radix);
            } else {
                scanner.nextLine();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            scanner.nextLine();
            return null;
        }
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
