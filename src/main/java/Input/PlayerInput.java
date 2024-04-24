package Input;

import BoardStructure.Bag;
import BoardStructure.Board;
import BoardStructure.Tile;
import BoardStructure.Coordinates;
import Logic.Direction;
import Logic.Word;

import java.util.Scanner;

public class PlayerInput {

    // TODO: Find a way to get rid of these
    private Board board;
    private Bag bag;

    public PlayerInput(Board board, Bag bag) {
        this.board = board;
        this.bag = bag;
    }

    private void printTurnTypePrompt() {
        String outputLine = "Please type in your type of move.\n";
        outputLine += "P = play a word\nE = exchange letters\nX = pass turn";
        System.out.println(outputLine);
    }

    private Character parseAndCheckReturnTypeInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (input.length() != 1) {
            System.err.println("Too long input. Please try again.");
            return null;
        }

        return input.charAt(0);
    }

    public TurnType getTurnType() {
        while (true) {
            printTurnTypePrompt();

            Character chosenInput = parseAndCheckReturnTypeInput();
            if (chosenInput == null) continue;

            switch (chosenInput) {
                case 'P', 'p': return TurnType.PLAY_WORD;
                case 'E', 'e': return TurnType.EXCHANGE_LETTERS;
                case 'X', 'x': return TurnType.PASS_TURN;
                default: System.err.println("Invalid type of move input. Please try again.");
            }
        }
    }

    private void printCoordinatePrompt() {
        String outputLine = "Please type in the coordinate for the first tile e.g. D4 or B12";
        System.out.println(outputLine);
    }

    private Integer processAndCheckColumnInput(int boardSize, String parsedLine) {
        char columnCandidate = parsedLine.charAt(0); // first char corresponds to column
        columnCandidate = Character.toUpperCase(columnCandidate);

        if (columnCandidate < 'A' || columnCandidate >= 'A' + boardSize) {
            System.err.println("Column out of bounds. Please try again.");
            return null;
        }

        return columnCandidate - 'A'; // convert letter to column value i.e. A=0, B=1 etc.
    }

    private Integer processAndCheckRowInput(int boardSize, String parsedLine) {
        String rowCandidate = parsedLine.substring(1); // second char corresponds to row

        int row;
        try {
            // row numbering begins with 1, whereas internally they start with 0
            row = Integer.parseInt(rowCandidate) - 1;
        } catch (NumberFormatException e) {
            System.err.println("No row number input. Please try again.");
            return null;
        }

        if (row < 0 || row >= boardSize) {
            System.err.println("Row number out of bounds. Please try again.");
            return null;
        }

        return row;
    }

    public Coordinates getCoordinates(int boardSize) {
        while (true) {
            printCoordinatePrompt();

            // parse input
            Scanner scanner = new Scanner(System.in);
            String parsedLine = scanner.nextLine().trim();

            Integer column = processAndCheckColumnInput(boardSize, parsedLine);
            if (column == null) continue;

            Integer row = processAndCheckRowInput(boardSize, parsedLine);
            if (row == null) continue;

            return new Coordinates(row, column);
        }
    }

    private void printDirectionPrompt() {
        String outputLine = "Please type in which direction you want to play your word.\n";
        outputLine += "H = horizontally\nV = vertically";
        System.out.println(outputLine);
    }

    private Character parseAndCheckDirectionInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (input.length() != 1) {
            System.err.println("Too long input. Please try again.");
            return null;
        }

        return input.charAt(0);
    }

    public Direction getDirection() {
        while (true) {
            printDirectionPrompt();

            Character chosenInput = parseAndCheckDirectionInput();
            if (chosenInput == null) continue;

            switch (chosenInput) {
                case 'H', 'h': return Direction.HORIZONTALLY;
                case 'V', 'v': return Direction.VERTICALLY;
                default: System.err.println("Invalid direction input. Please try again.");
            }
        }
    }

    private void printWordInputPrompt() {
        String outputLine = "Please type in your word.";
        System.out.println(outputLine);
    }

    private String parseAndCheckWordInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        if (input.isBlank()) {
            System.err.println("No input found. Please try again.");
            return null;
        }

        // check if word is continuous and has only letters
        if (!input.chars().allMatch(Character::isLetter)) {
            System.err.println("Word should consist out of letters without a space. Please try again.");
            return null;
        }

        return input.toUpperCase();
    }

    public String getWordInput() {
        while (true) {
            printWordInputPrompt();

            String input = parseAndCheckWordInput();
            if (input == null) continue;

            return input;
        }
    }

    public Word getWord() {
        String wordInput = getWordInput();
        Coordinates coordinates = getCoordinates(this.board.getSize());
        Direction direction = getDirection();
        Tile[] wordTiles = this.bag.convertWordToTileArray(wordInput);

        return new Word(wordTiles, coordinates, direction);
    }
}
