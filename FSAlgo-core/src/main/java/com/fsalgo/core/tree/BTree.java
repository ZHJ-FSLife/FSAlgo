/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description: B-树
 */
public class BTree<T extends Comparable<T>> {

    /**
     * 度，B树的阶数为: 2 * degree - 1
     */
    private final int degree;

    private Node<T> root;

    public BTree() {
        this(2);
    }

    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node<>();
    }

    public Node<T> getRoot() {
        return root;
    }

    /**
     * 添加节点
     *
     * @param key 节点元素
     */
    public void add(T key) {
        Node<T> temp = root;
        if (temp.keys.size() < 2 * degree - 1) {
            add(temp, key);
            return;
        }
        Node<T> node = new Node<>();
        root = node;
        node.leaf = false;
        node.child.add(temp);
        split(node, temp, 0);
        add(node, key);
    }

    /**
     * 添加节点
     *
     * @param node 节点
     * @param key  节点元素
     */
    private void add(Node<T> node, T key) {
        int index = node.keys.size() - 1;
        if (node.leaf) {
            // node节点添加一个空元素，并将前面元素向后移一位，直到适合放入key元素的位置为止
            node.keys.add(null);
            while (index >= 0 && compareTo(node.keys.get(index), key)) {
                node.keys.set(index + 1, node.keys.get(index));
                index--;
            }
            node.keys.set(index + 1, key);
            return;
        }
        // 如果不是非叶子节点，则找到对应位置的子节点位置
        while (index >= 0 && compareTo(node.keys.get(index), key)) {
            index--;
        }
        index++;
        Node<T> child = node.child.get(index);
        // 如果子节点已满，则分裂子节点
        if (child.keys.size() >= 2 * degree - 1) {
            split(node, child, index);
            if (!compareTo(node.keys.get(index), key)) {
                index++;
            }
        }
        add(node.child.get(index), key);
    }

    /**
     * 分隔节点
     *
     * @param node      节点
     * @param childNode 子节点
     * @param index     索引
     */
    private void split(Node<T> node, Node<T> childNode, int index) {
        Node<T> temp = new Node<>();
        temp.leaf = childNode.leaf;
        for (int i = 0; i < degree - 1; i++) {
            temp.keys.add(childNode.keys.remove(degree));
        }
        if (!childNode.leaf) {
            for (int i = 0; i < degree; i++) {
                temp.child.add(childNode.child.remove(degree));
            }
        }
        node.child.add(index + 1, temp);
        node.keys.add(index, childNode.keys.remove(degree - 1));
    }

    /**
     * 比较节点元素大小
     */
    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    public static class Node<T> {
        boolean leaf;
        List<T> keys;
        List<Node<T>> child;

        public Node() {
            this.leaf = true;
            this.keys = new ArrayList<>();
            this.child = new ArrayList<>();
        }

        public List<Node<T>> getChild() {
            return child;
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }
}