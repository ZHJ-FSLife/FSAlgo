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
import java.util.Comparator;
import java.util.LinkedList;
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

    public Node<T> getRoot() {
        return root;
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
        if (node == null || node.point.getPoint().equals(point.getPoint())) {
            return best;
        }
        if (best.getPoint().equals(point.getPoint())) {
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

    @Override
    public String getName() {
        return "k-dimensional-tree";
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
