package BoardStructure;

import Utils.TestObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {

    private Rack rack;

    @BeforeEach
    void setup() {
        this.rack = new TestObjects().getTestRack();
    }

    @Test
    void removingExistingTileShouldNotThrowAnException() {
        assertDoesNotThrow(() -> this.rack.removeTileFromRack('?'));
    }

    @Test
    void removingNotExistingLetterShouldReturnRenamedBlankIfThereIsOne() {
        Tile removedTile = this.rack.removeTileFromRack('X');
        assertEquals(0, removedTile.getValue());
        assertEquals('X', removedTile.getLetter());
    }

    @Test
    void removingNotExistingTileShouldThrowAnException() {
        this.rack.removeTileFromRack('?');
        assertThrows(IllegalArgumentException.class, () -> this.rack.removeTileFromRack('F'));
    }

    @Test
    void removingOneTileShouldRemoveOnlyOneTileWithTheSameLetter() {
        // add replace tile with a second 'A'
        this.rack.removeTileFromRack('Y');
        this.rack.addTilesToRack(new Tile[]{new Tile('A', 1)});

        this.rack.removeTileFromRack('A');

        char[] remainingLetters = this.rack.getLettersOnRack();

        boolean letterExists = false;
        for (char letter : remainingLetters) {
            if (letter != 'A') continue;

            letterExists = true;
        }

        assertTrue(letterExists);
    }

    @Test
    void rackShouldContainAnR() {
        assertTrue(this.rack.doesRackContain('r'));
    }

    @Test
    void rackShouldContainABlankTile() {
        assertTrue(this.rack.doesRackContain('?'));
    }

    @Test
    void rackShouldNotContainAnF() {
        assertFalse(this.rack.doesRackContain('F'));
    }
}