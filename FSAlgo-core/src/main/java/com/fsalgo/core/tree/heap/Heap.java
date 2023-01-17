package com.fsalgo.core.tree.heap;

/**
 * @Author: root
 * @Date: 2023/1/17 13:06
 * @Description:
 */
public interface Heap<T> {

    void add(T t);

    T peek();

    T remove();

    int size();

    boolean isEmpty();

    boolean compareTo(T t1, T t2);
}
