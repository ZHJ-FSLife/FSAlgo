package com.fsalgo;

import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.specific.BinaryHeap;
import com.fsalgo.core.tree.heap.specific.FibonacciHeap;
import com.fsalgo.core.tree.heap.specific.LeftistHeap;
import com.fsalgo.core.tree.heap.specific.SkewHeap;
import org.junit.Test;

/**
 * @Author: root
 * @Date: 2023/1/17 9:03
 * @Description:
 */
public class HeapTest {

    @Test
    public void FibonacciHeapDemo() {
        int[] nums = {3, 1, 4, 5, 0, 2, 9, 7, 8, 6};
        Heap<Integer> heap = new FibonacciHeap<>();

        for (int num : nums) {
            heap.add(num);
        }

        int i = 0;
        while (!heap.isEmpty()) {
            if (i >= 5 && i < 10) {
                heap.add(nums[i]);
            }
            i++;
            System.out.print(heap.remove() + ", ");
        }
    }

    @Test
    public void BinaryHeapDemo() {
        int[] nums = {3, 1, 4, 5, 0, 2, 9, 7, 8, 6};

        Heap<Integer> heap = new BinaryHeap<>();

        for (int num : nums) {
            heap.add(num);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.remove() + ", ");
        }
    }

    @Test
    public void LeftisHeapDemo() {
        int[] nums = {3, 1, 4, 5, 0, 2, 9, 7, 8, 6};

        Heap<Integer> heap = new LeftistHeap<>();

        for (int num : nums) {
            heap.add(num);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.remove() + ", ");
        }
    }

    @Test
    public void SkewHeapDemo() {
        int[] nums = {3, 1, 4, 5, 0, 2, 9, 7, 8, 6};

        Heap<Integer> heap = new SkewHeap<>();

        for (int num : nums) {
            heap.add(num);
        }

        while (!heap.isEmpty()) {
            System.out.print(heap.remove() + ", ");
        }
    }
}
