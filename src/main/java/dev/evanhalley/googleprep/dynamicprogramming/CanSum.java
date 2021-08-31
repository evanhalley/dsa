package dev.evanhalley.googleprep.dynamicprogramming;

public class CanSum {

    public static void main(String[] args) {
        System.out.println(canSum(new int[]{7,11,13,17}, 300, new boolean[300 + 1]));
    }

    public static boolean canSum(int[] numbers, int target, boolean[] dp) {
        if (target > 0 && dp[target]) return true;
        if (target == 0) return true;
        if (target < 0) return false;

        for (int num : numbers) {
            int val = target - num;

            if (canSum(numbers, val, dp)) {
                dp[val] = true;
                return true;
            }
        }
        dp[target] = false;
        return false;
    }
}
