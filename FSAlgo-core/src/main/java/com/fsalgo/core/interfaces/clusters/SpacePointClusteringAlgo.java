package com.fsalgo.core.interfaces.clusters;

import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.List;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/7/4 15:59
 * @Description: 基于多维空间坐标点的聚类算法
 */
public interface SpacePointClusteringAlgo<T> {

    List<Set<SpacePoint<T>>> getClustering();
}
