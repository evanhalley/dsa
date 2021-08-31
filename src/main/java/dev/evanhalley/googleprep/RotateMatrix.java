package dev.evanhalley.googleprep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RotateMatrix {

    // moves, right, down, left, up
    private static final int[][] MOVES = {{ 0, 1 }, { 1, 0 }, {0, -1}, { -1, 0}};

    public static void main(String[] args) {
        int[][] matrix = {  {1} };
        new RotateMatrix().rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        rotateMatrix(matrix, n - 1, 0, n - 1);
    }

    public void rotateMatrix(int[][] matrix, int rightBottomBound, int leftTopBound, int spacesToMove) {
        int currentCol = leftTopBound;

        if (spacesToMove > 0) {
            for (int i = 0; i < spacesToMove; i++) {
                List<Point> destinations = calculateDestinations(leftTopBound, currentCol++, rightBottomBound,
                        leftTopBound, spacesToMove);
                executeSwaps(matrix, destinations);
            }
            rotateMatrix(matrix, rightBottomBound - 1, leftTopBound + 1,
                    spacesToMove - 2);
        }
    }

    public void executeSwaps(int[][] matrix, List<Point> points) {
        Map<Point, Integer> tempMap = new HashMap<>();

        for (Point point : points) {
            tempMap.put(point, matrix[point.row][point.col]);
        }

        matrix[points.get(0).row][points.get(0).col] = tempMap.get(points.get(3));
        matrix[points.get(3).row][points.get(3).col] = tempMap.get(points.get(2));
        matrix[points.get(2).row][points.get(2).col] = tempMap.get(points.get(1));
        matrix[points.get(1).row][points.get(1).col] = tempMap.get(points.get(0));
    }

    public List<Point> calculateDestinations(int row, int col, int rightBottomBound, int leftTopBound, int numMoves) {
        List<Point> destinations = new ArrayList<>(numMoves);
        int tempCol = col;
        int tempRow = row;

        for (int i = 0; i < MOVES.length; i++) {
            int[] move = MOVES[i];
            boolean moveSaved = false;

            while (!moveSaved) {
                tempRow += numMoves * move[0];
                tempCol += numMoves * move[1];

                // past the right bound
                if (tempCol > rightBottomBound) {
                    int diff = tempCol - rightBottomBound;
                    tempCol -= diff;
                    tempRow += diff;
                }

                // past the bottom bound
                else if (tempRow > rightBottomBound) {
                    int diff = tempRow - rightBottomBound;
                    tempCol -= diff;
                    tempRow -= diff;
                }

                // past the left bound
                else if (tempCol < leftTopBound) {
                    int diff = Math.abs(tempCol - leftTopBound);
                    tempCol += diff;
                    tempRow -= diff;
                }

                // past the top bound
                else if (tempRow < leftTopBound) {
                    int diff = Math.abs(tempRow - leftTopBound);
                    tempCol += diff;
                    tempRow += diff;
                }

                if (tempCol <= rightBottomBound && tempCol >= leftTopBound &&
                        tempRow <= rightBottomBound && tempRow >= leftTopBound) {
                    destinations.add(i, new Point(i, tempRow, tempCol));
                    moveSaved = true;
                }
            }
        }
        return destinations;
    }

    public static class Point {
        final int id;
        final int row;
        final int col;

        public Point(int id ,int row, int col) {
            this.id = id;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "id=" + id +
                    ", row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

}
