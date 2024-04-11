package IO;

import BoardStructure.Bag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagOutputTest {

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    void newBagHasAll30TilesDisplayed() {
        Bag bag = new Bag();
        StringBuilder[] lines = BagOutput.getBagOutputLines(bag);

        assertEquals(30, lines.length);
    }

    @Test
    void newBagShouldHave15ETiles() {
        Bag bag = new Bag();
        StringBuilder[] lines = BagOutput.getBagOutputLines(bag);
        String eLine = lines[4].toString();

        // E-Tile row width = numOfE * TileWidth * tileHeight + one newLine
        int expectedWidth = 15 * 5 * 2 + 1;
        assertEquals(expectedWidth, getStringLengthWithoutANSI(eLine));
    }
}