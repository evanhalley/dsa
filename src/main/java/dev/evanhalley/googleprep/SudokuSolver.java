package dev.evanhalley.googleprep;

import java.util.Arrays;

public class SudokuSolver {

    public static void main(String[] args) {
        int[][] board = {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0} };
        solve(board);
    }

    public static boolean solve(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean result = backtrack(board, m, n, 0, 0);
        System.out.println(Arrays.deepToString(board));
        return result;
    }

    public static boolean backtrack(int[][] board, int m, int n, int row, int col ) {

        if (row == m - 1 && col == n) {
            return true;
        }

        if (col == n) {
            row++;
            col = 0;
        }

        if (board[row][col] != 0) {
            return backtrack(board, m, n, row, col + 1);
        }

        for (int i = 1; i <= 9; i++) {

            if (isSafe(board, row, col, i)) {
                board[row][col] = i;

                if (backtrack(board, m, n, row, col + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    public static boolean isSafe(int[][] board, int currentRow, int currentCol, int val) {

        // iterate the current col (by row)
        for (int row = 0; row < board.length; row++) {
            if (board[row][currentCol] == val) {
                return false;
            }
        }

        // iterate the current row (by col)
        for (int col = 0; col < board[0].length; col++) {
            if (board[currentRow][col] == val) {
                return false;
            }
        }

        int boxRow = currentRow / 3;
        int boxCol = currentCol / 3;

        for (int row = boxRow * 3; row < boxRow * 3 + 3; row++) {

            for (int col = boxCol * 3; col < boxCol * 3 + 3; col++) {

                if (board[row][col] == val) {
                    return false;
                }
            }
        }
        return true;
    }
}
