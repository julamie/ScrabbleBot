package gameSetup;

import boardStructure.Bag;
import boardStructure.Board;
import boardStructure.Rack;
import boardStructure.Tile;
import input.GameInput;
import logic.Scoring;
import output.Output;
import playerBehaviour.HumanPlayer;
import playerBehaviour.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Game {
    private final Board board;
    private final Bag bag;
    private final Player[] players;

    public Game(Language language, int numHumans, int numBots) {
        this.board = new Board();
        this.bag = new Bag(language);
        this.players = createPlayersInRandomOrder(numHumans, numBots);
        Dictionary.setDictionaryLanguage(language); // needed for default dictionary language in WordValidation
    }

    private Player[] createPlayersInRandomOrder(int numHumans, int numBots) {
        GameInput input = new GameInput();
        int numPlayers = numHumans + numBots;
        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numHumans; i++) {
            players[i] = new HumanPlayer(this.board, this.bag);
            players[i].setName(input.getHumanPlayerName());
        }

        for (int i = numHumans; i < numPlayers; i++) {
            players[i] = new HumanPlayer(this.board, this.bag); // TODO: Replace with BotPlayer
            players[i].setName(input.getBotName());
        }

        Collections.shuffle(Arrays.asList(players));
        return players;
    }

    private void letPlayersFillTheirRack() {
        for (Player player: this.players) {
            player.fillRack();
        }
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

    private int calculateRackValueFromPlayer(Player player) {
        Rack rack = player.getRack();
        Tile[] rackLetters = rack.getTileRack().toArray(new Tile[0]);

        return Scoring.calculateOnlyTileValues(rackLetters);
    }

    private Player removeRackValuesFromPlayers() {
        Player emptyRackPlayer = null;

        for (Player player: this.players) {
            if (player.getRack().isEmpty()) {
                emptyRackPlayer = player;
                continue;
            }

            player.addToScore(-1 * calculateRackValueFromPlayer(player));
        }

        return emptyRackPlayer;
    }

    private void giftEmptyRackPlayerRackValues(Player playerWithNoTilesOnRack) {
        if (playerWithNoTilesOnRack == null) return;

        for (Player player: this.players) {
            if (player == playerWithNoTilesOnRack) continue;

            playerWithNoTilesOnRack.addToScore(calculateRackValueFromPlayer(player));
        }
    }

    private void showLeaderBoard() {
        System.out.println("The game has ended. Here are the scores:");
        Arrays.sort(this.players, Comparator.comparingInt(Player::getScore).reversed());

        for (int i = 0; i < this.players.length; i++) {
            Player currPlayer = this.players[i];
            System.out.printf("%d. Place: %s with score: %d\n", i + 1, currPlayer.getName(), currPlayer.getScore());
        }
    }

    private void endGame() {
        Player emptyRackPlayer = removeRackValuesFromPlayers();
        giftEmptyRackPlayerRackValues(emptyRackPlayer);
        showLeaderBoard();
    }

    public void start() {
        boolean isGameOver = false;

        letPlayersFillTheirRack();
        while (!isGameOver) {
            isGameOver = playOneRound();
        }
        endGame();
    }
}
