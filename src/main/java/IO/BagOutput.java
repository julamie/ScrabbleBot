package IO;

import BoardStructure.Bag;
import java.util.HashMap;


public class BagOutput {

    private static final String RESET = "\u001B[0;1m"; // no background, bold letters
    public static final String CREAM_BG = "\033[48;2;238;221;187;30m"; // black letters

    private static StringBuilder getUpperLineOfRow(char letter, int numRepetitions) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numRepetitions; i++) {
            output.append(CREAM_BG).append("  ").append(letter).append("  ");
            output.append(RESET).append(" ");
        }
        return output;
    }

    private static StringBuilder getLowerLineOfRow(int value, int numRepetitions) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numRepetitions; i++) {
            output.append(CREAM_BG);

            if (value == 0) {
                output.append("     ");
            } else if (value >= 10) {
                output.append("   ");
                output.append(value);
            } else {
                output.append("    ");
                output.append(value);
            }
            output.append(RESET).append(" ");
        }

        return output;
    }

    private static StringBuilder getTileRowOfSameLetter(char letter, int value, int numRepetitions) {
        StringBuilder output = new StringBuilder();
        StringBuilder upperLine = getUpperLineOfRow(letter, numRepetitions);
        StringBuilder lowerLine = getLowerLineOfRow(value, numRepetitions);
        output.append(upperLine).append('\n').append(lowerLine);

        return output;
    }

    public static void printBagContents(Bag bag) {
        StringBuilder output = new StringBuilder();

        HashMap<Character, Integer> startingDistribution = bag.getLetterDistribution();
        HashMap<Character, Integer> letterValues = bag.getLetterValues();
        HashMap<Character, Integer> bagDistribution = bag.getRemainingLetterDistribution();

        // display remaining normal letters
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            int letterValue = letterValues.get(letter);
            int numRepetitions = bagDistribution.getOrDefault(letter, 0);

            // skip letters that don't exist anymore
            if (numRepetitions == 0) continue;

            output.append(getTileRowOfSameLetter(letter, letterValue, numRepetitions));
            output.append('\n');
        }

        char[] specialChars = {'Ä', 'Ö', 'Ü', '?'};
        for (char letter : specialChars) {
            int letterValue = letterValues.get(letter);
            int numRepetitions = bagDistribution.getOrDefault(letter, 0);

            if (numRepetitions == 0) continue;

            output.append(getTileRowOfSameLetter(letter, letterValue, numRepetitions));
            output.append('\n');
        }

        System.out.println(output);
    }

}
