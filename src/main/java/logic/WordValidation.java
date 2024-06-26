package logic;

import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Rack;
import boardStructure.Square;
import gameSetup.Dictionary;
import playerBehaviour.Player;

public class WordValidation {

    private final Board board;
    private final Rack rack;
    private final Word word;

    public WordValidation(Board board, Rack rack, Word word) {
        this.board = board;
        this.rack = rack;
        this.word = word;
    }

    public WordValidation(Player player, Word word) {
        this(player.getBoard(), player.getRack(), word);
    }

    public boolean doesWordStayInBounds() {
        int lastPosition = this.word.getLength() - 1;
        Coordinates lastLetterCoordinates = this.word.getCoordinates(lastPosition);

        return  (lastLetterCoordinates.row() < this.board.getSize()) &&
                (lastLetterCoordinates.col() < this.board.getSize());
    }

    private boolean doesLetterNotOverlapOtherTiles(Square square, char letter) {
        return !square.isOccupied() || square.getLetter() == letter;
    }

    public boolean doesWordNotOverlapOtherTiles() {
        for (int i = 0; i < word.getLength(); i++) {
            Square currSquare = this.board.getSquareAt(word.getCoordinates(i));
            if (!doesLetterNotOverlapOtherTiles(currSquare, word.getLetter(i))) return false;
        }

        return true;
    }

    private boolean isWordSurroundedHorizontallyBySpaces() {
        Coordinates firstLetterCoords = this.word.getCoordinates(0);
        Coordinates lastLetterCoords = this.word.getCoordinates(this.word.getLength() - 1);

        Coordinates beforeCoords = firstLetterCoords.getLeft();
        Coordinates afterCoords = lastLetterCoords.getRight();

        boolean beforeUnconnected = beforeCoords == null || !this.board.isOccupiedAt(beforeCoords);
        boolean afterUnconnected = afterCoords.col() >= this.board.getSize() || !this.board.isOccupiedAt(afterCoords);

        return beforeUnconnected && afterUnconnected;
    }

    private boolean isWordSurroundedVerticallyBySpaces() {
        Coordinates firstLetterCoords = this.word.getCoordinates(0);
        Coordinates lastLetterCoords = this.word.getCoordinates(this.word.getLength() - 1);

        Coordinates beforeCoords = firstLetterCoords.getUpper();
        Coordinates afterCoords = lastLetterCoords.getLower();

        boolean beforeUnconnected = beforeCoords == null || !this.board.isOccupiedAt(beforeCoords);
        boolean afterUnconnected = afterCoords.row() >= this.board.getSize() || !this.board.isOccupiedAt(afterCoords);

        return beforeUnconnected && afterUnconnected;
    }

    public boolean isWordSurroundedBySpaces() {
        return switch (this.word.getDirection()) {
            case HORIZONTALLY -> isWordSurroundedHorizontallyBySpaces();
            case VERTICALLY -> isWordSurroundedVerticallyBySpaces();
        };
    }

    private boolean isTileConnectedToOtherTiles(Coordinates coordinates) {
        Square leftNeighbour  = this.board.getLeftNeighbour(coordinates);
        Square rightNeighbour = this.board.getRightNeighbour(coordinates);
        Square upperNeighbour = this.board.getUpperNeighbour(coordinates);
        Square lowerNeighbour = this.board.getLowerNeighbour(coordinates);

        boolean isConnectedLeft  =  leftNeighbour != null && leftNeighbour.isOccupied();
        boolean isConnectedRight = rightNeighbour != null && rightNeighbour.isOccupied();
        boolean isConnectedUp    = upperNeighbour != null && upperNeighbour.isOccupied();
        boolean isConnectedDown  = lowerNeighbour != null && lowerNeighbour.isOccupied();

        return isConnectedLeft || isConnectedRight || isConnectedUp || isConnectedDown;
    }

    public boolean isWordConnectedToWord() {
        if (this.board.isEmpty()) return true; // the first word can't be connected to any other word

        for (int i = 0; i < this.word.getLength(); i++) {
            Coordinates currCoordinates = this.word.getCoordinates(i);
            if (isTileConnectedToOtherTiles(currCoordinates)) return true;
        }

        return false;
    }

    private String removeTileFromRackIfPossible(String availableLetters, String letter) {
        if (availableLetters.contains(letter)) {
            return availableLetters.replaceFirst(letter, "");
        }
        if (availableLetters.contains("?")) {
            return availableLetters.replaceFirst("\\?", "");
        }

        return null;
    }

    public boolean canWordBePlayedWithTilesOnRack() {
        String availableLetters = new String(this.rack.getLettersOnRack());

        for (int i = 0; i < this.word.getLength(); i++) {
            String currLetter = this.word.getLetter(i) + "";
            Coordinates currCoordinates = this.word.getCoordinates(i);
            Square currSquare = this.board.getSquareAt(currCoordinates);

            if (currSquare.isOccupied()) {
                // occupied spaces have to have the correct letter placed already
                if (currSquare.getLetter() == currLetter.charAt(0)) continue;
                else return false;
            }

            availableLetters = removeTileFromRackIfPossible(availableLetters, currLetter);
            if (availableLetters == null) return false;
        }

        return true;
    }

    public boolean isWordInDictionary(String word) {
        return Dictionary.isWord(word);
    }

    public boolean isWordInDictionary() {
        return isWordInDictionary(this.word.toString());
    }

    public boolean areCrossWordsInDictionary() {
        String[] crossWords = new CrossCheck(this.board, this.word).getCrossWordStringList();

        for (String crossWord: crossWords) {
            if (!isWordInDictionary(crossWord)) return false;
        }

        return true;
    }

    public boolean isWordCoveringTheMiddleSquare() {
        int middlePosition = this.board.getSize() / 2; // 15 divided by 2 rounds down to 7
        for (int i = 0; i < this.word.getLength(); i++) {
            Coordinates currCoordinates = this.word.getCoordinates(i);
            if (currCoordinates.row() == middlePosition && currCoordinates.col() == middlePosition) return true;
        }

        return false;
    }

    public WordValidity getWordValidity() {
        // special case if the first move is made
        if (this.board.isEmpty()) {
            if (!isWordCoveringTheMiddleSquare()) return WordValidity.NOT_IN_MIDDLE_SQUARE;
        }

        if (!doesWordStayInBounds())           return WordValidity.NOT_IN_BOUNDS;
        if (!doesWordNotOverlapOtherTiles())   return WordValidity.OVERLAPS_TILES;
        if (!isWordSurroundedBySpaces())       return WordValidity.NO_SPACE_AROUND_WORD;
        if (!isWordConnectedToWord())          return WordValidity.DISCONNECTED;
        if (!canWordBePlayedWithTilesOnRack()) return WordValidity.TILES_NOT_ON_RACK;
        if (!isWordInDictionary())             return WordValidity.WORD_NOT_IN_DICTIONARY;
        if (!areCrossWordsInDictionary())      return WordValidity.CROSSWORD_NOT_IN_DICTIONARY;

        return WordValidity.VALID;
    }
}
