package game;

public interface IBoard {
    IPosition getPosition();

    Result makeMove(Move move);

    boolean hasExtraMove();

}