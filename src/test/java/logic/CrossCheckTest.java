package logic;

import boardStructure.Bag;
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
        String crossWordActual = this.crossCheckHorizontal.getCrossWord(new Coordinates(6, 9));

        assertEquals(crossWordExpected, crossWordActual);
    }

    @Test
    void verticalCrossCheckShouldFindTheLetters() {
        String crossWordExpected = "AIT";
        String crossWordActual = this.crossCheckVertical.getCrossWord(new Coordinates(6, 9));

        assertEquals(crossWordExpected, crossWordActual);
    }

    @Test
    void horizontalCrossWordListShouldBeCorrect() {
        String[] crossWordListExpected = {
                "BQ", "SIA", "PE", "IN"
        };
        String[] crossWordListActual = this.crossCheckHorizontal.getCrossWordList();

        assertArrayEquals(crossWordListExpected, crossWordListActual);
    }

    @Test
    void verticalCrossWordListShouldBeCorrect() {
        String[] crossWordListExpected = {
                "BL", "AIT", "FP", "ZI", "UE", "BEZUGL"
        };
        String[] crossWordListActual = this.crossCheckVertical.getCrossWordList();
        assertArrayEquals(crossWordListExpected, crossWordListActual);
    }
}