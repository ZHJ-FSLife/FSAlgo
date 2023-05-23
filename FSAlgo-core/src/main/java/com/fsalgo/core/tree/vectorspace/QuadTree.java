package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.math.geometrical.Distance;
import com.fsalgo.core.math.geometrical.DistanceMetric;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/19 13:17
 * @Description: 四叉树
 */
public class QuadTree<T extends Comparable<T>> {

    private static final int CHILD_NUMS = 4;

    private final Node<T> root;

    private final DistanceMetric distanceMetric;

    public QuadTree(List<SpacePoint<T>> points) {
        this(points, Distance.EUCLIDEAN);
    }

    public QuadTree(List<SpacePoint<T>> points, DistanceMetric dist) {
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
            return new Node<>(points.get(0), true);
        }

        Node<T> center = findCenter(points);
        List<List<SpacePoint<T>>> child = new ArrayList<>(CHILD_NUMS);
        for (int i = 0; i < CHILD_NUMS; i++) {
            child.add(new ArrayList<>());
        }

        for (SpacePoint<T> point : points) {
            child.get(calcQuadrant(center.point.getCoord(), point.getCoord())).add(point);
        }

        for (int i = 0; i < CHILD_NUMS; i++) {
            center.child[i] = buildTree(child.get(i));
        }
        return center;
    }

    /**
     * 搜索距离目标节点最近节点的坐标
     *
     * @param point 目标节点
     * @return 最近节点
     */
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        if (point.getCoord().length != 2) {
            throw new IllegalArgumentException("the point not be tow dimension!");
        }
        Node<T> node = nearest(point, root);
        return node.point;
    }

    private Node<T> nearest(SpacePoint<T> point, Node<T> center) {
        if (center == null) {
            return null;
        }
        int quadrant = calcQuadrant(center.point.getCoord(), point.getCoord());
        Node<T> child;
        // 如果point所在象限内没有子节点，找下一个象限的其它子节点
        while (true) {
            child = center.child[quadrant];
            if (child != null) {
                break;
            }
            quadrant = (quadrant + 1) % CHILD_NUMS;
        }
        if (!child.leaf) {
            child = nearest(point, child);
        }
        double radius = distanceMetric.getDistance(point.getCoord(), child.point.getCoord());

        return child;
    }

    /**
     * 公式计算坐标属于第几象限
     * quadrant = ((x / |x|) * 0.5 + 0.5) * (-y / |y|) - ((y / |y|) * 0.5 + 0.5) + 3
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 象限（数组索引）
     */
    private int calcQuadrant(double[] center, double[] target) {
        if (center.length != 2 || target.length != 2) {
            throw new IllegalArgumentException("the point not be tow dimension!");
        }
        // +0.1是为了避免x或y为0时，将其归到最近的象限上
        double x = target[0] - center[0] + 0.1;
        double y = target[1] - center[1] + 0.1;
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

    public static class Node<T extends Comparable<T>> {
        // 点坐标信息
        SpacePoint<T> point;
        // [第一象限, 第二象限, 第三象限, 第四象限]
        Node<T>[] child;
        // 是否是叶子节点
        boolean leaf;

        public Node(SpacePoint<T> point) {
            this(point, false);
        }

        public Node(SpacePoint<T> point, boolean leaf) {
            this.point = point;
            this.leaf = leaf;
            if (!leaf) {
                child = new Node[CHILD_NUMS];
            }
        }

    }

}
