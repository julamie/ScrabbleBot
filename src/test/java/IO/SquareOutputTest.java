package IO;

import BoardStructure.Square;
import BoardStructure.SquareType;
import BoardStructure.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareOutputTest {

    private final Square emptySquare, occupiedSquare;
    private final Square doubleLetterSquare, tripleLetterSquare;
    private final Square doubleWordSquare, tripleWordSquare;

    public SquareOutputTest() {
        this.emptySquare = new Square(SquareType.NORMAL);
        this.occupiedSquare = new Square(SquareType.NORMAL);
        this.occupiedSquare.occupySquare(new Tile('E', 1));
        this.doubleLetterSquare = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.tripleLetterSquare = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.doubleWordSquare = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.tripleWordSquare = new Square(SquareType.WORD_BONUS_TRIPLE);
    }

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    void upperLineOfSquareShouldAlwaysHaveWidth5() {
        String[] upperLines = {
                SquareOutput.getSquareOutputLines(emptySquare)[0].toString(),
                SquareOutput.getSquareOutputLines(occupiedSquare)[0].toString(),
                SquareOutput.getSquareOutputLines(doubleLetterSquare)[0].toString(),
                SquareOutput.getSquareOutputLines(tripleLetterSquare)[0].toString(),
                SquareOutput.getSquareOutputLines(doubleWordSquare)[0].toString(),
                SquareOutput.getSquareOutputLines(tripleWordSquare)[0].toString(),
        };

        for (String line : upperLines) {
            assertEquals(5, getStringLengthWithoutANSI(line));
        }
    }

    @Test
    void lowerLineOfSquareShouldAlwaysHaveWidth5() {
        String[] lowerLines = {
                SquareOutput.getSquareOutputLines(emptySquare)[1].toString(),
                SquareOutput.getSquareOutputLines(occupiedSquare)[1].toString(),
                SquareOutput.getSquareOutputLines(doubleLetterSquare)[1].toString(),
                SquareOutput.getSquareOutputLines(tripleLetterSquare)[1].toString(),
                SquareOutput.getSquareOutputLines(doubleWordSquare)[1].toString(),
                SquareOutput.getSquareOutputLines(tripleWordSquare)[1].toString(),
        };

        for (String line : lowerLines) {
            assertEquals(5, getStringLengthWithoutANSI(line));
        }
    }

    @Test
    void printingSquareShouldAlwaysHaveHeightOfTwo() {
        StringBuilder[][] squareLines = {
                SquareOutput.getSquareOutputLines(emptySquare),
                SquareOutput.getSquareOutputLines(occupiedSquare),
                SquareOutput.getSquareOutputLines(doubleLetterSquare),
                SquareOutput.getSquareOutputLines(tripleLetterSquare),
                SquareOutput.getSquareOutputLines(doubleWordSquare),
                SquareOutput.getSquareOutputLines(tripleWordSquare),
        };

        for (StringBuilder[] lines : squareLines) {
            assertEquals(2, lines.length);
        }
    }

}