package com.fsalgo.core.tree;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/27 19:55
 * @Description: 并查集
 */
public class UnionFind<T> {

    /**
     * 记录节点的父级节点（或根节点），初始为节点本身
     */
    private final Map<T, T> parents;

    /**
     * 节点高度
     */
    private final Map<T, Integer> heights;

    public UnionFind() {
        parents = new LinkedHashMap<>();
        heights = new HashMap<>();
    }

    public UnionFind(Set<T> nodes) {

        parents = new LinkedHashMap<>();
        heights = new HashMap<>();

        for (T node : nodes) {
            parents.put(node, node);
            heights.put(node, 0);
        }
    }

    /**
     * 添加节点，并初始其父节点和高度
     */
    public void add(T node) {
        if (parents.containsKey(node)) {
            throw new IllegalArgumentException("the node already exists!");
        }
        parents.put(node, node);
        heights.put(node, 0);
    }

    /**
     * 查找指定节点的根节点
     */
    public T findRoot(T node) {
        if (!parents.containsKey(node)) {
            throw new IllegalArgumentException("the node is not exists!");
        }

        T current = node;
        while (!current.equals(parents.get(current))) {
            current = parents.get(current);
        }

        final T root = current;
        current = node;

        // 压缩树，根节点即是父节点（人话：你祖宗就是你爹）
        // 重点！通过压缩树的高度，减少时间的消耗，保证每次查找时时间复杂度都保持在O(1)
        while (!current.equals(root)) {
            T parent = parents.get(current);
            parents.put(current, root);
            current = parent;
        }

        return current;
    }

    /**
     * 合并节点，将根节点高度最高的根节点作为高度低的节点的父节点，尽可能降低树的高度
     */
    public void union(T node1, T node2) {
        if (!parents.containsKey(node1) || !parents.containsKey(node2)) {
            throw new IllegalArgumentException("the node must exist!");
        }

        T root1 = findRoot(node1);
        T root2 = findRoot(node2);

        if (root1.equals(root2)) {
            return;
        }

        // 比较两个根节点的高度
        if (heights.get(root1) >= heights.get(root2)) {
            parents.put(root2, root1);
            heights.put(root1, heights.get(root2) + 1);
            return;
        }
        parents.put(root1, root2);
        heights.put(root2, heights.get(root1) + 1);
    }

    /**
     * 校验两个节点是否在同一颗树中
     */
    public boolean check(T node1, T node2) {
        if (!parents.containsKey(node1) || !parents.containsKey(node2)) {
            throw new IllegalArgumentException("the node must exist!");
        }

        T root1 = findRoot(node1);
        T root2 = findRoot(node2);

        return root1.equals(root2);
    }

    public int size() {
        return parents.size();
    }

}
