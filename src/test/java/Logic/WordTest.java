package Logic;

import BoardStructure.Coordinates;
import BoardStructure.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    private Word testWordHorizontally, testWordVertically;

    @BeforeEach
    void createTestWords() {
        Tile[] letters = {
                new Tile('A', 1),
                new Tile('U', 1),
                new Tile('T', 1),
                new Tile('O', 2)
        };
        Coordinates coordinates = new Coordinates(4, 5);
        this.testWordHorizontally = new Word(letters, coordinates, Direction.HORIZONTALLY);
        this.testWordVertically = new Word(letters, coordinates, Direction.VERTICALLY);
    }

    @Test
    void getLetter() {
        assertEquals('O', testWordHorizontally.getLetter(3));
        assertEquals('O', testWordVertically.getLetter(3));
    }

    @Test
    void getLetterValue() {
        assertEquals(2, testWordHorizontally.getLetterValue(3));
        assertEquals(2, testWordVertically.getLetterValue(3));
    }

    @Test
    void getCoordinate() {
        assertEquals(new Coordinates(4, 8), testWordHorizontally.getCoordinates(3));
        assertEquals(new Coordinates(7, 5), testWordVertically.getCoordinates(3));
    }
}