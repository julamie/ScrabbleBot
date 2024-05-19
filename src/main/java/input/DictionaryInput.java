package input;

import gameSetup.Language;
import org.quinto.dawg.CompressedDAWGSet;
import org.quinto.dawg.ModifiableDAWGSet;

import java.io.*;
import java.util.ArrayList;

public class DictionaryInput {

    private final String wordListPath;
    private final String dictionaryPath;

    public DictionaryInput(Language language) {
        this.wordListPath = switch (language) {
            // found in: https://github.com/enz/german-wordlist/blob/master/words
            case GERMAN -> "src/main/resources/dictionaries/wordListGerman.txt";
            // found in: https://github.com/jmlewis/valett/blob/master/scrabble/sowpods.txt
            case ENGLISH -> "src/main/resources/dictionaries/wordListEnglish.txt";
        };
        this.dictionaryPath = switch (language) {
            case GERMAN -> "src/main/resources/dictionaries/germanDictionary.ser";
            case ENGLISH -> "src/main/resources/dictionaries/englishDictionary.ser";
        };
    }

    public CompressedDAWGSet getDictionary() {
        if (!new File(this.dictionaryPath).exists()) storeWordListOnDisk(getWords());

        return loadDictionaryFromDisk();
    }

    private ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        BufferedReader reader = getFileReader();

        String word = getNextWord(reader);
        while (word != null) {
            words.add(word);
            word = getNextWord(reader);
        }

        return words;
    }

    private BufferedReader getFileReader() {
        try {
            return new BufferedReader(new FileReader(this.wordListPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String getNextWord(BufferedReader reader) {
        String word;

        try {
            word = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (word == null) return null;
        return word.toUpperCase();
    }

    private void storeWordListOnDisk(ArrayList<String> words) {
        CompressedDAWGSet compressedDictionary = new ModifiableDAWGSet(words).compress();

        // store dictionary on disk
        try {
            ObjectOutputStream dictionaryFileStream = new ObjectOutputStream(new FileOutputStream(this.dictionaryPath));
            dictionaryFileStream.writeObject(compressedDictionary);
            dictionaryFileStream.flush();
            dictionaryFileStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CompressedDAWGSet loadDictionaryFromDisk() {
        CompressedDAWGSet dictionary;

        // load stored dictionary file from disk
        try {
            ObjectInputStream dictionaryFileStream = new ObjectInputStream(new FileInputStream(this.dictionaryPath));
            dictionary = (CompressedDAWGSet) dictionaryFileStream.readObject();
            dictionaryFileStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dictionary;
    }
}
