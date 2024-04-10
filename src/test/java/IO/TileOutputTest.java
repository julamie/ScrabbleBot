package IO;

import BoardStructure.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileOutputTest {

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "").length();
    }

    @Test
    void upperLineOfTileShouldHaveWidth5() {
        StringBuilder[] lines = TileOutput.getTileOutputLines(new Tile('A', 1));
        String upperLine = lines[0].toString();

        assertEquals(5, getStringLengthWithoutANSI(upperLine));
    }

    @Test
    void lowerLineOfTileShouldAlwaysHaveWidth5() {
        Tile blankTile = new Tile('?', 0);
        Tile singleDigitTile = new Tile('E', 1);
        Tile doubleDigitTile = new Tile('Y', 10);

        StringBuilder[] blankTileOutput = TileOutput.getTileOutputLines(blankTile);
        StringBuilder[] singleDigitTileOutput = TileOutput.getTileOutputLines(singleDigitTile);
        StringBuilder[] doubleDigitTileOutput = TileOutput.getTileOutputLines(doubleDigitTile);

        String blankTileLowerLine = blankTileOutput[0].toString();
        String singleDigitTileLowerLine = singleDigitTileOutput[0].toString();
        String doubleDigitTileLowerLine = doubleDigitTileOutput[0].toString();

        assertEquals(5, getStringLengthWithoutANSI(blankTileLowerLine));
        assertEquals(5, getStringLengthWithoutANSI(singleDigitTileLowerLine));
        assertEquals(5, getStringLengthWithoutANSI(doubleDigitTileLowerLine));
    }

    @Test
    void blankTileShouldNotDisplayItsValue() {
        Tile blankTile = new Tile('?', 0);

        String lowerLine = TileOutput.getTileOutputLines(blankTile)[1].toString();

        // remove ANSI Encoding from string
        lowerLine = lowerLine.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[ -/]*[@-~]", "");

        assertEquals("     ", lowerLine);
    }

    @Test
    void printingTileShouldHaveHeightOfTwo() {
        Tile tile = new Tile('X', 8);

        StringBuilder[] lines = TileOutput.getTileOutputLines(tile);
        assertEquals(2, lines.length);
    }
}