package dev.evanhalley.googleprep.util;

public class ArrayUtil {

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        //System.out.println(String.format("Swapping arr[%s]=%s <-> arr[%s]=%s", i, arr[i], j, arr[j]));
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
