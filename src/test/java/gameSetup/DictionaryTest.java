package gameSetup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @AfterEach
    void cleanUp() {
        Dictionary.setDictionaryLanguage(Language.GERMAN); // set langauge to default
    }

    @Test
    void beispielShouldBeInGermanDictionaryOnly() {
        assertTrue(Dictionary.isWord("Beispiel"));
        Dictionary.setDictionaryLanguage(Language.ENGLISH);
        assertFalse(Dictionary.isWord("Beispiel"));
    }

    @Test
    void exampleShouldBeInEnglishDictionaryOnly() {
        assertFalse(Dictionary.isWord("example"));
        Dictionary.setDictionaryLanguage(Language.ENGLISH);
        assertTrue(Dictionary.isWord("example"));
    }

    @Test
    void axolotlShouldBeInBothDictionaries() {
        assertTrue(Dictionary.isWord("Axolotl"));
        Dictionary.setDictionaryLanguage(Language.ENGLISH);
        assertTrue(Dictionary.isWord("axolotl"));
    }

    @Test
    void xnopytShouldNotBeInEitherDictionary() {
        assertFalse(Dictionary.isWord("Xnopyt"));
        Dictionary.setDictionaryLanguage(Language.ENGLISH);
        assertFalse(Dictionary.isWord("xnopyt"));
    }
}