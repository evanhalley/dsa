package dev.evanhalley.googleprep.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NumIslands {

    private static final char LAND = '1';

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','1','0'},
                {'0','0','0','0','0'},
                {'1','0','0','1','0'},
                {'1','0','0','1','0'}};
        System.out.println("Number of Islands: " + getNumberOfIslands(grid));
    }

    public static int getNumberOfIslands(char[][] grid) {
        Set<Point> visitedPoints = new HashSet<>();
        int numberOfIslands = 0;

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == (LAND)) {
                    Point point = new Point(row, col);
                    numberOfIslands += dfs(grid, point, visitedPoints);
                }
            }
        }
        return numberOfIslands;
    }

    public static int dfs(char[][] grid, Point startingPoint, Set<Point> visitedPoints) {
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(startingPoint);
        int numberOfIslands = 0;

        while (!stack.isEmpty()) {
            Point point = stack.pop();

            if (visitedPoints.contains(point)) {
                continue;
            }
            numberOfIslands = 1;
            visitedPoints.add(point);

            for (Point neighbor : getNeighbors(grid, point)) {
                stack.push(neighbor);
            }
        }
        return numberOfIslands;
    }

    public static List<Point> getNeighbors(char[][] grid, Point point) {
        List<Point> neighbors = new LinkedList<>();

        if (point.row - 1 >= 0 && grid[point.row - 1][point.col] == LAND) {
            neighbors.add(new Point(point.row - 1, point.col));
        }
        if (point.col - 1 >= 0 && grid[point.row][point.col - 1] == LAND) {
            neighbors.add(new Point(point.row, point.col - 1));
        }
        if (point.row + 1 < grid.length && grid[point.row + 1][point.col] == LAND) {
            neighbors.add(new Point(point.row + 1, point.col));
        }
        if (point.col + 1 < grid[0].length && grid[point.row][point.col + 1] == LAND) {
            neighbors.add(new Point(point.row, point.col + 1));
        }
        return neighbors;
    }

    public static class Point {
        final int row;
        final int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (row != point.row) return false;
            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    class Solution {

        public int longestLine(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int ones = 0;

            // horizontal
            for (int i = 0; i < matrix.length; i++) {
                int count = 0;

                for (int j = 0; j < matrix[0].length; j++) {

                    if (matrix[i][j] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }
            // vertical
            for (int i = 0; i < matrix[0].length; i++) {
                int count = 0;

                for (int j = 0; j < matrix.length; j++) {

                    if (matrix[j][i] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }

            // upper diagonal
            for (int i = 0; i < matrix[0].length || i < matrix.length; i++) {
                int count = 0;

                for (int x = 0, y = i; x < matrix.length && y < matrix[0].length; x++, y++) {

                    if (matrix[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }

            // lower diagonal
            for (int i = 0; i < matrix[0].length || i < matrix.length; i++) {
                int count = 0;

                for (int x = i, y = 0; x < matrix.length && y < matrix[0].length; x++, y++) {

                    if (matrix[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }

            // upper anti-diagonal
            for (int i = 0; i < matrix[0].length || i < matrix.length; i++) {
                int count = 0;

                for (int x = 0, y = matrix[0].length - i - 1; x < matrix.length && y >= 0; x++, y--) {

                    if (matrix[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }
            // lower anti-diagonal
            for (int i = 0; i < matrix[0].length || i < matrix.length; i++) {
                int count = 0;

                for (int x = i, y = matrix[0].length - 1; x < matrix.length && y >= 0; x++, y--) {

                    if (matrix[x][y] == 1) {
                        count++;
                        ones = Math.max(ones, count);
                    } else {
                        count = 0;
                    }
                }
            }
            return ones;
        }
    }

    class SolutionDp {

        final int HORIZONTAL = 0;
        final int VERTICAL = 1;
        final int DIAGONAL = 2;
        final int ANTIDIAGONAL = 3;

        public int longestLine(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int ones = 0;
            int[][][] dp = new int[matrix.length][matrix[0].length][4];

            for (int i = 0; i < matrix.length; i++) {

                for (int j = 0; j < matrix[0].length; j++) {

                    if (matrix[i][j] == 1) {
                        // horizontal dp -
                        dp[i][j][HORIZONTAL] = j > 0 ? dp[i][j - 1][HORIZONTAL] + 1 : 1;
                        // vertical dp |
                        dp[i][j][VERTICAL] = i > 0 ? dp[i - 1][j][VERTICAL] + 1 : 1;
                        // diagonal dp /
                        dp[i][j][DIAGONAL] = (i > 0 && j > 0) ? dp[i - 1][j - 1][DIAGONAL] + 1 : 1;
                        // antidiagonal dp \
                        dp[i][j][ANTIDIAGONAL] = (i > 0 && j < matrix[0].length - 1) ? dp[i - 1][j + 1][ANTIDIAGONAL] + 1 : 1;

                        // track the max
                        ones = Math.max(ones, Math.max(Math.max(dp[i][j][0], dp[i][j][1]),
                                Math.max(dp[i][j][2], dp[i][j][3])));
                    }
                }
            }
            return ones;
        }
    }
}