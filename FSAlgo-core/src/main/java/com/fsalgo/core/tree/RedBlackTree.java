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

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/23 19:38
 * @Description: 红黑树
 * 1、每个节点都是红色或黑色
 * 2、根节点是黑色
 * 3、每个叶子节点（NIL节点）都是黑色的
 * 4、如果一个节点是红色的，则它的两个子节点都是黑色的
 * 5、对于每个节点，从该节点到其所有后代叶子节点的简单路径上，均包含相同数量的黑色节点
 */
public class RedBlackTree<K extends Comparable<K>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Comparator<? super K> comparator;

    private Node<K> root;

    private Node<K> leaf;

    public RedBlackTree() {
        this(Comparator.naturalOrder());
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
        leaf = new Node<>(null);
        root = leaf;
    }

    public Node<K> getRoot() {
        return this.root;
    }

    public void add(K key) {
        Node<K> node = new Node<>(key, true);
        add(node);
        addFixup(node);
    }

    private void add(Node<K> node) {
        Node<K> temp = leaf;
        Node<K> next = root;

        while (next != leaf) {
            temp = next;
            next = compareTo(node, next) ? next.left : next.right;
        }

        node.parent = temp;
        if (temp == leaf) {
            root = node;
        } else if (compareTo(node, temp)) {
            temp.left = node;
        } else {
            temp.right = node;
        }

        node.left = node.right = leaf;
    }

    private void addFixup(Node<K> node) {
        while (node.parent.red) {
            if (node.parent == node.parent.parent.left) {
                Node<K> uncle = node.parent.parent.right;
                if (uncle.red) {
                    uncle.red = false;
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node<K> uncle = node.parent.parent.left;
                if (uncle.red) {
                    uncle.red = false;
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.red = false;
                    node.parent.parent.red = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.red = false;
    }

    private void leftRotate(Node<K> node) {
        Node<K> temp = node.right;

        node.right = temp.left;
        if (temp.left != leaf) {
            temp.left.parent = node;
        }

        temp.parent = node.parent;

        if (node.parent == leaf) {
            this.root = temp;
        } else {
            if (node.parent.left == node) {
                node.parent.left = temp;
            } else {
                node.parent.right = temp;
            }
        }

        temp.left = node;
        node.parent = temp;
    }

    private void rightRotate(Node<K> node) {
        Node<K> temp = node.left;

        node.left = temp.right;
        if (temp.right != leaf) {
            temp.right.parent = node;
        }

        temp.parent = node.parent;

        if (node.parent == leaf) {
            this.root = temp;
        } else {
            if (node.parent.right == node) {
                node.parent.right = temp;
            } else {
                node.parent.left = temp;
            }
        }

        temp.right = node;
        node.parent = temp;
    }

    private boolean compareTo(Node<K> x, Node<K> y) {
        return compareTo(x.key, y.key);
    }

    public boolean compareTo(K x, K y) {
        return comparator.compare(x, y) > 0;
    }

    public static class Node<K extends Comparable<K>> {
        K key;
        boolean red;
        Node<K> left;
        Node<K> right;
        Node<K> parent;

        public Node(K key) {
            this(key, false);
        }

        public Node(K key, boolean red) {
            this.key = key;
            this.red = red;
        }

        public List<Node<K>> getChild() {
            List<Node<K>> childs = new LinkedList<>();
            if (left != null) {
                childs.add(left);
            }
            if (right != null) {
                childs.add(right);
            }
            return childs;
        }

        public boolean isRed() {
             return red;
        }

        @Override
        public String toString() {
            return key == null ? "nil" : key.toString() + (red ? "R" : "B");
        }
    }
}
