import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Rack;
import IO.BagOutput;
import IO.BoardOutput;
import IO.RackOutput;

public class Main {
    public static void main(String[] args) {
        var board = new Board();
        var rack = new Rack();
        var bag = new Bag();

        BoardOutput.printBoard(board);
        RackOutput.printRack(rack);
        BagOutput.printBagContents(bag);
    }
}