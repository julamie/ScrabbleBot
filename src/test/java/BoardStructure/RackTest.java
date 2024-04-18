package BoardStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {

    private Rack rack;

    @BeforeEach
    void setup() {
        this.rack = createTestRack();
    }

    private Rack createTestRack() {
        Rack rack = new Rack();
        Tile[] newTiles = {
                new Tile('A', 1),
                new Tile('B', 3),
                new Tile('Q', 10),
                new Tile('Ü', 6),
                new Tile('?', 0),
                new Tile('A', 1)
        };
        rack.addTilesToRack(newTiles);

        return rack;
    }

    @Test
    void addOneTileShouldReturnTrue() {
        Tile[] newTile = {new Tile('E', 1)};
        assertTrue(this.rack.addTilesToRack(newTile));
    }

    @Test
    void addTooManyTilesToRackShouldReturnFalse() {
        Tile[] newTiles = {
                new Tile('G', 3),
                new Tile('Ü', 7)
        };
        assertFalse(this.rack.addTilesToRack(newTiles));
    }

    @Test
    void removingExistingTileShouldNotThrowAnException() {
        assertDoesNotThrow(() -> this.rack.removeTileFromRack('?'));
    }

    @Test
    void removingNotExistingLetterShouldReturnRenamedBlankIfThereIsOne() {
        Tile removedTile = this.rack.removeTileFromRack('X');
        assertTrue(removedTile.getValue() == 0);
        assertEquals('X', removedTile.getLetter());
    }

    @Test
    void removingNotExistingTileShouldThrowAnException() {
        this.rack.removeTileFromRack('?');
        assertThrows(IllegalArgumentException.class, () -> this.rack.removeTileFromRack('F'));
    }

    @Test
    void removingOneTileShouldRemoveOnlyOneTileWithTheSameLetter() {
        this.rack.removeTileFromRack('A');

        char[] remainingLetters = this.rack.getLettersOnRack();

        boolean letterExists = false;
        for (char letter : remainingLetters) {
            if (letter != 'A') continue;

            letterExists = true;
        }

        assertTrue(letterExists);
    }
}