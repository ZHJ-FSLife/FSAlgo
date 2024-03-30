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
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description: B+树
 */
public class BPlusTree<K extends Comparable<K>, V> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 根节点
     */
    private NonLeafNode<K, V> root;

    /**
     * 第一节节点
     */
    private LeafNode<K, V> firstNode;

    /**
     * 度，B+树的阶数为: 2 * degree - 1
     */
    private final int degree;

    public BPlusTree(int degree) {
        this.degree = degree;
    }

    /**
     * 添加节点
     *
     * @param key   K
     * @param value V
     */
    public void add(K key, V value) {
        if (isEmpty()) {
            firstNode = new LeafNode<>(degree, new KeyValuePair<>(key, value));
            return;
        }

        LeafNode<K, V> leafNode = root == null ? firstNode : findLeafNode(root, key);
        if (leafNode.add(new KeyValuePair<>(key, value))) {
            return;
        }
        // 叶子节点失败，分裂节点
    }

    /**
     * 找到合适位置的叶子节点
     *
     * @param node NonLeafNode
     * @param key  K
     * @return LeafNode
     */
    private LeafNode<K, V> findLeafNode(NonLeafNode<K, V> node, K key) {
        List<K> keys = node.keys;
        int index = 0;
        for (; index < degree - 1; index++) {
            if (key.compareTo(keys.get(index)) > 0) {
                break;
            }
        }
        Node<K, V> child = node.children.get(index);

        if (child instanceof LeafNode) {
            return (LeafNode<K, V>) child;
        }
        // 当前节点还不是叶子节点，往下递归
        return findLeafNode((NonLeafNode<K, V>) node.children.get(index), key);
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    abstract class Node<K extends Comparable<K>, V> {

        final int degree;

        NonLeafNode<K, V> parent;

        protected Node(int degree) {
            this.degree = degree;
        }

        protected int maxDegree() {
            return 2 * degree - 1;
        }

        protected int minDegree() {
            return degree;
        }

        /**
         * 当前节点是否满了
         *
         * @return true or false
         */
        abstract protected boolean isFull();
    }

    class NonLeafNode<K extends Comparable<K>, V> extends Node<K, V> {

        List<K> keys;

        List<Node<K, V>> children;

        public NonLeafNode(int degree) {
            super(degree);
        }

        @Override
        protected boolean isFull() {
            return keys.size() >= maxDegree();
        }

    }

    class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {

        int degree;

        List<KeyValuePair<K, V>> keyValuePairs;

        protected LeafNode(int degree, KeyValuePair<K, V> keyValuePair) {
            super(degree);
            this.keyValuePairs = new ArrayList<>();
            add(keyValuePair);
        }

        public boolean add(KeyValuePair<K, V> keyValuePair) {
            if (isFull()) {
                return false;
            }
            for (int i = 0; i < keyValuePairs.size(); i++) {
                if (keyValuePair.key.compareTo(keyValuePairs.get(i).key) < 0) {
                    keyValuePairs.add(i, keyValuePair);
                    return true;
                }
            }
            keyValuePairs.add(keyValuePair);
            return true;
        }

        @Override
        protected boolean isFull() {
            return keyValuePairs.size() >= maxDegree();
        }
    }

    static class KeyValuePair<K extends Comparable<K>, V> {

        K key;
        V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


}











