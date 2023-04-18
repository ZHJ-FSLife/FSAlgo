package com.fsalgo.core.tree;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/5 9:15
 * @Description: B+树
 */
public class BPlusTree<T extends Comparable<T>, V> {

    public static class Node<T> {
        boolean leaf;
        List<T> keys;
        List<Node<T>> child;
        Node<T> next;
    }

}











