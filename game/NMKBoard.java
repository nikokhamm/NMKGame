package game;

import java.util.Arrays;

public class NMKBoard implements IBoard, IPosition {
    private final Cell[][] field;
    private final int n;
    private final int m;
    private final int k;
    private int drawCount;

    private boolean extraMove = false;

    public NMKBoard(int n, int m, int k) {
        this.n = n; // rows
        this.m = m; // colls
        this.k = k;
        drawCount = m * n;
        this.field = generateField();
    }

    @Override
    public boolean hasExtraMove() {
        return extraMove;
    }

    @Override
    public int getRowNumber() {
        return n;
    }

    @Override
    public int getCollumnNumber() {
        return m;
    }

    @Override
    public IPosition getPosition() {
        return this;
    }

    private Cell[][] generateField() {
        Cell[][] returnField = new Cell[n][m]; // Cell[row][col]
        for (Cell[] row : returnField) {
            Arrays.fill(row, Cell.E);
        }
        return returnField;
    }

    @Override
    public boolean isValid(Move move) {
        int col = move.getCol();
        int row = move.getRow();
        if (col < 0 || col >= m || row < 0 || row >= n) {
            return false;
        }
        return field[row][col] == Cell.E;
    }

    @Override
    public Result makeMove(Move move) {
        extraMove = false;

        if (!isValid(move)) {
            return Result.LOSS;
        }

        field[move.getRow()][move.getCol()] = move.getCell();

        drawCount--;

        if (checkWin(move)) {
            return Result.WIN;
        }

        if (drawCount == 0) {
            return Result.DRAW;
        }

        return Result.UNKNOWN;
    }

    private int checkWinDirection(int row, int col, Cell cell, int dx, int dy) {
        int count = 0;
        for (int i = 1; i < k; i++) {
            if (row + i * dy < 0 || row + i * dy >= n
                    || col + i * dx < 0 || col + i * dx >= m) {
                break;
            }
            if (field[row + i * dy][col + i * dx] == cell) {
                count++;
            } else {
                break;
            }
        }

        for (int t = 1; t < k; t++) {
            int i = -t;
            if (row + i * dy < 0 || row + i * dy >= n
                    || col + i * dx < 0 || col + i * dx >= m) {
                break;
            }
            if (field[row + i * dy][col + i * dx] == cell) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    private boolean checkWin(Move move) {
        int row = move.getRow();
        int col = move.getCol();
        Cell cell = move.getCell();

        int[][] delta = {
                {1, 0},   // up-down
                {0, 1},   // left-right
                {1, 1},   // main diag
                {1, -1}   // secondary diag
        };

        boolean winFlag = false;

        for (int[] d : delta) {
            int count = checkWinDirection(row, col, cell, d[0], d[1]);

            if (count + 1 >= 4) {
                extraMove = true;
            }

            if (count >= k - 1) {
                winFlag = true;
            }

        }

        return winFlag;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("  ");
        for (int i = 1; i <= m; i++) {
            stringBuilder.append(i);
        }
        stringBuilder.append(System.lineSeparator());
        for (int i = 0; i < n; i++) {
            stringBuilder.append(i + 1).append(' ');
            for (int j = 0; j < m; j++) {
                stringBuilder.append(Cell.CELL_TO_SYMBOL.get(field[i][j]));
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}