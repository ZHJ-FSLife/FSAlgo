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

import com.fsalgo.core.enums.GraphTypeEnum;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.AbstractBaseGraph;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2023/2/19 3:05
 * @Description: 有向图
 */
public class DirectedGraph<N> extends AbstractBaseGraph<N> implements Serializable {

    private static final long serialVersionUID = 1L;

    public DirectedGraph() {
        super();
    }

    /**
     * 添加边
     *
     * @param edge 边
     */
    @Override
    public void addEdge(Edge<N> edge) {
        N source = edge.getSource();
        N target = edge.getTarget();

        addNode(source);
        addNode(target);

        unattainable.remove(target);

        graphMap.get(source).setOutgoing(edge);
        graphMap.get(target).setIncoming(edge);

        edgeSize++;
    }

    @Override
    public void removeEdge(N source, N target) {
        super.removeEdge(source, target);

        int connectEdgeSize = 0;

        EdgeContainer<N> sourceEdgeContainer = graphMap.get(source);
        EdgeContainer<N> targetEdgeContainer = graphMap.get(target);

        if (sourceEdgeContainer.getOutgoing().containsKey(target)) {
            connectEdgeSize += sourceEdgeContainer.getOutgoing().get(target).size();
            sourceEdgeContainer.removeAdjacentAll(target);
        }

        if (targetEdgeContainer.getIncoming().containsKey(source)) {
            connectEdgeSize += targetEdgeContainer.getIncoming().get(source).size();
            targetEdgeContainer.removeAdjacentAll(source);
        }

        addUnattainableNode(source);
        addUnattainableNode(target);

        edgeSize -= connectEdgeSize;
    }

    @Override
    public GraphTypeEnum getGraphType() {
        return GraphTypeEnum.DIRECTED_GRAPH;
    }

    @Override
    public String getName() {
        return "directed-graph";
    }
}
