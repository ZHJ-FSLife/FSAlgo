package com.fsalgo.core.struct.specific;

import com.fsalgo.core.constant.BaseConstant;
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

    private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

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

        graphMap.get(source).setOutgoing(edge);
        graphMap.get(target).setIncoming(edge);

        edgeSize++;
    }

    @Override
    public void removeEdge(N source, N target) {
        super.removeEdge(source, target);

        int connectEdgeSize = 0;

        EdgeContainer<N> sourceEdgeContainer = graphMap.get(source);
        sourceEdgeContainer.removeAdjacent(target);
        connectEdgeSize = sourceEdgeContainer.getOutgoing().size();
        sourceEdgeContainer.removeOutgoing(target);

        EdgeContainer<N> targetEdgeContainer = graphMap.get(target);


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
