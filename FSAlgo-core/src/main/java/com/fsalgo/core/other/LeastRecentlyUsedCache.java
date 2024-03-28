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

package com.fsalgo.core.other;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2024/3/22 6:10
 * @Description: LRU Cache | 最近最少使用
 */
public class LeastRecentlyUsedCache<K, V> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<K, Node<K, V>> cache;

    private final Node<K, V> head;

    private final Node<K, V> tail;

    private final int capacity;

    public LeastRecentlyUsedCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity cannot be less than 0");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    /**
     * 通过key获取val
     *
     * @param key K
     * @return V
     */
    public V get(K key) {
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        // 使用过的节点移至链表头部
        moveNodeToHead(node);
        return node.val;
    }

    /**
     * 添加键值对
     *
     * @param key K
     * @param val V
     */
    public void put(K key, V val) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            node.val = val;
            moveNodeToHead(node);
            return;
        }
        // 超过最大容量，移除最久未使用的节点
        if (cache.size() >= capacity) {
            removeTailNode();
        }
        // 增添新节点
        Node<K, V> newNode = new Node<>(key, val);
        cache.put(key, newNode);
        addNodeToHead(newNode);
    }

    /**
     * 添加节点到链表头
     *
     * @param node 节点
     */
    private void addNodeToHead(Node<K, V> node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    /**
     * 移除链表末尾节点
     */
    private void removeTailNode() {
        Node<K, V> temp = tail.prev;
        removeNode(temp);
        cache.remove(temp.key);
    }

    /**
     * 从链表中移除节点
     *
     * @param node 节点
     */
    private void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 指定节点移到链表头部
     *
     * @param node 节点
     */
    private void moveNodeToHead(Node<K, V> node) {
        removeNode(node);
        addNodeToHead(node);
    }

    private static class Node<K, V> {
        K key;
        V val;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

}
