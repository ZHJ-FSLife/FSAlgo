package com.fsalgo.core.clustering;

import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.KDTree;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/6 9:14
 * @Description: Density-Based Spatial Clustering of Applications with Noise / 基于密度的空间聚类
 */
public class DBSCAN implements ClusteringAlgorithm {

    private final int density;

    private final double radius;

    private KDTree kdTree;

    private final Set<double[]> flag = new HashSet<>();

    public DBSCAN(int density, double radius) {
        this.density = density;
        this.radius = radius;
    }

    /**
     * 借坐标集中的所有节点按密度与半径进行划分簇
     * @param coords 节点坐标集
     * @return list<cluster>
     */
    @Override
    public List<List<double[]>> cluster(List<double[]> coords) {
        List<List<double[]>> result = new ArrayList<>();
        kdTree = new KDTree(coords);
        for (double[] coord : coords) {
            if (flag.contains(coord)) {
                continue;
            }
            List<double[]> list = search(coord);
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        for (double[] coord : coords) {
            if (flag.contains(coord)) {
                continue;
            }
            result.add(new LinkedList<>() {{
                add(coord);
            }});
        }
        return result;
    }

    /**
     * 指定节点搜寻半径内的节点，其节点数满足密度要求，可划分未一个簇
     * @param source 指定节点坐标
     * @return 簇
     */
    private List<double[]> search(double[] source) {
        List<double[]> result = new ArrayList<>();
        Deque<double[]> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            double[] curr = queue.pop();
            List<double[]> searchNode = kdTree.range(curr, radius);
            if (searchNode.size() < density) {
                continue;
            }
            for (double[] coord : searchNode) {
                if (flag.contains(coord)) {
                    continue;
                }
                flag.add(coord);
                result.add(coord);
                queue.add(coord);
            }
        }
        return result;
    }
}
