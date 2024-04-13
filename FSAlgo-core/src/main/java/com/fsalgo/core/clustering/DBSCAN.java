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
package com.fsalgo.core.clustering;

import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.tree.vectorspace.NearestNeighborSearch;
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

    private static final long serialVersionUID = 1L;

    private final int density;

    private final double radius;

    private List<SpacePoint<T>> points;

    private NearestNeighborSearch neighborSearch;

    private final Set<SpacePoint<T>> flag = new HashSet<>();

    public DBSCAN(int density, double radius, List<SpacePoint<T>> points) {
        this(density, radius, points, new KDTree(points));
    }

    /**
     * @param density        密度，至少相邻的节点数
     * @param radius         半径
     * @param points         节点集
     * @param neighborSearch 邻近搜索算法（默认使用KD-Tree）
     */
    public DBSCAN(int density, double radius, List<SpacePoint<T>> points, NearestNeighborSearch neighborSearch) {
        this.density = density;
        this.radius = radius;
        this.points = points;
        this.neighborSearch = neighborSearch;
    }

    /**
     * 借坐标集中的所有节点按密度与半径进行划分簇
     *
     * @param data 节点坐标集
     * @return list<cluster>
     */
    @Override
    public Clustering<T> getClustering() {
        List<Set<SpacePoint<T>>> result = new LinkedList<>();
        for (SpacePoint<T> point : points) {
            if (flag.contains(point)) {
                continue;
            }
            Set<SpacePoint<T>> list = search(point);
            if (!list.isEmpty()) {
                result.add(list);
            }
        }
        for (SpacePoint<T> point : points) {
            if (flag.contains(point)) {
                continue;
            }
            result.add(new LinkedHashSet<>() {{
                add(point);
            }});
        }
        return new ClusteringAlgorithm.ClusteringImpl(result);
    }

    /**
     * 指定节点搜寻半径内的节点，其节点数满足密度要求，可划分未一个簇
     *
     * @param source 指定节点坐标
     * @return 簇
     */
    private Set<SpacePoint<T>> search(SpacePoint<T> source) {
        Set<SpacePoint<T>> result = new LinkedHashSet<>();
        Deque<SpacePoint<T>> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            SpacePoint<T> curr = queue.pop();
            List<SpacePoint<T>> searchNode = neighborSearch.range(curr, radius);
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
