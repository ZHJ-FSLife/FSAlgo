package com.fsalgo.core.interfaces.clusters;

import com.fsalgo.core.interfaces.NameEntity;

/**
 * @Author: root
 * @Date: 2023/7/4 15:59
 * @Description: 基于关系网络图节点的聚类算法
 */
public interface GraphNodeClusteringAlgo<N> extends NameEntity {

    Clustering<N> getClustering();
}
