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
 * @Date: 2024/3/22 6:38
 * @Description: LFU Cache | 最近最不常使用
 * 实现方案：双哈希表+双链表
 * get、put 时间复杂度：O(1)
 */
public class LeastFrequentlyUsedCache<K, V> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int minFreq;

    private final int capacity;

    /**
     * 通过key快速定位节点，只有由节点前后指针操作当前所在双链表
     */
    private final Map<K, Node<K, V>> keyMap;

    /**
     * key为使用频率，不同频率维护一组双向链表
     */
    private final Map<Integer, DoublyLinkedList<K, V>> freqMap;

    public LeastFrequentlyUsedCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("The capacity cannot be less than 0");
        }
        this.minFreq = 0;
        this.capacity = capacity;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    /**
     * 通过key获取val
     *
     * @param key K
     * @return V
     */
    public V get(K key) {
        if (!keyMap.containsKey(key)) {
            return null;
        }
        Node<K, V> node = keyMap.get(key);
        V val = node.val;
        update(key, val, node);
        return val;
    }

    /**
     * 添加节点
     *
     * @param key K
     * @param val V
     */
    public void put(K key, V val) {
        if (!keyMap.containsKey(key)) {
            // 如果超出容量大小，移除访问最不频繁的freqMap中的最后一个
            if (keyMap.size() == capacity) {
                Node<K, V> node = freqMap.get(minFreq).getTail();
                keyMap.remove(node.key);
                freqMap.get(minFreq).remove(node);

                if (freqMap.get(minFreq).size == 0) {
                    freqMap.remove(minFreq);
                }
            }
            DoublyLinkedList<K, V> list = freqMap.getOrDefault(1, new DoublyLinkedList<>());
            list.addFirst(new Node<>(key, val, 1));
            freqMap.put(1, list);
            keyMap.put(key, freqMap.get(1).getHead());
            minFreq = 1;
            return;
        }
        Node<K, V> node = keyMap.get(key);
        update(key, val, node);
    }

    private void update(K key, V val, Node<K, V> node) {
        int frequency = node.frequency;
        freqMap.get(frequency).remove(node);
        if (freqMap.get(frequency).size == 0) {
            freqMap.remove(frequency);
            if (minFreq == frequency) {
                minFreq++;
            }
        }
        DoublyLinkedList<K, V> list = freqMap.getOrDefault(frequency + 1, new DoublyLinkedList<>());
        list.addFirst(new Node<>(key, val, frequency + 1));
        freqMap.put(frequency + 1, list);
        keyMap.put(key, freqMap.get(frequency + 1).getHead());
    }

    private static class Node<K, V> {
        K key;
        V val;
        int frequency;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this(null, null, 1);
        }

        public Node(K key, V val, int frequency) {
            this.key = key;
            this.val = val;
            this.frequency = frequency;
        }
    }

    private static class DoublyLinkedList<K, V> {
        int size;
        Node<K, V> head;
        Node<K, V> tail;

        public DoublyLinkedList() {
            this.size = 0;
            this.head = new Node<>();
            this.tail = new Node<>();
            this.head.next = tail;
            this.tail.prev = head;
        }

        public void addFirst(Node<K, V> node) {
            node.next = head.next;
            node.next.prev = node;
            node.prev = head;
            head.next = node;
            size++;
        }

        public void remove(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        public Node<K, V> getHead() {
            return head.next;
        }

        public Node<K, V> getTail() {
            return tail.prev;
        }
    }

}
