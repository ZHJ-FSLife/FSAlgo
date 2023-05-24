package com.fsalgo.core.tree.vectorspace.impl;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/19 13:17
 * @Description: 四叉树
 */
public class QuadTree<T extends Comparable<T>> extends AbstractQuadOcTree<T> {

    private static final int CHILD_NUMS = 4;

    private final Node<T> root;

    private final DistanceMetric distanceMetric;

    public QuadTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public QuadTree(List<SpacePoint<T>> points, DistanceMetric dist) {
        super();
        this.distanceMetric = dist;
        if (points.isEmpty()) {
            throw new IllegalArgumentException("points cannot be empty!");
        }
        root = buildTree(points);
    }

    /**
     * 构建QuadTree
     *
     * @param points 坐标集
     * @return root
     */
    private Node<T> buildTree(List<SpacePoint<T>> points) {
        if (points.isEmpty()) {
            return null;
        }
        if (points.size() == 1) {
            return new Node(points.get(0), true);
        }

        Node<T> center = findCenter(points);
        List<List<SpacePoint<T>>> child = new ArrayList<>(CHILD_NUMS);
        for (int i = 0; i < CHILD_NUMS; i++) {
            child.add(new ArrayList<>());
        }

        for (SpacePoint<T> point : points) {
            child.get(calcCoordinateIndex(center.getPoint().getCoord(), point.getCoord())).add(point);
        }

        for (int i = 0; i < CHILD_NUMS; i++) {
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
        if (point.getCoord().length != 2) {
            throw new IllegalArgumentException("the point not be tow dimension!");
        }
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
        // 如果point所在象限内没有子节点，找下一个象限的其它子节点
        while (true) {
            child = center.getChild()[quadrant];
            if (child != null) {
                break;
            }
            quadrant = (quadrant + 1) % CHILD_NUMS;
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

    @Override
    protected int getDimension() {
        return 2;
    }

    @Override
    protected int getChildNums() {
        return 4;
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
        if (center.length != dimension || target.length != dimension) {
            throw new IllegalArgumentException("the point not be tow dimension!");
        }
        // +0.1是为了避免x或y为0时，将其归到最近的象限上
        double x = target[0] - center[0];
        double y = target[1] - center[1];
        x = x == 0 ? x + 0.1 : x;
        y = y == 0 ? y + 0.1 : y;
        int quadrant = (int) (((x / Math.abs(x)) * 0.5 + 0.5) * (-y / Math.abs(y)) - ((y / Math.abs(y)) * 0.5 + 0.5) + 3);
        return quadrant - 1;
    }

    private Node<T> findCenter(List<SpacePoint<T>> points) {
        double[] center = new double[2];
        for (SpacePoint<T> point : points) {
            if (point.getCoord().length != 2) {
                throw new IllegalArgumentException("the point not be tow dimension!");
            }
            center[0] += point.getCoord()[0];
            center[1] += point.getCoord()[1];
        }
        center[0] /= points.size();
        center[1] /= points.size();
        return (new Node<T>(new SpacePoint.SpacePointImpl<T>(null, center)));
    }

}
