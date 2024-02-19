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
import java.util.ArrayList;
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

    private int degree;

    private Node<T> root;

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

    public void add(T key) {
        if (isFull(root)) {

        }
        add(root, key);
    }

    private void add(Node<T> node, T key) {
        int index = findInsertIndex(node, key);
        if (node.leaf) {
            node.keys.add(index, key);
            return;
        }
        Node<T> child = node.children.get(index);
        if (isFull(child)) {
            splitChildren(node, index);
            if (compareTo(key, node.keys.get(index)) > 0) {
                index++;
            }
        }
        add(node.children.get(index), key);
    }

    private void splitChildren(Node<T> parent, int index) {
        Node<T> child = parent.children.get(index);
        Node<T> newChild = new Node<>(child.leaf);

        int midIndex = child.keys.size() / 2;

        if (!child.leaf) {
            
        }
    }

    private boolean isFull(Node<T> node) {
        return node.keys.size() >= 2 * degree - 1;
    }

    private int findInsertIndex(Node<T> node, T key) {
        int index = 0;
        List<T> keys = node.keys;
        while (index < keys.size() && compareTo(key, keys.get(index)) > 0) {
            index++;
        }
        return index;
    }

    private int findSearchIndex(Node<T> node, T key) {
        int index = 0;
        List<T> keys = node.keys;
        while (index < keys.size() && compareTo(key, keys.get(index)) >= 0) {
            index++;
        }
        return index - 1;
    }

    private int compareTo(T x, T y) {
        return comparator.compare(x, y);
    }

    static class Node<T> {
        boolean leaf;
        List<T> keys;
        List<Node<T>> children;
        Node<T> next;

        public Node(boolean leaf) {
            this.leaf = leaf;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
            this.next = null;
        }
    }

}











