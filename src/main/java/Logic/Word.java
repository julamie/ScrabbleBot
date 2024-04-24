package Logic;

import BoardStructure.Coordinates;
import BoardStructure.Tile;

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

    public int getLength() {
        return this.word.length;
    }
}
