package com.fsalgo.core.sort;

/**
 * @Author: root
 * @Date: 2023/3/4 15:56
 * @Description: 快速排序
 */
public class QuickSort<T extends Comparable<T>> {

    public void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array cannot be empty!");
        }
        sort(arr, 0, arr.length - 1);
    }

    public void sort(T[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array cannot be empty!");
        }
        if (left > right) {
            return;
        }
        // 取数组中间带的值
        T mid = arr[left + (right - left) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            // 找到左边大于mid的值
            while (compareTo(mid, arr[i])) {
                i++;
            }
            // 找到右边小于mid的值
            while (compareTo(arr[j], mid)) {
                j--;
            }
            // 交换左右两边的值
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        // 对左边子数组排序
        if (j > left) {
            sort(arr, left, j);
        }
        // 对右边子数组排序
        if (i < right) {
            sort(arr, i, right);
        }
    }


    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    private void swap(T[] arr, int x, int y) {
        T temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
