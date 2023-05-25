package com.fsalgo.core.tree.vectorspace.impl;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;
import com.fsalgo.core.util.VectorUtil;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/19 13:17
 * @Description: 四叉树
 */
public class QuadTree<T extends Comparable<T>> extends AbstractQuadOcTree<T> {

    public QuadTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public QuadTree(List<SpacePoint<T>> points, DistanceMetric distanceMetric) {
        super(distanceMetric);
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        root = buildTree(points);
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

    private Node<T> nearest(SpacePoint<T> point, Node<T> center) {
        if (center == null) {
            return null;
        }
        int quadrant = calcCoordinateIndex(center.getPoint().getCoord(), point.getCoord());
        Node<T> child;
        // 如果point所在象限内没有子节点，找下一个象限的其它子节点（只要划分了区域，就必然有一个节点存在）
        while (true) {
            child = center.getChild()[quadrant];
            if (child != null) {
                break;
            }
            quadrant = (quadrant + 1) % childNums;
        }
        if (!child.getLeaf()) {
            child = nearest(point, child);
        }
        // double radius = distanceMetric.getDistance(point.getCoord(), child.point.getCoord());

        return child;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return null;
    }

    /**
     * 公式计算坐标属于第几象限
     * quadrant = ((x / |x|) * 0.5 + 0.5) * (-y / |y|) - ((y / |y|) * 0.5 + 0.5) + 3
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 象限（数组索引）
     */
    @Override
    protected int calcCoordinateIndex(double[] center, double[] target) {
        VectorUtil.checkDims(center, dimension);
        VectorUtil.checkDims(center, target);

        // +0.1是为了避免x或y为0时，将其归到最近的象限上
        double x = target[0] - center[0];
        double y = target[1] - center[1];
        x += x == 0 ? 0.1 : 0;
        y += y == 0 ? 0.1 : 0;
        int quadrant = (int) (((x / Math.abs(x)) * 0.5 + 0.5) * (-y / Math.abs(y)) - ((y / Math.abs(y)) * 0.5 + 0.5) + 3);
        return quadrant - 1;
    }

    @Override
    protected int getDimension() {
        return 2;
    }

}
