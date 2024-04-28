package logic;

import boardStructure.Coordinates;
import boardStructure.Tile;

public class Word {

    private final Tile[] word;
    private final Coordinates firstLetterCoordinates;
    private final Direction direction;

    public Word(Tile[] word, Coordinates firstLetterCoordinates, Direction direction) {
        this.word = word;
        this.firstLetterCoordinates = firstLetterCoordinates;
        this.direction = direction;
    }

    public char getLetter(int position) {
        return word[position].getLetter();
    }

    public int getLetterValue(int position) {
        return word[position].getValue();
    }

    public Coordinates getCoordinates(int position) {
        return switch (this.direction) {
            case HORIZONTALLY -> new Coordinates(this.firstLetterCoordinates.row(), this.firstLetterCoordinates.col() + position);
            case VERTICALLY -> new Coordinates(this.firstLetterCoordinates.row() + position, this.firstLetterCoordinates.col());
        };
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getLength() {
        return this.word.length;
    }

    public Character getLetterAtCoordinates(Coordinates coordinates) {
        int firstRow = this.firstLetterCoordinates.row();
        int firstCol = this.firstLetterCoordinates.col();

        // one coordinate in a word should be the same
        if (firstRow != coordinates.row() && firstCol != coordinates.col()) return null;

        int position;
        if (firstRow != coordinates.row()) {
            position = coordinates.row() - this.firstLetterCoordinates.row();
        } else {
            position = coordinates.col() - this.firstLetterCoordinates.col();
        }

        // position is too far from word or before it
        if (position < 0 || position >= this.word.length) return null;

        return this.word[position].getLetter();
    }

    @Override
    public String toString() {
        char[] letters = new char[this.word.length];

        for (int i = 0; i < letters.length; i++) {
            letters[i] = this.word[i].getLetter();
        }

        return new String(letters);
    }
}
