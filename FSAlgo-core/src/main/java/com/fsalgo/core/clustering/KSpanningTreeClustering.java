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

import com.fsalgo.core.graph.spanningtree.PrimMinimumSpanningTree;
import com.fsalgo.core.interfaces.ClusteringAlgorithm;
import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.UnionFind;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/6/30 15:39
 * @Description: K生成树聚类
 * 缺点：
 * 1.对噪声和异常值敏感：
 * 对噪声和异常值比较敏感。由于该算法是基于生成树的聚类方法，噪声或异常值可能导致生成的树结构产生偏移或不稳定。
 * 2.需要指定K值：
 * 需要预先指定簇的数量K。但在实际应用中，确定合适的K值可能是一个挑战。如果选择不合适的K值，可能会导致聚类结果不理想
 * 3.对数据分布的假设：
 * 假设数据点在每个簇中具有类似于生成树的分布。然而，对于非均匀分布的数据或具有复杂形状的簇，该算法可能无法准确地划分数据。
 * 4.计算复杂度高：
 * 生成树的构建和数据点的分配过程涉及计算相似度或距离矩阵，并进行迭代计算。在大规模数据集上，算法的计算复杂度可能较高。
 * 5.无法处理高维数据：
 * 在处理高维数据时可能遇到困难。在高维空间中，数据点之间的距离度量变得复杂，导致聚类结果可能不准确。
 */
public class KSpanningTreeClustering<N extends Comparable<N>> implements ClusteringAlgorithm<N>, Serializable {

    private static final long serialVersionUID = 1L;

    private Graph<N> graph;

    private int k;

    public KSpanningTreeClustering(Graph<N> graph, int k) {
        this.graph = graph;
        if (k < 1 || k > graph.nodeSize()) {
            throw new IllegalArgumentException("Illegal number of clusters");
        }
        this.k = k;
    }


    @Override
    public Clustering<N> getClustering() {

        // 获取最小生成树
        SpanningTreeAlgorithm<N> primMst = new PrimMinimumSpanningTree<>(graph);

        UnionFind<N> unionFind = new UnionFind<>(graph.nodes());
        List<Edge<N>> edges = new ArrayList<>(primMst.getSpanningTree().getEdges());
        edges.sort(Comparator.comparingDouble(Edge::getWeight));

        for (Edge<N> edge : edges) {
            if (unionFind.numberOfSets() == k) {
                break;
            }
            N source = edge.getSource();
            N target = edge.getTarget();
            if (unionFind.check(source, target)) {
                continue;
            }
            unionFind.union(source, target);
        }

        Map<N, Set<N>> clusterMap = new LinkedHashMap<>();
        for (N node : graph.nodes()) {
            N curr = unionFind.findRoot(node);
            clusterMap.putIfAbsent(curr,  new LinkedHashSet<>());
            clusterMap.get(curr).add(node);
        }

        return new ClusteringAlgorithm.ClusteringImpl<>(new LinkedList<>(clusterMap.values()));
    }

    @Override
    public String getName() {
        return "K-SpanningTree Clustering";
    }
}
