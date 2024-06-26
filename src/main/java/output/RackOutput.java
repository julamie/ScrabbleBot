package output;

import boardStructure.Rack;
import boardStructure.Tile;

public class RackOutput {

    public static StringBuilder[] getRackOutputLines(Rack rack) {
        StringBuilder upperLine = new StringBuilder();
        StringBuilder lowerLine = new StringBuilder();

        // append the upper and lower lines of every tile to the respective output lines
        for (Tile tile : rack.getTileRack()) {
            StringBuilder[] tileLines = TileOutput.getTileOutputLines(tile);

            upperLine.append(tileLines[0]).append(' ');
            lowerLine.append(tileLines[1]).append(' ');
        }

        return new StringBuilder[]{
                upperLine,
                lowerLine
        };
    }
}
