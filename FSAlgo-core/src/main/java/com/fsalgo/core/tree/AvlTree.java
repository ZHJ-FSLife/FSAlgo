package com.fsalgo.core.tree;

/**
 * @Author: root
 * @Date: 2023/1/17 13:40
 * @Description: 自平衡二叉树
 */
public class AvlTree<T extends Comparable<T>> {

    private static final int MAX_HEIGHT_DIFFERENCE = 1;

    static class Node<T> {
        T key;
        int height;
        Node<T> left;
        Node<T> right;

        public Node(T key, int height) {
            this.key = key;
            this.height = height;
        }
    }
}
