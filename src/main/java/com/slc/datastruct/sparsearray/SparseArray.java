package com.slc.datastruct.sparsearray;

/**
 * 稀疏数组
 *
 * @author slc
 */
public class SparseArray {
    static int[][] array = new int[8][8];

    static {
        array[1][1] = 4;
        array[2][3] = 4;
        array[4][5] = 4;
    }


    public static void main(String[] args) {
        foreachArray(array);
        System.out.println("---------------------------");

        int[][] sparseArray = convertArrayToSparseArray(array);
        foreachArray(sparseArray);
        System.out.println("---------------------------");

        int[][] reconvertArray = convertSparseArrayToArray(sparseArray);
        foreachArray(reconvertArray);
    }

    /**
     * 普通数组-->稀疏数组
     * @param array 普通数组
     * @return 稀疏数组
     */
    public static int[][] convertArrayToSparseArray(int[][] array) {
        //计算需要存储的数（不为0的数）
        int count = 0;
        for (int[] ints : array) {
            for (int number : ints) {
                if (number != 0) {
                    count++;
                }
            }
        }

        //初始化稀疏数组
        int[][] sparseArray = new int[(count + 1)][3];
        sparseArray[0][0] = 8;
        sparseArray[0][1] = 8;
        sparseArray[0][2] = count;

        int row = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    sparseArray[++row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = array[i][j];
                }
            }
        }
        return sparseArray;
    }

    /**
     * 稀疏数组-->普通数组
     * @param sparseArray 稀疏数组
     * @return 普通数组
     */
    public static int[][] convertSparseArrayToArray(int[][] sparseArray) {
        int[][] simpleArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            simpleArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return simpleArray;
    }

    /**
     * 简单的遍历打印
     * @param array 数组
     */
    public static void foreachArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }

    }
}
