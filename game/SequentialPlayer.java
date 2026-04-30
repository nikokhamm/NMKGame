package game;

public class SequentialPlayer implements IPlayer {
    @Override
    public Move move(IPosition position, Cell cell) throws Exception {
        for (int i = 0; i < position.getRowNumber(); i++) {
            for (int j = 0; j < position.getCollumnNumber(); j++) {
                Move move = new Move(i, j, cell);
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        return new Move(0, 0, Cell.E);
    }
}