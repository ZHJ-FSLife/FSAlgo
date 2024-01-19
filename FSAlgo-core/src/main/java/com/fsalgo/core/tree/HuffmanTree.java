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
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description:
 */
public class HuffmanTree<T extends Comparable<T>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int LEFT = 0;

    private static final int RIGHT = 1;

    public static class Node<T> {
        T key;
        int count;
        Node<T> left;
        Node<T> right;

        public Node(T key, int count) {
            this.key = key;
            this.count = count;
        }

        public Node(Node<T> left, Node<T> right) {
            this.left = left;
            this.right = right;
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
        }
    }

    /**
     * 构建huffman数
     *
     * @param content 字符串文本内容
     * @return huffman树
     */
    public Node<T> createHuffmanTree(T[] content) {
        Map<T, Integer> map = new HashMap<>();
        for (T key : content) {
            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
        }

        PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.count)
        );
        for (T key : map.keySet()) {
            priorityQueue.add(new Node<>(key, map.get(key)));
        }

        // 开始构建huffman编码，每两个为一组
        int index = 0;
        Node<T> left = null;
        while (priorityQueue.size() > 1) {
            index++;
            left = index == 1 ? priorityQueue.remove() : left;
            if (index == 2) {
                priorityQueue.add(new Node<>(left, priorityQueue.remove()));
                index = 0;
                left = null;
            }
        }
        priorityQueue.add(new Node<>(left, priorityQueue.remove()));
        return priorityQueue.remove();
    }

    /**
     * 遍历huffman tree的叶子结点全路径，向左为0，向右为1
     *
     * @param root huffman树
     * @param map  huffman编码表
     * @param path huffman叶子节点全路径
     */
    public void huffmanCode(Node<T> root, Map<T, String> map, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            map.put(root.key, path);
        } else {
            huffmanCode(root.left, map, path + "0");
            huffmanCode(root.right, map, path + "1");
        }
    }

    /**
     * huffman编码解码，还原字符串文本
     *
     * @param root   huffman树
     * @param code   huffman编码
     * @param result huffman编码解码后文本
     */
    public void huffmanDecode(Node<T> tree, Node<T> root, List<Byte> code, int index, StringBuilder result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.append(root.key);
            // 从根节点重新开始向下搜索
            huffmanDecode(tree, tree, code, index, result);
        }
        if (index >= code.size()) {
            return;
        }
        if (code.get(index) == LEFT) {
            huffmanDecode(tree, root.left, code, index + 1, result);
        }
        if (code.get(index) == RIGHT) {
            huffmanDecode(tree, root.right, code, index + 1, result);
        }
    }
}
