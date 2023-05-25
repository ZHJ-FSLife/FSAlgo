package com.fsalgo.core.tree.vectorspace.impl;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractNearestNeighborSearch;
import com.fsalgo.core.tree.vectorspace.NearestNeighborSearch;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/5 22:37
 * @Description:
 */
public class BallTree<T extends Comparable<T>> extends AbstractNearestNeighborSearch<T> {

    private final Node<T> root;

    public BallTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public BallTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(distanceMetric);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        root = buildTree(points);
    }

    /**
     * 构建ball-tree
     *
     * @param points 坐标集
     * @return root
     */
    private Node<T> buildTree(List<SpacePoint<T>> points) {
        SpacePoint<T> center = findCenter(points);
        double radius = findRadius(points, center);
        Node<T> node = new Node<>(center, points, radius);
        if (points.size() == 1) {
            return node;
        }
        List<SpacePoint<T>> left = new LinkedList<>();
        List<SpacePoint<T>> right = new LinkedList<>();
        partition(points, left, right, center);
        node.left = buildTree(left);
        node.right = buildTree(right);
        return node;
    }

    /**
     * 将坐标集从中心点开始划分出左右两个范围
     *
     * @param points 坐标集
     * @param left   左坐标集
     * @param right  右坐标集
     * @param center 中心点
     */
    private void partition(List<SpacePoint<T>> points, List<SpacePoint<T>> left, List<SpacePoint<T>> right, SpacePoint<T> center) {
        for (SpacePoint<T> point : points) {
            double distance = distanceMetric.getDistance(point.getCoord(), center.getCoord());
            if (distance < root.radius) {
                left.add(point);
            } else {
                right.add(point);
            }
        }
    }

    /**
     * 找到坐标集中的中心点
     *
     * @param points 坐标集
     * @return 中心点
     */
    private SpacePoint<T> findCenter(List<SpacePoint<T>> points) {
        int dimension = points.get(0).getCoord().length;
        double[] center = new double[dimension];
        for (SpacePoint<T> point : points) {
            for (int i = 0; i < dimension; i++) {
                center[i] += point.getCoord()[i];
            }
        }
        for (int i = 0; i < dimension; i++) {
            center[i] /= points.size();
        }
        return new SpacePoint.SpacePointImpl<>(null, center);
    }

    /**
     * 找到中心点的最大半径
     *
     * @param points 坐标集
     * @param center 中心点
     * @return 半径
     */
    private double findRadius(List<SpacePoint<T>> points, SpacePoint<T> center) {
        double radius = 0;
        for (SpacePoint<T> point : points) {
            double distance = distanceMetric.getDistance(center.getCoord(), point.getCoord());
            radius = Math.max(distance, radius);
        }
        return radius;
    }

    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        return null;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return null;
    }

    public static class Node<T extends Comparable<T>> {
        final SpacePoint<T> center;
        final List<SpacePoint<T>> points;
        final double radius;
        Node<T> left;
        Node<T> right;

        public Node(SpacePoint<T> center, List<SpacePoint<T>> points, double radius) {
            this.center = center;
            this.points = points;
            this.radius = radius;
        }
    }
}
