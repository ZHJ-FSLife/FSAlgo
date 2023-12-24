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
package com.fsalgo.core.tree.vectorspace.specific;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractNearestNeighborSearch;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Node<T> getRoot() {
        return root;
    }

    /**
     * 构建ball-tree
     *
     * @param points 坐标集
     * @return root
     */
    private Node<T> buildTree(List<SpacePoint<T>> points) {
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return new Node<>(points.get(0), 0, null, null);
        }

        SpacePoint<T> center = findCenter(points);
        SpacePoint<T> farthestLeft = findFarthest(points, center);
        SpacePoint<T> farthestRight = findFarthest(points, farthestLeft);

        List<SpacePoint<T>> left = new LinkedList<>();
        List<SpacePoint<T>> right = new LinkedList<>();

        for (SpacePoint<T> point : points) {
            double toLeft = distanceMetric.getDistance(point.getCoord(), farthestLeft.getCoord());
            double toRight = distanceMetric.getDistance(point.getCoord(), farthestRight.getCoord());
            if (toLeft < toRight) {
                left.add(point);
            } else {
                right.add(point);
            }
        }
        Node<T> leftNode = buildTree(left);
        Node<T> rightNode = buildTree(right);

        double radius = distanceMetric.getDistance(center.getCoord(), farthestLeft.getCoord());

        return new Node<>(center, radius, leftNode, rightNode);
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
     * 找坐标集中里中心点最远的点
     *
     * @param points 坐标集
     * @param center 中心点
     * @return 最远点
     */
    private SpacePoint<T> findFarthest(List<SpacePoint<T>> points, SpacePoint<T> center) {
        double radius = 0;
        SpacePoint<T> farthest = center;
        for (SpacePoint<T> point : points) {
            double distance = distanceMetric.getDistance(center.getCoord(), point.getCoord());
            if (distance > radius) {
                radius = distance;
                farthest = point;
            }
        }
        return farthest;
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
        if (root == null) {
            throw new IllegalArgumentException("the ball-tree is not built!");
        }
        return nearest(point, root, null);
    }

    private SpacePoint<T> nearest(SpacePoint<T> point, Node<T> node, SpacePoint<T> best) {
        if (node == null) {
            return best;
        }
        double dist = distanceMetric.getDistance(point.getCoord(), node.point.getCoord());
        if (node.leaf) {
            if (best == null) {
                best = node.point;
                return best;
            }
            double toBest = distanceMetric.getDistance(point.getCoord(), best.getCoord());
            best = dist < toBest ? node.point : best;
        }
        if (node.left != null && dist <= node.radius + node.left.radius) {
            nearest(point, node.left, best);
        }
        if (node.right != null && dist <= node.radius + node.right.radius) {
            nearest(point, node.right, best);
        }

        return best;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        List<SpacePoint<T>> result = new ArrayList<>();
        range(root, point, radius, result);
        return result;
    }

    private void range(Node<T> node, SpacePoint<T> point, double radius, List<SpacePoint<T>> result) {
        if (node == null) {
            return;
        }
        double dist = distanceMetric.getDistance(point.getCoord(), node.point.getCoord());
        if (node.leaf && dist <= radius) {
            result.add(node.point);
        }
        if (node.left != null && dist <= node.radius + node.left.radius) {
            range(node.left, point, radius, result);
        }
        if (node.right != null && dist <= node.radius + node.right.radius) {
            range(node.right, point, radius, result);
        }
    }

    @Override
    public String getName() {
        return "ball-tree";
    }

    public static class Node<T extends Comparable<T>> {
        final SpacePoint<T> point;
        final double radius;
        Node<T> left;
        Node<T> right;
        boolean leaf;

        public Node(SpacePoint<T> point, double radius, Node<T> left, Node<T> right) {
            this.point = point;
            this.radius = radius;
            this.left = left;
            this.right = right;
            this.leaf = point.getPoint() != null;
        }

        @Override
        public String toString() {
            return leaf ? point.getPoint().toString() : Arrays.toString(point.getCoord());
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
    }
}
