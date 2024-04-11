package IO;

import BoardStructure.Bag;
import BoardStructure.Tile;

import java.util.ArrayList;
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

    public static StringBuilder[] getBagOutputLines(Bag bag) {
        ArrayList<StringBuilder> lines = new ArrayList<>();

        HashMap<Character, Integer> startingDistribution = bag.getLetterDistribution();
        HashMap<Character, Integer> letterValues = bag.getLetterValues();
        HashMap<Character, Integer> bagDistribution = bag.getRemainingLetterDistribution();

        char[] tileLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ?".toCharArray();
        for (char letter : tileLetters) {
            int letterValue = letterValues.get(letter);
            int numRepetitions = bagDistribution.getOrDefault(letter, 0);

            // skip letters that aren't in the bag anymore
            if (numRepetitions == 0) continue;

            StringBuilder tileRow = getTileRowOfSameLetter(letter, letterValue, numRepetitions);
            lines.add(tileRow);
        }

        return lines.toArray(new StringBuilder[0]);
    }
}
