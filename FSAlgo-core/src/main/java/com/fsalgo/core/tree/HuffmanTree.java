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

import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.specific.FibonacciHeap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description:
 */
public class HuffmanTree<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Byte LEFT = 0;

    public static final Byte RIGHT = 1;

    private final List<T> content;

    private final Node<T> root;

    public HuffmanTree(List<T> content) {
        this.content = content;
        root = builder(content);
    }

    /**
     * 构建 Huffman 树
     *
     * @param content 原文本集合，例如原字符串大文本，可以按 "词"、"字符" 进行拆解成集合的形式来构建树
     * @return 根节点
     */
    private Node<T> builder(List<T> content) {
        // 统计每个元素出现的频率
        Map<T, Integer> map = new HashMap<>(16);
        for (T curr : content) {
            map.put(curr, map.containsKey(curr) ? map.get(curr) + 1 : 1);
        }

        //构建小顶堆，按出现元素频率放入
        Heap<Node<T>> heap = new FibonacciHeap<>(Comparator.comparingInt(n -> n.count));
        for (T key : map.keySet()) {
            heap.add(new Node<>(key, map.get(key)));
        }

        // 每次以堆中出现频率最小的两个元素合并为一个节点，后放回堆中直至只剩一个节点
        while (heap.size() > 1) {
            Node<T> first = heap.remove();
            Node<T> second = heap.remove();
            heap.add(new Node<>(first, second));
        }
        return heap.remove();
    }

    /**
     * 将原文本集合进行编码，为byte集合的形式存储
     *
     * @return 编码结果
     */
    public List<Byte> encode() {
        // 编码映射表
        Map<T, List<Byte>> map = new HashMap<>(16);
        encode(root, map, new ArrayList<>());

        // 原文本集合转Byte集合
        List<Byte> code = new ArrayList<>();
        for (T key : content) {
            code.addAll(map.get(key));
        }

        return code;
    }

    /**
     * 将原文本集合进行编码，为byte集合的形式存储
     *
     * @param node 递归的每一个树节点
     * @param map  编码映射表
     * @param list 每组编码结果
     */
    private void encode(Node<T> node, Map<T, List<Byte>> map, List<Byte> list) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            map.put(node.key, new ArrayList<>(list));
            return;
        }
        encode(node.left, map, new ArrayList<>(list) {{
            add(LEFT);
        }});
        encode(node.right, map, new ArrayList<>(list) {{
            add(RIGHT);
        }});
    }

    /**
     * 将原文本编码结果解码，还原内容
     *
     * @param code 编码
     * @return 解码原集合内容
     */
    public List<T> decode(List<Byte> code) {
        List<T> result = new ArrayList<>();
        decode(root, code, 0, result);
        return result;
    }

    /**
     * 将原文本编码结果解码，还原内容
     *
     * @param node   递归的每一个树节点
     * @param code   编码内容
     * @param index  已处理的编码集合中索引位置
     * @param result 解码原集合内容
     */
    private void decode(Node<T> node, List<Byte> code, int index, List<T> result) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            result.add(node.key);
            decode(root, code, index, result);
        }
        if (index >= code.size()) {
            return;
        }
        if (LEFT.equals(code.get(index))) {
            decode(node.left, code, index + 1, result);
        }
        if (RIGHT.equals(code.get(index))) {
            decode(node.right, code, index + 1, result);
        }
    }

    /**
     * 树节点
     *
     * @param <T>
     */
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
            // 堆中合并节点时，其当前节点的频率为合并后左右两个子节点的总频率
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
        }
    }
}
