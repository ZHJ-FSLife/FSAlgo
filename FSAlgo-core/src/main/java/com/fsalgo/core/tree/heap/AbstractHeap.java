package com.fsalgo.core.tree.heap;

/**
 * @Author: root
 * @Date: 2023/3/26 2:31
 * @Description:
 */
public abstract class AbstractHeap<T extends Comparable<T>> implements Heap<T> {

    /**
     * 比较堆中元素的大小 - 默认为小顶堆
     *
     * @return true or false
     */
    @Override
    public boolean compareTo(T x, T y) {
        return x.compareTo(y) < 0;
    }

}
