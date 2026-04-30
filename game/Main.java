package game;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Insert n, m - for board size, and k - for winning condition");
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();

            System.out.println("Would you like to play regular or arrange a tournament?");
            System.out.println("Choose: 1 - for regular, 2 - for tournament");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    Game game = new Game(true, new HumanPlayer(), new SequentialPlayer(), new NMKBoard(n, m, k));
                    game.play();
                }
                case 2 -> {
                    System.out.println("Insert n - for number of players, and then insert each of the players: Bad, Random, Sequential or Human ");

                    int num = sc.nextInt();
                    IPlayer[] playersClub = new IPlayer[num];
                    for (int i = 0; i < num; i++) {
                        String playerChoice = sc.next();
                        switch (playerChoice) {
                            case "Bad" -> {
                                playersClub[i] = new BadPlayer();
                            }
                            case "Human" -> {
                                playersClub[i] = new HumanPlayer();
                            }
                            case "Random" -> {
                                playersClub[i] = new RandomPlayer();
                            }
                            case "Sequential" -> {
                                playersClub[i] = new SequentialPlayer();
                            }
                            default -> {
                                System.out.print("Insert player type again");
                                i--;
                                System.out.println();
                            }
                        }
                    }

                    int[] scores = new int[num];

                    for (int i = 0; i < num; i++) {
                        for (int j = i + 1; j < num; j++) {
                            int result1 = new Game(true, playersClub[i], playersClub[j], new NMKBoard(n, m, k)).play();
                            scores[i] += result1;
                            scores[j] += (4 - result1);
                            int result2 = new Game(true, playersClub[j], playersClub[i], new NMKBoard(n, m, k)).play();
                            scores[i] += (4 - result2);
                            scores[j] += result2;
                        }
                    }

                    int winnerIndex = 0;
                    System.out.println("Resulting table of score: ");
                    for (int i = 0; i < num; i++) {
                        System.out.printf("Player %d has got %d and points", i + 1, scores[i]);
                        System.out.println();
                        if (scores[winnerIndex] < scores[i]) {
                            winnerIndex = i;
                        }
                    }
                    System.out.println();
                    System.out.printf("And the winner is Player number %d with %d points!!", winnerIndex + 1, scores[winnerIndex]);
                }
            }

        }
    }
}