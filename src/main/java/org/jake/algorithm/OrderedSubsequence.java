package org.jake.algorithm;

import java.util.ArrayList;

public class OrderedSubsequence {
    public static void printOrderedSubsequence(int[] arr) {
        // Ordered subsequence
        //   - 첫 숫자 또는 이전 숫자 보다 큰 수: array에 숫자 추가
        //   - 이전 숫자 보다 작은 수: array.size()가 2보다 크면 정답 리스트에 추가. 아니면 array 초기화 후 현재 숫자 추가
        //   - 마지막 숫자: array.size()가 2보다 크면 정답 리스트에 추가
        //   - 이전 숫자 변수 필요
        //   - 2개 이상이 있을 수 있다: Ordered subsequence를 저장하는 ArrayList<ArrayList<Integer>> 필요
        // Not
        //   - 그 이외
        int previousNum = -1;
        ArrayList<ArrayList<Integer>> orderedSubsequences = new ArrayList<>();
        ArrayList<Integer> orderedSubsequence = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            Integer num = arr[i];
            if (i == 0 || previousNum < num) {
                orderedSubsequence.add(num);

                if (i == arr.length - 1) {
                    if (orderedSubsequence.size() >= 2) {
                        orderedSubsequences.add(orderedSubsequence);
                    }
                }
            } else {
                if (orderedSubsequence.size() >= 2) {
                    orderedSubsequences.add(orderedSubsequence);
                }
                orderedSubsequence = new ArrayList<>();
                orderedSubsequence.add(num);
            }

            previousNum = num;
        }

        for (ArrayList<Integer> subsequence : orderedSubsequences) {
            System.out.println(subsequence);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { -1, 12, 13, 11, -1, 9, 8, 2, 6, 30 };
        printOrderedSubsequence(arr);
    }
}
