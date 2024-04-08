package BoardStructure;

import BoardStructure.Board;
import BoardStructure.Square;
import BoardStructure.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;

    public BoardTest() {
        this.board = new Board();
    }

    @Test
    void setTileOnEmptySquareShouldReturnTrue() {
        Board board = new Board();
        boolean placingResult = board.setTileOnBoard(new Tile('Ä', 6), 3, 4);

        assertTrue(placingResult);
    }

    @Test
    void setTileOnOccupiedSquareShouldReturnFalse() {
        Board board = new Board();
        board.setTileOnBoard(new Tile('Ä', 6), 3, 4);
        boolean placingResult = board.setTileOnBoard(new Tile('O', 2), 3, 4);

        assertFalse(placingResult);
    }

    @Test
    void getSquareAtCorrectPositionShouldReturnASquare() {
        Board board = new Board();
        board.setTileOnBoard(new Tile('W', 4), 0, 12);
        Square returnedSquare = board.getSquareAtPos(0, 12);

        assertEquals(Square.class, returnedSquare.getClass());

    }

    @Test
    void getSquareAtIncorrectPositionShouldReturnIndexOutOfBoundsException() {
        Board board = new Board();
        assertThrows(IndexOutOfBoundsException.class, () -> board.getSquareAtPos(15, 4));
    }

    @Test
    void boardSizeShouldBe15() {
        assertEquals(15, this.board.getSize());
    }

    @Test
    void UnoccupiedSquareShouldReturnFalseBecauseItsNotOccupied() {
        assertFalse(board.isOccupiedAtPos(2, 2));
    }

    @Test
    void OccupiedSquareShouldReturnTrueIfBecauseItsOccupied() {
        board.setTileOnBoard(new Tile('X', 8), 4, 12);
        assertTrue(board.isOccupiedAtPos(4, 12));
    }

}