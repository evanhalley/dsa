package dev.evanhalley.googleprep.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanConstruct {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int row = 0; row < (n + 1) / 2; row ++) {

            for (int col = 0; col < n / 2; col++) {
                int cell = n - 1;
                int temp = matrix[cell - col][row];
                matrix[cell - col][row] = matrix[cell - row][cell - col];
                matrix[cell - row][cell - col] = matrix[col][cell - row];
                matrix[col][cell - row] = matrix[row][col];
                matrix[row][col] = temp;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("abcdefabcdcddez", Arrays.asList("ab", "abc", "cd", "de", "f", "abcd"),
                new HashMap<>()));
    }

    public static boolean canConstruct(String target, List<String> wordBank, Map<String, Boolean> memo) {

        if (memo.containsKey(target)) {
            return memo.get(target);
        }

        if (target.isEmpty()) {
            return true;
        }

        for (String word : wordBank) {
            int index = target.indexOf(word);
            String remainder = null;

            if (index == 0) {
                remainder = target.substring(word.length());
            } else if (index > 0 && index == target.length() - word.length()) {
                remainder = target.substring(0, index);
            }

            if (remainder != null && canConstruct(remainder, wordBank, memo)) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }

}
