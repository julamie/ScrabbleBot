package input;

import boardStructure.Bag;
import boardStructure.Board;
import boardStructure.Coordinates;
import gameSetup.Language;
import logic.Direction;
import logic.TurnType;
import playerBehaviour.HumanPlayer;
import playerBehaviour.Player;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class PlayerInputTest {

    private final Player player = new HumanPlayer(new Board(), new Bag(Language.GERMAN));
    private final PlayerInput input = new PlayerInput(player);
    private final InputStream systemInBackup = System.in; // backup System.in to restore it later;
    private final PrintStream systemOutBackup = System.out;
    private final PrintStream systemErrBackup = System.err;
    private final ByteArrayOutputStream capturedOutputStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream capturedErrStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(capturedOutputStream));
        System.setErr(new PrintStream(capturedErrStream));
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemInBackup);
        System.setOut(systemOutBackup);
        System.setErr(systemErrBackup);
    }

    void mockInputToSystemIn(String mockInput) {
        ByteArrayInputStream in = new ByteArrayInputStream(mockInput.getBytes());
        System.setIn(in);
    }

    @Test
    void typingAWordShouldReturnPlayWordAsTurnType() {
        mockInputToSystemIn("Axolotl ");
        TurnType chosenTurnType = this.input.determineTurnType();

        assertEquals(TurnType.PLAY_WORD, chosenTurnType);
    }

    @Test
    void typingEShouldReturnExchangeLettersAsTurnType() {
        mockInputToSystemIn("E");

        TurnType chosenTurnType = this.input.determineTurnType();
        assertEquals(TurnType.EXCHANGE_LETTERS, chosenTurnType);
    }

    @Test
    void typingPShouldReturnPassTurnAsTurnType() {
        mockInputToSystemIn("P");

        TurnType chosenTurnType = this.input.determineTurnType();
        assertEquals(TurnType.PASS_TURN, chosenTurnType);
    }

    @Test
    void typingSShouldPrintTheBagContents() {
        mockInputToSystemIn("S");

        // can't really check if the bag is really printed out, so test if you need to input something after it
        assertThrows(NoSuchElementException.class, this.input::determineTurnType);
    }

    @Test
    void typingBlankInputShouldPrintPromptAgain() {
        mockInputToSystemIn("        ");

        // no line found, means the input prompt loops again
        assertThrows(NoSuchElementException.class, this.input::determineTurnType);
    }

    @Test
    void typingUnknownLetterShouldPrintPromptAgain() {
        mockInputToSystemIn("W");

        // no line found, means the input prompt loops again
        assertThrows(NoSuchElementException.class, this.input::determineTurnType);
    }

    @Test
    void typingCorrectCoordinatesShouldReturnThoseCoordinatesConverted() {
        mockInputToSystemIn("F12");

        Coordinates coordinatesExpected = new Coordinates(11, 5);
        Coordinates coordinatesActual = this.input.getCoordinates();

        assertEquals(coordinatesExpected.row(), coordinatesActual.row());
        assertEquals(coordinatesExpected.col(), coordinatesActual.col());
    }

    @Test
    void typingLetterOutOfBoundsAsColumnShouldLoopAgain() {
        mockInputToSystemIn("P12");

        assertThrows(NoSuchElementException.class, this.input::getCoordinates);
    }

    @Test
    void typingRowNumberOutOfBoundsShouldLoopAgain() {
        mockInputToSystemIn("O16");

        assertThrows(NoSuchElementException.class, this.input::getCoordinates);
    }

    @Test
    void typingNoNumberAsRowShouldLoopAgain() {
        mockInputToSystemIn("Axolotl");

        assertThrows(NoSuchElementException.class, this.input::getCoordinates);
    }

    @Test
    void typingHShouldReturnHorizontalDirection() {
        mockInputToSystemIn("h");

        assertEquals(Direction.HORIZONTALLY, this.input.getDirection());
    }

    @Test
    void typingVShouldReturnHorizontalDirection() {
        mockInputToSystemIn("v");

        assertEquals(Direction.VERTICALLY, this.input.getDirection());
    }

    @Test
    void typingUnknownCharacterShouldLoopAgain() {
        mockInputToSystemIn("U");

        assertThrows(NoSuchElementException.class, this.input::getDirection);
    }

    @Test
    void typingTooLongInputShouldLoopAgain() {
        mockInputToSystemIn("too long input");

        assertThrows(NoSuchElementException.class, this.input::getDirection);
    }

    @Test
    void typingNoWordShouldLoopAgain() {
        mockInputToSystemIn("       ");

        assertThrows(NoSuchElementException.class, this.input::getDirection);
    }

    @Test
    void typingSpaceShouldLoopAgain() {
        mockInputToSystemIn("Spaced word");

        assertThrows(NoSuchElementException.class, this.input::determineTurnType);
    }

    @Test
    void typingValidStringOfLettersShouldReturnThatAsACharArray() {
        mockInputToSystemIn("AH?E");

        assertArrayEquals(new char[]{'A', 'H', '?', 'E'}, this.input.getLettersToExchange());
    }

    @Test
    void typingBlankWordForLettersToExchangeShouldLoopAgain() {
        mockInputToSystemIn("       ");

        assertThrows(NoSuchElementException.class, this.input::getLettersToExchange);
    }

    @Test
    void typingNonLetterForLettersToExchangeShouldLoopAgain() {
        mockInputToSystemIn("fd2r");

        assertThrows(NoSuchElementException.class, this.input::getLettersToExchange);
    }
}