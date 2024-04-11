package IO;

import BoardStructure.Board;
import BoardStructure.Square;

public class BoardOutput {

    private static StringBuilder getSquareRowOutput(Board board, int row) {
        StringBuilder topLineOfRow = new StringBuilder();
        StringBuilder bottomLineOfRow = new StringBuilder();

        // concatenate the square's information
        for (int col = 0; col < board.getSize(); col++) {
            Square currSquare = board.getSquareAtPos(row, col);
            StringBuilder[] currSquareLines = SquareOutput.getSquareOutputLines(currSquare);

            topLineOfRow.append(currSquareLines[0]);
            bottomLineOfRow.append(currSquareLines[1]);
        }

        return topLineOfRow.append('\n').append(bottomLineOfRow);
    }

    public static StringBuilder[] getBoardOutputLines(Board board) {
        StringBuilder[] lines = new StringBuilder[board.getSize()];

        for (int row = 0; row < board.getSize(); row++) {
            lines[row] = getSquareRowOutput(board, row);
        }

        return lines;
    }

}
