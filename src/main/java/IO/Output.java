package IO;

import BoardStructure.*;

public class Output {

    public static void printTile(Tile tile) {
        StringBuilder[] lines = TileOutput.getTileOutputLines(tile);
        StringBuilder output = new StringBuilder();

        for (StringBuilder line : lines) {
            output.append(line).append('\n');
        }

        System.out.println(output);
    }

    public static void printSquare(Square square) {
        StringBuilder[] lines = SquareOutput.getSquareOutputLines(square);
        StringBuilder output = new StringBuilder();

        for (StringBuilder line : lines) {
            output.append(line).append('\n');
        }

        System.out.println(output);
    }

    public static void printRack(Rack rack) {
        StringBuilder[] lines = RackOutput.getRackOutputLines(rack);
        StringBuilder output = new StringBuilder();

        for (StringBuilder line : lines) {
            output.append(line).append('\n');
        }

        System.out.println(output);
    }

    public static void printBag(Bag bag) {
        StringBuilder[] lines = BagOutput.getBagOutputLines(bag);
        StringBuilder output = new StringBuilder();

        for (StringBuilder line : lines) {
            output.append(line).append('\n');
        }

        System.out.println(output);
    }

    public static void printBoard(Board board) {
        StringBuilder[] lines = BoardOutput.getBoardOutputLines(board);
        StringBuilder output = new StringBuilder();

        for (StringBuilder line : lines) {
            output.append(line).append('\n');
        }

        System.out.println(output);
    }

}
