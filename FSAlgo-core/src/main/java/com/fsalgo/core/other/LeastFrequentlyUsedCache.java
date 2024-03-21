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

/**
 * @Author: root
 * @Date: 2024/3/22 6:38
 * @Description: LFU Cache | 最近最不常使用
 */
public class LeastFrequentlyUsedCache<K, V> {

    private final int capacity;

    public LeastFrequentlyUsedCache(int capacity) {
        this.capacity = capacity;
    }

    private static class Node<K, V> {
        int frequency;
        K key;
        V val;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.frequency = 1;
        }
    }

}
