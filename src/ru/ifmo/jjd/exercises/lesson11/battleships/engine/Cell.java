package ru.ifmo.jjd.exercises.lesson11.battleships.engine;

import ru.ifmo.jjd.exercises.lesson11.battleships.Settings;

public class Cell {
    public final int x; // row
    public final int y; // col
    private State state = State.NONE;

    public Cell(int x, int y) {
        if (x < 0 || x >= Settings.FIELD_HEIGHT_MAX) {
            throw new IndexOutOfBoundsException(x + " must be in [0;" + (Settings.FIELD_HEIGHT_MAX - 1) + "]");
        }
        if (y < 0 || y >= Settings.FIELD_WIDTH_MAX) {
            throw new IndexOutOfBoundsException(y + " must be in [0;" + (Settings.FIELD_WIDTH_MAX - 1) + "]");
        }
        this.x = x;
        this.y = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if (state == null) {
            throw new NullPointerException("New cell state cannot be null");
        }
        switch (state) {
            case NONE -> {
                if (this.state == State.EMPTY) {
                    this.state = state;
                }
            }
            case BUSY, EMPTY -> {
                if (this.state == State.NONE) {
                    this.state = state;
                }
            }
            case MISS -> {
                if (this.state == State.NONE || this.state == State.EMPTY) {
                    this.state = state;
                }
            }
            case HIT -> {
                if (this.state == State.NONE || this.state == State.EMPTY || this.state == State.BUSY) {
                    this.state = state;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "" + (char) (y + 65) + (x + 1);
    }

    public enum State {
        // unknown, free to place a battleship or to shoot to
        NONE(' '),
        // occupied by player's battleship
        BUSY('O'),
        // definitely empty, not occupied by a battleship; adjacent to a battleship; unreasonable to shoot to
        EMPTY('\u00B7'),
        MISS('*'),
        HIT('X');

        final char symbol;

        State(char symbol) {
            this.symbol = symbol;
        }
    }
}
