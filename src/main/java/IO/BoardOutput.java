package IO;

import BoardStructure.Board;
import BoardStructure.Square;

public class BoardOutput {

    private static StringBuilder getRowInformation(Board board, int row) {
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

    public static void printBoard(Board board) {
        StringBuilder outputString = new StringBuilder();

        for (int row = 0; row < board.getSize(); row++) {
            outputString.append(getRowInformation(board, row)).append('\n');
        }

        System.out.println(outputString);
    }

}
