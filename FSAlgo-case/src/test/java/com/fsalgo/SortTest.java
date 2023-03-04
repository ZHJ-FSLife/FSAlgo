package com.fsalgo;

import com.fsalgo.core.sort.*;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2023/3/4 16:08
 * @Description:
 */
public class SortTest {

    @Test
    public void QuickSortDemo() {

        Integer[] nums = new Integer[]{5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
        QuickSort<Integer> numSort = new QuickSort<>() {
            @Override
            public boolean compareTo(Integer o1, Integer o2) {
                return o1.compareTo(o2) < 0;
            }
        };
        numSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

    @Test
    public void MergeSortDemo() {
        Integer[] nums = new Integer[]{5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
        MergeSort<Integer> numSort = new MergeSort<>() {
            @Override
            public boolean compareTo(Integer o1, Integer o2) {
                return o1.compareTo(o2) < 0;
            }
        };
        // 非原地归并，开辟临时数组，空间复杂度O(n)
        Integer[] temp = new Integer[nums.length];
        numSort.sort(nums, temp, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
