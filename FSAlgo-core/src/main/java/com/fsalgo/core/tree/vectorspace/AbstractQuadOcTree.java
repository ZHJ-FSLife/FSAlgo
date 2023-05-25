package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;

import java.util.ArrayList;
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

    protected AbstractQuadOcTree() {
        this(Distance.EUCLIDEAN);
    }

    protected AbstractQuadOcTree(DistanceMetric distanceMetric) {
        super(distanceMetric);
        this.dimension = getDimension();
        this.childNums = (int) Math.pow(2, this.dimension);
    }

    /**
     * 构建QuadTree
     *
     * @param points 坐标集
     * @return root
     */
    protected Node<T> buildTree(List<SpacePoint<T>> points) {
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return new Node(points.get(0), true);
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
            this(point, leaf, dimension);

        }

        public Node(SpacePoint<T> point, boolean leaf, int dimension) {
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
    }
}
