package BoardStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    private Bag bag;
    private Rack rack;

    @BeforeEach
    void setup() {
        this.bag = new Bag();
        this.rack = new Rack();
    }

    private void removeAlmostAllTilesFromBag() {
        while (this.bag.getSize() > 3) {
            this.bag.drawTilesFromBagToRack(this.rack, 1);
            char letterOnRack = this.rack.getLettersOnRack()[0];
            this.rack.removeTileFromRack(letterOnRack);
        }
    }

    @Test
    void drawAllowedAmountOfTilesShouldReturnTrue() {
        assertTrue(this.bag.drawTilesFromBagToRack(this.rack, 7));
    }

    @Test
    void drawTooManyTilesAtOnceShouldReturnFalse() {
        assertFalse(this.bag.drawTilesFromBagToRack(this.rack, 8));
    }

    @Test
    void drawingFromAlmostEmptyBagShouldReturnLessTilesThanRequested() {
        removeAlmostAllTilesFromBag();
        bag.drawTilesFromBagToRack(this.rack, 7);

        int rackSizeExpected = 3; // empty rack plus remaining 3 tiles from bag
        int bagSizeExpected = 0;

        assertEquals(rackSizeExpected, this.rack.getSize());
        assertEquals(bagSizeExpected, this.bag.getSize());
    }

    @Test
    void exchangingTilesOnAlmostEmptyBagShouldReturnFalse() {
        removeAlmostAllTilesFromBag();

        Tile[] testTileToExchange = {new Tile('F', 3)};
        assertFalse(bag.exchangeTiles(this.rack, testTileToExchange));
    }

    @Test
    void afterExchangingTilesTheNumberOfTilesInBagAndRackShouldBeTheSameAsBefore() {
        Tile[] newTiles = {
                new Tile('A', 1),
                new Tile('B', 3),
                new Tile('R', 1),
                new Tile('Ü', 6),
                new Tile('?', 0),
                new Tile('A', 1)
        };
        this.rack.addTilesToRack(newTiles);

        int bagSizePrev = this.bag.getSize();
        int rackSizePrev = this.rack.getSize();

        Tile[] tilesToExchange = {
                this.rack.removeTileFromRack('A'),
                this.rack.removeTileFromRack('R'),
                this.rack.removeTileFromRack('Ü')
        };

        assertTrue(this.bag.exchangeTiles(this.rack, tilesToExchange));
        assertEquals(bagSizePrev, this.bag.getSize());
        assertEquals(rackSizePrev, this.rack.getSize());
    }

    @Test
    void newBagDistributionShouldBeTheSame() {
        Map<Character, Integer> expectedDistribution = new HashMap<>();
        expectedDistribution.put('E', 15);
        expectedDistribution.put('N', 9);
        expectedDistribution.put('S', 7);
        expectedDistribution.put('I', 6);
        expectedDistribution.put('R', 6);
        expectedDistribution.put('T', 6);
        expectedDistribution.put('U', 6);
        expectedDistribution.put('A', 5);
        expectedDistribution.put('D', 4);
        expectedDistribution.put('H', 4);
        expectedDistribution.put('G', 3);
        expectedDistribution.put('L', 3);
        expectedDistribution.put('O', 3);
        expectedDistribution.put('M', 4);
        expectedDistribution.put('B', 2);
        expectedDistribution.put('W', 1);
        expectedDistribution.put('Z', 1);
        expectedDistribution.put('C', 2);
        expectedDistribution.put('F', 2);
        expectedDistribution.put('K', 2);
        expectedDistribution.put('P', 1);
        expectedDistribution.put('Ä', 1);
        expectedDistribution.put('J', 1);
        expectedDistribution.put('Ü', 1);
        expectedDistribution.put('V', 1);
        expectedDistribution.put('Ö', 1);
        expectedDistribution.put('X', 1);
        expectedDistribution.put('Q', 1);
        expectedDistribution.put('Y', 1);
        expectedDistribution.put('?', 2);

        Map<Character, Integer> actualDistribution = this.bag.getRemainingLetterDistribution();
        assertEquals(expectedDistribution, actualDistribution);
    }

    @Test
    void isEmptyShouldReturnFalseIfItHasElementsInside() {
        assertFalse(this.bag.isEmpty());
    }

    @Test
    void isEmptyShouldReturnTrueInAnEmptyBag() {
        removeAlmostAllTilesFromBag();
        this.bag.drawTilesFromBagToRack(this.rack, 3);
        assertTrue(this.bag.isEmpty());
    }
}