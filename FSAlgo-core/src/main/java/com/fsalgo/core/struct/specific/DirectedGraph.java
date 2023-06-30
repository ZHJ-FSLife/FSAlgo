package com.fsalgo.core.struct.specific;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.AbstractBaseGraph;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2023/2/19 3:05
 * @Description:
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

        addEdgeContainer(source);
        addEdgeContainer(target);

        edgeMap.get(source).setOutgoing(edge);
        nodeMap.get(source).setAdjacent(target);
        nodeMap.get(source).setOutgoing(target);

        edgeMap.get(target).setIncoming(edge);
        nodeMap.get(target).setAdjacent(source);
        nodeMap.get(target).setIncoming(source);

        edgeSize++;
    }

    @Override
    public String getName() {
        return "directed-graph";
    }
}
