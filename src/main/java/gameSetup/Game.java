package gameSetup;

import boardStructure.Bag;
import boardStructure.Board;
import playerBehaviour.HumanPlayer;
import playerBehaviour.Player;

public class Game {

    public static Language language;
    public static Board board;
    public static Bag bag;

    private final Player[] players;

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
