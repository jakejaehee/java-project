package org.jake.algorithm;

import java.text.DecimalFormat;
import java.util.List;

public class DecimalWithPlacesAfterIt {
    public static void plusMinus(int n, List<Integer> arr) {
        int countOfPositive = 0;
        int countOfNegative = 0;
        int countOfZero = 0;

        for (int i = 0; i < n && i < arr.size(); i++) {
            if (arr.get(i) > 0) {
                countOfPositive++;
            } else if (arr.get(i) < 0) {
                countOfNegative++;
            } else {
                countOfZero++;
            }
        }

        DecimalFormat format = new DecimalFormat("0.000000");

        System.out.println(format.format((double) countOfPositive / n));
        System.out.println(format.format((double) countOfNegative / n));
        System.out.println(format.format((double) countOfZero / n));
    }

    public static void main(String[] args) {
        plusMinus(6, List.of(-4, 3, -9, 0, 4, 1));
    }
}
