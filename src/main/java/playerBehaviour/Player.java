package playerBehaviour;

import boardStructure.*;
import logic.Scoring;
import logic.Word;
import logic.WordValidation;
import logic.WordValidity;

public abstract class Player {

    protected final Board board;
    protected final Bag bag;
    protected final Rack rack;
    protected String name;
    protected int score;
    protected int numberConsecutivePasses;

    public Player(Board board, Bag bag) {
        this.board = board;
        this.bag = bag;
        this.rack = new Rack();
        this.score = 0;
        this.numberConsecutivePasses = 0;
    }

    public void fillRack() {
        // rack should have 7 tiles, or less if there are no more tiles in the bag
        int numTiles = Math.min(this.bag.getSize(), 7 - this.rack.getSize());

        Tile[] removedTiles = this.bag.drawTilesFromBag(numTiles);
        this.rack.addTilesToRack(removedTiles);
    }

    protected WordValidity checkWord(Word word) {
        WordValidation wordValidation = new WordValidation(this, word);

        return wordValidation.getWordValidity();
    }

    protected boolean setWordOnBoard(Word word) {
        if (checkWord(word) != WordValidity.VALID) return false;

        // replace tiles in word with actual tiles from rack, where blanks can be inside
        word = replaceWordTilesWithTilesFromRack(word);

        // change points before setting tiles on board for checking if tile is already on board in Scoring
        handleScores(word);

        // set tiles on board
        for (int position = 0; position < word.getLength(); position++) {
            setTileOnBoard(word, position);
        }

        fillRack();

        return true;
    }

    private Word replaceWordTilesWithTilesFromRack(Word word) {
        Tile[] tiles = new Tile[word.getLength()];

        for (int position = 0; position < tiles.length; position++) {
            tiles[position] = getTileToPlay(word, position);
        }

        return new Word(tiles, word.getCoordinates(0), word.getDirection());
    }

    private Tile getTileToPlay(Word word, int position) {
        char currLetter = word.getLetter(position);
        Coordinates currCoordinates = word.getCoordinates(position);
        Square currSquare = this.board.getSquareAt(currCoordinates);

        // don't replace the tile if it is already on the board
        if (currSquare.isOccupied() && currSquare.getLetter() == currLetter) {
            return word.getWordAsTileArray()[position];
        }

        return this.rack.removeTileFromRack(currLetter);
    }

    private void setTileOnBoard(Word word, int position) {
        Tile tileFromRack = word.getWordAsTileArray()[position];
        Coordinates tileCoordinate = word.getCoordinates(position);

        if (this.board.isOccupiedAt(tileCoordinate)) return;

        this.board.setTileOnBoard(tileFromRack, tileCoordinate);
    }

    private void handleScores(Word word) {
        this.score += new Scoring(this.board, word).calculateScore();
        if (this.rack.isEmpty()) this.score += 50;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberConsecutivePasses() {
        return this.numberConsecutivePasses;
    }

    public abstract void makeMove();

}
