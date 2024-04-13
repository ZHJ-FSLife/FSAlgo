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
package com.fsalgo.core.struct.specific;

import com.fsalgo.core.other.enums.GraphTypeEnum;
import com.fsalgo.core.struct.Edge;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/26 23:09
 * @Description: 有向无环图
 */
public class DirectedAcyclicGraph<N> extends DirectedGraph<N> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 队列记录入度为0的节点，用于增量式拓朴排序校验
     */
    private Deque<N> queue = new LinkedList<>();

    public DirectedAcyclicGraph() {
        super();
    }

    @Override
    public void addNode(N node) {
        super.addNode(node);
        // 如果该节点入度为0，加入队列
        if (incomingNodes(node).isEmpty()) {
            queue.add(node);
        }

    }

    @Override
    public void addEdge(Edge<N> edge) {
        super.addEdge(edge);
        N target = edge.getTarget();

        if (hasCycle()) {
            throw new IllegalArgumentException("There are loops in the figure！");
        }
    }

    /**
     * 增量式拓扑排序的方式检验是否满足有向无环图
     *
     * @return true or false
     */
    private boolean hasCycle() {
        Set<N> visited = new HashSet<>();
        Map<N, Integer> inDegreeCount = new HashMap<>();

        while (!queue.isEmpty()) {
            N node = queue.poll();
            // 队列中节点可能随着新edge加入而度不为0
            if (!incomingNodes(node).isEmpty()) {
                continue;
            }
            visited.add(node);
            for (N next : outgoingNodes(node)) {
                if (inDegreeCount.containsKey(next)) {

                }
            }

        }

        return visited.size() != nodeSize();
    }

    @Override
    public GraphTypeEnum getGraphType() {
        return GraphTypeEnum.DIRECTED_ACYCLIC_GRAPH;
    }

    @Override
    public String getName() {
        return "directed-acyclic-graph";
    }

}
