package com.fsalgo.core.tree;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/6/26 15:18
 * @Description: Aho-Corasick 自动机，多模式匹配
 */
public class AhoCorasick {

    private final Node root;

    public AhoCorasick() {
        this.root = new Node();
        this.root.key = '/';
    }

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

    public List<String> search(String word) {
        Node curr = root;
        List<String> result = new ArrayList<>();
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
                    result.add(getWord(node));
                }
                node = node.failureLink;
            }
        }
        return result;
    }

    private String getWord(Node node) {
        StringBuilder result = new StringBuilder();
        while (node != root) {
            result.insert(0, node.key);
            node = node.parent;
        }
        return result.toString();
    }

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
