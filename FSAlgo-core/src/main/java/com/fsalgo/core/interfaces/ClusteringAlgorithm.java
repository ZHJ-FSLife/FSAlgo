package com.fsalgo.core.interfaces;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/3/26 0:12
 * @Description: 聚类算法
 */
public interface ClusteringAlgorithm {

    List<List<double[]>> cluster(List<double[]> coords);

}
