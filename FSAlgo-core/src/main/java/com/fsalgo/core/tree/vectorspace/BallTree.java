package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/5 22:37
 * @Description:
 */
public class BallTree<T extends Comparable<T>> {

    private Node<T> root;

    private final DistanceMetric distanceMetric;

    public BallTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public BallTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        this.distanceMetric = distanceMetric;
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        root = buildTree(points);
    }

    private Node<T> buildTree(List<SpacePoint<T>> points) {
        SpacePoint<T> center = calcCenter(points);
        double radius = calcRadius(center, points);
        Node<T> node = new Node<>(center, points, radius);
        if (points.size() == 1) {
            return node;
        }
        List<SpacePoint<T>> left = new ArrayList<>();
        List<SpacePoint<T>> right = new ArrayList<>();
        partition(center, points, left, right);
        node.left = buildTree(left);
        node.right = buildTree(right);
        return node;
    }

    private void partition(SpacePoint<T> center, List<SpacePoint<T>> points, List<SpacePoint<T>> left, List<SpacePoint<T>> right) {
        for (SpacePoint<T> point : points) {
            if (distanceMetric.getDistance(center.getCoord(), point.getCoord()) < root.radius) {
                left.add(point);
            } else {
                right.add(point);
            }
        }
    }

    private SpacePoint<T> calcCenter(List<SpacePoint<T>> points) {
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

    private double calcRadius(SpacePoint<T> center, List<SpacePoint<T>> points) {
        double maxDistace = 0;
        for (SpacePoint<T> point : points) {
            double distance = distanceMetric.getDistance(center.getCoord(), point.getCoord());
            maxDistace = Math.max(maxDistace, distance);
        }
        return maxDistace;
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
