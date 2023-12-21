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

/**
 * @Author: root
 * @Date: 2023/2/19 23:28
 * @Description: 无向图
 */
public class UndirectedGraph<N> extends DirectedGraph<N> implements Serializable {

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

    public UndirectedGraph() {
        super();
    }

    /**
     * 添加边
     *
     * @param edge 边
     */
    @Override
    public void addEdge(Edge<N> edge) {
        super.addEdge(edge);

        // 无向图在有向图的基础上添加一条反向的边
        Edge<N> inversion = edge.inversion();
        graphMap.get(edge.getSource()).setIncoming(inversion);
        graphMap.get(edge.getTarget()).setOutgoing(inversion);
    }

    @Override
    public void removeEdge(N source, N target) {
        // super.removeEdge(source, target);

        EdgeContainer<N> sourceEdgeContainer = graphMap.get(source);
        EdgeContainer<N> targetEdgeContainer = graphMap.get(target);

        int connectEdgeSize = sourceEdgeContainer.getOutgoing().size();

        sourceEdgeContainer.removeAdjacent(target);
        sourceEdgeContainer.removeIncoming(target);
        sourceEdgeContainer.removeOutgoing(target);

        targetEdgeContainer.removeAdjacent(source);
        targetEdgeContainer.removeIncoming(source);
        targetEdgeContainer.removeOutgoing(source);

        edgeSize -= connectEdgeSize;
    }

    @Override
    public GraphTypeEnum getGraphType() {
        return GraphTypeEnum.UNDIRECTED_GRAPH;
    }

    @Override
    public String getName() {
        return "undirected-graph";
    }
}