/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
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
