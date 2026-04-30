package game;

import java.io.PrintStream;
import java.util.Scanner;

public class HumanPlayer implements IPlayer {
    Scanner sc;
    PrintStream out;

    public HumanPlayer(Scanner sc, PrintStream out) {
        this.sc = sc;
        this.out = out;
    }

    public HumanPlayer() {
        this(new Scanner(System.in), System.out);
    }

    private boolean wrongInput(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Move move(IPosition position, Cell cell) throws Exception {
        while (true) {
            out.println();
            out.println("Current position: ");
            out.println(position);
            out.println("Make your move! Insert row and collumn: ");
            String rowString = sc.next();
            String colString = sc.next();

            if (wrongInput(colString) || wrongInput(rowString)) {
                out.println();
                out.println("That move was invalid... You should insert just two digits");
            } else {
                int row = Integer.parseInt(rowString) - 1;
                int col = Integer.parseInt(colString) - 1;
                Move move = new Move(row, col, cell);
                if (position.isValid(move)) {
                    return move;
                }
                out.println();
                out.println("That move was invalid... Try to stay in bound of the playing field!");
            }
        }
    }
}