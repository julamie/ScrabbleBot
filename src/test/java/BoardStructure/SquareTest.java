package BoardStructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    private Square generateNormalEmptySquare() {
        return new Square(SquareType.NORMAL);
    }

    private Square generateNormalOccupiedSquare() {
        Square occupiedSquare = new Square(SquareType.NORMAL);
        occupiedSquare.occupySquare(new Tile('E', 1));
        return occupiedSquare;
    }

    @Test
    public void normalSquareShouldReturnNormalSquareType() {
        Square normalSquare = generateNormalEmptySquare();

        assertEquals(SquareType.NORMAL, normalSquare.getType());
    }

    @Test void doubleLetterBonusShouldReturnThisAsItsType() {
        Square doubleLetterSquare = new Square(SquareType.LETTER_BONUS_DOUBLE);

        assertEquals(SquareType.LETTER_BONUS_DOUBLE, doubleLetterSquare.getType());
    }

    @Test
    void occupyEmptySquareShouldNotThrowError() {
        Square emptySquare = generateNormalEmptySquare();
        Tile normalTile = new Tile('G', 3);

        assertDoesNotThrow(() -> emptySquare.occupySquare(normalTile));
    }

    @Test
    void occupyAlreadyOccupiedSquareShouldThrowException() {
        Square occupiedSquare = generateNormalOccupiedSquare();
        Tile secondTile = new Tile('B', 2);

        assertThrows(IllegalArgumentException.class, () -> occupiedSquare.occupySquare(secondTile));
    }

    @Test
    void emptySquareShouldNotBeOccupied() {
        Square emptySquare = generateNormalEmptySquare();
        assertFalse(emptySquare.isOccupied());
    }

    @Test
    void occupiedSquareShouldBeOccupied() {
        Square occupiedSquare = generateNormalOccupiedSquare();
        assertTrue(occupiedSquare.isOccupied());
    }

    @Test
    void getLetterFromEmptySquareShouldReturnASpace() {
        Square emptySquare = generateNormalEmptySquare();

        assertEquals(' ', emptySquare.getLetter());
    }

    @Test
    void getLetterFromOccupiedSquareShouldReturnItsName() {
        Square occupiedSquare = generateNormalOccupiedSquare();

        assertEquals('E', occupiedSquare.getLetter());
    }

    @Test
    void getValueFromEmptySquareShouldReturnMinusOne() {
        Square emptySquare = generateNormalEmptySquare();

        assertEquals(-1, emptySquare.getValue());
    }

    @Test
    void getValueFromOccupiedSquareShouldReturnItsValue() {
        Square occupiedSquare = generateNormalOccupiedSquare();

        assertEquals(1, occupiedSquare.getValue());
    }

}