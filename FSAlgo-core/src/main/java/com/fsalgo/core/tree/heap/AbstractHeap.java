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
package com.fsalgo.core.tree.heap;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/26 2:31
 * @Description:
 */
public abstract class AbstractHeap<T> implements Heap<T>, Serializable {

    private static final long serialVersionUID = 1L;

    protected final Comparator<? super T> comparator;

    @SuppressWarnings("unchecked")
    protected AbstractHeap(Comparator<? super T> comparator) {
        if (comparator == null) {
            this.comparator = (Comparator<? super T>) Comparator.naturalOrder();
            return;
        }
        this.comparator = comparator;
    }

    /**
     * 泛型数组形式添加多元素
     *
     * @param keys 多元素
     */
    @Override
    public void addAll(T[] keys) {
        for (T key : keys) {
            add(key);
        }
    }

    /**
     * 泛型集合形式添加多元素
     *
     * @param keys 多元素
     */
    @Override
    public void addAll(List<T> keys) {
        for (T key : keys) {
            add(key);
        }
    }

    /**
     * 比较堆中元素的大小 - 默认为小顶堆
     *
     * @return true or false
     */
    @Override
    public boolean compareTo(T x, T y) {
        return comparator.compare(x, y) < 0;
    }

}
