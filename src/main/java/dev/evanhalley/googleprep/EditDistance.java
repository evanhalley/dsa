package dev.evanhalley.googleprep;

public class EditDistance {

    public static void main(String[] args) {
        System.out.println(editDistance("horse", "hors"));
    }

    public static int editDistance(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {

            for (int j = 0; j <= n; j++) {

                // If first string is empty, only option is to insert all characters of second string
                if (i == 0) {
                    dp[i][j] = j;
                }

                // If second string is empty, only option is to remove all characters of second string
                else if (j == 0) {
                    dp[i][j] = i;
                }

                // If last characters are same, ignore last char and recur for remaining string
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                // If the last character is different consider all possibilities and find the minimum
                else {
                    dp[i][j] = 1 + min(dp[i][j - 1], // insert
                            dp[i - 1][j], // delete
                            dp[i - 1][j - 1]); // replace
                }
            }
        }
        return dp[m][n];
    }

    public static int min(int i, int j, int k) {
        return Math.min(i, Math.min(j, k));
    }
}
