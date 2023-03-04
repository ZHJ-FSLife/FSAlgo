package com.fsalgo.core.sort;

/**
 * @Author: root
 * @Date: 2023/3/4 16:23
 * @Description:
 */
public class MergeSort<T extends Comparable<T>> {

    public void sort(T[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array cannot be empty!");
        }
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 原地归并，空间复杂度O(1)
     */
    public void sort(T[] arr, int left, int right) {

    }

    public void sort(T[] arr, T[] temp) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array cannot be empty!");
        }
        sort(arr, temp, 0, arr.length - 1);
    }

    /**
     * 非原地归并，空间复杂度O(n)
     */
    public void sort(T[] arr, T[] temp, int begin, int end) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("array cannot be empty!");
        }
        if (begin >= end) {
            return;
        }
        int mid = ((end - begin) / 2) + begin;
        int begin1 = begin, end1 = mid;
        int begin2 = mid + 1, end2 = end;
        sort(arr, temp, begin1, end1);
        sort(arr, temp, begin2, end2);

        int i = begin;
        while (begin1 <= end1 && begin2 <= end2) {
            temp[i++] = !compareTo(arr[begin1], arr[begin2]) ? arr[begin1++] : arr[begin2++];
        }
        while (begin1 <= end1) {
            temp[i++] = arr[begin1++];
        }
        while (begin2 <= end2) {
            temp[i++] = arr[begin2++];
        }
        for (i = begin; i <= end; i++) {
            arr[i] = temp[i];
        }
    }

    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }
}
