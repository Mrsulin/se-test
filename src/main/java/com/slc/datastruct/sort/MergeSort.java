package com.slc.datastruct.sort;

import java.util.Arrays;
import java.util.Random;

public class MergeSort {

    public static void main(String[] args) {
        int[] a = new int[20];
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            a[i]=random.nextInt(100);
        }
        int[] sort = sort(a);
        Arrays.stream(sort).forEach(i-> System.out.print(i+","));
    }
    public static void print(int[] arr) {
        Arrays.stream(arr).forEach(i -> System.out.print(i + ","));
    }
    public static int[] sort(int[] array) {
        int arrayLength = array.length;
        if (arrayLength > 2) {
            int midIndex = array.length / 2;
            return combine(sort(Arrays.copyOf(array, midIndex)), sort(Arrays.copyOfRange(array, midIndex, array.length)));
        } else {
            if (arrayLength == 1 || array[0] <= array[1]) {
                return array;
            } else {
                int temp = array[0];
                array[0] = array[1];
                array[1] = temp;
            }
        }
        return array;
    }

    public static int[] combine(int[] arr1, int[] arr2) {
        System.out.print("left: ");
        print(arr1);
        System.out.print("    right: ");
        print(arr2);
        System.out.println();
        int[] combineArray = new int[arr1.length + arr2.length];
        int arr1Length = arr1.length;
        int arr2Length = arr2.length;

        for (int i = 0, indexA = 0, indexB = 0; i < combineArray.length; i++) {
            if (indexA == arr1Length) {
                combineArray[i] = arr2[indexB];
                indexB++;
                continue;
            }
            if (indexB == arr2Length) {
                combineArray[i] = arr1[indexA];
                indexA++;
                continue;
            }
            if (arr1[indexA] <= arr2[indexB]) {
                combineArray[i] = arr1[indexA];
                indexA++;
            } else {
                combineArray[i] = arr2[indexB];
                indexB++;
            }
        }
        return combineArray;
    }
}
