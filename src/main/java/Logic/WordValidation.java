package Logic;

import BoardStructure.Board;
import BoardStructure.Coordinates;
import BoardStructure.Rack;
import BoardStructure.Square;

public class WordValidation {

    private final Board board;
    private final Rack rack;
    private final Word word;

    public WordValidation(Board board, Rack rack, Word word) {
        this.board = board;
        this.rack = rack;
        this.word = word;
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

    private boolean isTileConnectedToOtherTiles(Coordinates coordinates) {
        Square leftNeighbour  = this.board.getLeftNeighbour(coordinates);
        Square rightNeighbour = this.board.getRightNeighbour(coordinates);
        Square upperNeighbour = this.board.getUpperNeighbour(coordinates);
        Square lowerNeighbour = this.board.getLowerNeighbour(coordinates);

        if (leftNeighbour  != null && leftNeighbour.isOccupied()) return true;
        if (rightNeighbour != null && rightNeighbour.isOccupied()) return true;
        if (upperNeighbour != null && upperNeighbour.isOccupied()) return true;
        if (lowerNeighbour != null && lowerNeighbour.isOccupied()) return true;

        return false;
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
        String availableLetters = new String(rack.getLettersOnRack());

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
        return true; // TODO: Add check if word is in dictionary
    }

    public boolean isWordInDictionary() {
        return isWordInDictionary(this.word.toString());
    }

    public boolean areCrossWordsInDictionary() {
        String[] crossWords = new CrossCheck(this.board, this.word).getCrossWordList();

        for (String crossWord: crossWords) {
            if (!isWordInDictionary(crossWord)) return false;
        }

        return true;
    }

    public boolean isWordValid() {
        return  doesWordStayInBounds() &&
                doesWordNotOverlapOtherTiles() &&
                isWordConnectedToWord() &&
                canWordBePlayedWithTilesOnRack() &&
                isWordInDictionary() &&
                areCrossWordsInDictionary();
    }
}
