/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/11/27 14:35
 * @Description:
 */
public class RadixTree implements NameEntity {

    private Node root;

    private int size = 0;

    public RadixTree() {
        this.root = new Node();
        this.root.key = "";
    }

    public void add(List<String> words) {
        for (String word : words) {
            add(word);
        }
    }

    public void add(String word) {
        add(root, word);
    }

    private void add(Node node, String word) {
        // int matchLength = node.matchLength(word);
        // if (node == null)
    }

    public int size() {
        return this.size;
    }

    static class Node {
        String key;
        boolean end;
        List<Node> child;

        public Node() {
            end = false;
            child = new ArrayList<>();
        }

        public int matchLength(String key) {
            int length = 0;
            while (length < key.length() && length < this.key.length()) {
                if (key.charAt(length) != this.key.charAt(length)) {
                    break;
                }
                length++;
            }
            return length;
        }

        @Override
        public String toString() {
            return key;
        }
    }

    @Override
    public String getName() {
        return "radix-tree";
    }
}
