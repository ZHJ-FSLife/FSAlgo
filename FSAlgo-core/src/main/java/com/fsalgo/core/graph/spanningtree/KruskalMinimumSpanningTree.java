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
package com.fsalgo.core.graph.spanningtree;

import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.UnionFind;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最小生成树 - 克鲁斯卡尔算法
 */
public class KruskalMinimumSpanningTree<N> implements SpanningTreeAlgorithm<N>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Graph<N> graph;

    public KruskalMinimumSpanningTree(Graph<N> graph) {
        this.graph = Objects.requireNonNull(graph, "graph cannot be null");
    }

    @Override
    public SpanningTree<N> getSpanningTree() {

        if (graph.nodeSize() <= 1) {
            throw new IllegalArgumentException("there must be at least two nodes in the graph!");
        }

        double spanningTreeWeight = 0d;
        Set<Edge<N>> minimumSpanningTreeEdgeSet = new HashSet<>();

        List<Edge<N>> edges = new ArrayList<>(graph.edges());
        edges.sort(Comparator.comparingDouble(Edge::getWeight));
        UnionFind<N> unionFind = new UnionFind<>(graph.nodes());

        for (Edge<N> edge : edges) {
            N source = edge.getSource();
            N target = edge.getTarget();

            if (unionFind.check(source, target)) {
                continue;
            }

            unionFind.union(source, target);
            minimumSpanningTreeEdgeSet.add(edge);
            spanningTreeWeight += edge.getWeight();
        }

        return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
    }

    @Override
    public String getName() {
        return "kruskal";
    }

}
