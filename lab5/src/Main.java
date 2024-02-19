import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter text:");
        String inputText = scanner.nextLine();
        if( inputText.equals("text")){
            inputText = "This is a test text containing multiple lines. Sentences may span across several lines. Ensure the program accurately handles scenarios such as an empty text, sentences without words starting with specified conditions, and demonstrates the correct sorting of words starting with vowels in alphabetical order based on the first consonant of each word. You can input this text directly into the program.\n";
        }
        sortAndPrintWords(inputText);
    }

    public static void sortAndPrintWords(String text) {
        if (text.trim().isEmpty()) {
            System.out.println("The text is empty.");
            return;
        }

        String regex = "\\b[aeiouAEIOU][a-zA-Z]*\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        String[] words = matcher.results()
                .map(MatchResult::group)
                .toArray(String[]::new);

        if (words.length == 0) {
            System.out.println("The text does not contain words starting with vowels.");
            return;
        }

        Arrays.sort(words, Comparator.comparingInt(word -> word.length() == 1 ? word.toLowerCase().charAt(0)
                : word.substring(1, 2).toLowerCase().charAt(0)));

        System.out.println("Sorted words:");
        Arrays.stream(words).forEach(System.out::println);
    }
}
