package ru.ifmo.jjd.exercises.lesson11.battleships.engine;

import java.util.HashSet;

public class Ship {
    public final HashSet<Cell> cells;

    public Ship(HashSet<Cell> cells) {
        if (cells == null) {
            throw new NullPointerException("Ship argument 'cells' cannot be null");
        }
        this.cells = cells;
    }

    public boolean checkIsSunk() {
        if (!cells.isEmpty()) {
            boolean isSunk = true;
            for (Cell c : cells) {
                isSunk &= c.getState() == Cell.State.HIT;
            }
            return isSunk;
        }
        return false;
    }
}
