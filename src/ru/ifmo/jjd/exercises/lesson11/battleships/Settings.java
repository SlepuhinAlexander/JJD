package ru.ifmo.jjd.exercises.lesson11.battleships;

public class Settings {
    public static final int FIELD_WIDTH_MIN = 6;
    public static final int FIELD_WIDTH_DEFAULT = 10;
    public static final int FIELD_WIDTH_MAX = 20;

    public static final int FIELD_HEIGHT_MIN = 6;
    public static final int FIELD_HEIGHT_DEFAULT = 10;
    public static final int FIELD_HEIGHT_MAX = 20;

    public static final int SHIP_LENGTH_MIN = 1;
    public static final int SHIP_LENGTH_MAX = 6;
    public static final int SHIP_LIMIT = 25;
    public static final int[] SHIP_LENGTHS_DEFAULT = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    public static final String[] SHIP_NAMES = {
            "corvette (1 tile)",
            "submarine (2 tiles)",
            "destroyer (3 tiles)",
            "battleship (4 tiles)",
            "carrier (5 tiles)",
            "flagship (6 tiles)"};

    public static boolean showAIField = false;
}
