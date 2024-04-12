package BoardStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private Tile exampleTile;

    @BeforeEach
    void setup() {
        this.board = new Board();
        this.exampleTile = new Tile('Ä', 6);
    }

    @Test
    void setTileOnEmptySquareShouldNotThrowException() {
        assertDoesNotThrow(() -> this.board.setTileOnBoard(this.exampleTile, 3, 4));
    }

    @Test
    void setTileOnOccupiedSquareShouldThrowException() {
        this.board.setTileOnBoard(this.exampleTile, 3, 4);

        assertThrows(IllegalArgumentException.class, () -> this.board.setTileOnBoard(new Tile('O', 2), 3, 4));
    }

    @Test
    void getSquareAtCorrectPositionShouldReturnASquare() {
        this.board.setTileOnBoard(this.exampleTile, 0, 12);
        Square returnedSquare = this.board.getSquareAtPos(0, 12);

        assertEquals(Square.class, returnedSquare.getClass());
    }

    @Test
    void getSquareAtIncorrectPositionShouldReturnIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.board.getSquareAtPos(15, 4));
    }

    @Test
    void boardSizeShouldBe15() {
        assertEquals(15, this.board.getSize());
    }

    @Test
    void UnoccupiedSquareShouldReturnFalseBecauseItsNotOccupied() {
        assertFalse(this.board.isOccupiedAtPos(2, 2));
    }

    @Test
    void OccupiedSquareShouldReturnTrueIfBecauseItsOccupied() {
        this.board.setTileOnBoard(this.exampleTile, 4, 12);
        assertTrue(this.board.isOccupiedAtPos(4, 12));
    }

}