package dev.evanhalley.googleprep;

public class JumpGameII {

    public static void main(String[] args) {
        System.out.println(jump(new int[] { 10,9,8,7,6,5,4,3,2,1,1,0 }));
    }

    public static int jump(int[] nums) {

        int i = 0;
        int numOfJumps = 0;

        while (i != nums.length - 1) {
            int val = nums[i];
            i = getIndexWithLargestValue(nums, i + 1, i + val);
            numOfJumps++;
        }
        return numOfJumps;
    }

    static int getIndexWithLargestValue(int[] arr, int start, int end) {
        int index = start - 1;
        int max = -1;

        for (int i = start; i <= end; i++) {

            if (i == arr.length - 1) {
                return i;
            }

            if (i + arr[i] >= arr.length) {
                return i;
            }

            if (arr[i] > 0 && arr[i] >= max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

}
