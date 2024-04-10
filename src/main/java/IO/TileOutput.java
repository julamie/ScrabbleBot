package IO;

import BoardStructure.Tile;

public class TileOutput {
    private static final String RESET = "\u001B[0;1m"; // no background, bold letters
    private static final String CREAM_BG = "\033[48;2;238;221;187;30m"; // black letters

    private static StringBuilder getUpperLineOfTile(Tile tile) {
        StringBuilder output = new StringBuilder(CREAM_BG);

        return output.append("  ").append(tile.getLetter()).append("  ").append(RESET);
    }

    private static StringBuilder getLowerLineOfTile(Tile tile) {
        StringBuilder output = new StringBuilder(CREAM_BG);

        // don't print letter value of blank tile
        if (tile.isBlank()) return output.append("     ").append(RESET);

        // add whitespace for values
        if (tile.getValue() >= 10) output.append("   ");
        else output.append("    ");

        return output.append(tile.getValue()).append(RESET);
    }

    public static StringBuilder[] getTileOutputLines(Tile tile) {
        StringBuilder[] lines = {
                getUpperLineOfTile(tile),
                getLowerLineOfTile(tile)
        };

        return lines;
    }

    public static void printTile(Tile tile) {
        StringBuilder[] lines = getTileOutputLines(tile);

        System.out.println(lines[0] + "\n" + lines[1]);
    }
}
