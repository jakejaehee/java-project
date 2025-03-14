package org.jake.algorithm;

import java.util.HashSet;

/**
 * 0이 있는 row와 col을 모두 0으로 치환한다.
 *
 * Original matrix:
 *     1    2    3
 *     4    5    0
 *     7    0    0
 *    10   11   12
 *
 * Zerofied matrix:
 *     1    0    0
 *     0    0    0
 *     0    0    0
 *    10    0    0
 */
public class ZerofyMatrix {
    public static int[][] zerofy(int[][] matrix) {
        HashSet<Integer> zRow = new HashSet<>();
        HashSet<Integer> zCol = new HashSet<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 0) {
                    zRow.add(row);
                    zCol.add(col);
                }
            }
        }

        int[][] ret = new int[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (zRow.contains(row) || zCol.contains(col)) {
                    ret[row][col] = 0;
                } else {
                    ret[row][col] = matrix[row][col];
                }
            }
        }
        return ret;
    }

    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.format("%5d", matrix[row][col]);
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 0}, {7, 0, 0}, {10, 11, 12}};
        printMatrix(matrix);
        System.out.println();
        printMatrix(zerofy(matrix));
    }
}
