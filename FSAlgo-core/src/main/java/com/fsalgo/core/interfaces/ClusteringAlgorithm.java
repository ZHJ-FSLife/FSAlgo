package com.fsalgo.core.interfaces;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/3/26 0:12
 * @Description: 聚类算法
 */
public interface ClusteringAlgorithm<T> extends NameEntity {

    List<List<SpacePoint<T>>> getClustering(List<SpacePoint<T>> data);

    Clustering<T> getClustering();

    interface Clustering<T> {

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
    }

    class ClusteringImpl<T> implements Clustering<T>, Serializable {

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
