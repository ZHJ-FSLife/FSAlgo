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

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.enums.GraphTypeEnum;
import com.fsalgo.core.struct.Edge;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/3/26 23:09
 * @Description: 有向无环图
 */
public class DirectedAcyclicGraph<N> extends DirectedGraph<N> implements Serializable {

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

    private final Map<N, Integer> indegree = new HashMap<>();

    public DirectedAcyclicGraph() {
        super();
    }

    @Override
    public void addNode(N node) {
        super.addNode(node);
        indegree.putIfAbsent(node, 0);
    }

    @Override
    public void addEdge(Edge<N> edge) {
        super.addEdge(edge);
        N target = edge.getTarget();

        indegree.put(target, indegree.get(target) + 1);
        if (hasCycle()) {
            throw new IllegalArgumentException("There are loops in the figure！");
        }
    }

    /**
     * 拓朴排序检查是否存在环路（但是每次插入边时，都要检查一遍，比较耗时）
     *
     * @return true or false
     */
    private boolean hasCycle() {
        Set<N> visited = new HashSet<>();
        Deque<N> zeroIndegree = new LinkedList<>();
        for (N node : indegree.keySet()) {
            if (indegree.get(node) == 0) {
                zeroIndegree.offer(node);
            }
        }
        while (!zeroIndegree.isEmpty()) {
            N node = zeroIndegree.poll();
            visited.add(node);
            for (N next : outgoingNodes(node)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    zeroIndegree.offer(next);
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
