package IO;

import BoardStructure.Rack;
import BoardStructure.Tile;

public class RackOutput {

    public static void printRack(Rack rack) {
        StringBuilder upperLine = new StringBuilder();
        StringBuilder lowerLine = new StringBuilder();

        // append the upper and lower lines of every tile to the respective output lines
        for (Tile tile : rack.getTileRack()) {
            StringBuilder[] tileLines = TileOutput.getTileOutputLines(tile);

            upperLine.append(tileLines[0]).append(' ');
            lowerLine.append(tileLines[1]).append(' ');
        }

        String output = upperLine.toString() + '\n' + lowerLine + '\n';
        System.out.println(output);
    }
}
