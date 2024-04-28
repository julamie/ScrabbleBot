package boardStructure;

import gameSetup.Language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Bag {

    private final ArrayList<Tile> bag;
    private final Language language;

    public Bag(Language language) {
        this.bag = fillBag(language);
        this.language = language;
    }

    private ArrayList<Tile> fillBag(Language language) {
        // fill the bag according to the starting tile distribution
        ArrayList<Tile> bag = new ArrayList<>();

        language.getLetterDistribution().forEach(
            ((letter, numTiles) -> {
                Tile newTile = new Tile(letter, language.getLetterValues().get(letter));
                ArrayList<Tile> newTiles = generateNTiles(newTile, numTiles);
                bag.addAll(newTiles);
            }
        ));

        return bag;
    }

    private ArrayList<Tile> generateNTiles(Tile tile, int numTiles) {
        ArrayList<Tile> tiles = new ArrayList<>();

        for (int i = 0; i < numTiles; i++) {
            tiles.add(new Tile(tile.getLetter(), tile.getValue()));
        }

        return tiles;
    }

    public Tile[] drawTilesFromBag(int numTiles) {
        Random rng = new Random();
        Tile[] removedTiles = new Tile[numTiles];

        for (int i = 0; i < numTiles; i++) {
            int randomPosition = rng.nextInt(this.bag.size());
            Tile removedTile = this.bag.remove(randomPosition);
            removedTiles[i] = removedTile;
        }

        return removedTiles;
    }

    public void addTilesToBag(Tile[] tiles) {
        this.bag.addAll(Arrays.asList(tiles));
    }

    public HashMap<Character, Integer> getRemainingLetterDistribution() {
        HashMap<Character, Integer> currDistribution = new HashMap<>();
        for (Tile tile: this.bag) {
            int currNumOccurrences = currDistribution.getOrDefault(tile.getLetter(), 0);
            currDistribution.put(tile.getLetter(), currNumOccurrences + 1);
        }

        return currDistribution;
    }

    public int getSize() {
        return this.bag.size();
    }

    public boolean isEmpty() {
        return this.bag.isEmpty();
    }

    public Language getLanguage() {
        return language;
    }
}
