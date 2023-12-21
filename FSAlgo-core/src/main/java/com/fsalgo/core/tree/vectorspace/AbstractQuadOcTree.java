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
package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.util.VectorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/5/24 10:19
 * @Description:
 */
public abstract class AbstractQuadOcTree<T extends Comparable<T>> extends AbstractNearestNeighborSearch<T> {

    protected Node<T> root;

    protected int dimension;
    protected int childNums;

    protected AbstractQuadOcTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(distanceMetric);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        this.dimension = getDimension();
        this.childNums = (int) Math.pow(2, this.dimension);
        root = buildTree(points);
    }

    public Node<T> getRoot() {
        return root;
    }

    /**
     * 构建树结构
     *
     * @param points 坐标集
     * @return root
     */
    protected Node<T> buildTree(List<SpacePoint<T>> points) {
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return new Node<>(points.get(0), true);
        }

        Node<T> center = findCenter(points);
        List<List<SpacePoint<T>>> child = new ArrayList<>(childNums);
        for (int i = 0; i < childNums; i++) {
            child.add(new ArrayList<>());
        }

        for (SpacePoint<T> point : points) {
            child.get(calcCoordinateIndex(center.getPoint().getCoord(), point.getCoord())).add(point);
        }

        for (int i = 0; i < childNums; i++) {
            center.getChild()[i] = buildTree(child.get(i));
        }
        return center;
    }

    /**
     * 搜索距离目标节点最近节点的坐标
     *
     * @param point 目标节点
     * @return 最近节点
     */
    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        VectorUtil.checkDims(point.getCoord(), dimension);

        Node<T> node = nearest(point, root);
        if (node == null) {
            return null;
        }
        return node.getPoint();
    }

    protected Node<T> nearest(SpacePoint<T> point, Node<T> center) {
        if (center == null || center.getLeaf()) {
            return center;
        }
        int areaIndex = calcCoordinateIndex(center.getPoint().getCoord(), point.getCoord());
        Node<T> child;
        // 如果point所在象限内没有子节点，找下一个象限的其它子节点（只要划分了区域，就必然有一个节点存在）
        while (true) {
            child = center.getChild()[areaIndex];
            if (child != null) {
                break;
            }
            areaIndex = (areaIndex + 1) % childNums;
        }
        if (!child.getLeaf()) {
            child = nearest(point, child);
        }

        double radius = distanceMetric.getDistance(point.getCoord(), child.getPoint().getCoord());
        for (int i = 0; i < 2 * dimension; i++) {
            double[] temp = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                int index = i % dimension;
                if (index != j) {
                    temp[j] = point.getCoord()[j];
                    continue;
                }
                if (i % 2 == 0) {
                    temp[index] = point.getCoord()[index] - radius;
                } else {
                    temp[index] = point.getCoord()[index] + radius;
                }
            }
            int involvedAreaIndex = calcCoordinateIndex(center.getPoint().getCoord(), temp);
            Node<T> next = center.getChild()[involvedAreaIndex];
            Node<T> involvedArea = nearest(point, next);
            if (involvedArea == null) {
                continue;
            }
            double nextRadius = distanceMetric.getDistance(point.getCoord(), involvedArea.getPoint().getCoord());
            if (nextRadius < radius) {
                child = involvedArea;
            }
        }

        return child;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return new ArrayList<>();
    }

    /**
     * 计算目标节点坐标位于子节点的具体坐标位置（二维：四象限；三维：八分体）
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 索引
     */
    protected abstract int calcCoordinateIndex(double[] center, double[] target);

    /**
     * 获取中心坐标位置
     *
     * @param points 节点集
     * @return 中心坐标
     */
    protected Node<T> findCenter(List<SpacePoint<T>> points) {
        double[] center = new double[dimension];
        for (SpacePoint<T> point : points) {
            if (point.getCoord().length != dimension) {
                throw new IllegalArgumentException("inconsistent dimensions of points!");
            }
            for (int i = 0; i < dimension; i++) {
                center[i] += point.getCoord()[i];
            }
        }
        for (int i = 0; i < dimension; i++) {
            center[i] /= points.size();
        }
        return (new Node<T>(new SpacePoint.SpacePointImpl<T>(null, center)));
    }

    /**
     * 获取维度
     *
     * @return 维度
     */
    protected abstract int getDimension();

    public class Node<T extends Comparable<T>> {
        // 点坐标信息
        private final SpacePoint<T> point;

        // 是否为叶子节点
        private final boolean leaf;

        // 子节点，数组索引对应二维坐标系中的四象限，和三维坐标系中的八分体
        private Node<T>[] child;

        public Node(SpacePoint<T> point) {
            this(point, false);
        }

        public Node(SpacePoint<T> point, boolean leaf) {
            this.point = point;
            this.leaf = leaf;
            if (!leaf) {
                child = new Node[childNums];
            }
        }

        public SpacePoint<T> getPoint() {
            return point;
        }

        public Node<T>[] getChild() {
            return child;
        }

        public boolean getLeaf() {
            return leaf;
        }

        @Override
        public String toString() {
            return Arrays.toString(point.getCoord());
        }
    }
}
