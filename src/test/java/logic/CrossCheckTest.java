package logic;

import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;
import gameSetup.Language;
import utils.TestObjects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CrossCheckTest {

    private final CrossCheck crossCheckHorizontal;
    private final CrossCheck crossCheckVertical;

    public CrossCheckTest() {
        this.crossCheckHorizontal = getCustonCrossCheck(
                new Coordinates(4, 9), Direction.VERTICALLY);
        this.crossCheckVertical = getCustonCrossCheck(
                new Coordinates(6, 7), Direction.HORIZONTALLY);
    }

    private CrossCheck getCustonCrossCheck(Coordinates coordinates, Direction direction) {
        Board board = new TestObjects().getTestBoard();
        // add a new tile for better cross-check testing
        board.setTileOnBoard(new Tile('S', 1), 6, 8);

        Tile[] letters = Tile.convertStringToTileArray("BEISPIEL", Language.GERMAN);
        Word word = new Word(letters, coordinates, direction);
        return new CrossCheck(board, word);
    }

    @Test
    void horizontalCrossCheckShouldFindTheLetters() {
        String crossWordExpected = "SIA";
        Tile tile = new Tile('I', 1);
        Coordinates coordinates = new Coordinates(6, 9);
        String crossWordActual = this.crossCheckHorizontal.getCrossWordString(tile, coordinates);

        assertEquals(crossWordExpected, crossWordActual);
    }

    @Test
    void verticalCrossCheckShouldFindTheLetters() {
        String crossWordExpected = "AIT";
        Tile tile = new Tile('I', 1);
        Coordinates coordinates = new Coordinates(6, 9);
        String crossWordActual = this.crossCheckVertical.getCrossWordString(tile, coordinates);

        assertEquals(crossWordExpected, crossWordActual);
    }

    @Test
    void horizontalCrossWordListShouldBeCorrect() {
        String[] crossWordListExpected = {
                "BQ", "SIA", "PE", "IN"
        };
        String[] crossWordListActual = this.crossCheckHorizontal.getCrossWordStringList();

        assertArrayEquals(crossWordListExpected, crossWordListActual);
    }

    @Test
    void horizontalCrossCheckShouldReturnTheCorrectCoordinates() {
        Coordinates coordinatesEmptyLeftExpected = new Coordinates(4, 9);
        Coordinates coordinatesFilledLeftExpected = new Coordinates(6, 8);

        // test coordinates of cross-word of the B and I of Beispiel
        Word[] words = this.crossCheckHorizontal.getCrossWordList();
        Coordinates coordinatesEmptyLeftActual = words[0].getCoordinates(0);
        Coordinates coordinatesFilledLeftActual = words[1].getCoordinates(0);

        assertEquals(coordinatesEmptyLeftExpected, coordinatesEmptyLeftActual);
        assertEquals(coordinatesFilledLeftExpected, coordinatesFilledLeftActual);
    }

    @Test
    void verticalCrossWordListShouldBeCorrect() {
        String[] crossWordListExpected = {
                "BL", "AIT", "FP", "ZI", "UE", "BEZUGL"
        };
        String[] crossWordListActual = this.crossCheckVertical.getCrossWordStringList();
        assertArrayEquals(crossWordListExpected, crossWordListActual);
    }

    @Test
    void verticalCrossCheckShouldFindTheCorrectCoordinates() {
        Coordinates coordinatesEmptyLeftExpected = new Coordinates(6, 7);
        Coordinates coordinatesFilledLeftExpected = new Coordinates(1, 14);

        // test coordinates of cross-word of the B and L of Beispiel
        Word[] words = this.crossCheckVertical.getCrossWordList();
        Coordinates coordinatesEmptyLeftActual = words[0].getCoordinates(0);
        Coordinates coordinatesFilledLeftActual = words[words.length - 1].getCoordinates(0);

        assertEquals(coordinatesEmptyLeftExpected, coordinatesEmptyLeftActual);
        assertEquals(coordinatesFilledLeftExpected, coordinatesFilledLeftActual);
    }
}