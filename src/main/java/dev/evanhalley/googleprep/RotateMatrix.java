package dev.evanhalley.googleprep;

import java.util.ArrayList;
import java.util.List;

public class RotateMatrix {

    // moves, right, down, left, up
    private static final int[][] MOVES = {{ 0, 1 }, { 1, 0 }, {0, -1}, { -1, 0}};

    public static void main(String[] args) {
        int[][] matrix = {  {1,2}, {3,4} };
        new RotateMatrix().rotate(matrix);
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        rotateMatrix(matrix, n - 1, 0, n - 1);
    }

    public void rotateMatrix(int[][] matrix, int rightBottomBound, int leftTopBound, int spacesToMove) {
        int currentRow = 0;
        int currentCol = 0;

        if (spacesToMove > 0) {
            List<Integer[]> destinations = calculateDestinations(currentRow, currentCol, rightBottomBound,
                    leftTopBound, spacesToMove);
            executeSwaps(matrix, destinations);
            rotateMatrix(matrix, rightBottomBound - 1, leftTopBound + 1,
                    spacesToMove - 2);
        }
    }

    public void executeSwaps(int[][] matrix, List<Integer[]> destinations) {
        int temp = matrix[destinations.get(3)[0]][destinations.get(3)[1]];
        matrix[destinations.get(1)[0]][destinations.get(1)[1]] = matrix[destinations.get(0)[0]][destinations.get(0)[1]];
        matrix[destinations.get(2)[0]][destinations.get(2)[1]] = matrix[destinations.get(1)[0]][destinations.get(1)[1]];
        matrix[destinations.get(3)[0]][destinations.get(3)[1]] = matrix[destinations.get(2)[0]][destinations.get(2)[1]];
        matrix[destinations.get(0)[0]][destinations.get(0)[1]] = temp;
    }

    public List<Integer[]> calculateDestinations(int row, int col, int rightBottomBound, int leftTopBound, int numMoves) {
        List<Integer[]> destinations = new ArrayList<>(numMoves);
        int tempCol = col;
        int tempRow = row;

        for (int[] move : MOVES) {
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
                    destinations.add(new Integer[] { tempRow, tempCol });
                    moveSaved = true;
                }
            }
        }
        return destinations;
    }

}
