package dev.evanhalley.googleprep;

public class RemoveDuplicateNumbersInSortedArray {

    public static void main(String[] args) {
        removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4});
    }

    public static int removeDuplicates(int[] nums) {
        int lastNumberRead = -1;
        int indexToOverwrite = -1;
        int numUniqueCharacters = 0;

        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];

            // first discover a duplicate
            if (currentNumber == lastNumberRead && indexToOverwrite == -1) {
                indexToOverwrite = i;
            }

            // we reached a different number, lets do the swap
            else if (currentNumber != lastNumberRead && indexToOverwrite != -1) {
                swap(nums, i, indexToOverwrite);
                indexToOverwrite++;
            }

            if (currentNumber != lastNumberRead) {
                lastNumberRead = currentNumber;
                numUniqueCharacters++;
            }
        }

        while (indexToOverwrite < nums.length) {
            nums[indexToOverwrite++] = 0;
        }
        return numUniqueCharacters;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
