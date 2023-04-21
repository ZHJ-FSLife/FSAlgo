package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/25 21:07
 * @Description: K-Dimensional-Tree, 用于处理多维空间中数据节点距离的问题，例如KNN、K-Means、DBSAN...等算法需要计算点与点之间的距离
 */
public class KDTree<T extends Comparable<T>> {

    private final Node<T> root;

    private final DistanceMetric distanceMetric;

    public KDTree(List<SpacePoint<T>> data) {
        this(data, Distance.EUCLIDEAN);
    }

    public KDTree(List<SpacePoint<T>> data, DistanceMetric dist) {
        this.distanceMetric = dist;
        if (data.isEmpty()) {
            throw new IllegalArgumentException("node coord cannot be empty!");
        }
        root = buildTree(data, 0);
    }

    /**
     * 构建KD树
     *
     * @param data  节点坐标集
     * @param depth 深度
     * @return root
     */
    private Node<T> buildTree(List<SpacePoint<T>> data, int depth) {
        if (data.isEmpty()) {
            return null;
        }
        if (data.size() == 1) {
            return new Node<>(data.get(0), depth);
        }
        int axis = depth % data.get(0).getCoord().length;
        data.sort(Comparator.comparing(n -> n.getCoord()[axis]));

        int index = data.size() / 2;
        Node<T> node = new Node<>(data.get(index), depth);
        node.left = buildTree(data.subList(0, index), depth + 1);
        node.right = buildTree(data.subList(index + 1, data.size()), depth + 1);

        return node;
    }

    /**
     * 搜索指定坐标最近的点的坐标
     *
     * @param data 节点坐标
     * @return 距离最近的节点的坐标
     */
    public SpacePoint<T> nearest(SpacePoint<T> data) {
        if (root == null) {
            throw new IllegalArgumentException("the kd-tree is not built!");
        }
        return nearest(root, data, 0, root.data);
    }

    /**
     * 搜索指定坐标最近的点的坐标
     *
     * @param node  节点
     * @param data  节点坐标
     * @param depth 深度
     * @param best  最近坐标
     * @return 最近坐标
     */
    private SpacePoint<T> nearest(Node<T> node, SpacePoint<T> data, int depth, SpacePoint<T> best) {
        if (node == null) {
            return best;
        }
        if (node.data.getPoint() != data.getPoint()) {
            double distance1 = distanceMetric.getDistance(data.getCoord(), node.data.getCoord());
            double distance2 = distanceMetric.getDistance(data.getCoord(), best.getCoord());
            if (distance1 < distance2) {
                best = node.data;
                best.setDistance(distance1);
            }
        }
        int axis = depth % data.getCoord().length;

        double diff = data.getCoord()[axis] - node.data.getCoord()[axis];
        Node<T> first = diff < 0 ? node.left : node.right;
        Node<T> second = diff < 0 ? node.right : node.left;

        best = nearest(first, data, depth + 1, best);

        if (Math.pow(diff, 2) < distanceMetric.getDistance(data.getCoord(), best.getCoord())) {
            best = nearest(second, data, depth + 1, best);
        }
        return best;
    }

    /**
     * 指定节点半径内的左右节点的坐标
     *
     * @param data   节点坐标
     * @param radius 搜寻半径
     * @return 半径内所有节点的坐标
     */
    public List<SpacePoint<T>> range(SpacePoint<T> data, double radius) {
        List<SpacePoint<T>> result = new ArrayList<>();
        range(root, data, radius, 0, result);
        return result;
    }

    /**
     * 指定节点半径内的左右节点的坐标
     *
     * @param node   节点
     * @param data   节点坐标
     * @param radius 搜寻半径
     * @param depth  深度
     * @param result 结果坐标
     */
    private void range(Node<T> node, SpacePoint<T> data, double radius, int depth, List<SpacePoint<T>> result) {
        if (node == null) {
            return;
        }
        if (distanceMetric.getDistance(node.data.getCoord(), data.getCoord()) <= radius) {
            result.add(node.data);
        }
        int axis = depth % data.getCoord().length;
        if (node.left != null && data.getCoord()[axis] - radius <= node.data.getCoord()[axis]) {
            range(node.left, data, radius, depth + 1, result);
        }
        if (node.right != null && data.getCoord()[axis] + radius >= node.data.getCoord()[axis]) {
            range(node.right, data, radius, depth + 1, result);
        }
    }


    public static class Node<T extends Comparable<T>> {
        SpacePoint<T> data;
        int depth;
        Node<T> left;
        Node<T> right;

        public Node(SpacePoint<T> data, int depth) {
            this.data = data;
            this.depth = depth;
        }
    }
}
