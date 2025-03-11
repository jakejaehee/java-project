package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static void main(String[] args) {
        System.out.println(getAnagramIndices("abcde", "ed"));
    }
}

