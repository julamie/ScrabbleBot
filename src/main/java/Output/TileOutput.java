package Output;

import BoardStructure.Tile;

public class TileOutput {
    private static final String RESET = "\u001B[0m"; // no background
    private static final String CREAM_BG = "\033[48;2;238;221;187;30;1m"; // black, bold letters

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
        return new StringBuilder[]{
                getUpperLineOfTile(tile),
                getLowerLineOfTile(tile)
        };
    }
}
