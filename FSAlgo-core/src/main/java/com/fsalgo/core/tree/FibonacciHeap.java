package com.fsalgo.core.tree;

import java.util.NoSuchElementException;

/**
 * @Author: root
 * @Date: 2022/12/23 19:23
 * @Description: 斐波那契堆 - Fibonacci Heap
 * 简化版，只实现了暂时需要的功能
 * 用于替代 PriorityQueue 所定义的普通堆，均摊复杂度，优化 AStar、dijkstra、prim... 等算法
 * <p>
 * org.jheaps 有现成的类库，就不用就是要自己造轮子就是玩（doge）
 * <p>
 * add -> O(1)
 * peek -> O(1)
 * remove -> O(logN)
 * decreaseKey -> O(1)
 */
public class FibonacciHeap<T extends Comparable<T>> {

    private static final double GOLDEN_RATIO = (1 + Math.sqrt(5)) / 2;

    private Node<T> min;

    private int size = 0;

    private int rootNum = 0;

    /**
     * 添加新元素到堆中，并创建其对应的堆节点
     *
     * @param key 节点元素
     */
    public void add(T key) {
        if (key == null) {
            throw new IllegalArgumentException("the node cannot be empty!");
        }
        addToRootList(new Node<>(key));
        size++;
    }

    /**
     * 将新的根节点添加到最小根节点的左侧
     *
     * @param root 新的根节点
     */
    private void addToRootList(Node<T> root) {
        if (min == null) {
            min = root;
            rootNum = 1;
            return;
        }
        unionNodeToLeft(min, root);

        if (compareTo(root.key, min.key)) {
            min = root;
        }
        rootNum++;

    }

    /**
     * 移除并获取最小根节点
     *
     * @return 最小根节点
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T minKey = min.key;

        if (size == 1) {
            min = null;
            size--;
            rootNum--;
            return minKey;
        }

        Node<T> child = min.child;
        if (child != null) {
            while (child.right != min.child) {
                child.parent = null;
                child = child.right;
            }
        }

        if (rootNum > 1) {
            min.right.left = min.left;
            min.left.right = min.right;
            if (child != null) {
                min.left.right = child;
                min.right.left = child.left;
                child.left.right = min.right;
                child.left = min.left;
            }
            rootNum += min.degree - 1;
        } else {
            rootNum += min.degree - 1;
            min = min.child;
        }
        size--;
        if (min != null) {
            consolidate(min.right);
        }
        return minKey;
    }

    /**
     * 从执行的根节点开始处理，将根列表中degree一致的跟节点，合并成新的堆
     *
     * @param current 当前访问节点
     */
    private void consolidate(Node<T> current) {
        if (current == null) {
            return;
        }
        Node<T>[] bucket = new Node[(int) (Math.floor(Math.log(size) / Math.log(GOLDEN_RATIO)) + 1)];

        int count = rootNum;
        for (int i = 0; i < count; i++) {
            int index = current.degree;
            // 将 degree 一致的根节点两两合并
            while (bucket[index] != null) {
                Node<T> prev = bucket[index];
                bucket[index] = null;
                if (compareTo(prev.key, current.key)) {
                    removeSubHeap(current);
                    union(prev, current);
                    index = prev.degree;
                    current = prev;
                } else {
                    removeSubHeap(prev);
                    union(current, prev);
                    index = current.degree;
                }
                rootNum--;
            }
            bucket[index] = current;
            current = current.right;
        }
        min = findMinOfNewRoots(current);
    }

    /**
     * 从最小跟节点开始，将根列表中degree一致的跟节点，合并成新的堆
     */
    private void consolidate() {
        consolidate(min);
    }

    /**
     * 获取堆顶最小根节点
     *
     * @return 最小根节点
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return min.key;
    }

    /**
     * 从执行根节点位置开始处理，找到新根节点列表中最小的根节点
     *
     * @param root 指定的根节点节点
     * @return 最小根节点
     */
    private Node<T> findMinOfNewRoots(Node<T> root) {
        Node<T> current = root;
        Node<T> minRoot = root;
        while (current.right != root) {
            current = current.right;
            minRoot = compareTo(minRoot.key, current.key) ? minRoot : current;
        }
        return minRoot;
    }

    /**
     * 移除指定的子堆
     *
     * @param root 子堆的根节点
     */
    private void removeSubHeap(Node<T> root) {
        root.left.right = root.right;
        root.right.left = root.left;
    }

    /**
     * 合并节点
     *
     * @param x 节点
     * @param y 节点
     */
    private void union(Node<T> x, Node<T> y) {
        y.left = y.right = y;
        y.parent = x;
        if (x.child == null) {
            x.child = y;
        } else {
            unionNodeToLeft(x.child, y);
        }
        x.degree++;
    }

    /**
     * 将节点 y 添加到节点 x 的左侧
     *
     * @param x 节点
     * @param y 节点
     */
    private void unionNodeToLeft(Node<T> x, Node<T> y) {
        x.left.right = y;
        y.left = x.left;
        y.right = x;
        x.left = y;
    }

    /**
     * 判断当前堆中节点是否为空
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 节点的key值比较 - 默认为小顶堆
     *
     * @return true or false
     */
    public boolean compareTo(T x, T y) {
        return x.compareTo(y) < 0;
    }

    static class Node<T> {
        // 当前节点的关系信息
        T key;
        // 度 - 改节点的子节点数量，但不包含子节点的节点
        int degree;
        // 标记 - 该节点是否有子节点被移除
        // （暂且用不上修改key的功能所有懒得写就没用上该属性）
        boolean mark;
        Node<T> left;
        Node<T> right;
        Node<T> child;
        Node<T> parent;

        public Node(T key) {
            this.key = key;
            this.mark = false;
            this.left = this;
            this.right = this;
        }

        @Override
        public String toString() {
            return key.toString();
        }

        private void mark() {
            if (!isRoot()) {
                this.mark = true;
            }
        }

        private boolean isRoot() {
            return this.parent == null;
        }
    }
}
