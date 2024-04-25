package BoardStructure;

import java.util.ArrayList;
import java.util.Arrays;

public class Rack {

    private final ArrayList<Tile> tileRack;

    public Rack() {
        this.tileRack = new ArrayList<>();
    }

    public void addTilesToRack(Tile[] tiles) {
        this.tileRack.addAll(Arrays.asList(tiles));
    }

    public Tile removeTileFromRack(char letter) {
        // remove exactly one tile with the correct letter
        for (int i = 0; i < this.tileRack.size(); i++) {
            char currLetter = this.tileRack.get(i).getLetter();
            if (currLetter != letter) continue;

            return this.tileRack.remove(i);
        }

        // remove blank tile if there is one
        for (int i = 0; i < this.tileRack.size(); i++) {
            Tile currTile = this.tileRack.get(i);
            if (!currTile.isBlank()) continue;

            currTile.setLetter(letter); // TODO: Should rack change the letter value of a blank tile?
            return this.tileRack.remove(i);
        }

        // no tile to remove has been found
        throw new IllegalArgumentException("No tile with this letter has been found");
    }

    public ArrayList<Tile> getTileRack() {
        // TODO: I'm not sure if I want to keep this method as it is right now
        return tileRack;
    }

    public char[] getLettersOnRack() {
        char[] rackLetters = new char[this.tileRack.size()];

        for (int i = 0; i < this.tileRack.size(); i++) {
            rackLetters[i] = this.tileRack.get(i).getLetter();
        }

        return rackLetters;
    }

    public int getSize() {
        return this.tileRack.size();
    }

    public boolean isEmpty() {
        return this.tileRack.isEmpty();
    }
}
