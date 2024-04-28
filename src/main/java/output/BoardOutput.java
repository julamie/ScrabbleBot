package output;

import boardStructure.Board;
import boardStructure.Square;

public class BoardOutput {

    private static StringBuilder getSquareRowOutput(Board board, int row) {
        StringBuilder topLineOfRow = new StringBuilder();
        StringBuilder bottomLineOfRow = new StringBuilder();

        // add row number to the left
        if (row + 1 < 10) topLineOfRow.append(' ');
        topLineOfRow.append(row + 1).append(' ');
        bottomLineOfRow.append("   ");

        // concatenate the square's information
        for (int col = 0; col < board.getSize(); col++) {
            Square currSquare = board.getSquareAtPos(row, col);
            StringBuilder[] currSquareLines = SquareOutput.getSquareOutputLines(currSquare);

            topLineOfRow.append(currSquareLines[0]);
            bottomLineOfRow.append(currSquareLines[1]);
        }

        // add row number to the right
        if (row + 1 < 10) topLineOfRow.append(' ');
        topLineOfRow.append(' ').append(row + 1);
        bottomLineOfRow.append("   ");

        return topLineOfRow.append('\n').append(bottomLineOfRow);
    }

    private static StringBuilder getLetterCoordinateLine(int boardSize) {
        StringBuilder coordinateLine = new StringBuilder();

        coordinateLine.append("   "); // move line right because of the row number
        // print a line like this:  A    B    C    D  ...
        for (char letter = 'A'; letter < 'A' + boardSize; letter++) {
            coordinateLine.append("  ").append(letter).append("  ");
        }
        coordinateLine.append("   ");


        return coordinateLine;
    }

    public static StringBuilder[] getBoardOutputLines(Board board) {
        StringBuilder[] lines = new StringBuilder[board.getSize() + 2];

        StringBuilder coordinateLine = getLetterCoordinateLine(board.getSize());

        // set coordinate line at top and bottom
        lines[0] = coordinateLine;
        lines[lines.length - 1] = coordinateLine;

        // add all rows of squares plus their row number on the side
        for (int row = 0; row < board.getSize(); row++) {
            lines[row + 1] = getSquareRowOutput(board, row);
        }

        return lines;
    }

}
