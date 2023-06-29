package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.AbstractBaseGraph;

/**
 * @Author: root
 * @Date: 2023/2/19 23:28
 * @Description:
 */
public class UndirectedGraph<N> extends AbstractBaseGraph<N> {

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
        N source = edge.getSource();
        N target = edge.getTarget();

        addNode(source);
        addNode(target);

        addEdgeContainer(source);
        addEdgeContainer(target);

        Edge<N> reverseEdge = new Edge<>(target, source, edge.getWeight());
        edgeMap.get(source).setOutgoing(edge);
        edgeMap.get(source).setIncoming(reverseEdge);
        nodeMap.get(source).setAdjacent(target);

        edgeMap.get(target).setIncoming(edge);
        edgeMap.get(target).setOutgoing(reverseEdge);
        nodeMap.get(target).setAdjacent(source);

        edgeSize++;
    }

    @Override
    public String getName() {
        return "undirected-graph";
    }
}