package BoardStructure;

import IO.RackOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RackTest {

    private Rack rack;

    public RackTest() {
        this.rack = createTestRack();
    }

    private Rack createTestRack() {
        Rack rack = new Rack();
        Tile[] newTiles = {
                new Tile('A', 1),
                new Tile('B', 3),
                new Tile('R', 1),
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
        assertTrue(rack.addTilesToRack(newTile));
    }

    @Test
    void addTooManyTilesToRackShouldReturnFalse() {
        Tile[] newTiles = {
                new Tile('G', 3),
                new Tile('Ü', 7)
        };
        assertFalse(rack.addTilesToRack(newTiles));
    }

    @Test
    void removingExistingTileShouldNotThrowAnException() {
        assertDoesNotThrow(() -> rack.removeTileFromRack('?'));
    }

    @Test
    void removingNotExistingTileShouldThrowAnException() {
        assertThrows(IllegalArgumentException.class, () -> rack.removeTileFromRack('F'));
    }

    @Test
    void removingOneTileShouldRemoveOnlyOneTileWithTheSameLetter() {
        rack.removeTileFromRack('A');

        char[] remainingLetters = rack.getLettersOnRack();

        boolean letterExists = false;
        for (char letter : remainingLetters) {
            if (letter != 'A') continue;

            letterExists = true;
        }

        assertTrue(letterExists);
    }
}