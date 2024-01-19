/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.fsalgo.core.interfaces;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.interfaces.NameEntity;
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

    /**
     * 获取聚类
     *
     * @param <T>
     * @return Clustering<T>
     */
    <T> Clustering<T> getClustering();

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

        private static final long serialVersionUID = 1L;

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
