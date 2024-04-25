package Utils;

import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Rack;
import BoardStructure.Tile;

public class TestObjects {
    public Board getTestBoard() {
        Board testBoard = new Board();

        // sets the word 'AXOLOTL' horizontally in the middle of the board
        testBoard.setTileOnBoard(new Tile('A', 1), 7, 4);
        testBoard.setTileOnBoard(new Tile('X', 8), 7, 5);
        testBoard.setTileOnBoard(new Tile('O', 2), 7, 6);
        testBoard.setTileOnBoard(new Tile('L', 2), 7, 7);
        testBoard.setTileOnBoard(new Tile('O', 2), 7, 8);
        testBoard.setTileOnBoard(new Tile('T', 1), 7, 9);
        testBoard.setTileOnBoard(new Tile('L', 2), 7, 10);

        // sets 'QUALEN' vertically at the 'L' of 'AXOLOTL'
        testBoard.setTileOnBoard(new Tile('Q', 10), 4, 10);
        testBoard.setTileOnBoard(new Tile('U', 1), 5, 10);
        testBoard.setTileOnBoard(new Tile('A', 1), 6, 10);
        testBoard.setTileOnBoard(new Tile('E', 1), 8, 10);
        testBoard.setTileOnBoard(new Tile('N', 1), 9, 10);

        // sets 'AUFZUG' with a blank 'Z' on the board
        Tile blankTile = new Tile('?', 0);
        blankTile.setLetter('Z');
        testBoard.setTileOnBoard(new Tile('A', 1), 5, 9);
        testBoard.setTileOnBoard(new Tile('F', 4), 5, 11);
        testBoard.setTileOnBoard(blankTile, 5, 12);
        testBoard.setTileOnBoard(new Tile('U', 1), 5, 13);
        testBoard.setTileOnBoard(new Tile('G', 2), 5, 14);

        // sets 'RABBI' horizontally, simultaneously creating the words 'AB' and 'XI'
        testBoard.setTileOnBoard(new Tile('R', 1), 8, 1);
        testBoard.setTileOnBoard(new Tile('A', 1), 8, 2);
        testBoard.setTileOnBoard(new Tile('B', 3), 8, 3);
        testBoard.setTileOnBoard(new Tile('B', 3), 8, 4);
        testBoard.setTileOnBoard(new Tile('I', 1), 8, 5);

        // sets 'RÄTE' vertically at the 'R' of 'RABBI'
        testBoard.setTileOnBoard(new Tile('Ä', 6), 9, 1);
        testBoard.setTileOnBoard(new Tile('T', 1), 10, 1);
        testBoard.setTileOnBoard(new Tile('E', 1), 11, 1);

        // sets BEZUG vertically at the right edge of the board
        testBoard.setTileOnBoard(new Tile('B', 3), 1, 14);
        testBoard.setTileOnBoard(new Tile('E', 1), 2, 14);
        testBoard.setTileOnBoard(new Tile('Z', 3), 3, 14);
        testBoard.setTileOnBoard(new Tile('U', 1), 4, 14);

        return testBoard;
    }

    public Rack getTestRack() {
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

        return rack;
    }

    public Bag getTestBag() {
        Bag bag = new Bag();
        bag.drawTilesFromBag(bag.getSize());
        bag.addTilesToBag(new Tile[] {
                new Tile('E', 1),
                new Tile('A', 1),
                new Tile('H', 2),
                new Tile('F', 4),
                new Tile('S', 1),
                new Tile('C', 4),
                new Tile('K', 4)
        });

        return bag;
    }

}
