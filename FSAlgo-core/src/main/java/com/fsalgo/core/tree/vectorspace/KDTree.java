package com.fsalgo.core.tree.vectorspace;

import com.fsalgo.core.geometrical.Distance;
import com.fsalgo.core.geometrical.DistanceMetric;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/25 21:07
 * @Description:
 */
public class KDTree {

    private final Node root;

    private final int dimentional;

    private final static DistanceMetric DEF_DIST = Distance.EUCLIDEAN;

    public KDTree(List<double[]> coords) {
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
     * @param coords 节点坐标集
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
        // 根据当前节点所在深度，决定应该取哪一维度值对坐标集排序
        int axis = depth % coords.get(0).length;
        coords.sort(Comparator.comparing(n -> n[axis]));

        // 获取中心节点，以该节点分隔左右子节点
        int index = coords.size() / 2;
        Node node = new Node(coords.get(index), depth);
        if (node.coord.length != dimentional) {
            throw new IllegalArgumentException("the new node dimension is inconsistent with the node dimension in the tree!");
        }
        node.left = buildTree(coords.subList(0, index), depth + 1);
        node.right = buildTree(coords.subList(index + 1, coords.size()), depth + 1);

        return node;
    }

    /**
     * 搜索指定坐标最近的点的坐标
     * @param coord 节点坐标
     * @return 距离最近的节点的坐标
     */
    public double[] nearest(double[] coord) {
        if (root == null) {
            throw new IllegalArgumentException("the kd-tree is not built!");
        }
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

    /**
     * 指定节点半径内的左右节点的坐标
     * @param coord 节点坐标
     * @param radius 搜寻半径
     * @return 半径内所有节点的坐标
     */
    public List<double[]> range(double[] coord, double radius) {
        List<double[]> result = new ArrayList<>();
        range(root, coord, radius, 0, result);
        return result;
    }

    private void range(Node node, double[] coord, double radius, int depth, List<double[]> result) {
        if (node == null) {
            return;
        }
        if (DEF_DIST.getDistance(node.coord, coord) <= radius) {
            result.add(node.coord);
        }
        int axis = depth % coord.length;
        if (node.left != null && coord[axis] - radius <= node.coord[axis]) {
            range(node.left, coord, radius, depth + 1, result);
        }
        if (node.right != null && coord[axis] + radius >= node.coord[axis]) {
            range(node.right, coord, radius, depth + 1, result);
        }
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
    }
}
