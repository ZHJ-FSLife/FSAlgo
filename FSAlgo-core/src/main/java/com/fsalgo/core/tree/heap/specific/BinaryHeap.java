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
package com.fsalgo.core.tree.heap.specific;

import com.fsalgo.core.tree.heap.AbstractHeap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 二叉堆、优先队列
 * java自带类库有现成的，java.util.PriorityQueue
 */
public class BinaryHeap<T extends Comparable<T>> extends AbstractHeap<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 存放堆节点
     */
    private final List<T> queue;

    public BinaryHeap() {
        this(Comparator.naturalOrder());
    }

    public BinaryHeap(Comparator<? super T> comparator) {
        super(comparator);
        this.queue = new ArrayList<>();
    }

    /**
     * 当前堆大小
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * 当前堆是都为空
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return queue.toString();
    }

    /**
     * 获取堆顶元素
     */
    @Override
    public T peek() {
        return queue.get(0);
    }

    /**
     * 元素添加到堆中，并调整堆序
     *
     * @param val 新节点元素
     */
    @Override
    public void add(T val) {
        queue.add(val);
        if (size() <= 1) {
            return;
        }

        // 新节点插入队列末尾, 下标为 size() - 1; 左节点的父节点为 (index - 1) / 2, 右节点的父节点为 (index - 2) / 2
        int index = size() - 1;
        int pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
        // 堆末尾节点与父级节点比较，决定是否上移保持堆序
        while (compareTo(val, queue.get(pIndex))) {
            swap(index, pIndex);
            index = pIndex;
            pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
            if (pIndex < 0) {
                break;
            }
        }
    }

    /**
     * 移除堆中最值，并调整堆序
     *
     * @return 堆顶最值
     */
    @Override
    public T remove() {
        if (size() == 0) {
            return null;
        }
        // 堆顶和最后一个节点交换
        swap(0, size() - 1);
        T result = queue.remove(size() - 1);

        // 堆顶节点向下和子节点比较，决定是否下移保持堆序
        int index = 0;
        while ((index * 2) + 1 < size()) {
            int top;
            int left = (index * 2) + 1;
            int right = (index * 2) + 2;

            if (right < size()) {
                // 如果存在右子节点，取左、右节点最值，与当前节点比较。
                top = compareTo(queue.get(left), queue.get(right)) ? left : right;
                top = compareTo(queue.get(top), queue.get(index)) ? top : index;
            } else {
                // 如果只存在左子节点，与当前节点比较
                top = compareTo(queue.get(left), queue.get(index)) ? left : index;
            }
            // 如果最值为当前节点，则结束堆节点下移
            if (top == index) {
                break;
            }
            swap(index, top);
            index = top;
        }
        return result;
    }

    /**
     * 交换堆中元素
     */
    private void swap(int x, int y) {
        T temp = queue.get(x);
        queue.set(x, queue.get(y));
        queue.set(y, temp);
    }

    @Override
    public String getName() {
        return "binary-heap";
    }

}
