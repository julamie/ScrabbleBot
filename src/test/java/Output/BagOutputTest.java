package Output;

import BoardStructure.Bag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagOutputTest {

    private Bag bag;

     @BeforeEach
     void setup() {
         this.bag = new Bag();
     }

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    void newBagHasAll30TilesDisplayed() {
        StringBuilder[] lines = BagOutput.getBagOutputLines(this.bag);

        assertEquals(30, lines.length);
    }

    @Test
    void newBagShouldHave15ETiles() {
        StringBuilder[] lines = BagOutput.getBagOutputLines(this.bag);
        String eLine = lines[4].toString();

        // E-Tile row width = numOfE * TileWidth * tileHeight + one newLine
        int expectedWidth = 15 * 5 * 2 + 1;
        assertEquals(expectedWidth, getStringLengthWithoutANSI(eLine));
    }
}