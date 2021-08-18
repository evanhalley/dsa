package dev.evanhalley.googleprep;

import static dev.evanhalley.googleprep.util.ArrayUtil.printArray;
import static dev.evanhalley.googleprep.util.ArrayUtil.swap;

public class KthLargestElementInArray {

    public static void main(String[] args) {
        // 8,4,2,5,8,5,2,3,6,87,9,0
        // 0,2,2,3,4,5,5,7,8,8,9,87
        System.out.println(findKthLargest(new int[] {8,4,2,5,8,5,2,3,6,87,9,0}, 3));
        System.out.println(findKthLargestWithQuickSort(new int[] {8,4,2,5,8,5,2,3,6,87,9,0}, 3));
    }

    public static int findKthLargest(int[] nums, int k) {
        MinHeap minHeap = new MinHeap();

        for (int num : nums) {
            minHeap.add(num);

            if (minHeap.getSize() > k) {
                minHeap.poll();
            }
        }
        return minHeap.poll();
    }

    public static int findKthLargestWithQuickSort (int[] nums, int k) {
        int indexToFind = nums.length - k;
        return quickSort(nums, indexToFind,0, nums.length - 1);
    }

    private static int quickSort(int[] arr, int indexToFind, int left, int right) {

        if (left == right && left == indexToFind) {
            // we finished sorting the list and the item we are looking for is at it's resting spot, return it
            return arr[indexToFind];
        } else if (left < right) {
            int finalPivotIdx = partition(arr, left, right);

            // the item we are looking for is at it's resting spot, return it, don't need to sort the rest
            if (finalPivotIdx == indexToFind) {
                return arr[finalPivotIdx];
            }

            // we are only interested in sorting the side of the pivot where the index of the number we want is
            if (indexToFind > finalPivotIdx) {
                return quickSort(arr, indexToFind, finalPivotIdx + 1, right);
            } else {
                return quickSort(arr, indexToFind, left, finalPivotIdx - 1);
            }
        }
        return -1;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        int j = left;

        for (; j < right; j++) {

            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, j);
        return i + 1;
    }

}
