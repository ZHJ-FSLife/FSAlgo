package com.fsalgo.core.clustering;

import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/6 9:14
 * @Description: k-means clustering algorithm / K均值聚类
 */
public class KMeans<T extends Comparable<T>> implements ClusteringAlgorithm<T> {

    // 感觉不是很好用，懒得写了。

    @Override
    public List<List<SpacePoint<T>>> cluster(List<SpacePoint<T>> coords) {
        return null;
    }

    @Override
    public String getName() {
        return "k-means";
    }

}
