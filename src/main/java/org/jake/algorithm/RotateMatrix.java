package org.jake.algorithm;

/**
 * Original matrix:
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * 10 11 12
 *
 * Rotated matrix:
 * 10 7 4 1
 * 11 8 5 2
 * 12 9 6 3
 */
public class RotateMatrix {
    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] rotate(int[][] matrix) {
        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                rotatedMatrix[c][rotatedMatrix[0].length - r - 1] = matrix[r][c];
            }
        }

        return rotatedMatrix;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        System.out.println("Original matrix:");
        printMatrix(matrix);

        System.out.println();
        System.out.println("Rotated matrix:");
        printMatrix(rotate(matrix));
    }
}

