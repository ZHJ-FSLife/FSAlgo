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

import com.fsalgo.core.interfaces.NameEntity;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/6/26 15:18
 * @Description: Aho-Corasick 自动机，多模式匹配
 * 字典树的基础上，加上fail指针
 */
public class AhoCorasick implements NameEntity, Serializable {

    private static final long serialVersionUID = 1L;

    private final Node root;

    /**
     * 初始化根节点
     */
    public AhoCorasick() {
        this.root = new Node();
        this.root.key = '/';
    }

    /**
     * 添加多个关键字构建字典树
     *
     * @param words 关键字集
     */
    public void add(List<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    /**
     * 添加关键字构建字典树
     *
     * @param word 关键字
     */
    public void add(String word) {
        Node curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.child.containsKey(ch)) {
                curr.child.put(ch, new Node());
            }
            curr.child.get(ch).key = ch;
            curr.child.get(ch).parent = curr;
            curr = curr.child.get(ch);
        }
        curr.end = true;
    }

    /**
     * 搜索包含关键字，并统计其出现频率
     *
     * @param word 字符串
     * @return 关键字集
     */
    public Map<String, Integer> search(String word) {
        Node curr = root;
        Map<String, Integer> result = new HashMap<>();
        for (char ch : word.toCharArray()) {
            while (!curr.child.containsKey(ch) && curr != root) {
                curr = curr.failureLink;
            }
            curr = curr.child.get(ch);
            if (curr == null) {
                curr = root;
            }

            Node node = curr;
            while (node != root) {
                if (node.end) {
                    result.compute(getWord(node), (k, v) -> v == null ? 1 : v + 1);
                }
                node = node.failureLink;
            }
        }
        return result;
    }

    /**
     * 从叶子节点向上遍历获取完整字符串
     *
     * @param node 叶子节点
     * @return 字符串
     */
    private String getWord(Node node) {
        StringBuilder result = new StringBuilder();
        while (node != root) {
            result.insert(0, node.key);
            node = node.parent;
        }
        return result.toString();
    }

    /**
     * 在字典树的基础上构建fail指针
     */
    public void buildFailureLinks() {
        root.failureLink = root;
        Deque<Node> queue = new LinkedList<>(root.child.values());

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.parent == root) {
                curr.failureLink = root;
            }
            for (Node child : curr.child.values()) {
                queue.add(child);

                Node failureLink = curr.failureLink;
                if (failureLink != null && failureLink.child.containsKey(child.key)) {
                    child.failureLink = failureLink.child.get(child.key);
                }
                if (child.failureLink == null) {
                    child.failureLink = root;
                }
            }
        }
    }

    @Override
    public String getName() {
        return "aho-corasick";
    }

    static class Node {
        char key;
        boolean end;
        Node parent;
        Node failureLink;
        Map<Character, Node> child;

        public Node() {
            this.end = false;
            this.failureLink = null;
            this.child = new HashMap<>();
        }

        @Override
        public String toString() {
            return key + ": { 'isEnd' : '" + end + "', 'fail' : '" + failureLink.key + "' }";
        }
    }
}
