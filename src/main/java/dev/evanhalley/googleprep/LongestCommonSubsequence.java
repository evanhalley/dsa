package dev.evanhalley.googleprep;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        new Solution().longestCommonSubsequence("xaxx", "a");
    }

    public static class Solution {
        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();
            int[][] dp = new int[m][n];

            for (int i = 0; i < m; i++) {

                for (int j = 0; j < n; j++) {

                    if (text1.charAt(i) == text2.charAt(j)) {
                        int carryover = i > 0 && j > 0 ? dp[i - 1][j - 1] : 0;
                        dp[i][j] = 1 + carryover;
                    } else {
                        int max = 0;

                        if (i > 0 && j > 0) {
                            max = Math.max(dp[i - 1][j], dp[i][j - 1]);
                        } else if (i == 0 && j > 0) {
                            max = dp[i][j - 1];
                        } else if (i > 0) {
                            max = dp[i - 1][j];
                        }
                        dp[i][j] = max;
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
