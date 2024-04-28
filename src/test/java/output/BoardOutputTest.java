package output;

import boardStructure.Board;
import boardStructure.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardOutputTest {

    private final StringBuilder[] boardLines;

    public BoardOutputTest() {
        Board board = createTestBoard();
        this.boardLines = BoardOutput.getBoardOutputLines(board);
    }

    private Board createTestBoard() {
        Board board = new Board();

        // sets the word 'AXOLOTL' horizontally in the middle of the board
        board.setTileOnBoard(new Tile('A', 1), 7, 4);
        board.setTileOnBoard(new Tile('X', 8), 7, 5);
        board.setTileOnBoard(new Tile('O', 2), 7, 6);
        board.setTileOnBoard(new Tile('L', 2), 7, 7);
        board.setTileOnBoard(new Tile('O', 2), 7, 8);
        board.setTileOnBoard(new Tile('T', 1), 7, 9);
        board.setTileOnBoard(new Tile('L', 2), 7, 10);

        // sets 'QUALEN' vertically at the 'L' of 'AXOLOTL'
        board.setTileOnBoard(new Tile('Q', 10), 4, 10);
        board.setTileOnBoard(new Tile('U', 1), 5, 10);
        board.setTileOnBoard(new Tile('A', 1), 6, 10);
        board.setTileOnBoard(new Tile('E', 1), 8, 10);
        board.setTileOnBoard(new Tile('N', 1), 9, 10);

        // sets 'AUFZUG' with a blank 'Z' on the board
        board.setTileOnBoard(new Tile('A', 1), 5, 9);
        board.setTileOnBoard(new Tile('F', 4), 5, 11);
        board.setTileOnBoard(new Tile('?', 0), 5, 12);
        board.setTileOnBoard(new Tile('U', 1), 5, 13);
        board.setTileOnBoard(new Tile('G', 2), 5, 14);

        // sets 'RABBI' horizontally, simultaneously creating the words 'AB' and 'XI'
        board.setTileOnBoard(new Tile('R', 1), 8, 1);
        board.setTileOnBoard(new Tile('A', 1), 8, 2);
        board.setTileOnBoard(new Tile('B', 3), 8, 3);
        board.setTileOnBoard(new Tile('B', 3), 8, 4);
        board.setTileOnBoard(new Tile('I', 1), 8, 5);

        // sets 'RÄTE' vertically at the 'R' of 'RABBI'
        board.setTileOnBoard(new Tile('Ä', 6), 9, 1);
        board.setTileOnBoard(new Tile('T', 1), 10, 1);
        board.setTileOnBoard(new Tile('E', 1), 11, 1);

        return board;
    }

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    public void printedBoardShouldBe15SquaresWideEverywhere() {
        int boardHeight = 15;
        int[] boardWidthExpected = new int[boardHeight];
        int[] boardWidthActual = new int[boardHeight];

        // coordinate line width = rowLineWidth + numSquares * squareWidth + rowLineWidth
        assertEquals(3 + 15 * 5 + 3, this.boardLines[0].toString().length());
        assertEquals(3 + 15 * 5 + 3, this.boardLines[16].toString().length());

        for (int i = 0; i < boardHeight; i++) {
            // Square row width = rowLineWidth + numSquares * squareWidth * tileHeight + rowLineWidth + one newLine
            boardWidthExpected[i] = 2 * 3 + 15 * 5 * 2 + 2 * 3 + 1;
            boardWidthActual[i] = getStringLengthWithoutANSI(this.boardLines[i + 1].toString());
        }

        // check width dimensions
        assertArrayEquals(boardWidthExpected, boardWidthActual);
    }

    @Test
    public void printedBoardShouldHave17Lines() {
        assertEquals(15 + 2, this.boardLines.length); // square lines plus two letter coordinate lines
    }
}
