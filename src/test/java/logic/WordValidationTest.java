package logic;

import boardStructure.*;
import utils.TestObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordValidationTest {

    private Board testBoard;
    private Rack testRack;

    @BeforeEach
    void setUp() {
        this.testBoard = new TestObjects().getTestBoard();
        this.testRack = new TestObjects().getTestRack();
    }

    private WordValidation getCustomWordValidator(String input, Coordinates coordinates, Direction direction) {
        Bag bag = new Bag();
        Tile[] letters = bag.convertWordToTileArray(input);
        Word word = new Word(letters, coordinates, direction);

        return new WordValidation(this.testBoard, this.testRack, word);
    }

    private WordValidation getAxolotlValidator(Coordinates coords, Direction direction) {
        return getCustomWordValidator("AXOLOTL", coords, direction);
    }

    @Test
    void wordShouldBeInBounds() {
        WordValidation validationHorizontal = getAxolotlValidator(new Coordinates(7, 8), Direction.HORIZONTALLY);
        WordValidation validationVertical = getAxolotlValidator(new Coordinates(8, 7), Direction.VERTICALLY);

        assertTrue(validationHorizontal.doesWordStayInBounds());
        assertTrue(validationVertical.doesWordStayInBounds());
    }

    @Test
    void wordShouldHorizontallyBeOutOfBounds() {
        WordValidation validationHorizontal = getAxolotlValidator(new Coordinates(7, 9), Direction.HORIZONTALLY);

        assertFalse(validationHorizontal.doesWordStayInBounds());
    }

    @Test
    void wordShouldVerticallyBeOutOfBounds() {
        WordValidation validationVertical = getAxolotlValidator(new Coordinates(9, 7), Direction.VERTICALLY);

        assertFalse(validationVertical.doesWordStayInBounds());
    }

    @Test
    void wordShouldNotOverlap() {
        WordValidation validation = getAxolotlValidator(new Coordinates(4, 7), Direction.VERTICALLY);

        assertTrue(validation.doesWordNotOverlapOtherTiles());
    }

    @Test
    void wordShouldOverlap() {
        WordValidation validation = getAxolotlValidator(new Coordinates(9, 8), Direction.HORIZONTALLY);

        assertFalse(validation.doesWordNotOverlapOtherTiles());
    }

    @Test
    void wordShouldBeConnectedToWordToTheLeft() {
        WordValidation validation = getAxolotlValidator(new Coordinates(11, 2), Direction.HORIZONTALLY);

        assertTrue(validation.isWordConnectedToWord());
    }

    @Test
    void wordShouldBeConnectedToWordToTheRight() {
        WordValidation validation = getAxolotlValidator(new Coordinates(1, 13), Direction.HORIZONTALLY);

        assertTrue(validation.isWordConnectedToWord());
    }

    @Test
    void wordShouldBeConnectedToWordAbove() {
        WordValidation validation = getAxolotlValidator(new Coordinates(10, 7), Direction.HORIZONTALLY);

        assertTrue(validation.isWordConnectedToWord());
    }

    @Test
    void wordShouldBeConnectedToWordBelow() {
        WordValidation validation = getAxolotlValidator(new Coordinates(3, 4), Direction.HORIZONTALLY);

        assertTrue(validation.isWordConnectedToWord());
    }

    @Test
    void wordShouldBeDisconnected() {
        WordValidation validation = getAxolotlValidator(new Coordinates(0, 3), Direction.VERTICALLY);

        assertFalse(validation.isWordConnectedToWord());
    }

    @Test
    void wordCanBePlacedWithTilesFromRack() {
        WordValidation validation = getCustomWordValidator(
                "ÜBLER", new Coordinates(5, 7), Direction.VERTICALLY);

        assertTrue(validation.canWordBePlayedWithTilesOnRack());
    }

    @Test
    void wordCantBePlacedBecauseOfWrongOccupyingTile() {
        WordValidation validation = getCustomWordValidator(
                "ÜBLER", new Coordinates(4, 7), Direction.VERTICALLY);

        assertFalse(validation.canWordBePlayedWithTilesOnRack());
    }

    @Test
    void wordCanBePlacedWithTileOnRackButOnlyUsingABlank() {
        WordValidation validation = getCustomWordValidator(
                "ÜBLER", new Coordinates(5, 7), Direction.VERTICALLY);

        this.testRack.removeTileFromRack('B');
        assertTrue(validation.canWordBePlayedWithTilesOnRack());

        this.testRack.removeTileFromRack('?');
        assertFalse(validation.canWordBePlayedWithTilesOnRack());
    }

    @Test
    void wordCantBePlacedWithTilesOnRackBecauseOfMissingLetter() {
        WordValidation validation = getCustomWordValidator(
                "BLAU", new Coordinates(6, 7), Direction.VERTICALLY);

        this.testRack.removeTileFromRack('?');
        assertFalse(validation.canWordBePlayedWithTilesOnRack());
    }

    @Test
    void axolotlShouldBeAWordInTheDictionary() {
        WordValidation validation = getAxolotlValidator(new Coordinates(0, 0), Direction.VERTICALLY);

        assertTrue(validation.isWordInDictionary());
    }

    @Test
    void xnopytShouldNotBeAWordInTheDictionary() {
        // TODO: Uncomment when dictionary lookup is implemented

        //WordValidation validation = getCustomWordValidator(
        //                                "XNOPYT",
        //                                new Coordinates(0, 0),
        //                                Direction.HORIZONTALLY);
        //assertFalse(validation.isWordInDictionary());
    }

    @Test
    void firstWordInMiddleShouldBeInTheMiddleOfTheBoard() {
        this.testBoard = new Board();
        WordValidation validationHorizontal = getAxolotlValidator(new Coordinates(7,1), Direction.HORIZONTALLY);
        WordValidation validationVertical = getAxolotlValidator(new Coordinates(1,7), Direction.VERTICALLY);

        assertTrue(validationHorizontal.isWordCoveringTheMiddleSquare());
        assertTrue(validationVertical.isWordCoveringTheMiddleSquare());
    }

    @Test
    void firstWordInMiddleShouldNotBeInTheMiddleOfTheBoard() {
        this.testBoard = new Board();
        WordValidation validationHorizontal = getAxolotlValidator(new Coordinates(7,0), Direction.HORIZONTALLY);
        WordValidation validationVertical = getAxolotlValidator(new Coordinates(0,7), Direction.VERTICALLY);

        assertFalse(validationHorizontal.isWordCoveringTheMiddleSquare());
        assertFalse(validationVertical.isWordCoveringTheMiddleSquare());
    }
}