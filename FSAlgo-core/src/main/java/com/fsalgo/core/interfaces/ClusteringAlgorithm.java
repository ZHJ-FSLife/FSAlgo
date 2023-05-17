package com.fsalgo.core.interfaces;

import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/26 0:12
 * @Description: 聚类算法
 */
public interface ClusteringAlgorithm<T> extends NameEntity {

    List<List<SpacePoint<T>>> cluster(List<SpacePoint<T>> coords);

}
