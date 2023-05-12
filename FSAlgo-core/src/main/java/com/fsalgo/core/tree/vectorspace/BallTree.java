package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;

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
