package PlayerBehaviour;

import BoardStructure.*;
import BoardStructure.Coordinates;
import Logic.Word;
import Logic.WordValidation;

public abstract class Player {

    protected Board board;
    protected Bag bag;
    protected Rack rack;
    protected int score;

    public Player(Board board, Bag bag) {
        this.board = board;
        this.bag = bag;
        this.rack = new Rack();
        this.score = 0;

        // TODO: Should the player immediately fill their rack?
        fillRack();
    }

    private void fillRack() {
        // rack should have 7 tiles, or less if there are no more tiles in the bag
        int numTiles = Math.min(this.bag.getSize(), 7 - this.rack.getSize());

        Tile[] removedTiles = this.bag.drawTilesFromBag(numTiles);
        this.rack.addTilesToRack(removedTiles);
    }


    private void setTileOnBoard(Word word, int position) {
        char currLetter = word.getLetter(position);
        Coordinates currCoordinates = word.getCoordinates(position);
        Square currSquare = this.board.getSquareAt(currCoordinates);

        if (currSquare.isOccupied() && currSquare.getLetter() == currLetter) return;

        Tile tileFromRack = this.rack.removeTileFromRack(currLetter);
        this.board.setTileOnBoard(tileFromRack, currCoordinates);
    }

    protected boolean isWordLegalToPlay(Word word) {
        WordValidation wordValidation = new WordValidation(this.board, this.rack, word);
        return wordValidation.isWordValid();
    }

    protected boolean setWordOnBoard(Word word) {
        if (!isWordLegalToPlay(word)) return false;

        for (int position = 0; position < word.getLength(); position++) {
            setTileOnBoard(word, position);
        }
        fillRack();

        return true;
    }

    protected boolean exchangeTiles(Tile[] tilesFromRack) {
        // you can't exchange letters if there are less than 7 tiles in the bag
        if (this.bag.getSize() < 7) return false;

        // get new tiles from the bag and put the other ones back
        Tile[] tilesFromBag = this.bag.drawTilesFromBag(tilesFromRack.length);
        this.bag.addTilesToBag(tilesFromRack);
        rack.addTilesToRack(tilesFromBag);

        return true;
    }

    public void addToScore(int points) {
        this.score += points;
    }

    public Rack getRack() {
        return this.rack;
    }

    public abstract void makeMove();

}
