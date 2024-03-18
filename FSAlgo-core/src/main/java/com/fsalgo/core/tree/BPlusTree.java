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
package com.fsalgo.core.tree;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description: B+树
 */
public class BPlusTree<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Comparator<? super T> comparator;

    private final int degree;

    private Node<T> root;

    private LeafNode<T> firstLeafNode;

    public BPlusTree() {
        this(2);
    }

    @SuppressWarnings("unchecked")
    public BPlusTree(int degree) {
        this(degree, (Comparator<? super T>) Comparator.naturalOrder());
    }

    public BPlusTree(int degree, Comparator<? super T> comparator) {
        this.degree = degree;
        this.comparator = comparator;
    }

    private int compareTo(T x, T y) {
        return comparator.compare(x, y);
    }

    static class Node<T> {
        NonLeafNode<T> parent;
    }

    static class NonLeafNode<T> extends Node<T> {
        int degree;
        List<T> keys;
        List<Node<T>> child;
    }

    static class LeafNode<T> extends Node<T> {
        T key;
        LeafNode<T> last;
        LeafNode<T> next;

        public LeafNode(T key) {
            this.key = key;
        }
    }


}











