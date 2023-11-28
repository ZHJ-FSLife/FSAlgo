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
            add(root, word);
        }
    }

    public void add(Node node, String word) {
        int matchLength = node.matchLength(word);
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
