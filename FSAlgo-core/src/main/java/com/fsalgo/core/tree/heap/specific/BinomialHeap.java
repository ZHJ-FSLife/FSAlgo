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
import java.util.Comparator;

/**
 * @Author: root
 * @Date: 2023/1/17 13:38
 * @Description: 二项式堆
 */
public class BinomialHeap<T extends Comparable<T>> extends AbstractHeap<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public BinomialHeap() {
        this(Comparator.naturalOrder());
    }

    public BinomialHeap(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public void add(T t) {

    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public T remove() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getName() {
        return "binomial-heap";
    }
}
