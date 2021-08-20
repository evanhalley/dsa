package dev.evanhalley.googleprep;

import java.util.ArrayList;
import java.util.List;

public class RotateMatrix {

    // moves, right, down, left, up
    private final static int[][] MOVES = {{ 0, 1 }, { 1, 0 }, {0, -1}, { -1, 0}};

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int swapsNeeded = n * n;

        int currentRow = 0;
        int currentCol = 2;

        for (int i = 0; i < swapsNeeded; i++) {
            calculateDestinations(currentRow, currentCol, n - 1, 0, n - 1);
        }

    }

    public int executeSwap(int[][] matrix, int row, int col) {
        return 0;
    }

    public List<Integer[]> calculateDestinations(int row, int col, int rightBottomBound, int leftTopBound, int numMoves) {
        List<Integer[]> destinations = new ArrayList<>(numMoves);
        int tempCol = col;
        int tempRow = row;
        int i = 0;

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
