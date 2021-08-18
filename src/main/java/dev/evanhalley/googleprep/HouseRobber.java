package dev.evanhalley.googleprep;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class HouseRobber {

    public static void main(String[] args) {
        int max = new Solution().rob(new int[] {226,174,214,16,218,48,153,131,128,17,157,142,88,43,37,157,43,221,191,68,206,23,225,82,54,118,111,46,80,49,245,63,25,194,72,80,143,55,209,18,55,122,65,66,177,101,63,201,172,130,103,225,142,46,86,185,62,138,212,192,125,77,223,188,99,228,90,25,193,211,84,239,119,234,85,83,123,120,131,203,219,10,82,35,120,180,249,106,37,169,225,54,103,55,166,124});
        //int max = new Solution().rob(new int[] {1,2,3,1}); //19, 2 + 8 + 9, 19 = 9 + 10 = 8 + 2
        /**
         * 19 = 9,8,2
         * 19 = num[start] + Math.max(dp[start-2], dp[start-3])
         * 19 = num[start] + Math.max(dp[start-2], dp[start-3]) + Math.max(dp[start-4], dp[start-5])
         * 19 = 9 + max(8,4) + max(2,0)
         */
        System.out.println(max);
    }

    static class Solution {
        public int rob(int[] nums) {

            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }
            int[] dp = new int[nums.length];
            return robLinear(nums, dp);
        }

        //2,4,8,9,9,3
        public int robLinear(int[] nums, int[] dp) {

            for (int i = 0; i < nums.length; i++) {

                if (i == 0 || i == 1) {
                    dp[i] = nums[i];
                } else if (i == 2) {
                    dp[i] = nums[i] + dp[i - 2];
                } else {
                   dp[i] = nums[i] + Math.max(dp[i - 2], dp[i - 3]);
                }
            }
            return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
        }

        public int robRecursive(int[] nums, int start, int[] dp) {

            if (start + 2 < nums.length && start + 3 < nums.length) {
                dp[start] = nums[start] + Math.max(robRecursive(nums, start + 2, dp), robRecursive(nums, start + 3, dp));
            } else if (start + 2 < nums.length) {
                dp[start] = nums[start] + robRecursive(nums, start + 2, dp);
            } else {
                dp[start] = nums[start];
            }
            return dp[start];
        }
    }
}