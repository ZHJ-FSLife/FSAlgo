package com.fsalgo.core.tree.heap;

import com.fsalgo.core.interfaces.NameEntity;

/**
 * @Author: root
 * @Date: 2023/1/17 13:06
 * @Description:
 */
public interface Heap<T> extends NameEntity {

    void add(T t);

    T peek();

    T remove();

    int size();

    boolean isEmpty();

    boolean compareTo(T x, T y);
}
