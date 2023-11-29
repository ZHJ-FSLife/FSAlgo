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
package com.fsalgo.core.graph;

import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.builder.GraphBuilder;
import com.fsalgo.core.struct.specific.EdgeContainer;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/5/26 9:14
 * @Description: 鲁汶算法，社区发现
 */
public class LouvainCommunityDetection<N> {

    private final Graph<N> graph;

    private Map<N, N> communityMap;

    public LouvainCommunityDetection(Graph<N> graph) {
        this.graph = graph;
        initContainer();
        calc();
    }

    private void initContainer() {
        if (Objects.isNull(graph)) {
            return;
        }
        // 初始化每个节点所属的社区
        communityMap = new HashMap<>();
        for (N node : graph.nodes()) {
            communityMap.put(node, node);
        }
    }

    private void calc() {
        boolean changed = true;
        while (changed) {
            changed = false;

            // 局部移动
            for (N node : graph.nodes()) {
                N originalCommunity = communityMap.get(node);
                N bestCommunity = originalCommunity;
                double maxDeltaQ = 0.0D;

                // 尝试将节点移动到相邻社区，计算模块度增益
                for (N adjacentNode : graph.adjacentNodes(node)) {
                    N adjacentCommunity = communityMap.get(adjacentNode);
                    double deltaQ = calcDeltaQ(graph, communityMap, node, originalCommunity, adjacentCommunity);

                    if (deltaQ > maxDeltaQ) {
                        maxDeltaQ = deltaQ;
                        bestCommunity = adjacentCommunity;
                    }
                }

                // 如果模块度增益度大于0，则将节点移动到最佳社区
                if (maxDeltaQ > 0) {
                    communityMap.put(node, bestCommunity);
                    changed = true;
                }
            }

            // 图整体重构
            if (changed) {
                //
            }
        }
    }

    /**
     * @param graph             图
     * @param communityMap      社区map
     * @param node              节点
     * @param originalCommunity 原始社区
     * @param targetCommunity   目标社区
     * @return delta-Q
     */
    private double calcDeltaQ(Graph<N> graph, Map<N, N> communityMap, N node, N originalCommunity, N targetCommunity) {
        double deltaQ;

        // 计算移动节点前的模块度
        double modularityBefore = calcModularity(graph, communityMap);

        // 移动节点到目标社区
        communityMap.put(node, targetCommunity);

        // 计算移动节点后的模块度
        double modularityAfter = calcModularity(graph, communityMap);

        // 计算模块度增益
        deltaQ = modularityAfter - modularityBefore;

        // 还原节点原来所属的社区
        communityMap.put(node, originalCommunity);

        return deltaQ;
    }

    /**
     * 计算图中的模块度
     *
     * @param graph        图
     * @param communityMap 社区map
     * @return 图的模块度
     */
    private double calcModularity(Graph<N> graph, Map<N, N> communityMap) {
        int edgeSize = graph.edgeSize();
        double modularity = 0.0D;

        for (N node : graph.nodes()) {
            N community = communityMap.get(node);
            Set<N> adjacentNodes = graph.adjacentNodes(node);
            for (N adjacentNode : adjacentNodes) {
                N adjacentCommunity = communityMap.get(adjacentNode);
                double mod = community == adjacentCommunity ? 1.0 : 0.0;
                mod -= ((double) adjacentNodes.size() * graph.adjacentNodes(adjacentNode).size()) / (2 * edgeSize);
                modularity += mod;
            }
        }
        modularity /= (2 * edgeSize);
        return modularity;
    }

    /**
     * 重构新图，合并社区为新节点
     *
     * @param graph        图
     * @param communityMap 社区map
     * @return 新图
     */
    private Graph<N> rebuildGraph(Graph<N> graph, Map<N, N> communityMap) {
        Graph<N> newGraph = GraphBuilder.<N>undirected().build();
        Map<N, EdgeContainer<N>> communityNodeMap = new LinkedHashMap<>();

        // 初始化社区的边集合
        for (N node : communityMap.keySet()) {
            N community = communityMap.get(node);
            if (!communityNodeMap.containsKey(community)) {
                communityNodeMap.put(node, new EdgeContainer<>(n -> new LinkedHashMap<>(), node));
            }
        }

        // 构建新图，将合取合并为节点

        return newGraph;
    }
}
