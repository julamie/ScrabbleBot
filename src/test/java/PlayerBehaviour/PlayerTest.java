package PlayerBehaviour;

import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Coordinates;
import BoardStructure.Tile;
import Logic.Direction;
import Logic.Word;
import Utils.TestObjects;
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
        Tile[] letters = this.bag.convertWordToTileArray("XNOPYT");
        Word word = new Word(letters, new Coordinates(0, 0), Direction.HORIZONTALLY);

        assertFalse(player.setWordOnBoard(word));
    }

    @Test
    void placingLegalWordShouldReturnTrue() {
        Tile[] letters = this.bag.convertWordToTileArray("SCHAFE");
        Word word = new Word(letters, new Coordinates(2, 9), Direction.HORIZONTALLY);

        assertTrue(player.setWordOnBoard(word));
    }

    @Test
    void fillingUpTilesFromEmptyBagShouldNotGiveAnythingBack() {
        Tile[] letters = this.bag.convertWordToTileArray("SCHAFE");
        Word word = new Word(letters, new Coordinates(2, 9), Direction.HORIZONTALLY);
        player.setWordOnBoard(word);

        assertEquals(2, player.getRack().getSize());
    }

    @Test
    void exchangingTilesOnAlmostEmptyBagShouldReturnFalse() {
        // let the bag have only five tiles inside;
        // new almost empty bag, because a new player immediately takes the remaining tiles
        this.bag = new TestObjects().getTestBag();
        this.bag.drawTilesFromBag(this.bag.getSize() - 5);

        Tile[] testTileToExchange = {new Tile('F', 3)};
        assertFalse(player.exchangeTiles(testTileToExchange));
    }

}