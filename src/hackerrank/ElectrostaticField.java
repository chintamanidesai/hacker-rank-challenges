package hackerrank;

import java.util.stream.IntStream;

public class ElectrostaticField {

    public static void main(String[] args) {

        int [] input1 = {4, 3, 5};
        int electrostaticField = findMaximum(input1, "PNP", 3);
        System.out.println("Total Charge: " + electrostaticField);
    }

    public static int findMaximum(int [] input1, String input2, int input3) {

       final char[] chargeArray = input2.toCharArray();

        final int totalCharge = IntStream.range(0, input1.length)
                .map(i -> input1[i] * (chargeArray[i] == 'P' ? 1 : - 1))
                .reduce(0, Integer::sum);

        final int electrostaticField = totalCharge * 100;

        return electrostaticField;
    }

    // Java 7
    /*public static int findMaximum(int [] input1, String input2, int input3) {

        final char[] chargeArray = input2.toCharArray();

        int totalCharge = 0;

        for (int i = 0; i < input1.length; i ++) {
            totalCharge =  totalCharge + (input1[i] * (chargeArray[i] == 'P' ? 1 : - 1));
        }

        return totalCharge * 100;
    }*/


}
