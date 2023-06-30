package com.fsalgo.core.clustering;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.specific.KDTree;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/6 9:14
 * @Description: Density-Based Spatial Clustering of Applications with Noise / 基于密度的空间聚类
 */
public class DBSCAN<T extends Comparable<T>> implements ClusteringAlgorithm<T>, Serializable {

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

    private final int density;

    private final double radius;

    private KDTree kdTree;

    private final Set<SpacePoint<T>> flag = new HashSet<>();

    public DBSCAN(int density, double radius) {
        this.density = density;
        this.radius = radius;
    }

    /**
     * 借坐标集中的所有节点按密度与半径进行划分簇
     *
     * @param data 节点坐标集
     * @return list<cluster>
     */
    @Override
    public List<List<SpacePoint<T>>> cluster(List<SpacePoint<T>> data) {
        List<List<SpacePoint<T>>> result = new ArrayList<>();
        kdTree = new KDTree<>(data);
        for (SpacePoint<T> node : data) {
            if (flag.contains(node)) {
                continue;
            }
            List<SpacePoint<T>> list = search(node);
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        for (SpacePoint<T> node : data) {
            if (flag.contains(node)) {
                continue;
            }
            result.add(new LinkedList<>() {{
                add(node);
            }});
        }
        return result;
    }

    /**
     * 指定节点搜寻半径内的节点，其节点数满足密度要求，可划分未一个簇
     *
     * @param source 指定节点坐标
     * @return 簇
     */
    private List<SpacePoint<T>> search(SpacePoint<T> source) {
        List<SpacePoint<T>> result = new ArrayList<>();
        Deque<SpacePoint<T>> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            SpacePoint<T> curr = queue.pop();
            List<SpacePoint<T>> searchNode = kdTree.range(curr, radius);
            if (searchNode.size() < density) {
                continue;
            }
            for (SpacePoint<T> node : searchNode) {
                if (flag.contains(node)) {
                    continue;
                }
                flag.add(node);
                result.add(node);
                queue.add(node);
            }
        }
        return result;
    }

    @Override
    public String getName() {
        return "dbscan";
    }
}
