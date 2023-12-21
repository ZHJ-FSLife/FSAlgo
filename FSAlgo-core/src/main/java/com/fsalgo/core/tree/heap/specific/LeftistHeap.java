/*
 * FSAlgo
 * hhttps://github.com/ZHJ-FSLife/FSAlgo
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

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * @Author: root
 * @Date: 2023/1/17 13:39
 * @Description: 左倾堆 - 主要用于两个堆的合并
 */
public class LeftistHeap<T extends Comparable<T>> extends AbstractHeap<T> {

    private Node<T> root;

    private int size = 0;

    public LeftistHeap() {
        this(Comparator.naturalOrder());
    }

    public LeftistHeap(Comparator<? super T> comparator) {
        super(comparator);
    }

    @Override
    public void add(T t) {
        root = union(root, new Node<>(t));
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return root.key;
    }

    @Override
    public T remove() {
        T min = root.key;
        root = union(root.left, root.right);
        size--;
        return min;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 合并堆
     *
     * @param node1 堆1根节点
     * @param node2 堆2根节点
     * @return 合并后的堆
     */
    private Node<T> union(Node<T> node1, Node<T> node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (!compareTo(node1.key, node2.key)) {
            Node<T> temp = node1;
            node1 = node2;
            node2 = temp;
        }

        node1.right = union(node1.right, node2);
        if (node1.left == null || node1.right.npl > node1.left.npl) {
            Node<T> temp = node1.left;
            node1.left = node1.right;
            node1.right = temp;
        }
        node1.npl = node1.right == null ? 0 : node1.right.npl + 1;
        return node1;
    }

    public static class Node<T> {
        T key;
        int npl = 0;
        Node<T> left;
        Node<T> right;

        public Node(T key) {
            this.key = key;
        }
    }

    @Override
    public String getName() {
        return "leftist-heap";
    }
}
