package dev.evanhalley.googleprep.algorithm;

public class Kadane {

    public static void main(String[] args) {
        System.out.println(maximumSum(new int[] { 3, -4 ,1, 2, 6, -2 }));
        System.out.println(maximumSum(new int[] { -1, -1, -1, 3 }));
    }

    public static int maximumSum(int[] nums) {
        int best = 0;
        int sum = 0;

        for (int num : nums) {
            sum = Math.max(num, sum + num);
            best = Math.max(best, sum);
        }
        return best;
    }


}
