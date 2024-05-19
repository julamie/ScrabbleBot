package playerBehaviour;

import boardStructure.Bag;
import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;
import gameSetup.Language;
import logic.Direction;
import logic.Word;
import utils.TestObjects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Bag bag;
    private Player player;

    @BeforeEach
    void setUp() {
        this.bag = new TestObjects().getTestBag();
        Board board = new TestObjects().getTestBoard();
        this.player = new HumanPlayer(board, bag);
    }

    @Test
    void placingInvalidWordShouldReturnFalse() {
        Tile[] letters = Tile.convertStringToTileArray("XNOPYT", Language.GERMAN);
        Word word = new Word(letters, new Coordinates(0, 0), Direction.HORIZONTALLY);

        assertFalse(player.setWordOnBoard(word));
    }

    @Test
    void placingLegalWordShouldReturnTrue() {
        this.player.fillRack();
        Tile[] letters = Tile.convertStringToTileArray("SCHAFE", Language.GERMAN);
        Word word = new Word(letters, new Coordinates(2, 9), Direction.HORIZONTALLY);

        assertTrue(player.setWordOnBoard(word));
    }

    @Test
    void fillingUpTilesFromEmptyBagShouldNotGiveAnythingBack() {
        this.player.fillRack();
        Tile[] letters = Tile.convertStringToTileArray("SCHAFE", Language.GERMAN);
        Word word = new Word(letters, new Coordinates(2, 9), Direction.HORIZONTALLY);
        player.setWordOnBoard(word);

        assertEquals(2, player.getRack().getSize());
    }

    @Test
    void exchangingTilesOnAlmostEmptyBagShouldReturnFalse() {
        // let the bag have only five tiles inside
        // new almost empty bag, because a new player immediately takes the remaining tiles
        this.player.getBag().drawTilesFromBag(this.bag.getSize() - 5);

        Tile[] testTileToExchange = {new Tile('F', 3)};
        assertFalse(player.exchangeTiles(testTileToExchange));
    }

}