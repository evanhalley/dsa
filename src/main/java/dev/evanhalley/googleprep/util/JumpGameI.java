package dev.evanhalley.googleprep.util;

public class JumpGameI {

    public static void main(String[] args) {
        System.out.println(canJump(new int[] { 2,3,1,0,4 }));
    }

    public static boolean canJump(int[] nums) {
        int goal = nums.length - 1;

        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];

            if (i + val >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }


}
