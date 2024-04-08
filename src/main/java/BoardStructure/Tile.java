package BoardStructure;

public class Tile {

    private char letter;
    private int value;

    public Tile(char letter, int value) {
        if (!checkTile(letter, value)) throw new IllegalArgumentException("Illegal arguments used for Tiles.");

        if (!isBlank()) this.letter = Character.toUpperCase(letter);
        this.value = value;
    }

    private boolean checkTile(char letter, int value) {
        if (letter == '?') return value == 0;

        return Character.isLetter(letter) && value > 0;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public boolean isBlank() {
        return (this.getLetter() == '?');
    }
}
