package org.jake.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class QueensAttack {
    public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
        // obstacles -> int[n][n] board
        // value == 1 -> obstacle
        // right: (r_q, c_q + i) while c_q + i < n and value != 1
        // left: (r_q, c_q - i) while c_q - i < n and value != 1
        // up: (r_q + i, c_q) while r_q + i < n and value != 1
        // down: (r_q - i, c_q) while r_q - i >= 0 and value != 1
        // right-up: (r_q + i, c_q + i) while r_q + i < n && c_q + i < n and value != 1
        // right-down: (r_q - i, c_q + i) while r_q - 1 < n && c_q + i < n and value != 1
        // left-up: (r_q + i, c_q - i) while r_q + i < n && c_q - i < n and value != 1
        // left-down: (r_q - i, c_q - i) while r_q - i < n && c_q - i < n and value != 1

        int count = 0;
        HashSet<String> obs = toHashSet(obstacles);

        r_q = r_q - 1;
        c_q = c_q - 1;

        // right: (r_q, c_q + i) while c_q + i < n and value != 1
        for (int i = 1; c_q + i < n && !isObstacle(r_q, c_q + i, obs); i++) {
            count++;
        }

        // left: (r_q, c_q - i) while c_q - i >= 0 and value != 1
        for (int i = 1; c_q - i >= 0 && !isObstacle(r_q, c_q - i, obs); i++) {
            count++;
        }

        // up: (r_q + i, c_q) while r_q + i < n and value != 1
        for (int i = 1; r_q + i < n && !isObstacle(r_q + i, c_q, obs); i++) {
            count++;
        }

        // down: (r_q - i, c_q) while r_q - i >= 0 and value != 1
        for (int i = 1; r_q - i >= 0 && !isObstacle(r_q - i, c_q, obs); i++) {
            count++;
        }

        // right-up: (r_q + i, c_q + i) while r_q + i < n && c_q + i < n and value != 1
        for (int i = 1; r_q + i < n && c_q + i < n && !isObstacle(r_q + i, c_q + i, obs); i++) {
            count++;
        }

        // right-down: (r_q - i, c_q + i) while r_q - 1 >= 0 && c_q + i < n and value != 1
        for (int i = 1; r_q - i >= 0 && c_q + i < n && !isObstacle(r_q - i, c_q + i, obs); i++) {
            count++;
        }

        // left-up: (r_q + i, c_q - i) while r_q + i < n && c_q - i >= 0 and value != 1
        for (int i = 1; r_q + i < n && c_q - i >= 0 && !isObstacle(r_q + i, c_q - i, obs); i++) {
            count++;
        }

        // left-down: (r_q - i, c_q - i) while r_q - i >= 0 && c_q - i >= 0 and value != 1
        for (int i = 1; r_q - i >= 0 && c_q - i >= 0 && !isObstacle(r_q - i, c_q - i, obs); i++) {
            count++;
        }

        return count;
    }

    private static HashSet<String> toHashSet(List<List<Integer>> obstacles) {
        HashSet<String> set = new HashSet<>();
        for (List<Integer> obstacle : obstacles) {
            String key = (obstacle.get(0) - 1) + "," + (obstacle.get(1) - 1);
            set.add(key);
        }
        return set;
    }

    private static boolean isObstacle(int r, int c, HashSet<String> obstacles) {
        return obstacles.contains(r + "," + c);
    }

    private static List<Integer> convertToList(int[] obstacle) {
        List<Integer> list = new ArrayList<>();
        list.add(obstacle[0]);
        list.add(obstacle[1]);
        return list;
    }

    public static void main(String[] args) {
        List<List<Integer>> obstacles = new ArrayList<>();
        obstacles.add(convertToList(new int[] { 20001, 20002 }));
        obstacles.add(convertToList(new int[] { 20001, 20004 }));
        obstacles.add(convertToList(new int[] { 20000, 20003 }));
        obstacles.add(convertToList(new int[] { 20002, 20003 }));
        obstacles.add(convertToList(new int[] { 20000, 20004 }));
        obstacles.add(convertToList(new int[] { 20000, 20002 }));
        obstacles.add(convertToList(new int[] { 20002, 20004 }));
        obstacles.add(convertToList(new int[] { 20002, 20002 }));
        obstacles.add(convertToList(new int[] { 564, 323 }));

        int count = queensAttack(88587, 9, 20001, 20003, obstacles);
        System.out.println(count);
    }
}
