package game;

public interface IPosition {
    boolean isValid(Move move);

    int getRowNumber();

    int getCollumnNumber();
}