package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Anagram {
    public static List<Integer> getAnagramIndices(String haystack, String needle) {
        if (needle == null || "".equals(needle) || haystack.length() < needle.length())
            return null;

        char[] arrH = haystack.toLowerCase().toCharArray();
        char[] arrNS = needle.toLowerCase().toCharArray();
        Arrays.sort(arrNS);
        List<Integer> indices = new ArrayList<Integer>();

        for (int i = 0; i <= arrH.length - arrNS.length; i++) {
            if (startsWithAnagram(arrH, i, arrNS)) {
                indices.add(i);
            }
        }
        return indices.isEmpty()? null: indices;
    }

    public static boolean startsWithAnagram(char @NotNull [] arrH, int offset, char @NotNull [] arrNS) {
        if (arrH.length - offset < arrNS.length)
            return false;
        char[] arrHS = new char[arrNS.length];
        for (int i = 0; i < arrHS.length; i++) {
            arrHS[i] = arrH[offset + i];
        }
        Arrays.sort(arrHS);
        for (int i = 0; i < arrNS.length; i++) {
            if (arrHS[i] != arrNS[i]) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> findAnagram(String haystack, String needle) {
        List<Integer> indices = new LinkedList<Integer>();

        char[] arrH = haystack.toCharArray();
        char[] arrN = needle.toCharArray();
        Arrays.sort(arrN);

        for (int i = 0; i <= arrH.length - arrN.length; i++) {
            char[] arrHSub = new char[arrN.length];
            System.arraycopy(arrH, i, arrHSub, 0, arrN.length);
            Arrays.sort(arrHSub);

            int j = 0;
            for (; j < arrN.length; j++) {
                if (arrHSub[j] != arrN[j]) {
                    break;
                }
            }
            if (j == arrN.length) {
                indices.add(i);
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        String haystack = "abcde";
        String needle = "ed";

        System.out.println(getAnagramIndices(haystack, needle));

        List<Integer> indices = findAnagram(haystack, needle);
        System.out.println(indices);
    }
}

