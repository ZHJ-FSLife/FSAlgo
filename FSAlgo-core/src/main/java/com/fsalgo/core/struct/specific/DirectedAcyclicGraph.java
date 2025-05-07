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

import com.fsalgo.core.iterator.graph.DepthFirstIterator;
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

    public DirectedAcyclicGraph() {
        super();
    }

    @Override
    public void addNode(N node) {
        super.addNode(node);
    }

    @Override
    public void addEdge(Edge<N> edge) {
        boolean hasSource = containsNode(edge.getSource());
        boolean hasTarget = containsNode(edge.getTarget());
        super.addEdge(edge);
        if (hasCycle(edge)) {
            removeEdge(edge);
            if (!hasSource) {
                removeNode(edge.getSource());
            }
            if (!hasTarget) {
                removeNode(edge.getTarget());
            }
            throw new IllegalArgumentException("There are loops in the figure！");
        }
    }

    /**
     *
     * 深度搜索的方式检验是否满足有向无环图
     *
     * @param edge 新增的edge
     * @return true or false
     */
    private boolean hasCycle(Edge<N> edge) {
        Iterator<N> dfsIterator = new DepthFirstIterator<>(this, edge.getTarget());
        while(dfsIterator.hasNext()) {
            if (Objects.equals(dfsIterator.next(), edge.getSource())) {
                return true;
            }
        }
        return false;
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
