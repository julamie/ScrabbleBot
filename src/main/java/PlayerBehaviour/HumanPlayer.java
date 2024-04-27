package PlayerBehaviour;

import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Coordinates;
import BoardStructure.Tile;
import Input.PlayerInput;
import Logic.Direction;
import Logic.TurnType;
import Logic.Word;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board, Bag bag) {
        super(board, bag);
    }

    private Word getWordFromPlayer(PlayerInput input) {
        String wordInput = input.getWordToPlay();
        Tile[] letters = this.bag.convertWordToTileArray(wordInput);
        Coordinates coordinates = input.getCoordinates(this.board.getSize());
        Direction direction = input.getDirection();

        return new Word(letters, coordinates, direction);
    }

    private boolean handlePlayWord(PlayerInput input) {
        Word word = getWordFromPlayer(input);

        boolean validMoveMade = setWordOnBoard(word);
        if (!validMoveMade) System.out.println("Your word can't be played here. Please try again.");

        return validMoveMade;
    }

    private boolean areAllLettersOnTheRack(char[] lettersToExchange) {
        boolean allLettersOnRack = true;
        for (char letter: lettersToExchange) {
            if (!this.rack.doesRackContain(letter)) {
                allLettersOnRack = false;
                break;
            }
        }

        if (!allLettersOnRack) {
            System.out.println("Not all letters input are on the rack. Please try again.");
            return false;
        }

        return true;
    }

    private boolean handleExchangeLetters(PlayerInput input) {
        char[] lettersToExchange = input.getLettersToExchange();
        if (!areAllLettersOnTheRack(lettersToExchange)) return false;

        Tile[] tilesToExchange = new Tile[lettersToExchange.length];
        for (char letter: lettersToExchange) {
            this.rack.removeTileFromRack(letter);
        }

        boolean validMoveMade = exchangeTiles(tilesToExchange);
        if (!validMoveMade) System.out.println("There are less than seven tiles in the bag.\n" +
                "Exchanging unavailable.");

        return validMoveMade;
    }

    @Override
    public void makeMove() {
        PlayerInput input = new PlayerInput(this.bag, this.rack);

        boolean validMoveMade = false;
        while (!validMoveMade) {
            TurnType turnType = input.determineTurnType();
            validMoveMade = switch (turnType) {
                case PLAY_WORD -> handlePlayWord(input);
                case EXCHANGE_LETTERS -> handleExchangeLetters(input);
                case PASS_TURN -> true;
            };
        }
    }
}
