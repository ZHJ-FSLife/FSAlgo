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
package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 14:45
 * @Description: 弗洛伊德多源最短路径
 */
public class FloydWarshallShortestPath<N> implements ShortestPathAlgorithm<N> {

    private final Graph<N> graph;

    private double[][] weight;

    private Map<N, Integer> indexs;

    public FloydWarshallShortestPath(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        weight = new double[graph.nodeSize()][graph.nodeSize()];
    }

    private void calcShortPath() {
        for (N node : graph.nodes()) {
            int nodeIndexs = indexs.get(node);
            for (Edge<N> edge : graph.outgoingEdges(node)) {
                int nextIndexs = indexs.get(edge.getTarget());
                weight[nodeIndexs][nextIndexs] = edge.getWeight();
            }
        }

        // for (N k : graph.nodes()) {
        //     for (N i : graph.nodes()) {
        //         for (N j : graph.nodes()) {
        //
        //         }
        //     }
        // }
    }

    @Override
    public GraphPath<N> getPath(N source, N target) {
        return null;
    }

    @Override
    public double getPathWeight(N source, N target) {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
