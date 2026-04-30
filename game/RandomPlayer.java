package game;

import java.util.Random;

public class RandomPlayer implements IPlayer {
    private final Random random = new Random();

    @Override
    public Move move(IPosition position, Cell cell) throws Exception {
        Move move;
        do {
            int row = random.nextInt(position.getRowNumber());
            int col = random.nextInt(position.getCollumnNumber());
            move = new Move(row, col, cell);
        } while (!position.isValid(move));
        return move;
    }
}