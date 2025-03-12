package org.jake.algorithm;

import java.util.HashMap;

public class Palindrom {
    public static boolean isPermutationOfPalindrom(String str) {
        HashMap<Character, Integer> countOfChar = new HashMap<Character, Integer>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ')
                continue;
            Integer count = countOfChar.get(c);
            if (count == null) {
                countOfChar.put(c, 1);
            } else {
                countOfChar.put(c, count + 1);
            }
        }

        int oddCount = 0;
        for (Integer count : countOfChar.values()) {
            if (count % 2 == 1) {
                oddCount++;
            }
        }

        return oddCount <= 1;
    }

    public static boolean isPalindrom1(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i))
                return false;
        }
        return true;
    }

    public static boolean isPalindrom2(String str) {
        String tmp = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            tmp += str.charAt(i);
        }
        return str.equals(tmp);
    }

    public static void main(String[] args) {
        String str = "rats live on no evil star";
        System.out.println(isPermutationOfPalindrom("aa bb cc ee bb"));
        System.out.println(isPalindrom1(str));
        System.out.println(isPalindrom2(str));
    }
}
