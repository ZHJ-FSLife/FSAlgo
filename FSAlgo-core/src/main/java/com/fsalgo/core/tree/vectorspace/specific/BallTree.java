/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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
        SpacePoint<T> farthest = findFarthest(points, center);
        double radius = findRadius(points, center);

        List<SpacePoint<T>> left = new LinkedList<>();
        List<SpacePoint<T>> right = new LinkedList<>();

        for (SpacePoint<T> point : points) {
            double toCenter = distanceMetric.getDistance(point.getCoord(), center.getCoord());
            double toFarthest = distanceMetric.getDistance(point.getCoord(), farthest.getCoord());
            if (toCenter > toFarthest) {
                right.add(point);
            } else {
                left.add(point);
            }
        }
        Node<T> leftNode = buildTree(left);
        Node<T> rightNode = buildTree(right);
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
        SpacePoint<T> farthest = null;
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
        return null;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return null;
    }

    @Override
    public String getName() {
        return "ball-tree";
    }

    public static class Node<T extends Comparable<T>> {
        final SpacePoint<T> center;
        final double radius;
        Node<T> left;
        Node<T> right;

        public Node(SpacePoint<T> center, double radius, Node<T> left, Node<T> right) {
            this.center = center;
            this.radius = radius;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return center.getPoint() == null ? Arrays.toString(center.getCoord()) : center.getPoint().toString();
        }

        public List<Node<T>> getChild() {
            return new LinkedList<>() {{
                add(left);
                add(right);
            }};
        }
    }
}
