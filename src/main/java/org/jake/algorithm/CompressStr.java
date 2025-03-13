package org.jake.algorithm;

public class CompressStr {
    public static String compress(String str) {
        char[] arr = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        Character prevCh = null;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];
            if (prevCh == null) {
                prevCh = ch;
                count = 1;
                sb.append(ch);
            } else if (prevCh != ch) {
                if (count > 1) {
                    sb.append(count);
                }
                prevCh = ch;
                count = 1;
                sb.append(ch);
            } else {
                count++;
                if (i == arr.length - 1) {
                    sb.append(count);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(compress("aabbcccdeee"));
        System.out.println(compress("abcdee"));
        System.out.println(compress("aaabbbbccde"));
    }
}

