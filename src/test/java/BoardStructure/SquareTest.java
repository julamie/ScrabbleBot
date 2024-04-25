package BoardStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    Square normalEmptySquare;
    Square normalOccupiedSquare;

    @BeforeEach
    void setup() {
        this.normalEmptySquare = new Square(SquareType.NORMAL);
        this.normalOccupiedSquare = new Square(SquareType.NORMAL);
        this.normalOccupiedSquare.occupySquare(new Tile('E', 1));
    }

    @Test
    public void normalSquareShouldReturnNormalSquareType() {
        assertEquals(SquareType.NORMAL, normalEmptySquare.getType());
    }

    @Test
    void doubleLetterBonusShouldReturnThisAsItsType() {
        Square doubleLetterSquare = new Square(SquareType.LETTER_BONUS_DOUBLE);

        assertEquals(SquareType.LETTER_BONUS_DOUBLE, doubleLetterSquare.getType());
    }

    @Test
    void occupyEmptySquareShouldNotThrowError() {
        Tile normalTile = new Tile('G', 3);

        assertDoesNotThrow(() -> this.normalEmptySquare.occupySquare(normalTile));
    }

    @Test
    void occupyAlreadyOccupiedSquareShouldThrowException() {
        Tile secondTile = new Tile('B', 2);

        assertThrows(IllegalArgumentException.class, () -> this.normalOccupiedSquare.occupySquare(secondTile));
    }

    @Test
    void emptySquareShouldNotBeOccupied() {
        assertFalse(this.normalEmptySquare.isOccupied());
    }

    @Test
    void occupiedSquareShouldBeOccupied() {
        assertTrue(this.normalOccupiedSquare.isOccupied());
    }

    @Test
    void getLetterFromEmptySquareShouldReturnASpace() {
        assertNull(this.normalEmptySquare.getLetter());
    }

    @Test
    void getLetterFromOccupiedSquareShouldReturnItsName() {
        assertEquals('E', this.normalOccupiedSquare.getLetter());
    }

    @Test
    void getValueFromEmptySquareShouldReturnMinusOne() {
        assertNull(this.normalEmptySquare.getValue());
    }

    @Test
    void getValueFromOccupiedSquareShouldReturnItsValue() {
        assertEquals(1, this.normalOccupiedSquare.getValue());
    }

}