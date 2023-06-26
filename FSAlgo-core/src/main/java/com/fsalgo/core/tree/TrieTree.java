package com.fsalgo.core.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/26 11:08
 * @Description:
 */
public class TrieTree {

    private final Node root;

    public TrieTree() {
        this.root = new Node();
    }

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
