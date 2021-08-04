package com.slc.datastruct.code;

import java.util.LinkedList;

/**
 * 给定一个整型数组，找出两个整数为指定整数的和
 *
 * @author slc
 */
public class SortArray {


    public static void main(String[] args) {

        search2(new int[]{1, 2, 3, 4, 4,5, 6, 7, 7,8}, 8);

    }

    public static void search1(int[] array, int t) {


        for (int i = 0; i < array.length; i++) {
            int needNum = t - array[i];
            for (int j = 0; j < array.length; j++) {
                if (i != j) {
                    if (array[j] == needNum) {
                        System.out.printf("两个数为：%d,%d\n", array[j], array[i]);
                        break;
                    }
                }
            }

        }

    }

    public static void search2(int[] array, int t) {

        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int sum = array[left] + array[right];

            if (sum == t) {
                System.out.printf("两个数为：%d,%d\n", array[left], array[right]);
                right--;
            }

            if (sum < t) {
                left++;
            }

            if (sum > t) {
                right--;
            }
        }

    }

    public static void search3(int[] array, int t) {
        LinkedList<Object> objects = new LinkedList<>();
    }
}
