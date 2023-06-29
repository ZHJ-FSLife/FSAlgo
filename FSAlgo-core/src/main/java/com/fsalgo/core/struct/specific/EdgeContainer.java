package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.EdgeSetFactory;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 3:44
 * @Description:
 */
public class EdgeContainer<N> {

    /**
     * 入边
     */
    Set<Edge<N>> incoming;

    /**
     * 出边
     */
    Set<Edge<N>> outgoing;

    public EdgeContainer(EdgeSetFactory<N> edgeSetFactory, N node) {
        incoming = edgeSetFactory.createEdgeSet(node);
        outgoing = edgeSetFactory.createEdgeSet(node);
    }

    public void setIncoming(Edge<N> edge) {
        incoming.add(edge);
    }

    public void setOutgoing(Edge<N> edge) {
        outgoing.add(edge);
    }

    public Set<Edge<N>> getIncoming() {
        return incoming;
    }

    public Set<Edge<N>> getOutgoing() {
        return outgoing;
    }
}
