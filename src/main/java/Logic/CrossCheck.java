package Logic;

import BoardStructure.Board;
import BoardStructure.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CrossCheck {
    private final Board board;
    private final Word word;

    public CrossCheck(Board board, Word word) {
        this.board = board;
        this.word = word;
    }

    private String getLeftCrossWord(Coordinates coordinates) {
        StringBuilder leftPart = new StringBuilder();

        coordinates = coordinates.getLeft();
        while (coordinates != null && this.board.isOccupiedAt(coordinates)) {
            leftPart.insert(0, this.board.getLetterAt(coordinates));
            coordinates = coordinates.getLeft();
        }

        return leftPart.toString();
    }

    private String getRightCrossWord(Coordinates coordinates) {
        StringBuilder rightPart = new StringBuilder();

        coordinates = coordinates.getRight();
        while (coordinates.col() < this.board.getSize() && this.board.isOccupiedAt(coordinates)) {
            rightPart.append(this.board.getLetterAt(coordinates));
            coordinates = coordinates.getRight();
        }

        return rightPart.toString();
    }

    private String getUpperCrossWord(Coordinates coordinates) {
        StringBuilder upperPart = new StringBuilder();

        coordinates = coordinates.getUpper();
        while (coordinates != null && this.board.isOccupiedAt(coordinates)) {
            upperPart.insert(0, this.board.getLetterAt(coordinates));
            coordinates = coordinates.getUpper();
        }

        return upperPart.toString();
    }

    private String getLowerCrossWord(Coordinates coordinates) {
        StringBuilder lowerPart = new StringBuilder();

        coordinates = coordinates.getLower();
        while (coordinates.row() < this.board.getSize() && this.board.isOccupiedAt(coordinates)) {
            lowerPart.append(this.board.getLetterAt(coordinates));
            coordinates = coordinates.getLower();
        }

        return lowerPart.toString();
    }

    private String getCrossWordHorizontal(Coordinates coordinates) {
        String leftPart = getLeftCrossWord(coordinates);
        char currLetter = this.word.getLetterAtCoordinates(coordinates);
        String rightPart = getRightCrossWord(coordinates);

        if (leftPart.isEmpty() && rightPart.isEmpty()) return null;
        return leftPart + currLetter + rightPart;
    }

    private String getCrossWordVertical(Coordinates coordinates) {
        String upperPart = getUpperCrossWord(coordinates);
        char currLetter = this.word.getLetterAtCoordinates(coordinates);
        String lowerPart = getLowerCrossWord(coordinates);

        if (upperPart.isEmpty() && lowerPart.isEmpty()) return null;
        return upperPart + currLetter + lowerPart;
    }

    public String getCrossWord(Coordinates coordinates) {
        return switch (this.word.getDirection()) {
            // check the opposite direction for cross-check
            case HORIZONTALLY -> getCrossWordVertical(coordinates);
            case VERTICALLY -> getCrossWordHorizontal(coordinates);
        };
    }

    public String[] getCrossWordList() {
        List<String> crossWordList = new ArrayList<>();
        for (int i = 0; i < this.word.getLength(); i++) {
            Coordinates currCoordinates = this.word.getCoordinates(i);

            // don't cross-check already occupied spaces
            if (this.board.isOccupiedAt(currCoordinates)) continue;

            String potentialCrossWord = getCrossWord(currCoordinates);

            if (potentialCrossWord == null) continue;
            crossWordList.add(potentialCrossWord);
        }

        // convert list to array
        String[] crossWords = new String[crossWordList.size()];
        return crossWordList.toArray(crossWords);
    }
}
