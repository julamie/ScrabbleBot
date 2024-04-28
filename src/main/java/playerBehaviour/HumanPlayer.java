package playerBehaviour;

import boardStructure.Bag;
import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;
import input.PlayerInput;
import logic.Direction;
import logic.TurnType;
import logic.Word;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board, Bag bag) {
        super(board, bag);
    }

    private Word getValidWordFromPlayer(PlayerInput input) {
        String wordInput = input.getWordToPlay();
        Tile[] letters = this.bag.convertWordToTileArray(wordInput);
        Coordinates coordinates = input.getCoordinates();
        Direction direction = determineDirection(input, letters, coordinates);
        if (direction == null) return null;

        return new Word(letters, coordinates, direction);
    }

    private Direction determineDirection(PlayerInput input, Tile[] letters, Coordinates coordinates) {
        Word horizontalWord = new Word(letters, coordinates, Direction.HORIZONTALLY);
        Word verticalWord = new Word(letters, coordinates, Direction.VERTICALLY);

        boolean horizontalPlayIsPossible = isWordLegalToPlay(horizontalWord);
        boolean verticalPlayIsPossible = isWordLegalToPlay(verticalWord);

        // return the direction based on which direction a word is allowed to be played
        // if both directions are possible, ask the player
        if (!horizontalPlayIsPossible && !verticalPlayIsPossible) return null;
        if (horizontalPlayIsPossible && verticalPlayIsPossible) return input.getDirection();
        if (horizontalPlayIsPossible) return Direction.HORIZONTALLY;
        else return Direction.VERTICALLY;
    }

    private boolean handlePlayWord(PlayerInput input) {
        Word word = getValidWordFromPlayer(input);
        if (word == null) {
            System.err.println("Your word can't be played here. Please try again.");
            return false;
        }
        setWordOnBoard(word);

        return true;
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
            System.err.println("Not all letters input are on the rack. Please try again.");
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
        if (!validMoveMade) System.err.println("There are less than seven tiles in the bag.\n" +
                "Exchanging unavailable.");

        return validMoveMade;
    }

    @Override
    public TurnType makeMove() {
        PlayerInput input = new PlayerInput(this);

        TurnType chosenTurnType = null;
        boolean validMoveMade = false;
        while (!validMoveMade) {
            chosenTurnType = input.determineTurnType();
            validMoveMade = switch (chosenTurnType) {
                case PLAY_WORD -> handlePlayWord(input);
                case EXCHANGE_LETTERS -> handleExchangeLetters(input);
                case PASS_TURN -> true;
            };

        }

        return chosenTurnType;
    }
}
