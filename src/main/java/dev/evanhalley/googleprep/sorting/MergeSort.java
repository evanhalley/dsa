package dev.evanhalley.googleprep.sorting;

import static dev.evanhalley.googleprep.util.ArrayUtil.printArray;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[] { 34, 423, 1 };
        int[] sorted = mergeSort(arr, 0, arr.length - 1);
        printArray(sorted);
    }

    static int[] mergeSort(int[] arr, int left, int right) {

        if (left >= right) {
            return new int[] { arr[left] };
        }
        int middle = left + (right - left + 1) / 2;
        int[] leftArr = mergeSort(arr, left, middle - 1);
        int[] rightArr = mergeSort(arr, middle, right);
        return merge(leftArr, rightArr);
    }

    static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {

            if (left[i] < right[j]) {
                merged[k++] = left[i++];
            } else {
                merged[k++] = right[j++];
            }
        }

        while (i < left.length) {
            merged[k++] = left[i++];
        }

        while (j < right.length) {
            merged[k++] = right[j++];
        }
        return merged;
    }


}
