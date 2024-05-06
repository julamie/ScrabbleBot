package gameSetup;

import java.util.HashMap;

public enum Language {
    GERMAN (generateGermanLetterDistribution(), generateGermanLetterValues()),
    ENGLISH (generateEnglishLetterDistribution(), generateEnglishLetterValues());

    private final HashMap<Character, Integer> letterDistribution;
    private final HashMap<Character, Integer> letterValues;

    Language(HashMap<Character, Integer> letterDistribution, HashMap<Character, Integer> letterValues) {
        this.letterDistribution = letterDistribution;
        this.letterValues = letterValues;
    }

    private static HashMap<Character, Integer> generateGermanLetterDistribution() {
        HashMap<Character, Integer> distribution = new HashMap<>();

        // distribution based on the German Scrabble version
        distribution.put('E', 15);
        distribution.put('N', 9);
        distribution.put('S', 7);
        distribution.put('I', 6);
        distribution.put('R', 6);
        distribution.put('T', 6);
        distribution.put('U', 6);
        distribution.put('A', 5);
        distribution.put('D', 4);
        distribution.put('H', 4);
        distribution.put('G', 3);
        distribution.put('L', 3);
        distribution.put('O', 3);
        distribution.put('M', 4);
        distribution.put('B', 2);
        distribution.put('W', 1);
        distribution.put('Z', 1);
        distribution.put('C', 2);
        distribution.put('F', 2);
        distribution.put('K', 2);
        distribution.put('P', 1);
        distribution.put('Ä', 1);
        distribution.put('J', 1);
        distribution.put('Ü', 1);
        distribution.put('V', 1);
        distribution.put('Ö', 1);
        distribution.put('X', 1);
        distribution.put('Q', 1);
        distribution.put('Y', 1);
        distribution.put('?', 2);

        return distribution;
    }
    private static HashMap<Character, Integer> generateEnglishLetterDistribution() {
        HashMap<Character, Integer> distribution = new HashMap<>();

        // distribution based on the English Scrabble version
        distribution.put('E', 12);
        distribution.put('A', 9);
        distribution.put('I', 9);
        distribution.put('O', 8);
        distribution.put('N', 6);
        distribution.put('R', 6);
        distribution.put('T', 6);
        distribution.put('L', 4);
        distribution.put('S', 4);
        distribution.put('U', 4);
        distribution.put('D', 4);
        distribution.put('G', 3);
        distribution.put('B', 2);
        distribution.put('C', 2);
        distribution.put('M', 2);
        distribution.put('P', 2);
        distribution.put('F', 2);
        distribution.put('H', 2);
        distribution.put('V', 2);
        distribution.put('W', 2);
        distribution.put('Y', 2);
        distribution.put('K', 1);
        distribution.put('J', 1);
        distribution.put('X', 1);
        distribution.put('Q', 1);
        distribution.put('Z', 1);
        distribution.put('?', 2);

        return distribution;
    }

    private static HashMap<Character, Integer> generateGermanLetterValues() {
        HashMap<Character, Integer> letterValues = new HashMap<>();

        // letter values based on the German Scrabble version
        letterValues.put('N', 1);
        letterValues.put('S', 1);
        letterValues.put('I', 1);
        letterValues.put('E', 1);
        letterValues.put('R', 1);
        letterValues.put('T', 1);
        letterValues.put('U', 1);
        letterValues.put('A', 1);
        letterValues.put('D', 1);

        letterValues.put('H', 2);
        letterValues.put('G', 2);
        letterValues.put('L', 2);
        letterValues.put('O', 2);

        letterValues.put('M', 3);
        letterValues.put('B', 3);
        letterValues.put('W', 3);
        letterValues.put('Z', 3);

        letterValues.put('C', 4);
        letterValues.put('F', 4);
        letterValues.put('K', 4);
        letterValues.put('P', 4);

        letterValues.put('Ä', 6);
        letterValues.put('J', 6);
        letterValues.put('Ü', 6);
        letterValues.put('V', 6);

        letterValues.put('Ö', 8);
        letterValues.put('X', 8);

        letterValues.put('Q', 10);
        letterValues.put('Y', 10);

        letterValues.put('?', 0);

        return letterValues;
    }

    private static HashMap<Character, Integer> generateEnglishLetterValues() {
        HashMap<Character, Integer> letterValues = new HashMap<>();

        // letter values based on the English Scrabble version
        letterValues.put('E', 1);
        letterValues.put('A', 1);
        letterValues.put('I', 1);
        letterValues.put('O', 1);
        letterValues.put('N', 1);
        letterValues.put('R', 1);
        letterValues.put('T', 1);
        letterValues.put('L', 1);
        letterValues.put('S', 1);
        letterValues.put('U', 1);

        letterValues.put('D', 2);
        letterValues.put('G', 2);

        letterValues.put('B', 3);
        letterValues.put('C', 3);
        letterValues.put('M', 3);
        letterValues.put('P', 3);

        letterValues.put('F', 4);
        letterValues.put('H', 4);
        letterValues.put('V', 4);
        letterValues.put('W', 4);
        letterValues.put('Y', 4);

        letterValues.put('K', 5);

        letterValues.put('J', 8);
        letterValues.put('X', 8);

        letterValues.put('Q', 10);
        letterValues.put('Z', 10);

        letterValues.put('?', 0);

        return letterValues;
    }

    public HashMap<Character, Integer> getLetterDistribution() {
        return this.letterDistribution;
    }

    public HashMap<Character, Integer> getLetterValues() {
        return letterValues;
    }
}
