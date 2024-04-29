package playerBehaviour;

import boardStructure.*;
import logic.Word;
import logic.WordValidation;
import logic.WordValidity;

public abstract class Player {

    protected final Board board;
    protected final Bag bag;
    protected final Rack rack;
    protected int score;
    protected int numberConsecutivePasses;

    public Player(Board board, Bag bag) {
        this.board = board;
        this.bag = bag;
        this.rack = new Rack();
        this.score = 0;
        this.numberConsecutivePasses = 0;

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

    protected WordValidity checkWord(Word word) {
        WordValidation wordValidation = new WordValidation(this, word);

        return wordValidation.getWordValidity();
    }

    protected boolean setWordOnBoard(Word word) {
        if (checkWord(word) != WordValidity.VALID) return false;

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

    public Board getBoard() {
        return this.board;
    }

    public Rack getRack() {
        return this.rack;
    }

    public Bag getBag() {
        return this.bag;
    }

    public int getScore() {
        return this.score;
    }

    public int getNumberConsecutivePasses() {
        return this.numberConsecutivePasses;
    }

    public abstract void makeMove();

}
