package boardStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    private Bag bag;

    @BeforeEach
    void setup() {
        this.bag = new Bag();
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
        this.bag.drawTilesFromBag(this.bag.getSize());
        assertTrue(this.bag.isEmpty());
    }
}