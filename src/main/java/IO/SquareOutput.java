package IO;

import BoardStructure.Square;
import BoardStructure.SquareType;
import BoardStructure.Tile;

public class SquareOutput {

    private static final String RESET = "\u001B[0m"; // no background, bold letters
    public static final String PINK_PG = "\u001B[48;2;229;181;179;37;1m"; // gray letters
    public static final String RED_BG = "\u001B[48;2;234;56;32;90;1m"; // dark gray letters
    public static final String LIGHT_BLUE_BG = "\u001B[48;2;175;203;239;37;1m"; // gray letters
    public static final String BLUE_BG = "\u001B[48;2;10;143;223;90;1m"; // dark gray letters

    private static StringBuilder getUpperLineOfEmptySquare(Square square) {
        StringBuilder line = new StringBuilder();
        SquareType squareType = square.getType();

        switch (squareType) {
            case NORMAL: {
                line.append("     ");
                break;
            }
            case LETTER_BONUS_DOUBLE: {
                line.append(LIGHT_BLUE_BG);
                line.append("  2x ");
                line.append(RESET);
                break;
            }
            case LETTER_BONUS_TRIPLE: {
                line.append(BLUE_BG);
                line.append("  3x ");
                line.append(RESET);
                break;
            }
            case WORD_BONUS_DOUBLE: {
                line.append(PINK_PG);
                line.append("  2x ");
                line.append(RESET);
                break;
            }
            case WORD_BONUS_TRIPLE: {
                line.append(RED_BG);
                line.append("  3x ");
                line.append(RESET);
                break;
            }
        }

        return line;
    }

    private static StringBuilder getLowerLineOfEmptySquare(Square square) {
        StringBuilder line = new StringBuilder();
        SquareType squareType = square.getType();

        switch (squareType) {
            case NORMAL: {
                line.append("     ");
                break;
            }
            case LETTER_BONUS_DOUBLE: {
                line.append(LIGHT_BLUE_BG);
                line.append(" LTR ");
                line.append(RESET);
                break;
            }
            case LETTER_BONUS_TRIPLE: {
                line.append(BLUE_BG);
                line.append(" LTR ");
                line.append(RESET);
                break;
            }
            case WORD_BONUS_DOUBLE: {
                line.append(PINK_PG);
                line.append(" WRD ");
                line.append(RESET);
                break;
            }
            case WORD_BONUS_TRIPLE: {
                line.append(RED_BG);
                line.append(" WRD ");
                line.append(RESET);
                break;
            }
        };

        return line;
    }

    public static StringBuilder[] getSquareOutputLines(Square square) {
        // use TileOutput to get the output lines of the occupying tile
        if (square.isOccupied()) {
            Tile dummyTile = new Tile(square.getLetter(), square.getValue());
            return TileOutput.getTileOutputLines(dummyTile);
        }

        StringBuilder[] lines = {
                getUpperLineOfEmptySquare(square),
                getLowerLineOfEmptySquare(square)
        };

        return lines;
    }
}
