package IO;

import BoardStructure.Rack;
import BoardStructure.Tile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RackOutputTest {

    private Rack rack;

    public RackOutputTest() {
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
                new Tile('E', 1),
                new Tile('Y', 10)
        };
        rack.addTilesToRack(newTiles);

        return rack;
    }

    private static int getStringLengthWithoutANSI(String str) {
        // String length doesn't work well if ANSI Escape Codes for coloured terminal output is used
        // Source: https://stackoverflow.com/a/64677848
        return str.replaceAll("(\\x9B|\\x1B\\[)[0-?]*[-/]*[@-~]", "").length();
    }

    @Test
    public void rackWithSevenTilesShouldBeSevenTilesWide() {
        StringBuilder[] rackLines = RackOutput.getRackOutputLines(this.rack);

        int rackWidthExpected = 7 * 5 + 7; // 7 tiles with width 5 plus 7 spaces
        int rackTopWidthActual = getStringLengthWithoutANSI(rackLines[0].toString());
        int rackBottomWidthActual = getStringLengthWithoutANSI(rackLines[1].toString());

        assertEquals(rackWidthExpected, rackTopWidthActual);
        assertEquals(rackWidthExpected, rackBottomWidthActual);
    }

    @Test
    public void rackShouldBeOfHeightTwo() {
        StringBuilder[] rackLines = RackOutput.getRackOutputLines(this.rack);

        assertEquals(2, rackLines.length);
    }
}