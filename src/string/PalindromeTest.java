package string;

public class PalindromeTest {

    public static void main(String[] args) {
        String value = "abba";
        boolean isPalindrome = isPalindrome(value);
        System.out.println("String: " + value + ", isPalindrome: " + isPalindrome);
    }

    private static boolean isPalindrome(String value) {
        if (null == value || value.length() < 3) {
            System.err.println("String cannot be null or less than 3 char");
            return false;
        }

        final int length = value.length();
        final boolean isEvenLength = (length % 2) == 0;
        final int middleIndex = length / 2;

        int i = middleIndex -1, j;

        if (isEvenLength) {
            j = middleIndex;
        } else {
            j = middleIndex + 1;
        }

        boolean isPalindrome = true;

        while (i >= 0 && j <= length) {
            if (value.charAt(i) != value.charAt(j)) {
                isPalindrome = false;
                break;
            }

            i--;
            j++;
        }

        return isPalindrome;
    }


}
