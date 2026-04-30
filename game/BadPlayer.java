package game;

public class BadPlayer implements IPlayer {
    @Override
    public Move move(IPosition position, Cell cell) throws Exception {
        throw new UnsupportedOperationException();
    }
}