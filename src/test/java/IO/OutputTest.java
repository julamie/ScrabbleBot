package IO;

import BoardStructure.*;
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
        Rack rack = new Rack();
        Tile[] newTiles = {
                new Tile('A', 1),
                new Tile('B', 3),
                new Tile('R', 1),
                new Tile('Ü', 6),
                new Tile('?', 0),
                new Tile('E', 1),
                new Tile('Y', 10)
        };
        rack.addTilesToRack(newTiles);

        Output.printRack(rack);
    }

    @Test
    void printFullBag() {
        Bag bag = new Bag();

        Output.printBag(bag);
    }

    @Test
    void printTestBoard() {
        Board board = new Board();

        // sets the word 'AXOLOTL' horizontally in the middle of the board
        board.setTileOnBoard(new Tile('A', 1), 7, 4);
        board.setTileOnBoard(new Tile('X', 8), 7, 5);
        board.setTileOnBoard(new Tile('O', 2), 7, 6);
        board.setTileOnBoard(new Tile('L', 2), 7, 7);
        board.setTileOnBoard(new Tile('O', 2), 7, 8);
        board.setTileOnBoard(new Tile('T', 1), 7, 9);
        board.setTileOnBoard(new Tile('L', 2), 7, 10);

        // sets 'QUALEN' vertically at the 'L' of 'AXOLOTL'
        board.setTileOnBoard(new Tile('Q', 10), 4, 10);
        board.setTileOnBoard(new Tile('U', 1), 5, 10);
        board.setTileOnBoard(new Tile('A', 1), 6, 10);
        board.setTileOnBoard(new Tile('E', 1), 8, 10);
        board.setTileOnBoard(new Tile('N', 1), 9, 10);

        // sets 'AUFZUG' with a blank 'Z' on the board
        board.setTileOnBoard(new Tile('A', 1), 5, 9);
        board.setTileOnBoard(new Tile('F', 4), 5, 11);
        board.setTileOnBoard(new Tile('?', 0), 5, 12);
        board.setTileOnBoard(new Tile('U', 1), 5, 13);
        board.setTileOnBoard(new Tile('G', 2), 5, 14);

        // sets 'RABBI' horizontally, simultaneously creating the words 'AB' and 'XI'
        board.setTileOnBoard(new Tile('R', 1), 8, 1);
        board.setTileOnBoard(new Tile('A', 1), 8, 2);
        board.setTileOnBoard(new Tile('B', 3), 8, 3);
        board.setTileOnBoard(new Tile('B', 3), 8, 4);
        board.setTileOnBoard(new Tile('I', 1), 8, 5);

        // sets 'RÄTE' vertically at the 'R' of 'RABBI'
        board.setTileOnBoard(new Tile('Ä', 6), 9, 1);
        board.setTileOnBoard(new Tile('T', 1), 10, 1);
        board.setTileOnBoard(new Tile('E', 1), 11, 1);

        Output.printBoard(board);
    }
}