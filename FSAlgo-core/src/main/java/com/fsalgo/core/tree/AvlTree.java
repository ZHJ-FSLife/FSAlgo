/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
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
 * @Date: 2023/1/17 13:40
 * @Description: 自平衡二叉树
 */
public class AvlTree<T extends Comparable<T>> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Comparator<? super T> comparator;

    private static final int MAX_HEIGHT_DIFFERENCE = 1;

    public AvlTree() {
        this(Comparator.naturalOrder());
    }

    public AvlTree(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /**
     * 新增节点
     *
     * @param root 树节点
     * @param key  新节点元素
     * @return 返回已插入新节点到树中的根节点
     */
    public Node<T> add(Node<T> root, T key) {
        if (root == null) {
            return new Node<>(key, 1);
        }
        if (compareTo(root.key, key)) {
            root.left = add(root.left, key);
        } else {
            root.right = add(root.right, key);
        }
        root.height = updateHeight(root);
        return rotate(root);
    }

    /**
     * 对不平衡的树进行寻转，先根据左右节点高度来决定应该往哪一个方向寻转
     *
     * @param root 树节点
     * @return 返回已通过循环保持了平横的树
     */
    private Node<T> rotate(Node<T> root) {
        if (isBalance(root)) {
            return root;
        }
        int height = getHeight(root.left) - getHeight(root.right);
        if (height > 0) {
            if (getHeight(root.left) == getHeight(root.left.left) + 1) {
                root = rotateLL(root);
            } else {
                root = rotateRL(root);
            }
        } else {
            if (getHeight(root.right) == getHeight(root.right.right) + 1) {
                root = rotateRR(root);
            } else {
                root = rotateLR(root);
            }
        }

        return root;
    }

    /**
     * 左旋
     *
     * @param root 树节点
     * @return 返回已通过循环保持了平横的树
     */
    private Node<T> rotateLL(Node<T> root) {
        Node<T> head = root.left;
        root.left = head.right;
        head.right = root;

        root.height = updateHeight(root);
        head.height = updateHeight(head);
        return head;
    }

    /**
     * 右旋
     *
     * @param root 树节点
     * @return 返回已通过循环保持了平横的树
     */
    private Node<T> rotateRR(Node<T> root) {
        Node<T> head = root.right;
        root.right = head.left;
        head.left = root;

        root.height = updateHeight(root);
        root.height = updateHeight(head);
        return head;
    }

    /**
     * 先右旋后左旋
     *
     * @param root 树节点
     * @return 返回已通过循环保持了平横的树
     */
    private Node<T> rotateRL(Node<T> root) {
        root.left = rotateRR(root.left);
        return rotateLL(root);
    }

    /**
     * 先左旋后右旋
     *
     * @param root 树节点
     * @return 返回已通过循环保持了平横的树
     */
    private Node<T> rotateLR(Node<T> root) {
        root.right = rotateLL(root.right);
        return rotateRR(root);
    }

    /**
     * 更新树节点的高度
     *
     * @param root 树节点
     * @return 返回已更新高度的树节点
     */
    private int updateHeight(Node<T> root) {
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * 获取树节点的高度
     *
     * @param root 树节点
     * @return 树节点的高度
     */
    public int getHeight(Node<T> root) {
        return root != null ? root.height : 0;
    }

    /**
     * 判断树是否平横
     *
     * @param root 树节点
     * @return true or false
     */
    public boolean isBalance(Node<T> root) {
        return Math.abs(getHeight(root.left) - getHeight(root.right)) <= MAX_HEIGHT_DIFFERENCE;
    }

    /**
     * 比较节点元素大小
     */
    public boolean compareTo(T x, T y) {
        return comparator.compare(x, y) > 0;
    }

    public static class Node<T> {
        T key;
        int height;
        Node<T> left;
        Node<T> right;

        public Node(T key, int height) {
            this.key = key;
            this.height = height;
        }

        public List<Node<T>> getChild() {
            List<Node<T>> childs = new LinkedList<>();
            if (left != null) {
                childs.add(left);
            }
            if (right != null) {
                childs.add(right);
            }
            return childs;
        }

        @Override
        public String toString() {
            return key.toString();
        }
    }
}
