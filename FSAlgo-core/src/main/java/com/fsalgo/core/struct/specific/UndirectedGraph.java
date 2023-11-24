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
        super.removeEdge(source, target);

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