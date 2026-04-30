package game;

import java.util.Map;

public enum Cell {
    X,
    O,
    E,
    N; // N - unplayable

    public final static Map<Cell, Character> CELL_TO_SYMBOL = Map.of(
            Cell.X, 'X',
            Cell.O, '0',
            Cell.E, '.'
    );
}