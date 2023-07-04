package com.fsalgo.core.interfaces.clusters;

import com.fsalgo.core.constant.BaseConstant;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/7/4 15:57
 * @Description:
 */
public interface Clustering<T> {

    /**
     * 获取簇的数量
     *
     * @return 簇的数量
     */
    int getNumberClusters();

    /**
     * 获取簇
     *
     * @return 簇
     */
    List<Set<T>> getClusters();


    class ClusteringImpl<T> implements ClusteringAlgorithm.Clustering<T>, Serializable {

        private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

        private final List<Set<T>> clusters;

        public ClusteringImpl(List<Set<T>> clusters) {
            this.clusters = clusters;
        }

        @Override
        public int getNumberClusters() {
            return clusters.size();
        }

        @Override
        public List<Set<T>> getClusters() {
            return clusters;
        }
    }
}
