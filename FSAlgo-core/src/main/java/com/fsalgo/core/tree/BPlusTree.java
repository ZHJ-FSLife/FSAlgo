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
        KeyValuePair<K, V> kv = new KeyValuePair<>(key, value);
        leafNode.add(kv);
        if (!leafNode.isFull()) {
            return;
        }

        // 先往当前叶子节点添加新kv节点，添加完了之后如果满了，在考虑开始分裂
        LeafNode<K, V> nextLeafNode = leafNode.split();
        // 开始处理父节点
        // 如果当前叶子节点没有父节点，也就是说还没有开始分裂并构建出root节点来，初始化一个
        if (leafNode.parent == null) {
            root = new NonLeafNode<>(degree);
            leafNode.parent = root;
            nextLeafNode.parent = root;
        }

        int keyIndex = leafNode.parent.addKey(nextLeafNode.getFirstKey());
        // 如果当前父节点是新构建的还不存在其它key，那么子节点list里面没有leafNode
        if (leafNode.parent.keys.size() == 1) {
            leafNode.parent.addChild(keyIndex, leafNode);
        }
        leafNode.parent.addChild(keyIndex + 1, nextLeafNode);

        // 开始处理父节点分裂

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
            if (key.compareTo(keys.get(index)) <= 0) {
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

        @Override
        protected boolean isFull() {
            return keys.size() >= maxDegree();
        }

    }

    class LeafNode<K extends Comparable<K>, V> extends Node<K, V> {

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
         * @return
         */
        public LeafNode<K, V> split() {
            LeafNode<K, V> nextLeafNode = new LeafNode<>(degree);
            //默认先共用一个父节点
            nextLeafNode.parent = this.parent;
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
            return nextLeafNode;
        }

        /**
         * 获取kv集合中第一kv节点的K值，其父节点记录的K索引为子节点的第一个K值
         * @return
         */
        public K getFirstKey() {
            return keyValuePairs.get(0).key;
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

        @Override
        public String toString() {
            return key + ":" + value;
        }
    }


}