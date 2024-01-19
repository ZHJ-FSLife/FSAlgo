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

    public RedBlackTree() {
        this(Comparator.naturalOrder());
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public Node<K> getRoot() {
        return this.root;
    }

    public void add(K key) {
        add(new Node<>(key, false, null, null, null));
    }

    private void add(Node<K> node) {

        Node<K> temp = null;
        Node<K> next = this.root;

        while (next != null) {
            temp = next;
            next = compareTo(node, next) ? next.left : next.right;
        }
        node.parent = temp;

        if (temp != null) {
            addChild(temp, node);
        } else {
            this.root = node;
        }

        node.red = true;
        addFixup(node);
    }

    private void addFixup(Node<K> node) {
        Node<K> parent;
        Node<K> gparent;

        // 父节点不为空，并且当前节点为红色节点
        while (node != null && node.parent != null && node.red) {
            parent = node.parent;
            gparent = parent.parent;

            Node<K> uncle = parent == gparent.left ? gparent.right : gparent.right;
            if (parent == gparent.left) {
                // 叔节点为红色
                if (uncle != null && uncle.red) {
                    uncle.red = false;
                    parent.red = false;
                    gparent.red = true;
                    node = gparent;
                    continue;
                }
                // 叔节点为黑色， 且当当前节点为右节点
                if (parent.right == node) {
                    rotateLL(parent);
                    swap(node, parent);
                }
                parent.red = false;
                gparent.red = true;
                rotateRR(gparent);
            } else {
                if (uncle != null && uncle.red) {
                    uncle.red = false;
                    parent.red = false;
                    gparent.red = true;
                    node = gparent;
                    continue;
                }
                if (parent.left == node) {
                    rotateRR(parent);
                    swap(node, parent);
                }
                parent.red = false;
                gparent.red = true;
                rotateLL(gparent);
            }
        }
    }

    private void rotateLL(Node<K> node) {
        Node<K> temp = node.right;

        node.right = temp.left;
        if (temp.left != null) {
            temp.left.parent = node;
        }

        temp.parent = node.parent;

        if (node.parent == null) {
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

    private void rotateRR(Node<K> node) {
        Node<K> temp = node.left;

        node.left = temp.right;
        if (temp.right != null) {
            temp.right.parent = node;
        }

        temp.parent = node.parent;

        if (node.parent == null) {
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

    private void addChild(Node<K> parent, Node<K> child) {
        if (compareTo(parent, child)) {
            parent.right = child;
        } else {
            parent.left = child;
        }
    }

    private void swap(Node<K> x, Node<K> y) {
        Node<K> temp = x;
        x = y;
        y = temp;
    }

    private boolean compareTo(Node<K> x, Node<K> y) {
        return compareTo(x.key, y.key);
    }

    public boolean compareTo(K x, K y) {
        return comparator.compare(x, y) < 0;
    }

    public static class Node<K extends Comparable<K>> {
        K key;
        boolean red;
        Node<K> left;
        Node<K> right;
        Node<K> parent;

        public Node(K key, boolean red, Node<K> left, Node<K> right, Node<K> parent) {
            this.key = key;
            this.red = red;
            this.left = left;
            this.right = right;
            this.parent = parent;
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
    }
}
