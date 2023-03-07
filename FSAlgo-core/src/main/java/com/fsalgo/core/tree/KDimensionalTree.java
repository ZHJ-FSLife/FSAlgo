package com.fsalgo.core.tree;

import com.fsalgo.core.struct.metrics.Distance;
import com.fsalgo.core.struct.metrics.DistanceMetric;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/25 23:38
 * @Description: KD-Tree, 用于处理多维空间中数据节点距离的问题，例如KNN、K-Means、DBSAN...等算法需要计算点与点之间的距离
 */
public class KDimensionalTree {

    private final Node root;

    private final int dimentional;

    private final static DistanceMetric DEF_DIST = Distance.EUCLIDEAN;

    public KDimensionalTree(List<double[]> coords) {
        if (coords.isEmpty()) {
            throw new IllegalArgumentException("node coord cannot be empty!");
        }
        dimentional = coords.get(0).length;
        if (dimentional == 0) {
            throw new IllegalArgumentException("node is at least one-dimensional data!");
        }
        root = buildTree(coords, 0);
    }

    /**
     * 构建KD树
     *
     * @param coords 坐标集
     * @param depth  深度
     * @return root
     */
    private Node buildTree(List<double[]> coords, int depth) {
        if (coords.isEmpty()) {
            return null;
        }

        if (coords.size() == 1) {
            return new Node(coords.get(0), depth);
        }

        int axis = depth % coords.get(0).length;
        Comparator<double[]> comparator = Comparator.comparing(p -> p[axis]);
        coords.sort(comparator);

        int midIndex = coords.size() / 2;
        double[] midCoord = coords.get(midIndex);

        Node node = new Node(midCoord, depth);
        if (node.coord.length != dimentional) {
            throw new IllegalArgumentException("the new node dimension is inconsistent with the node dimension in the tree!");
        }
        node.left = buildTree(coords.subList(0, midIndex), depth + 1);
        node.right = buildTree(coords.subList(midIndex + 1, coords.size()), depth + 1);

        return node;
    }

    /**
     * 搜索指定坐标附近最近的点的坐标
     *
     * @param coord 坐标
     * @return 距离最近的点的坐标
     */
    public double[] nearest(double[] coord) {
        if (coord.length != dimentional) {
            throw new IllegalArgumentException("the new node dimension is inconsistent with the node dimension in the tree!");
        }
        return nearest(root, coord, 0, root.coord);
    }

    private double[] nearest(Node node, double[] coord, int depth, double[] best) {
        if (node == null) {
            return best;
        }

        if (DEF_DIST.getDistance(coord, node.coord) < DEF_DIST.getDistance(coord, best)) {
            best = node.coord;
        }

        int axis = depth % coord.length;
        double diff = coord[axis] - node.coord[axis];
        Node first = diff < 0 ? node.left : node.right;
        Node second = diff < 0 ? node.right : node.left;

        best = nearest(first, coord, depth + 1, best);

        if (Math.pow(diff, 2) < DEF_DIST.getDistance(coord, best)) {
            best = nearest(second, coord, depth + 1, best);
        }

        return best;
    }

    public List<double[]> range(double[] coord, double distance) {
        List<double[]> result = new ArrayList<>();
        range(coord, distance, result);
        return result;
    }

    private void range(double[] coord, double distance, List<double[]> result) {

    }

    public static class Node {
        double[] coord;
        int depth;
        Node left;
        Node right;

        public Node(double[] coord, int depth) {
            this.coord = coord;
            this.depth = depth;
        }

        /*@Override
        public int hashCode() {
            return Objects.hash(Arrays.hashCode(coord), depth);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Node node = (Node) obj;
            return Arrays.equals(coord, node.coord) && depth == node.depth;
        }*/
    }
}
