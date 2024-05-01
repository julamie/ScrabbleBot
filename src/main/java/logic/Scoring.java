package logic;

import boardStructure.Board;
import boardStructure.Square;
import boardStructure.Tile;

public class Scoring {

    private final Board board;
    private final Word word;

    public Scoring(Board board, Word word) {
        this.board = board;
        this.word = word;
    }

    public int calculateWordScore() {
        return calculateWordScore(this.word);
    }

    public int calculateWordScore(Word word) {
        int wordScore = 0;
        int wordFactor = 1;

        for (int i = 0; i < word.getLength(); i++) {
            int letterValue = word.getLetterValue(i);
            Square square = this.board.getSquareAt(word.getCoordinates(i));

            // calculate no extra points for tiles that are already there
            if (square.isOccupied()) {
                wordScore += square.getValue();
                continue;
            }

            switch (square.getType()) {
                case NORMAL -> wordScore += letterValue;
                case LETTER_BONUS_DOUBLE -> wordScore += 2 * letterValue;
                case LETTER_BONUS_TRIPLE -> wordScore += 3 * letterValue;
                case WORD_BONUS_DOUBLE -> {
                    wordScore += letterValue;
                    wordFactor *= 2;
                }
                case WORD_BONUS_TRIPLE -> {
                    wordScore += letterValue;
                    wordFactor *= 3;
                }
            }
        }

        return wordFactor * wordScore;
    }

    public static int calculateOnlyTileValues(Tile[] tiles) {
        int crossWordScore = 0;
        for (Tile tile : tiles) {
            crossWordScore += tile.getValue();
        }

        return crossWordScore;
    }

    public int calculateScore() {
        int totalScore = 0;
        totalScore += calculateWordScore();

        Word[] crossWords = new CrossCheck(this.board, this.word).getCrossWordList();
        for (Word crossWord: crossWords) {
            totalScore += calculateWordScore(crossWord);
        }

        return totalScore;
    }
}
