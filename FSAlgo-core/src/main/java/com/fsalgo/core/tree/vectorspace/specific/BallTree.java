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

import java.util.*;

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

        // 取中心坐标，根绝每个节点到中心坐标的距离排序
        double[] center = findCenter(points);
        points.sort(Comparator.comparingDouble(n -> distanceMetric.getDistance(center, n.getCoord())));

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

        SpacePoint<T> center = points.get(0);
        SpacePoint<T> farthestLeft = points.get(points.size() - 1);
        SpacePoint<T> farthestRight = findFarthest(points, farthestLeft);

        List<SpacePoint<T>> left = new LinkedList<>();
        List<SpacePoint<T>> right = new LinkedList<>();

        for (SpacePoint<T> point : points) {
            if (center.getPoint().equals(point.getPoint())) {
                continue;
            }
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

    private double[] findCenter(List<SpacePoint<T>> points) {
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
        return center;
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

    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        if (root == null) {
            throw new IllegalArgumentException("the ball-tree is not built!");
        }
        return nearest(point, root, root.point);
    }

    private SpacePoint<T> nearest(SpacePoint<T> point, Node<T> node, SpacePoint<T> best) {
        if (node == null || node.point.getPoint().equals(point.getPoint())) {
            return best;
        }
        double distance1 = distanceMetric.getDistance(point.getCoord(), node.point.getCoord());
        double distance2 = distanceMetric.getDistance(point.getCoord(), best.getCoord());
        if (distance1 < distance2) {
            best = node.point;
        }
        if (node.left != null) {
            best = nearest(point, node.left, best);
        }
        if (node.right != null) {
            best = nearest(point, node.right, best);
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
        if (dist <= radius) {
            result.add(node.point);
        }
        if (dist <= node.radius + radius) {
            range(node.left, point, radius, result);
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

        public Node(SpacePoint<T> point, double radius, Node<T> left, Node<T> right) {
            this.point = point;
            this.radius = radius;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return point.getPoint().toString();
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
