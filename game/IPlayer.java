package game;

public interface IPlayer {
    Move move(IPosition position, Cell cell) throws Exception;
}