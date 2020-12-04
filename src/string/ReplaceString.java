package string;

public class ReplaceString {

    public static void main(String[] args) {
        String originalString = "one two one two three one four";
        System.out.println("Original String = " + originalString);

        String replacedString = replaceLastOccurrenceOfWord(originalString, "one", "five");
        System.out.println("Replaced String = " + replacedString);
    }

    private static String replaceLastOccurrenceOfWord(String originalString, String wordToReplace, String replaceWord) {
        final int length = wordToReplace.length();
        final int beginIndex = originalString.lastIndexOf(wordToReplace);

        return new StringBuilder(originalString)
                .replace(beginIndex, beginIndex + length , replaceWord)
                .toString();
    }
}
