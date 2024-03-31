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
        // 找当适合当前kv元素的叶子节点位置，添加进去
        LeafNode<K, V> leafNode = root == null ? firstNode : findLeafNode(root, key);
        KeyValuePair<K, V> kv = new KeyValuePair<>(key, value);
        leafNode.add(kv);

        if (!leafNode.isFull()) {
            return;
        }
        // 当前叶子节点已满，分裂，并向上检查、分裂其父节点，返回最后一次分裂的父节点
        NonLeafNode<K, V> parent = leafNode.split();
        while (parent.isFull()) {
            parent = parent.split();
        }

        if (root == null || parent.parent == null) {
            root = parent;
        }
    }

    public void remove(K key) {

    }

    public V search(K key) {
        return null;
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
        for (; index < keys.size(); index++) {
            if (key.compareTo(keys.get(index)) < 0) {
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

    static abstract class Node<K extends Comparable<K>, V> {

        final int degree;

        NonLeafNode<K, V> parent;

        protected Node(int degree) {
            this.degree = degree;
        }

        protected int maxDegree() {
            return 2 * degree - 1;
        }

        /**
         * 获取kv集合中第一kv节点的K值，其父节点记录的K索引为子节点的第一个K值
         *
         * @return first key
         */
        abstract public K getFirstKey();

        /**
         * 当前节点是否满了
         *
         * @return true or false
         */
        abstract public boolean isFull();

        protected void handleParent(Node<K, V> nextNode) {
            if (parent == null) {
                parent = new NonLeafNode<>(degree);
                parent.addChild(0, this);
            }

            K firstOffspringKey = nextNode.getFirstKey();
            if (nextNode instanceof NonLeafNode) {
                firstOffspringKey = findFirstOffspringKey((NonLeafNode<K, V>) nextNode);
            }

            int keyIndex = parent.addKey(firstOffspringKey);
            parent.addChild(keyIndex + 1, nextNode);

            nextNode.parent = parent;
        }

        private K findFirstOffspringKey(NonLeafNode<K, V> node) {
            Node<K, V> child = node.children.get(0);
            if (child instanceof LeafNode) {
                return child.getFirstKey();
            }
            return findFirstOffspringKey((NonLeafNode<K, V>) child);
        }
    }

    static class NonLeafNode<K extends Comparable<K>, V> extends Node<K, V> {

        List<K> keys;

        List<Node<K, V>> children;

        public NonLeafNode(int degree) {
            super(degree);
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }

        /**
         * 添加入新的key，并返回当前添加key的索引位置，用于添加对应的子节点的位置
         *
         * @param key K
         * @return 当前key所添加的索引位置
         */
        public int addKey(K key) {
            int index = 0;
            for (; index < keys.size(); index++) {
                if (key.compareTo(keys.get(index)) < 0) {
                    break;
                }
            }
            keys.add(index, key);
            return index;
        }

        /**
         * 指定索引位置添加子节点
         *
         * @param index 需添加的索引位置
         * @param child 子节点
         */
        public void addChild(int index, Node<K, V> child) {
            children.add(index, child);
        }

        public NonLeafNode<K, V> split() {
            if (!isFull()) {
                 return this;
            }
            NonLeafNode<K, V> nextNonLeafNode = new NonLeafNode<>(degree);

            // 分配孩子节点到nextNonLeafNode中
            int mid = (int) Math.ceil(children.size() / 2.0);
            for (int i = children.size() - 1; i >= mid; i--) {
                Node<K, V> child = children.remove(i);
                child.parent = nextNonLeafNode;
                nextNonLeafNode.addChild(0, child);
                keys.remove(keys.size() - 1);
            }

            // 取孩子节点的K，从索引1开始
            for (int i = 1; i < nextNonLeafNode.children.size(); i++) {
                nextNonLeafNode.keys.add(nextNonLeafNode.children.get(i).getFirstKey());
            }

            handleParent(nextNonLeafNode);
            return parent;
        }

        @Override
        public K getFirstKey() {
            return keys.get(0);
        }

        @Override
        public boolean isFull() {
            return keys.size() >= maxDegree();
        }

        @Override
        public String toString() {
            return keys.toString();
        }
    }

    static class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {

        LeafNode<K, V> prev;

        LeafNode<K, V> next;

        List<KeyValuePair<K, V>> keyValuePairs;

        public LeafNode(int degree) {
            super(degree);
            this.keyValuePairs = new ArrayList<>();
        }

        public LeafNode(int degree, KeyValuePair<K, V> keyValuePair) {
            this(degree);
            add(keyValuePair);
        }

        /**
         * 添加kv节点到叶子节点中，插入排序的方式追加kv节点
         *
         * @param keyValuePair 键值对
         */
        public void add(KeyValuePair<K, V> keyValuePair) {
            int i = 0;
            for (; i < keyValuePairs.size(); i++) {
                if (keyValuePair.key.compareTo(keyValuePairs.get(i).key) < 0) {
                    break;
                }
            }
            keyValuePairs.add(i, keyValuePair);
        }

        /**
         * 中间分裂当前叶子节点，左半部分kv节点保留在当前叶子节点中，将右半部分kv节点分裂到新的叶子节点中去
         *
         * @return next new Leaf Node
         */
        public NonLeafNode<K, V> split() {
            LeafNode<K, V> nextLeafNode = new LeafNode<>(degree);
            // 更新当前节点与分裂后节点的前后指针
            nextLeafNode.prev = this;
            nextLeafNode.next = this.next;
            this.next = nextLeafNode;

            int mid = (int) Math.ceil(keyValuePairs.size() / 2.0);
            // keyValuePairs本就是有序递增的，倒着添加，可规避add方法内部for循环插入时带来的开销
            for (int i = keyValuePairs.size() - 1; i >= mid; i--) {
                // 此处remove的是原list中的最后一个kv节点
                nextLeafNode.add(keyValuePairs.remove(i));
            }

            handleParent(nextLeafNode);
            return parent;
        }

        @Override
        public K getFirstKey() {
            return keyValuePairs.get(0).key;
        }

        @Override
        public boolean isFull() {
            return keyValuePairs.size() >= maxDegree();
        }

        @Override
        public String toString() {
            return keyValuePairs.toString();
        }

    }

    static class KeyValuePair<K extends Comparable<K>, V> {

        K key;
        V value;

        public KeyValuePair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }

}