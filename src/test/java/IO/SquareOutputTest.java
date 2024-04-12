package IO;

import BoardStructure.Square;
import BoardStructure.SquareType;
import BoardStructure.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareOutputTest {

    private StringBuilder[][] squareLinesPerType;

    @BeforeEach
    void setup() {
        Square emptySquare = new Square(SquareType.NORMAL);
        Square occupiedSquare = new Square(SquareType.NORMAL);
        occupiedSquare.occupySquare(new Tile('E', 1));
        Square doubleLetterSquare = new Square(SquareType.LETTER_BONUS_DOUBLE);
        Square tripleLetterSquare = new Square(SquareType.LETTER_BONUS_TRIPLE);
        Square doubleWordSquare = new Square(SquareType.WORD_BONUS_DOUBLE);
        Square tripleWordSquare = new Square(SquareType.WORD_BONUS_TRIPLE);

        this.squareLinesPerType = new StringBuilder[][]{
                SquareOutput.getSquareOutputLines(emptySquare),
                SquareOutput.getSquareOutputLines(occupiedSquare),
                SquareOutput.getSquareOutputLines(doubleLetterSquare),
                SquareOutput.getSquareOutputLines(tripleLetterSquare),
                SquareOutput.getSquareOutputLines(doubleWordSquare),
                SquareOutput.getSquareOutputLines(tripleWordSquare),
        };
    }

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    void upperLineOfSquareShouldAlwaysHaveWidth5() {
        for (StringBuilder[] squareLines : this.squareLinesPerType) {
            String upperLine = squareLines[0].toString();

            assertEquals(5, getStringLengthWithoutANSI(upperLine));
        }
    }

    @Test
    void lowerLineOfSquareShouldAlwaysHaveWidth5() {
        for (StringBuilder[] squareLines : this.squareLinesPerType) {
            String lowerLine = squareLines[1].toString();

            assertEquals(5, getStringLengthWithoutANSI(lowerLine));
        }
    }

    @Test
    void printingSquareShouldAlwaysHaveHeightOfTwo() {
        for (StringBuilder[] squareLines : this.squareLinesPerType) {
            assertEquals(2, squareLines.length);
        }
    }

}