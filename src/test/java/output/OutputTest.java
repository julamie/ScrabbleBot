package output;

import boardStructure.*;
import utils.TestObjects;
import org.junit.jupiter.api.Test;

class OutputTest {
    @Test
    void printAllTileTypes() {
        Tile normalTile = new Tile('E', 1);
        Tile doubleDigitTile = new Tile('Q', 10);
        Tile blankTile = new Tile('?', 0);

        Output.printTile(normalTile);
        Output.printTile(doubleDigitTile);
        Output.printTile(blankTile);
    }

    @Test
    void printAllSquareTypes() {
        Square emptySquare = new Square(SquareType.NORMAL);
        Square occupiedSquare = new Square(SquareType.NORMAL);
        occupiedSquare.occupySquare(new Tile('Y', 10));
        Square doubleLetterSquare = new Square(SquareType.LETTER_BONUS_DOUBLE);
        Square tripleLetterSquare = new Square(SquareType.LETTER_BONUS_TRIPLE);
        Square doubleWordSquare = new Square(SquareType.WORD_BONUS_DOUBLE);
        Square tripleWordSquare = new Square(SquareType.WORD_BONUS_TRIPLE);

        Output.printSquare(emptySquare);
        Output.printSquare(occupiedSquare);
        Output.printSquare(doubleLetterSquare);
        Output.printSquare(tripleLetterSquare);
        Output.printSquare(doubleWordSquare);
        Output.printSquare(tripleWordSquare);
    }

    @Test
    void printTestRack() {
        Rack rack = new TestObjects().getTestRack();

        Output.printRack(rack);
    }

    @Test
    void printFullBag() {
        Bag bag = new Bag();

        Output.printBag(bag);
    }

    @Test
    void printTestBoard() {
        Board board = new TestObjects().getTestBoard();

        Output.printBoard(board);
    }
}