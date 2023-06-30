package com.fsalgo.core.tree.heap;

import com.fsalgo.core.interfaces.NameEntity;

/**
 * @Author: root
 * @Date: 2023/1/17 13:06
 * @Description:
 */
public interface Heap<T> extends NameEntity {

    /**
     * 添加节点元素
     *
     * @param t 节点元素
     */
    void add(T t);

    /**
     * 获取堆顶节点元素
     *
     * @return 堆顶节点元素
     */
    T peek();

    /**
     * 获取并移除堆顶节点元素
     *
     * @return 堆顶节点元素
     */
    T remove();

    /**
     * 获取堆大小
     *
     * @return 堆大小
     */
    int size();

    /**
     * 堆是否为空
     *
     * @return true or false
     */
    boolean isEmpty();

    /**
     * 比较堆中节点元素
     *
     * @param x 节点1
     * @param y 节点2
     * @return true or false
     */
    boolean compareTo(T x, T y);
}
