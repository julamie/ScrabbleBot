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
    private final ByteArrayOutputStream savedOutput = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public RackOutputTest() {
        this.rack = createTestRack();
    }

    @BeforeEach
    public void redirectTerminalOutputToObject() {
        System.setOut(new PrintStream(savedOutput));
        RackOutput.printRack(this.rack);
    }

    @AfterEach
    public void restoreOriginalOutputStream() {
        System.setOut(originalOut);
    }

    private Rack createTestRack() {
        Rack rack = new Rack();
        Tile[] newTiles = {
                new Tile('A', 1),
                new Tile('B', 3),
                new Tile('R', 1),
                new Tile('Ü', 6),
                new Tile('?', 0),
                new Tile('E', 1)
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
    public void rackWith6TilesShouldBeSixTilesWide() {
        String[] rackLines = savedOutput.toString().split("\n");

        int rackWidthExpected = 6 * 5 + 6; // 6 tiles with width 5 plus 6 spaces
        int rackTopWidthActual = getStringLengthWithoutANSI(rackLines[0]);
        int rackBottomWidthActual = getStringLengthWithoutANSI(rackLines[1]);

        assertEquals(rackWidthExpected, rackTopWidthActual);
        assertEquals(rackWidthExpected, rackBottomWidthActual);
    }

    @Test
    public void rackShouldBeOfHeightTwo() {
        String[] rackLines = savedOutput.toString().split("\n");
        assertEquals(2 + 1, rackLines.length); // 2 lines and one empty line
    }
}