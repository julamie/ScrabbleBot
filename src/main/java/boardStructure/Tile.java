package boardStructure;

import gameSetup.Language;

import java.util.HashMap;

public class Tile {

    private char letter;
    private final int value;
    private final boolean isBlank;

    public Tile(char letter, int value) {
        this.isBlank = letter == '?';
        if (!checkTile(letter, value)) throw new IllegalArgumentException("Illegal arguments used for Tiles.");

        this.letter = Character.toUpperCase(letter);
        this.value = value;
    }

    private boolean checkTile(char letter, int value) {
        if (this.isBlank) return value == 0;

        return Character.isLetter(letter) && value >= 0;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isBlank() {
        return this.isBlank;
    }

    public void setLetter(char letter) {
        if (this.letter != '?') throw new IllegalArgumentException("Can't change a tile that already has a letter on it");

        this.letter = letter;
    }

    public static Tile[] convertStringToTileArray(String wordInput, Language language) {
        HashMap<Character, Integer> letterValues = language.getLetterValues();
        Tile[] tiles = new Tile[wordInput.length()];

        for (int i = 0; i < wordInput.length(); i++) {
            char letter = wordInput.toUpperCase().charAt(i);
            Integer value = letterValues.get(letter);
            tiles[i] = new Tile(letter, value);
        }

        return tiles;
    }
}
