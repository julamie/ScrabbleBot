package BoardStructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void checkIfGeneratingValidTileDoesntThrowAnException() {
        assertDoesNotThrow(() -> new Tile('F', 3));
    }

    @Test
    void checkIfGeneratingBlankWithPositiveValueThrowsAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Tile('?', 2));
    }

    @Test
    void checkIfGeneratingValidBlankDoesntThrowAnException() {
        assertDoesNotThrow(() -> new Tile('?', 0));
    }

    @Test
    void checkIfGeneratingTileWithNegativeValueThrowsAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Tile('W', -1));
    }

    @Test
    void checkIfGeneratingTileWithInvalidCharThrowsAnException() {
        assertThrows(IllegalArgumentException.class, () -> new Tile('2', 3));
    }

    @Test
    void checkIfBlankShouldReturnFalse() {
        Tile tile = new Tile('E', 1);
        assertFalse(tile.isBlank());
    }

    @Test
    void checkIfBlankShouldReturnTrue() {
        Tile tile = new Tile('?', 0);
        assertTrue(tile.isBlank());
    }

    @Test
    void checkIfRenamedBlankTileStillCountsAsABlankTile() {
        Tile tile = new Tile('?', 0);
        tile.setLetter('Q');

        assertTrue(tile.isBlank());
    }

    @Test
    void givingABlankTileALetterShouldBeOkay() {
        Tile blankTile = new Tile('?', 0);
        blankTile.setLetter('E');

        assertEquals('E', blankTile.getLetter());
    }

    @Test
    void givingANormalTileANewLetterShouldThrowException() {
        Tile tile = new Tile('E', 1);

        assertThrows(IllegalArgumentException.class, () -> tile.setLetter('F'));
    }

    @Test
    void givingABlankTileWithPreviouslyGivenLetterNewLetterShouldReturnException() {
        Tile blankTile = new Tile('?', 0);
        blankTile.setLetter('X');

        assertThrows(IllegalArgumentException.class, () -> blankTile.setLetter('G'));
    }

}