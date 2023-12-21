/*
 * FSAlgo
 * hhttps://github.com/ZHJ-FSLife/FSAlgo
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
        this.root.setKey("");
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
        int matchLength = node.matchLength(word);
        if (node == null) {
            node.setKey(word);
        }
    }

    public int size() {
        return this.size;
    }

    static class Node {

        boolean end;
        List<Character> key;
        List<Node> child;

        public Node() {
            end = false;
            key = new ArrayList<>();
            child = new ArrayList<>();
        }

        public void setKey(String key) {
            for (int i=0; i<key.length(); i++) {
                this.key.add(key.charAt(i));
            }
        }

        public void setKey(List<Character> key) {
            this.key = key;
        }

        public int matchLength(String key) {
            int length = 0;
            while (length < key.length() && length < this.key.size()) {
                if (key.charAt(length) != this.key.get(length)) {
                    break;
                }
                length++;
            }
            return length;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }

    @Override
    public String getName() {
        return "radix-tree";
    }
}
