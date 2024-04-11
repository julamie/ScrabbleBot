import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Rack;
import IO.Output;

public class Main {
    public static void main(String[] args) {
        var board = new Board();
        var rack = new Rack();
        var bag = new Bag();

        Output.printBoard(board);
        Output.printRack(rack);
        Output.printBag(bag);
    }
}