package org.jake.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Maze:
 * 0 0 0 0
 * 1 0 1 0
 * 1 0 1 1
 * 1 0 1 -1
 * 1 0 0 0
 * 1 0 0 1
 *
 * Path:
 * 3 3 9 9
 * 1 3 1 9
 * 1 3 1 1
 * 1 3 1 -1
 * 1 3 3 3
 * 1 0 0 1
 */
public class Maze {
    class Coordinate {
        public int col;
        public int row;

        public Coordinate(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }

    public int stepsToExit = 0;

    private List<int[]> rows = new ArrayList<int[]>();

    public void addRow(int[] row) {
        this.rows.add(row);
    }

    private void checkBoundary(int col, int row) throws Exception {
        if (row >= rows.size() || col >= rows.get(row).length)
            throw new Exception("Out of boundary");
    }

    /**
     * Can pass if 0, otherwise can't.
     * Mark the coordinate that you passed as 9.
     *
     * @param col
     * @param row
     * @param stack
     * @return
     */
    public boolean moveTo(int col, int row, Stack<Coordinate> stack) throws Exception {
        checkBoundary(col, row);

        stack.push(new Coordinate(col, row));

        int value = rows.get(row)[col];

        // 1: wall
        if (value == 1) {
            stack.pop();
            return false;
        }

        // 9: already passed
        if (value == 9) {
            stack.pop();
            return false;
        }

        // -1: exit
        // 0: path
        if (value == -1) {
            stack.pop();
            return true;
        }

        rows.get(row)[col] = 9;

        if (col < rows.get(row).length - 1) {
            if (moveTo(col + 1, row, stack))
                return true;
        }
        if (col > 1) {
            if (moveTo(col - 1, row, stack))
                return true;
        }
        if (row < rows.size() - 1) {
            if (moveTo(col, row + 1, stack))
                return true;
        }
        if (row > 1) {
            if (moveTo(col, row - 1, stack))
                return true;
        }

        stack.pop();
        return false;
    }

    /**
     * mark the right path to the exit as 3
     *
     * @param stack
     */
    public void markPath(Stack<Coordinate> stack) {
        for (Coordinate coordinate : stack) {
            stepsToExit++;
            if (rows.get(coordinate.row)[coordinate.col] == -1)
                return;
            rows.get(coordinate.row)[coordinate.col] = 3;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : rows) {
            for (int c : row) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        /*
        0: path
        1: wall
        -1: exit
         */
        Maze maze = new Maze();
        maze.addRow(new int[] { 0, 0, 0, 0 });
        maze.addRow(new int[] { 1, 0, 1, 0 });
        maze.addRow(new int[] { 1, 0, 1, 1 });
        maze.addRow(new int[] { 1, 0, 1, -1 });
        maze.addRow(new int[] { 1, 0, 0, 0 });
        maze.addRow(new int[] { 1, 0, 0, 1 });

        System.out.println(maze);

        Stack<Coordinate> stack = new Stack<Coordinate>();
        try {
            maze.moveTo(0, 0, stack);
            maze.markPath(stack);
            System.out.println(maze);
            System.out.println("Steps to the exit: " + maze.stepsToExit);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
