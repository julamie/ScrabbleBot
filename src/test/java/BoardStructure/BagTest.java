package BoardStructure;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    private Bag bag;

    public BagTest() {
        this.bag = new Bag();
    }

    private void removeAlmostAllTilesFromBag() {
        Rack rack = new Rack();
        while (this.bag.getSize() > 3) {
            this.bag.drawTilesFromBagToRack(rack, 1);
            char letterOnRack = rack.getLettersOnRack()[0];
            rack.removeTileFromRack(letterOnRack);
        }
    }

    @Test
    void drawAllowedAmountOfTilesShouldReturnTrue() {
        Rack rack = new Rack();
        assertTrue(this.bag.drawTilesFromBagToRack(rack, 7));
    }

    @Test
    void drawTooManyTilesAtOnceShouldReturnFalse() {
        Rack rack = new Rack();
        assertFalse(this.bag.drawTilesFromBagToRack(rack, 8));
    }

    @Test
    void drawingFromAlmostEmptyBagShouldReturnLessTilesThanRequested() {
        Rack rack = new Rack();
        removeAlmostAllTilesFromBag();
        bag.drawTilesFromBagToRack(rack, 7);

        int rackSizeExpected = 3; // empty rack plus remaining 3 tiles from bag
        int bagSizeExpected = 0;

        assertEquals(rackSizeExpected, rack.getSize());
        assertEquals(bagSizeExpected, this.bag.getSize());
    }

    @Test
    void exchangingTilesOnAlmostEmptyBagShouldReturnFalse() {
        Rack rack = new Rack();
        removeAlmostAllTilesFromBag();

        Tile[] testTileToExchange = {new Tile('F', 3)};
        assertFalse(bag.exchangeTiles(rack, testTileToExchange));
    }

    @Test
    void afterExchangingTilesTheNumberOfTilesInBagAndRackShouldBeTheSameAsBefore() {
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

        int bagSizePrev = this.bag.getSize();
        int rackSizePrev = rack.getSize();

        Tile[] tilesToExchange = {
                rack.removeTileFromRack('A'),
                rack.removeTileFromRack('R'),
                rack.removeTileFromRack('Ü')
        };

        assertTrue(this.bag.exchangeTiles(rack, tilesToExchange));
        assertEquals(bagSizePrev, this.bag.getSize());
        assertEquals(rackSizePrev, rack.getSize());
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
        this.bag.drawTilesFromBagToRack(new Rack(), 3);
        assertTrue(this.bag.isEmpty());
    }
}