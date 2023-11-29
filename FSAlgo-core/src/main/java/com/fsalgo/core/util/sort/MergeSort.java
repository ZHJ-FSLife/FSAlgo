/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.fsalgo.core.util.sort;

/**
 * @Author: root
 * @Date: 2023/3/4 16:23
 * @Description: 归并排序
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
