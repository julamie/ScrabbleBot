package logic;

import boardStructure.Board;
import boardStructure.Coordinates;
import boardStructure.Tile;
import gameSetup.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestObjects;

import static org.junit.jupiter.api.Assertions.*;

class ScoringTest {

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = new TestObjects().getTestBoard();
    }

    @Test
    void regnenAloneShouldGive16Points() {
        Tile[] regen = Tile.convertStringToTileArray("REGNEN", Language.GERMAN);
        Word word = new Word(regen, new Coordinates(11, 0), Direction.HORIZONTALLY);

        assertEquals(16, new Scoring(this.board, word).calculateWordScore());
    }

    @Test
    void axolotlAsCrossWordShouldGive18Points() {
        Tile[] axolotl = Tile.convertStringToTileArray("AXOLOTL", Language.GERMAN);

        assertEquals(18, Scoring.calculateOnlyLetterValues(axolotl));
    }

    @Test
    void seifeShouldGive20Points() {
        Tile[] seife = Tile.convertStringToTileArray("SEIFE", Language.GERMAN);
        Word word = new Word(seife, new Coordinates(8, 11), Direction.VERTICALLY);

        assertEquals(20, new Scoring(this.board, word).calculateScore());
    }
}