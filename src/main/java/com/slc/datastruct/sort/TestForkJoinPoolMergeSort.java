package com.slc.datastruct.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 这是一个简单的Join/Fork计算过程，将1—1001数字相加
 */
public class TestForkJoinPoolMergeSort {
    public static void print(int[] arr) {
        Arrays.stream(arr).forEach(i -> System.out.print(i + ","));
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int[] a = new int[20];
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            a[i] = random.nextInt(100);
        }
        MergeTask mergeTask = new MergeTask(a);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(mergeTask);
        int[] ints = mergeTask.get();
        Arrays.stream(ints).forEach(i -> System.out.print(i + ","));
    }


    static class MergeTask extends RecursiveTask<int[]> {

        private int[] array = null;

        public MergeTask(int[] array) {
            this.array = array;
        }

        @Override
        protected int[] compute() {
            int length = array.length;
            if (length > 2) {
                int midIndex = length / 2;
                MergeTask mergeTask1 = new MergeTask(Arrays.copyOf(array, midIndex));
                mergeTask1.fork();
                MergeTask mergeTask2 = new MergeTask(Arrays.copyOfRange(array, midIndex, length));
                mergeTask2.fork();
                try {
                    return combine(mergeTask1.get(), mergeTask2.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                if (length == 1 || array[0] <= array[1]) {
                    return array;
                } else {
                    int temp = array[0];
                    array[0] = array[1];
                    array[1] = temp;
                }
            }
            return array;
        }
    }
}