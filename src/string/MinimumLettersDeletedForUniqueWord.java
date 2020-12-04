package string;

import java.util.*;

/**
 * Given a string S consisting of N lowercase letters, return the minimum number of letters that must be deleted
 * to obtained a word in which every letter occurs a unique number of times.
 *
 * We only care about occurrences of letters that appear at least once in result.
 */
public class MinimumLettersDeletedForUniqueWord {

    public static void main(String[] args) {

        String s1 = "aaaabbbb";
        System.out.println("String s1 = " + s1 + ", minimumLettersToDelete = " + minimumLettersToDelete(s1));

        String s2 = "ccaaffddecee";
        System.out.println("String s2 = " + s2 + ", minimumLettersToDelete = " + minimumLettersToDelete(s2));

        String s3 = "eeee";
        System.out.println("String s3 = " + s3 + ", minimumLettersToDelete = " + minimumLettersToDelete(s3));

        String s4 = "example";
        System.out.println("String s4 = " + s4 + ", minimumLettersToDelete = " + minimumLettersToDelete(s4));


    }

    private static int minimumLettersToDelete(String s) {
        final char[] charArray = s.toCharArray();
        final Map<Character, Integer> charOccurrence = new HashMap<>();

        for (int i = 0; i < charArray.length; i++) {
            charOccurrence.merge(charArray[i], 1, Integer::sum);
        }

        //System.out.println("charOccurrence = " + charOccurrence);

        final List<Integer> uniqueLetterLengthList = new ArrayList<>(charOccurrence.size());
        final Set<Character> uniqueLetters = charOccurrence.keySet();
        int minimumLettersToDelete = 0;

        for (char uniqueLetter : uniqueLetters) {
            Integer uniqueLetterLength = charOccurrence.get(uniqueLetter);

            while (uniqueLetterLengthList.contains(uniqueLetterLength)) {
                uniqueLetterLength--;
                minimumLettersToDelete++;

                if (uniqueLetterLength == 0)
                    break;
            }

            uniqueLetterLengthList.add(uniqueLetterLength);
        }

        return minimumLettersToDelete;
    }

}
