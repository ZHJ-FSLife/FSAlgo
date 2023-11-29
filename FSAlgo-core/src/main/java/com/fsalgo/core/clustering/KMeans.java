/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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
package com.fsalgo.core.clustering;

import com.fsalgo.core.interfaces.ClusteringAlgorithm;

/**
 * @Author: root
 * @Date: 2023/3/6 9:14
 * @Description: k-means clustering algorithm / K均值聚类
 */
public class KMeans<T extends Comparable<T>> implements ClusteringAlgorithm<T> {

    // 感觉不是很好用，懒得写了。

    @Override
    public Clustering<T> getClustering() {
        return null;
    }

    @Override
    public String getName() {
        return "k-means";
    }

}
