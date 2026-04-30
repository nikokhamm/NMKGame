package game;

//game gamaet, vse hodi tut pravilnie

public class Game {
    private final IPlayer p1;
    private final IPlayer p2;
    private final IBoard board;
    private final boolean log;


    public Game(boolean log, IPlayer p1, IPlayer p2, IBoard board) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = board;
        this.log = log;
    }

    public int play() {
        while (true) {
            Result r1 = playerMoves(p1, Cell.X);
            int result1 = encodeResult(r1, 1);
            if (result1 >= 0) {
                return result1;
            }

            if (board.hasExtraMove()) {
                System.out.printf("Player %s has extra move!", "X");
                System.out.println();
                r1 = playerMoves(p1, Cell.X);
                result1 = encodeResult(r1, 1);
                if (result1 >= 0) {
                    return result1;
                }
            }

            Result r2 = playerMoves(p2, Cell.O);
            int result2 = encodeResult(r2, 2);
            if (result2 >= 0) {
                return result2;
            }

            if (board.hasExtraMove()) {
                System.out.printf("Player %s has extra move!", "0");
                System.out.println();
                r2 = playerMoves(p2, Cell.O);
                result2 = encodeResult(r2, 2);
                if (result2 >= 0) {
                    return result2;
                }
            }
        }
    }

    private Result playerMoves(IPlayer player, Cell cell) {
        try {
            Move move = player.move(board.getPosition(), cell);
            Result result = board.makeMove(move);
            if (log) {
                System.out.printf("Player %s makes move (%d, %d) and gets %s%n%s%n", cell.toString(), move.getRow() + 1,
                        move.getCol() + 1, result, board);
                System.out.println();
            }
            return result;
        } catch (Exception e) {
            if (log) {
                System.out.printf("Player %s makes a bad move and LOSSES", cell.toString());
                System.out.println();
                System.out.println();
            }
            return Result.LOSS;
        }
    }

    public int encodeResult(Result result, int turn) {
        if (turn == 1) {
            return switch (result) {
                case WIN -> 3;
                case LOSS -> 1;
                case DRAW -> 0;
                case UNKNOWN -> -1;
            };
        }
        return switch (result) {
            case WIN -> 1;
            case LOSS -> 3;
            case DRAW -> 0;
            case UNKNOWN -> -1;
        };
    }
}