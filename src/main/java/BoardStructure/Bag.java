package BoardStructure;

import java.util.*;

public class Bag {

    private final HashMap<Character, Integer> letterDistribution;
    private final HashMap<Character, Integer> letterValues;
    private ArrayList<Tile> bag;

    public Bag() {
        this.letterDistribution = generateLetterDistribution();
        this.letterValues = generateLetterValues();
        this.bag = fillBag();
    }

    private HashMap<Character, Integer> generateLetterDistribution() {
        HashMap<Character, Integer> distribution = new HashMap<>();

        // distribution based on the German Scrabble version
        distribution.put('N', 9);
        distribution.put('S', 7);
        distribution.put('I', 6);
        distribution.put('E', 15);
        distribution.put('R', 6);
        distribution.put('T', 6);
        distribution.put('U', 6);
        distribution.put('A', 5);
        distribution.put('D', 4);
        distribution.put('H', 4);
        distribution.put('G', 3);
        distribution.put('L', 3);
        distribution.put('O', 3);
        distribution.put('M', 4);
        distribution.put('B', 2);
        distribution.put('W', 1);
        distribution.put('Z', 1);
        distribution.put('C', 2);
        distribution.put('F', 2);
        distribution.put('K', 2);
        distribution.put('P', 1);
        distribution.put('Ä', 1);
        distribution.put('J', 1);
        distribution.put('Ü', 1);
        distribution.put('V', 1);
        distribution.put('Ö', 1);
        distribution.put('X', 1);
        distribution.put('Q', 1);
        distribution.put('Y', 1);
        distribution.put('?', 2);

        return distribution;
    }

    private HashMap<Character, Integer> generateLetterValues() {
        HashMap<Character, Integer> letterValues = new HashMap<>();

        // letter values based on the German Scrabble version
        letterValues.put('N', 1);
        letterValues.put('S', 1);
        letterValues.put('I', 1);
        letterValues.put('E', 1);
        letterValues.put('R', 1);
        letterValues.put('T', 1);
        letterValues.put('U', 1);
        letterValues.put('A', 1);
        letterValues.put('D', 1);

        letterValues.put('H', 2);
        letterValues.put('G', 2);
        letterValues.put('L', 2);
        letterValues.put('O', 2);

        letterValues.put('M', 3);
        letterValues.put('B', 3);
        letterValues.put('W', 3);
        letterValues.put('Z', 3);

        letterValues.put('C', 4);
        letterValues.put('F', 4);
        letterValues.put('K', 4);
        letterValues.put('P', 4);

        letterValues.put('Ä', 6);
        letterValues.put('J', 6);
        letterValues.put('Ü', 6);
        letterValues.put('V', 6);

        letterValues.put('Ö', 8);
        letterValues.put('X', 8);

        letterValues.put('Q', 10);
        letterValues.put('Y', 10);

        letterValues.put('?', 0);

        return letterValues;
    }

    private ArrayList<Tile> fillBag() {
        // fill the bag according to the starting tile distribution
        ArrayList<Tile> bag = new ArrayList<>();

        this.letterDistribution.forEach(
            ((letter, numTiles) -> {
                Tile newTile = new Tile(letter, this.letterValues.get(letter));
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

    private Tile[] removeTiles(int numTiles) {
        Random rng = new Random();
        Tile[] removedTiles = new Tile[numTiles];

        for (int i = 0; i < numTiles; i++) {
            int randomNumber = rng.nextInt(this.bag.size());
            Tile removedTile = this.bag.remove(randomNumber);
            removedTiles[i] = removedTile;
        }

        return removedTiles;
    }

    public boolean drawTilesFromBagToRack(Rack rack, int numTiles) {
        // you can only draw as many tiles as there are in the bag
        numTiles = Math.min(this.bag.size(), numTiles);

        Tile[] removedTiles = removeTiles(numTiles);
        return rack.addTilesToRack(removedTiles);
    }

    public boolean exchangeTiles(Rack rack, Tile[] tilesFromRack) {
        // you can't exchange letters if there are less than 7 tiles in the bag
        if (this.bag.size() < 7) return false;

        Tile[] tilesFromBag = removeTiles(tilesFromRack.length);
        // move new tiles from bag to rack
        if (!rack.addTilesToRack(tilesFromBag)) return false;

        // put given tiles back to the bag
        this.bag.addAll(Arrays.asList(tilesFromRack));
        return true;
    }

    public int getSize() {
        return this.bag.size();
    }

    public HashMap<Character, Integer> getRemainingLetterDistribution() {
        HashMap<Character, Integer> currDistribution = new HashMap<>();
        for (Tile tile: this.bag) {
            int currNumOccurrences = currDistribution.getOrDefault(tile.getLetter(), 0);
            currDistribution.put(tile.getLetter(), currNumOccurrences + 1);
        }

        return currDistribution;
    }

    public boolean isEmpty() {
        return this.bag.isEmpty();
    }

    public HashMap<Character, Integer> getLetterDistribution() {
        return this.letterDistribution;
    }

    public HashMap<Character, Integer> getLetterValues() {
        return this.letterValues;
    }
}
