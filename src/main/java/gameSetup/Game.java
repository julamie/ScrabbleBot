package gameSetup;

import boardStructure.Bag;
import boardStructure.Board;
import output.Output;
import playerBehaviour.HumanPlayer;
import playerBehaviour.Player;

import java.util.Random;

public class Game {
    private final Board board;
    private final Bag bag;
    private final Player[] players;

    public Game(Language language, int numHumans, int numBots) {
        this.board = new Board();
        this.bag = new Bag(language);
        this.players = createPlayersInRandomOrder(numHumans, numBots);
    }

    private Player[] createPlayersInRandomOrder(int numHumans, int numBots) {
        int numPlayers = numHumans + numBots;
        Player[] players = new Player[numPlayers];

        int i = 0;
        while (i < numPlayers) {
            if (numHumans == 0) players[i] = new HumanPlayer(this.board, this.bag);
            else if (numBots == 0) players[i] = new HumanPlayer(this.board, this.bag); // TODO: Replace with BotPlayer
            else {
                Player newPlayer = createRandomPlayerType();
                if (newPlayer instanceof HumanPlayer) numPlayers--;
                else numBots--;
            }

            i++;
        }

        return players;
    }

    private Player createRandomPlayerType() {
        Random random = new Random();
        if (random.nextInt(2) == 0) return new HumanPlayer(this.board, this.bag);
        else return new HumanPlayer(this.board, this.bag); // TODO: Replace with BotPlayer
    }

    private boolean hasEveryPlayerPassedTwiceInARow() {
        for (Player player: this.players) {
            if (player.getNumberConsecutivePasses() < 2) return false;
        }
        return true;
    }

    private boolean isPlayersRackEmpty(Player player) {
        return this.bag.isEmpty() && player.getRack().isEmpty();
    }

    private boolean isGameOver(Player player) {
        return isPlayersRackEmpty(player) ||
               hasEveryPlayerPassedTwiceInARow();
    }

    private boolean playOneRound() {
        for (Player player : this.players) {
            Output.printBoard(this.board);
            player.makeMove();

            if (isGameOver(player)) {
                return true;
            }
        }

        return false;
    }

    public void start() {
        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = playOneRound();
        }
    }
}
