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

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/26 11:08
 * @Description: 字典树
 */
public class TrieTree {

    private final Node root;

    public TrieTree() {
        this.root = new Node();
    }

    /**
     * 添加字符串关键字
     *
     * @param word 字符串关键字
     */
    public void add(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.child.containsKey(ch)) {
                node.child.put(ch, new Node());
            }
            node = node.child.get(ch);
        }
        node.end = true;
    }

    /**
     * 关键字符串是否存在
     *
     * @param word 关键字符串
     * @return true or false
     */
    public boolean contains(String word) {
        Node node = root;
        for (char ch : word.toCharArray()) {
            if (!node.child.containsKey(ch)) {
                return false;
            }
            node = node.child.get(ch);
        }
        return node.end;
    }

    /**
     * 关键字前缀是否存在
     *
     * @param prefix 关键字前缀
     * @return true or false
     */
    public boolean containsKey(String prefix) {
        Node node = root;
        for (char ch : prefix.toCharArray()) {
            if (!node.child.containsKey(ch)) {
                return false;
            }
            node = node.child.get(ch);
        }
        return true;
    }

    static class Node {
        boolean end;
        Map<Character, Node> child;

        public Node() {
            this.end = false;
            this.child = new HashMap<>();
        }

    }
}
