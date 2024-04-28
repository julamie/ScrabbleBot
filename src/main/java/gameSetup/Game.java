package gameSetup;

import boardStructure.Bag;
import boardStructure.Board;
import output.Output;
import playerBehaviour.HumanPlayer;
import playerBehaviour.Player;

import java.util.Random;

public class Game {

    public static Language language;
    public static Board board;
    public static Bag bag;

    private final Player[] players;

    public Game(Language language, int numHumans, int numBots) {
        Game.language = language;
        Game.board = new Board();
        Game.bag = new Bag(language);
        this.players = createPlayersInRandomOrder(numHumans, numBots);
    }

    public void start() {
        int numMoves = 0;
        while (numMoves++ <= 10) {
            for (Player player : this.players) {
                player.makeMove();
                Output.printBoard(Game.board);
            }
        }
    }

    private Player[] createPlayersInRandomOrder(int numHumans, int numBots) {
        int numPlayers = numHumans + numBots;
        Player[] players = new Player[numPlayers];

        int i = 0;
        while (i < numPlayers) {
            if (numHumans == 0) players[i] = new HumanPlayer();
            else if (numBots == 0) players[i] = new HumanPlayer();

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
        if (random.nextInt(2) == 0) return new HumanPlayer();
        else return new HumanPlayer(); // TODO: Replace with BotPlayer
    }
}
