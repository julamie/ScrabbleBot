package logic;

import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;

import java.util.ArrayList;
import java.util.List;

public class CrossCheck {
    private final Board board;
    private final Word word;

    public CrossCheck(Board board, Word word) {
        this.board = board;
        this.word = word;
    }

    private Word getLeftCrossWord(Coordinates coordinates) {
        ArrayList<Tile> tiles = new ArrayList<>();

        Coordinates currCoordinates = coordinates.getLeft();
        while (currCoordinates != null && this.board.isOccupiedAt(currCoordinates)) {
            char letter = this.board.getLetterAt(currCoordinates);
            int value = this.board.getValueAt(currCoordinates);

            tiles.add(0, new Tile(letter, value));
            currCoordinates = currCoordinates.getLeft();
        }

        // current position is currently one space to the left of coordinates of first letter
        Coordinates firstLetterCoordinates;
        if (currCoordinates == null) firstLetterCoordinates = new Coordinates(coordinates.row(), 0);
        else firstLetterCoordinates = currCoordinates.getRight();

        return new Word(tiles.toArray(new Tile[0]), firstLetterCoordinates, Direction.HORIZONTALLY);
    }

    private Word getRightCrossWord(Coordinates coordinates) {
        ArrayList<Tile> tiles = new ArrayList<>();

        Coordinates currCoordinates = coordinates.getRight();
        Coordinates firstTileCoordinates = currCoordinates;
        while (currCoordinates.col() < this.board.getSize() && this.board.isOccupiedAt(currCoordinates)) {
            char letter = this.board.getLetterAt(currCoordinates);
            int value = this.board.getValueAt(currCoordinates);

            tiles.add(new Tile(letter, value));
            currCoordinates = currCoordinates.getRight();
        }

        return new Word(tiles.toArray(new Tile[0]), firstTileCoordinates, Direction.HORIZONTALLY);
    }

    private Word getUpperCrossWord(Coordinates coordinates) {
        ArrayList<Tile> tiles = new ArrayList<>();

        Coordinates currCoordinates = coordinates.getUpper();
        while (currCoordinates != null && this.board.isOccupiedAt(currCoordinates)) {
            char letter = this.board.getLetterAt(currCoordinates);
            int value = this.board.getValueAt(currCoordinates);

            tiles.add(0, new Tile(letter, value));
            currCoordinates = currCoordinates.getUpper();
        }

        // current position is currently one space above the coordinates of first letter
        Coordinates firstLetterCoordinates;
        if (currCoordinates == null) firstLetterCoordinates = new Coordinates(0, coordinates.col());
        else firstLetterCoordinates = currCoordinates.getLower();

        return new Word(tiles.toArray(new Tile[0]), firstLetterCoordinates, Direction.VERTICALLY);
    }

    private Word getLowerCrossWord(Coordinates coordinates) {
        ArrayList<Tile> tiles = new ArrayList<>();

        Coordinates currCoordinates = coordinates.getLower();
        Coordinates firstCoordinates = currCoordinates;
        while (currCoordinates.row() < this.board.getSize() && this.board.isOccupiedAt(currCoordinates)) {
            char letter = this.board.getLetterAt(currCoordinates);
            int value = this.board.getValueAt(currCoordinates);

            tiles.add(new Tile(letter, value));
            currCoordinates = currCoordinates.getLower();
        }

        return new Word(tiles.toArray(new Tile[0]), firstCoordinates, Direction.VERTICALLY);
    }

    private Tile[] combineTileArrayParts(Tile[] leftPart, Tile middlePart, Tile[] rightPart) {
        Tile[] combinedArray = new Tile[leftPart.length + 1 + rightPart.length];

        System.arraycopy(leftPart, 0, combinedArray, 0, leftPart.length);
        combinedArray[leftPart.length] = middlePart;
        System.arraycopy(rightPart, 0, combinedArray, leftPart.length + 1, rightPart.length);

        return combinedArray;
    }

    private Word getCrossWordHorizontal(Tile tile, Coordinates coordinates) {
        Word leftPart = getLeftCrossWord(coordinates);
        Word rightPart = getRightCrossWord(coordinates);

        // no cross-word found
        if (leftPart.toString().isEmpty() && rightPart.toString().isEmpty()) return null;

        Tile[] letters = combineTileArrayParts(leftPart.getWordAsTileArray(), tile, rightPart.getWordAsTileArray());

        Coordinates firstLetterCoordinates;
        if (leftPart.getLength() == 0) firstLetterCoordinates = coordinates;
        else firstLetterCoordinates = leftPart.getCoordinates(0);

        return new Word(letters, firstLetterCoordinates, Direction.HORIZONTALLY);
    }

    private Word getCrossWordVertical(Tile tile, Coordinates coordinates) {
        Word upperPart = getUpperCrossWord(coordinates);
        Word lowerPart = getLowerCrossWord(coordinates);

        // no cross-word found
        if (upperPart.toString().isEmpty() && lowerPart.toString().isEmpty()) return null;

        Tile[] letters = combineTileArrayParts(upperPart.getWordAsTileArray(), tile, lowerPart.getWordAsTileArray());
        Coordinates firstLetterCoordinates;
        if (upperPart.getLength() == 0) firstLetterCoordinates = coordinates;
        else firstLetterCoordinates = upperPart.getCoordinates(0);

        return new Word(letters, firstLetterCoordinates, Direction.VERTICALLY);
    }

    public Word getCrossWord(Tile tile, Coordinates coordinates) {
        return switch (this.word.getDirection()) {
            // check the opposite direction for cross-check
            case HORIZONTALLY -> getCrossWordVertical(tile, coordinates);
            case VERTICALLY -> getCrossWordHorizontal(tile, coordinates);
        };
    }

    public Word[] getCrossWordList() {
        List<Word> crossWordList = new ArrayList<>();
        for (int i = 0; i < this.word.getLength(); i++) {
            Tile currTile = this.word.getWordAsTileArray()[i];
            Coordinates currCoordinates = this.word.getCoordinates(i);

            // don't cross-check already occupied spaces
            if (this.board.isOccupiedAt(currCoordinates)) continue;

            Word potentialCrossWord = getCrossWord(currTile, currCoordinates);
            if (potentialCrossWord == null) continue;

            crossWordList.add(potentialCrossWord);
        }

        // convert list to array
        Word[] crossWords = new Word[crossWordList.size()];
        return crossWordList.toArray(crossWords);
    }

    public String getCrossWordString(Tile tile, Coordinates coordinates) {
        Word crossWord = getCrossWord(tile, coordinates);
        if (crossWord == null) return null;

        return crossWord.toString();
    }

    public String[] getCrossWordStringList() {
        Word[] wordList = getCrossWordList();
        String[] wordStringList = new String[wordList.length];

        for (int i = 0; i < wordList.length; i++) {
            wordStringList[i] = wordList[i].toString();
        }

        return wordStringList;
    }
}
