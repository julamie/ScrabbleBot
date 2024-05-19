package gameSetup;

import input.DictionaryInput;
import org.quinto.dawg.CompressedDAWGSet;

public class Dictionary {
    // default dictionary language is German, can be changed via setter
    private static CompressedDAWGSet dictionary = new DictionaryInput(Language.GERMAN).getDictionary();

    public static void setDictionaryLanguage(Language language) {
        dictionary = new DictionaryInput(language).getDictionary();
    }

    public static boolean isWord(String word) {
        return dictionary.contains(word.toUpperCase());
    }
}
