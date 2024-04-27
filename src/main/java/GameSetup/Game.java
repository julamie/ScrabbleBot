package GameSetup;

import BoardStructure.Bag;
import BoardStructure.Board;
import PlayerBehaviour.HumanPlayer;
import PlayerBehaviour.Player;

public class Game {

    public static Language language;
    public static Board board;
    public static Bag bag;

    private final Player[] players;

    public Game(Language language, Board board, Bag bag, int numHumans, int numBots) {
        Game.language = language;
        Game.board = board;
        Game.bag = bag;
        this.players = createPlayers(numHumans, numBots);
    }

    public Game(Language language, int numHumans, int numBots) {
        Game.language = language;
        Game.board = new Board();
        Game.bag = new Bag();
        this.players = createPlayers(numHumans, numBots);
    }

    private Player[] createPlayers(int numHumans, int numBots) {
        Player[] players = new Player[numHumans];
        for (int i = 0; i < numHumans; i++) {
            this.players[i] = new HumanPlayer();
        }
        // TODO: Add BotPlayers
        return players;
    }

}
