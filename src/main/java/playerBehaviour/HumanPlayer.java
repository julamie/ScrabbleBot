package playerBehaviour;

import boardStructure.Bag;
import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;
import input.PlayerInput;
import logic.*;

public class HumanPlayer extends Player {
    public HumanPlayer(Board board, Bag bag) {
        super(board, bag);
    }

    private Word getWordFromPlayer(PlayerInput input) {
        String wordInput = input.getWordToPlay();
        Tile[] letters = Tile.convertStringToTileArray(wordInput, this.bag.getLanguage());
        Coordinates coordinates = input.getCoordinates();
        Direction direction = determineDirection(input, letters, coordinates);

        return new Word(letters, coordinates, direction);
    }

    private Direction determineDirection(PlayerInput input, Tile[] letters, Coordinates coordinates) {
        Word horizontalWord = new Word(letters, coordinates, Direction.HORIZONTALLY);
        Word verticalWord = new Word(letters, coordinates, Direction.VERTICALLY);

        boolean horizontalPlayIsPossible = checkWord(horizontalWord) == WordValidity.VALID;
        boolean verticalPlayIsPossible = checkWord(verticalWord) == WordValidity.VALID;

        // if both directions are possible ask the player
        // also ask the player is none are possible, because then they get a helpful explanation
        // why a word can't be played there
        if (horizontalPlayIsPossible && !verticalPlayIsPossible) return Direction.HORIZONTALLY;
        if (!horizontalPlayIsPossible && verticalPlayIsPossible) return Direction.VERTICALLY;
        else return input.getDirection();
    }

    private boolean handlePlayWord(PlayerInput input) {
        Word word = getWordFromPlayer(input);

        WordValidity validity = checkWord(word);
        if (validity != WordValidity.VALID) {
            showReasonForInvalidWord(validity);
            return false;
        }

        setWordOnBoard(word);
        this.numberConsecutivePasses = 0;

        return true;
    }

    private void showReasonForInvalidWord(WordValidity validity) {
        switch (validity) {
            case NOT_IN_BOUNDS -> System.err.println("Your word is too long for the board.");
            case OVERLAPS_TILES -> System.err.println("Your word would overlap other tiles with a different letter.");
            case DISCONNECTED -> System.err.println("Your word doesn't connect with any other word on the board.");
            case TILES_NOT_ON_RACK -> System.err.println("You can't play that word with the tiles on your rack.");
            case WORD_NOT_IN_DICTIONARY -> System.err.println("This word doesn't exist.");
            case CROSSWORD_NOT_IN_DICTIONARY -> System.err.println("You are creating a second word that doesn't exist.");
            case NOT_IN_MIDDLE_SQUARE -> System.err.println("Your word must cover the middle square.");
        }
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
        if (validMoveMade) this.numberConsecutivePasses = 0;
        else System.err.println("There are less than seven tiles in the bag.\n" +
                    "Exchanging unavailable.");

        return validMoveMade;
    }

    private boolean handlePassTurn() {
        this.numberConsecutivePasses++;
        return true;
    }

    @Override
    public void makeMove() {
        PlayerInput input = new PlayerInput(this);

        boolean validMoveMade = false;
        while (!validMoveMade) {
            TurnType chosenTurnType = input.determineTurnType();
            validMoveMade = switch (chosenTurnType) {
                case PLAY_WORD -> handlePlayWord(input);
                case EXCHANGE_LETTERS -> handleExchangeLetters(input);
                case PASS_TURN -> handlePassTurn();
            };

        }
    }
}
