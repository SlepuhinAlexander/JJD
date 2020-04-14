package ru.ifmo.jjd.exercises.lesson11.battleships.engine;

import ru.ifmo.jjd.exercises.lesson11.battleships.Settings;

import java.util.Collection;
import java.util.HashSet;

public class Field {
    public final int height;
    public final int width;
    public final Cell[][] cells;

    public Field(int height, int width) {
        if (height < Settings.FIELD_HEIGHT_MIN || height > Settings.FIELD_HEIGHT_MAX) {
            throw new IllegalArgumentException("Field height " + height + " must be in [" +
                    Settings.FIELD_HEIGHT_MIN + ";" + Settings.FIELD_HEIGHT_MAX + "]");
        }
        if (width < Settings.FIELD_WIDTH_MIN || width > Settings.FIELD_WIDTH_MAX) {
            throw new IllegalArgumentException("Field width " + width + " must be in [" +
                    Settings.FIELD_WIDTH_MIN + ";" + Settings.FIELD_WIDTH_MAX + "]");
        }
        this.height = height;
        this.width = width;
        cells = new Cell[height][width];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Field() {
        this(Settings.FIELD_HEIGHT_DEFAULT, Settings.FIELD_WIDTH_DEFAULT);
    }

    public void clear() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public HashSet<Cell> getNearest(Cell cell) {
        if (cell == null) {
            throw new NullPointerException("Argument 'cell' cannot be null");
        }
        HashSet<Cell> result = new HashSet<>();
        int[][] nearest = {{cell.x - 1, cell.y}, {cell.x + 1, cell.y}, {cell.x, cell.y - 1}, {cell.x, cell.y + 1}};
        for (int[] ind : nearest) {
            if (ind[0] >= 0 && ind[0] < height && ind[1] >= 0 && ind[1] < width) {
                result.add(cells[ind[0]][ind[1]]);
            }
        }
        return result;
    }

    public HashSet<Cell> getNear(Cell cell) {
        if (cell == null) {
            throw new NullPointerException("Argument 'cell' cannot be null");
        }
        HashSet<Cell> result = new HashSet<>();
        for (int i = cell.x - 1; i <= cell.x + 1; i++) {
            for (int j = cell.y - 1; j <= cell.y + 1; j++) {
                if (i >= 0 && i < height && j >= 0 && j < width) {
                    result.add(cells[i][j]);
                }
            }
        }
        result.remove(cell);
        return result;
    }

    public HashSet<Cell> getNear(Collection<Cell> cells) {
        if (cells == null) {
            throw new NullPointerException("Argument 'cells' cannot be null");
        }
        HashSet<Cell> result = new HashSet<>();
        cells.forEach(cell -> result.addAll(getNear(cell)));
        result.removeAll(cells);
        return result;
    }

    public HashSet<Cell> getNear(Ship ship) {
        if (ship == null) {
            throw new NullPointerException("Argument 'ship' cannot be null");
        }
        return getNear(ship.cells);
    }

    public String[] toStringArr() {
        String[] result = new String[height + 1];
        StringBuilder builder = new StringBuilder();
        String buf = "";
        if (cells.length >= 10) {
            buf = " ";
        }
        builder.append(buf).append("  ");
        for (int i = 0; i < width; i++) {
            builder.append((char) ('A' + i)).append(" ");
        }
        result[0] = builder.toString();
        for (int i = 0; i < cells.length; i++) {
            builder.setLength(0);
            if (i < 9) {
                builder.append(buf);
            }
            builder.append(i + 1).append(' ');
            for (int j = 0; j < cells[i].length; j++) {
                builder.append(cells[i][j].getState().symbol).append(' ');
            }
            result[i + 1] = builder.toString();
        }
        return result;
    }

    // checks if given cell is from this field and with the same coordinates
    public boolean contains(Cell cell) {
        if (cell == null) {
            throw new NullPointerException("Cell cannot be null");
        }
        if (cell.x < 0 || cell.x >= height || cell.y < 0 || cell.y > width) {
            return false;
        }
        return cell == cells[cell.x][cell.y];
    }
}
