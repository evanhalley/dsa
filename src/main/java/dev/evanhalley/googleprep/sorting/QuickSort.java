package dev.evanhalley.googleprep.sorting;

import static dev.evanhalley.googleprep.util.ArrayUtil.printArray;
import static dev.evanhalley.googleprep.util.ArrayUtil.swap;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {5, 0, 2, 6, 1, 3, 7, 4};
        printArray(arr);
        quickSort(arr, 0, arr.length - 1);
        printArray(arr);
    }

    private static void quickSort(int[] arr, int left, int right) {

        if (left < right) {
            int finalPivotIdx = partition(arr, left, right);
            quickSort(arr, left, finalPivotIdx - 1);
            quickSort(arr, finalPivotIdx + 1, right);
        }

    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // always picking the last element as the pivot
        int i = left - 1;  // i remember's the last place we put the item less than the pivot
        int j = left; // j index while we iterate over the partition

        for (; j < right; j++) {

            if (arr[j] <= pivot) { // this value is less than our pivot
                i++; // move it to next place we want to swap
                swap(arr, i, j); //execute the swap
                printArray(arr);
            }
        }
        swap(arr, i + 1, j); // put the pivot in its final position
        return i + 1;
    }
}
