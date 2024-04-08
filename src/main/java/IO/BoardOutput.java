package IO;

import BoardStructure.Board;
import BoardStructure.Square;
import BoardStructure.SquareType;

public class BoardOutput {

    private static final String RESET = "\u001B[0;1m"; // no background, bold letters
    public static final String CREAM_BG = "\033[48;2;238;221;187;30m"; // black letters
    public static final String PINK_PG = "\u001B[48;2;229;181;179;37m"; // gray letters
    public static final String RED_BG = "\u001B[48;2;234;56;32;90m"; // dark gray letters
    public static final String LIGHT_BLUE_BG = "\u001B[48;2;175;203;239;37m"; // gray letters
    public static final String BLUE_BG = "\u001B[48;2;10;143;223;90m"; // dark gray letters


    private static StringBuilder getTopLineOfSquare(Square square) {
        SquareType squareType = square.getType();
        StringBuilder output = new StringBuilder();

        // draw the tile in the correct colour
        if (square.isOccupied()) {
            output.append(CREAM_BG);
            output.append("  ").append(square.getLetter()).append("  ");

            return output;
        }

        // depending on the BoardStructure.SquareType print the correct label for the empty square
        switch (squareType) {
            case NORMAL: {
                output.append(RESET);
                output.append("  ").append(square.getLetter()).append("  ");
                break;
            }
            case LETTER_BONUS_DOUBLE: {
                output.append(LIGHT_BLUE_BG);
                output.append("  2x ");
                break;
            }
            case LETTER_BONUS_TRIPLE: {
                output.append(BLUE_BG);
                output.append("  3x ");
                break;
            }
            case WORD_BONUS_DOUBLE: {
                output.append(PINK_PG);
                output.append("  2x ");
                break;
            }
            case WORD_BONUS_TRIPLE: {
                output.append(RED_BG);
                output.append("  3x ");
                break;
            }
        };

        return output;
    }

    private static StringBuilder getBottomLineOfSquare(Square square) {
        SquareType squareType = square.getType();
        StringBuilder output = new StringBuilder();

        // write the tile value on the square
        if (square.isOccupied()) {
            output.append(CREAM_BG);

            int value = square.getValue();
            if (value == 0) return output.append("     "); // don't write the value if it's a blank tile
            if (value >= 10) return output.append("   ").append(value);
            return output.append("    ").append(value);

        }

        // depending on the BoardStructure.SquareType print the value of the tile or the bonus name
        switch (squareType) {
            case NORMAL: {
                output.append(RESET);
                output.append("     ");
                break;
            }
            case LETTER_BONUS_DOUBLE: {
                output.append(LIGHT_BLUE_BG);
                output.append(" LTR ");
                break;
            }
            case LETTER_BONUS_TRIPLE: {
                output.append(BLUE_BG);
                output.append(" LTR ");
                break;
            }
            case WORD_BONUS_DOUBLE: {
                output.append(PINK_PG);
                output.append(" WRD ");
                break;
            }
            case WORD_BONUS_TRIPLE: {
                output.append(RED_BG);
                output.append(" WRD ");
                break;
            }
        };

        return output;
    }

    private static StringBuilder getRowInformation(Board board, int row) {
        StringBuilder topLineOfRow = new StringBuilder();
        StringBuilder bottomLineOfRow = new StringBuilder();

        // concatenate the square's information
        for (int col = 0; col < board.getSize(); col++) {
            Square currentSquare = board.getSquareAtPos(row, col);
            topLineOfRow.append(getTopLineOfSquare(currentSquare));
            bottomLineOfRow.append(getBottomLineOfSquare(currentSquare));
        }

        // build two lines of the row
        StringBuilder output = topLineOfRow.append(RESET).append('\n');
        output.append(bottomLineOfRow).append(RESET);

        return output;
    }

    public static void printBoard(Board board) {
        StringBuilder outputString = new StringBuilder();

        for (int row = 0; row < board.getSize(); row++) {
            outputString.append(getRowInformation(board, row)).append('\n');
        }
        System.out.println(outputString);
    }

}
