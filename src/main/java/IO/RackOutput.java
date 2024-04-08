package IO;

import BoardStructure.Rack;
import BoardStructure.Tile;

public class RackOutput {
    private static final String RESET = "\u001B[0m"; // no background, bold letters
    public static final String CREAM_BG_BOLD_LETTERS = "\033[1;48;2;238;221;187;30m"; // black letters

    private static StringBuilder getTopLineOfTile(Tile tile) {
        StringBuilder output = new StringBuilder(CREAM_BG_BOLD_LETTERS);

        // write the letter if it's not blank
        if (tile.isBlank()) output.append("     ");
        else output.append("  ").append(tile.getLetter()).append("  ");

        // add an empty space for the next letter
        output.append(RESET).append(' ');

        return output;
    }

    private static StringBuilder getBottomLineOfTile(Tile tile) {
        StringBuilder output = new StringBuilder(CREAM_BG_BOLD_LETTERS);

        int value = tile.getValue();
        if (tile.isBlank()) output.append("     ");
        else if (value >= 10) output.append("   ").append(value);
        else output.append("    ").append(value);

        output.append(RESET).append(' ');

        return output;
    }

    private static StringBuilder getTopLine(Rack rack) {
        StringBuilder output = new StringBuilder();
        for (Tile tile: rack.getTileRack()) {
            output.append(getTopLineOfTile(tile));
        }
        output.append(RESET);

        return output;
    }

    private static StringBuilder getBottomLine(Rack rack) {
        StringBuilder output = new StringBuilder();
        for (Tile tile: rack.getTileRack()) {
            output.append(getBottomLineOfTile(tile));
        }
        output.append(RESET);

        return output;
    }

    public static void printRack(Rack rack) {
        String rackOutput = getTopLine(rack).toString() + '\n' + getBottomLine(rack) + '\n';

        System.out.println(rackOutput);
    }
}
