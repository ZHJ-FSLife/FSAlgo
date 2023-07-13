package com.fsalgo.core.struct.specific;

import com.fsalgo.core.constant.BaseConstant;
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
    protected void removeEdge() {
        // edgeSize -= 1;
    }

    @Override
    public String getName() {
        return "undirected-graph";
    }
}