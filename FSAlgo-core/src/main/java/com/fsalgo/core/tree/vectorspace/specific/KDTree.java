package com.fsalgo.core.tree.vectorspace.specific;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractNearestNeighborSearch;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/25 21:07
 * @Description: K-Dimensional-Tree, 用于处理多维空间中数据节点距离的问题，例如KNN、K-Means、DBSAN...等算法需要计算点与点之间的距离
 */
public class KDTree<T extends Comparable<T>> extends AbstractNearestNeighborSearch<T> {

    private final Node<T> root;

    public KDTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public KDTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(distanceMetric);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        root = buildTree(points, 0);
    }

    /**
     * 构建KD树
     *
     * @param points 节点坐标集
     * @param depth  深度
     * @return root
     */
    private Node<T> buildTree(List<SpacePoint<T>> points, int depth) {
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return new Node<>(points.get(0), depth);
        }
        int axis = depth % points.get(0).getCoord().length;
        points.sort(Comparator.comparing(n -> n.getCoord()[axis]));

        int index = points.size() / 2;
        Node<T> node = new Node<>(points.get(index), depth);
        node.left = buildTree(points.subList(0, index), depth + 1);
        node.right = buildTree(points.subList(index + 1, points.size()), depth + 1);

        return node;
    }

    /**
     * 搜索指定坐标最近的点的坐标
     *
     * @param point 节点坐标
     * @return 距离最近的节点的坐标
     */
    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        if (root == null) {
            throw new IllegalArgumentException("the kd-tree is not built!");
        }
        return nearest(root, point, 0, root.point);
    }

    /**
     * 搜索指定坐标最近的点的坐标
     *
     * @param node  节点
     * @param point 节点坐标
     * @param depth 深度
     * @param best  最近坐标
     * @return 最近坐标
     */
    private SpacePoint<T> nearest(Node<T> node, SpacePoint<T> point, int depth, SpacePoint<T> best) {
        if (node == null) {
            return best;
        }
        if (best.getPoint().compareTo(point.getPoint()) == 0) {
            best = node.point;
        } else {
            double distance1 = distanceMetric.getDistance(point.getCoord(), node.point.getCoord());
            double distance2 = distanceMetric.getDistance(point.getCoord(), best.getCoord());
            if (distance1 < distance2) {
                best = node.point;
            }
        }
        int axis = depth % point.getCoord().length;

        double diff = point.getCoord()[axis] - node.point.getCoord()[axis];
        Node<T> first = diff < 0 ? node.left : node.right;
        Node<T> second = diff < 0 ? node.right : node.left;

        best = nearest(first, point, depth + 1, best);

        if (Math.pow(diff, 2) < distanceMetric.getDistance(point.getCoord(), best.getCoord())) {
            best = nearest(second, point, depth + 1, best);
        }
        return best;
    }

    /**
     * 指定节点半径内的左右节点的坐标
     *
     * @param point  节点坐标
     * @param radius 搜寻半径
     * @return 半径内所有节点的坐标
     */
    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        List<SpacePoint<T>> result = new ArrayList<>();
        range(root, point, radius, 0, result);
        return result;
    }

    /**
     * 指定节点半径内的左右节点的坐标
     *
     * @param node   节点
     * @param point  节点坐标
     * @param radius 搜寻半径
     * @param depth  深度
     * @param result 结果坐标
     */
    private void range(Node<T> node, SpacePoint<T> point, double radius, int depth, List<SpacePoint<T>> result) {
        if (node == null) {
            return;
        }
        if (distanceMetric.getDistance(node.point.getCoord(), point.getCoord()) <= radius) {
            result.add(node.point);
        }
        int axis = depth % point.getCoord().length;
        if (point.getCoord()[axis] - radius <= node.point.getCoord()[axis]) {
            range(node.left, point, radius, depth + 1, result);
        }
        if (point.getCoord()[axis] + radius >= node.point.getCoord()[axis]) {
            range(node.right, point, radius, depth + 1, result);
        }
    }


    public static class Node<T extends Comparable<T>> {
        SpacePoint<T> point;
        int depth;
        Node<T> left;
        Node<T> right;

        public Node(SpacePoint<T> point, int depth) {
            this.point = point;
            this.depth = depth;
        }
    }
}
