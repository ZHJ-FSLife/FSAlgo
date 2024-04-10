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
        if (degree <= 1) {
            throw new IllegalArgumentException("The degree cannot be less than 2");
        }
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

    /**
     * 搜索指定key对应的value
     *
     * @param key K
     * @return V
     */
    public V search(K key) {
        if (isEmpty()) {
            return null;
        }
        KeyValuePair<K, V> keyValuePair;
        // 如果root为空，表示当前只有一个叶子节点还没开始分裂，直接从firstNode开始找
        if (root == null) {
            keyValuePair = firstNode.findKeyValuePair(key);
            return keyValuePair == null ? null : keyValuePair.value;
        }
        // 从root开始递归下去找到对应的叶子节点
        keyValuePair = search(root, key).findKeyValuePair(key);
        return keyValuePair == null ? null : keyValuePair.value;
    }

    /**
     * 递归下去找到该key所在的叶子节点
     *
     * @param node 非叶子节点
     * @param key  K
     * @return 叶子节点
     */
    private LeafNode<K, V> search(NonLeafNode<K, V> node, K key) {
        int index = 0;
        for (; index < node.keys.size(); index++) {
            K nextKey = node.keys.get(index);
            if (nextKey.compareTo(key) > 0) {
                break;
            }
        }
        Node<K, V> child = node.children.get(index);
        if (child instanceof LeafNode) {
            return (LeafNode<K, V>) child;
        }
        return search((NonLeafNode<K, V>) child, key);
    }

    /**
     * 区间搜索，min >= x < max
     *
     * @param min 最小key
     * @param max 最大key
     * @return List<V>
     */
    public List<V> range(K min, K max) {
        List<V> result = new ArrayList<>();
        if (isEmpty() || min.compareTo(max) > 0) {
            return result;
        }
        // 找到>=min所在的叶子节点，从该叶子节点的next指针开始往下找，直到max为止
        LeafNode<K, V> startLeafNode = search(root, min);
        while (startLeafNode.next != null) {
            for (KeyValuePair<K, V> kv : startLeafNode.keyValuePairs) {
                K key = kv.key;
                if (key.compareTo(max) >= 0) {
                    return result;
                }
                if (key.compareTo(min) >= 0) {
                    result.add(kv.value);
                }
            }
            startLeafNode = startLeafNode.next;
        }
        return result;
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
         * 处理分割后的父节点
         *
         * @param nextNode
         */
        protected void handleParent(Node<K, V> nextNode) {
            if (parent == null) {
                parent = new NonLeafNode<>(degree);
                parent.addChild(0, this);
            }
            int keyIndex = parent.addKey(nextNode.getFirstKey());
            parent.addChild(keyIndex + 1, nextNode);
            nextNode.parent = parent;
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

        /**
         * 分割非叶子节点
         *
         * @return 当前非叶子节点分割两半之后，返回共同的一个父节点
         */
        public NonLeafNode<K, V> split() {
            if (!isFull()) {
                return this;
            }
            // 如果不存在父节点就创建一个
            if (parent == null) {
                parent = new NonLeafNode<>(degree);
                parent.addChild(0, this);
            }
            // 初始化一个分割后的next节点
            NonLeafNode<K, V> nextNonLeafNode = new NonLeafNode<>(degree);
            nextNonLeafNode.parent = parent;

            int keyMid = (int) Math.ceil(keys.size() / 2.0) - 1;
            int childMid = (int) Math.ceil(children.size() / 2.0);
            int keyIndex = keys.size() - 1;
            // 分割keys
            for (; keyIndex > keyMid; keyIndex--) {
                nextNonLeafNode.addKey(keys.remove(keyIndex));
            }
            // 分割children
            for (int childIndex = children.size() - 1; childIndex >= childMid; childIndex--) {
                Node<K, V> child = children.remove(childIndex);
                child.parent = nextNonLeafNode;
                nextNonLeafNode.addChild(0, child);
            }
            // 分割后中间剩余的key，作为分割后父节点的key（对应右节点最左边的第一个后代kv节点的key值）
            int nextKeyIndex = parent.addKey(keys.remove(keyIndex));
            parent.addChild(nextKeyIndex + 1, nextNonLeafNode);

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

        /**
         * 指向上一个叶子节点
         */
        LeafNode<K, V> prev;

        /**
         * 指向下一个叶子节点
         */
        LeafNode<K, V> next;

        /**
         * 存储叶子节点上的详细键值对
         */
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
         * @return 分割后的父节点
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

        /**
         * 遍历当前叶子节点，找到与之key对应的kv元素
         * degree不是很大的话，List<KeyValuePair>不会很大，没必要再二分查找了，
         *
         * @param key K
         * @return V
         */
        public KeyValuePair<K, V> findKeyValuePair(K key) {
            for (KeyValuePair<K, V> kv : keyValuePairs) {
                if (kv.key.compareTo(key) == 0) {
                    return kv;
                }
            }
            return null;
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