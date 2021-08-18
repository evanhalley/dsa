package dev.evanhalley.googleprep.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MinimumIsland {

    private static final char LAND = '1';

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','1','1'},
                {'0','0','0','0','0'},
                {'1','0','0','1','0'},
                {'1','0','0','1','0'}};
        System.out.println("Smallest Island Size: " + getMinimumIsland(grid));
    }

    public static int getMinimumIsland(char[][] grid) {
        Set<Point> visitedPoints = new HashSet<>();
        int smallestIslandSize = Integer.MIN_VALUE;

        for (int row = 0; row < grid.length; row++) {

            for (int col = 0; col < grid[0].length; col++) {

                if (grid[row][col] == (LAND)) {
                    Point point = new Point(row, col);
                    int islandSize = dfs(grid, point, visitedPoints);

                    if (islandSize > 0) {
                        smallestIslandSize = Math.max(smallestIslandSize, islandSize);
                    }
                }
            }
        }
        return smallestIslandSize;
    }

    public static int dfs(char[][] grid, Point startingPoint, Set<Point> visitedPoints) {
        Deque<Point> stack = new ArrayDeque<>();
        stack.push(startingPoint);
        int islandSize = 0;

        while (!stack.isEmpty()) {
            Point point = stack.pop();

            if (visitedPoints.contains(point)) {
                continue;
            }
            islandSize++;
            visitedPoints.add(point);

            for (Point neighbor : getNeighbors(grid, point)) {
                stack.push(neighbor);
            }
        }
        return islandSize;
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
}