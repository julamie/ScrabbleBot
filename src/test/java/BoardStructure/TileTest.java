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

}