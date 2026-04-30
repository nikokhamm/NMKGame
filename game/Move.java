package game;

public class Move {
    private final int row;
    private final int col;
    private final Cell cell;
    private boolean extraMove;

    public Move(int row, int col, Cell cell) {
        this.cell = cell;
        this.col = col;
        this.row = row;
        extraMove = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Cell getCell() {
        return cell;
    }

    public void setExtra() {
        extraMove = true;
    }

    @Override
    public String toString() {
        return "row: " + row + ", col: " + row;
    }
}