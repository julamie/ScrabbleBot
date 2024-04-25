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
    void lastLetterShouldBeO() {
        assertEquals('O', testWordHorizontally.getLetter(3));
        assertEquals('O', testWordVertically.getLetter(3));
    }

    @Test
    void lastLetterValueShouldBeTwo() {
        assertEquals(2, testWordHorizontally.getLetterValue(3));
        assertEquals(2, testWordVertically.getLetterValue(3));
    }

    @Test
    void getCoordinatesShouldReturnCorrectCoordinateForLastLetter() {
        assertEquals(new Coordinates(4, 8), testWordHorizontally.getCoordinates(3));
        assertEquals(new Coordinates(7, 5), testWordVertically.getCoordinates(3));
    }

    @Test
    void getLetterAtCoordinatesShouldFindFirstPosition() {
        assertEquals('A', testWordHorizontally.getLetterAtCoordinates(new Coordinates(4, 5)));
    }

    @Test
    void getLetterAtCoordinatesShouldReturnLetterAtPosition() {
        assertEquals('O', testWordHorizontally.getLetterAtCoordinates(new Coordinates(4, 8)));
        assertEquals('O', testWordVertically.getLetterAtCoordinates(new Coordinates(7, 5)));
    }

    @Test
    void getLetterAtCoordinatesShouldReturnNullForWrongCoordinates() {
        assertNull(testWordHorizontally.getLetterAtCoordinates(new Coordinates(0,0)));
        assertNull(testWordVertically.getLetterAtCoordinates(new Coordinates(0,0)));

        assertNull(testWordHorizontally.getLetterAtCoordinates(new Coordinates(4,9)));
        assertNull(testWordVertically.getLetterAtCoordinates(new Coordinates(8,5)));

        assertNull(testWordHorizontally.getLetterAtCoordinates(new Coordinates(4,4)));
        assertNull(testWordVertically.getLetterAtCoordinates(new Coordinates(3,5)));
    }

    @Test
    void toStringShouldReturnCorrectWord() {
        assertEquals("AUTO", testWordHorizontally.toString());
        assertEquals("AUTO", testWordVertically.toString());
    }
}