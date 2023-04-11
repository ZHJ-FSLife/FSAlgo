package com.fsalgo.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description: B-树
 */
public class BTree<T extends Comparable<T>> {

    /**
     * 度，B树的阶数为: 2 * degree - 1
     */
    private final int degree;

    private Node<T> root;

    public BTree() {
        this(2);
    }

    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node<>();
    }

    public void add(T key) {
        Node<T> temp = root;
        if (temp.keys.size() < 2 * degree - 1) {
            add(temp, key);
            return;
        }
        Node<T> node = new Node<>();
        root = node;
        node.leaf = false;
        node.child.add(temp);
        split(node, temp, 0);
        add(node, key);
    }

    private void add(Node<T> node, T key) {
        int index = node.keys.size() - 1;
        if (node.leaf) {
            // node节点添加一个空元素，并将前面元素向后移一位，直到适合放入key元素的位置为止
            node.keys.add(null);
            while (index >= 0 && compareTo(node.keys.get(index), key)) {
                node.keys.set(index + 1, node.keys.get(index));
                index--;
            }
            node.keys.set(index + 1, key);
            return;
        }
        // 如果不是非叶子节点，则找到对应位置的子节点位置
        while (index >= 0 && compareTo(node.keys.get(index), key)) {
            index--;
        }
        index++;
        Node<T> child = node.child.get(index);
        // 如果子节点已满，则分裂子节点
        if (child.keys.size() >= 2 * degree - 1) {
            split(node, child, index);
            if (!compareTo(node.keys.get(index), key)) {
                index++;
            }
        }
        add(node.child.get(index), key);
    }

    private void split(Node<T> node, Node<T> childNode, int index) {
        Node<T> temp = new Node<>();
        temp.leaf = childNode.leaf;
        for (int i = 0; i < degree - 1; i++) {
            temp.keys.add(childNode.keys.remove(degree));
        }
        if (!childNode.leaf) {
            for (int i = 0; i < degree; i++) {
                temp.child.add(childNode.child.remove(degree));
            }
        }
        node.child.add(index + 1, temp);
        node.keys.add(index, childNode.keys.remove(degree - 1));
    }

    /**
     * 比较节点元素大小
     */
    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    public static class Node<T> {
        boolean leaf;
        List<T> keys;
        List<Node<T>> child;

        public Node() {
            this.leaf = true;
            this.keys = new ArrayList<>();
            this.child = new ArrayList<>();
        }
    }
}