package IO;

import BoardStructure.Bag;
import BoardStructure.Tile;

import java.util.HashMap;


public class BagOutput {
    private static StringBuilder getTileRowOfSameLetter(char letter, int value, int numRepetitions) {
        StringBuilder output = new StringBuilder();
        StringBuilder upperLine = new StringBuilder();
        StringBuilder lowerLine = new StringBuilder();

        Tile dummyTile = new Tile(letter, value);
        StringBuilder[] tileLines = TileOutput.getTileOutputLines(dummyTile);

        // repeat the same letter to respective lines
        for (int i = 0; i < numRepetitions; i++) {
            upperLine.append(tileLines[0]);
            lowerLine.append(tileLines[1]);
        }

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
