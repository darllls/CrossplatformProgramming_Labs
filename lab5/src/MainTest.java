import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testEmptyText() {
        String emptyText = "";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(emptyText));
    }

    @Test
    public void testTextWithoutVowelWords() {
        String textWithoutVowels = "This is a test without vowel words.";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(textWithoutVowels));
    }

    @Test
    public void testTextWithVowelWords() {
        String textWithVowels = "Apple banana orange Elephant";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(textWithVowels));
    }

    @Test
    public void testTextWithSingleCharacterWord() {
        String singleCharacterWord = "A";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(singleCharacterWord));
    }

    @Test
    public void testTextWithSingleCharacterNonVowelWord() {
        String singleCharacterNonVowelWord = "Z";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(singleCharacterNonVowelWord));
    }

    @Test
    public void testMixedText() {
        String mixedText = "This is a test with Vowel Words like Apple, banana, and orange.";
        assertDoesNotThrow(() -> Main.sortAndPrintWords(mixedText));
    }
}
