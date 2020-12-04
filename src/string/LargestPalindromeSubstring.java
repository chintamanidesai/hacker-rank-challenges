package string;

/**
 * find out largest palindrome substring
 */
public class LargestPalindromeSubstring {

    private static int LARGEST_PALINDROME_STRING_START_POSITION;
    private static int LARGEST_PALINDROME_STRING_TOTAL_LENGTH;

    public static void main(String[] args) {
        final String value = "abcdcbaxyzyxpqrsttsrqpfg";
        //final String value = "xyy";
        final String largestPalindromeSubstring = findLargestPalindromeSubstring(value);
        System.out.println("Largest Palindrome Substring: " + largestPalindromeSubstring);
    }

    private static String findLargestPalindromeSubstring(String value) {

        final int totalLength = value.length();

        for (int i = 1; i < totalLength - 1; i++) {
            checkIfLargestPalindrome(value, i, i);
            checkIfLargestPalindrome(value, i , i + 1);
        }

        return value.substring(LARGEST_PALINDROME_STRING_START_POSITION, LARGEST_PALINDROME_STRING_START_POSITION + LARGEST_PALINDROME_STRING_TOTAL_LENGTH);
    }

    private static void checkIfLargestPalindrome(String value, int beginIndex, int beginIndex2) {
        while (beginIndex >=0 && beginIndex2 <= value.length() && value.charAt(beginIndex)==value.charAt(beginIndex2)) {
            beginIndex--;
            beginIndex2++;
        }

        beginIndex++; beginIndex2--;
        final int currentLargestLength = (beginIndex2 - beginIndex) + 1;

        if (currentLargestLength > LARGEST_PALINDROME_STRING_TOTAL_LENGTH ) {
            LARGEST_PALINDROME_STRING_TOTAL_LENGTH = currentLargestLength;
            LARGEST_PALINDROME_STRING_START_POSITION = beginIndex;
        }
    }
}
