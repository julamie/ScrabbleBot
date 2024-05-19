package input;

import boardStructure.Coordinates;
import logic.Direction;
import logic.TurnType;
import output.Output;
import playerBehaviour.Player;

import java.util.Scanner;

public class PlayerInput {
    private final Player player;
    private String wordInput;

    public PlayerInput(Player player) {
        this.player = player;
    }

    public TurnType determineTurnType() {
        printPlayerInformation();
        printTurnTypePrompt();

        while (true) {
            String chosenInput = parseTurnTypeInput();

            if (isInputAValidWord(chosenInput)) return TurnType.PLAY_WORD;

            if (!isInputASpecialMove(chosenInput)) continue;
            switch (chosenInput.charAt(0)) {
                case 'E': return TurnType.EXCHANGE_LETTERS;
                case 'P': return TurnType.PASS_TURN;
                case 'S': showTilesInBag(); break;
                default: System.err.println("Invalid input given. Please try again.");
            }
        }
    }

    private void printPlayerInformation() {
        System.out.println("Current player: " + this.player.getName());
        System.out.println("Your current score: " + player.getScore());
        Output.printRack(player.getRack());
    }

    private void printTurnTypePrompt() {
        String outputLine = "Please type in your word.\nAlternatively you can do the following:\n";
        outputLine += "E = exchange letters\nP = pass turn\nS = show remaining tiles in bag";
        System.out.println(outputLine);
    }

    private String parseTurnTypeInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        return input.trim().toUpperCase();
    }

    private boolean isInputAValidWord(String chosenInput) {
        if (chosenInput.length() <= 1) return false;

        // check if word is continuous and has only letters
        if (!chosenInput.chars().allMatch(Character::isLetter)) {
            System.err.println("Word should consist out of letters without a space. Please try again.");
            return false;
        }

        this.wordInput = chosenInput;
        return true;
    }

    private static boolean isInputASpecialMove(String chosenInput) {
        if (chosenInput.isBlank()) {
            System.err.println("No input given. Please try again.");
            return false;
        }
        return chosenInput.length() == 1;
    }

    private void showTilesInBag() {
        Output.printBag(player.getBag());
        printTurnTypePrompt();
    }

    public String getWordToPlay() {
        return this.wordInput;
    }

    public Coordinates getCoordinates() {
        printCoordinatePrompt();

        int boardSize = this.player.getBoard().getSize();
        while (true) {
            // parse input
            Scanner scanner = new Scanner(System.in);
            String parsedLine = scanner.nextLine().trim();
            if (parsedLine.isEmpty()) continue;

            Integer column = processAndCheckColumnInput(boardSize, parsedLine);
            if (column == null) continue;

            Integer row = processAndCheckRowInput(boardSize, parsedLine);
            if (row == null) continue;

            return new Coordinates(row, column);
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

    private void printLetterExchangePrompt() {
        String outputLine = "Please type in the letters of the tiles you want to change like a word.\n";
        outputLine += "e.g. for exchanging F, H and A, type FHA\n";
        outputLine += "Type '?' if you want to exchange a blank tile";
        System.out.println(outputLine);
    }

    private boolean areAllLettersInInputValid(String input) {
        if (input.isBlank()) return false;

        for (char letter: input.toCharArray()) {
            if (!Character.isLetter(letter) && letter != '?') return false;
        }

        return true;
    }

    public char[] getLettersToExchange() {
        printLetterExchangePrompt();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toUpperCase();

        while (!areAllLettersInInputValid(input)) {
            System.err.println("Please only input letters or '?' for blanks.");
            input = scanner.nextLine().trim().toUpperCase();
        }

        return input.toCharArray();
    }
}
