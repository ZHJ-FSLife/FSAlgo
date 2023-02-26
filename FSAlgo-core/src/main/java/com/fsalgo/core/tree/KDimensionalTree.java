package com.fsalgo.core.tree;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/2/25 23:38
 * @Description: 用于处理多维空间中数据节点距离的问题，例如KNN、K-Means、DBSAN...等算法需要计算点与点之间的距离
 */
public class KDimensionalTree {

    public static class Node {
        double[] value;
        Node left;
        Node right;

        public Node(double[] value) {
            this.value = value;
        }

    }

    public double calcDistance(Node source, Node target) {
        double distance = 0.00;
        for (int i = 0; i < source.value.length; i++) {
            distance += Math.pow(source.value[i] - target.value[i], 2);
        }
        return Math.sqrt(distance);
    }

    public void createKDTree(List<double[]> data) {

    }
}
