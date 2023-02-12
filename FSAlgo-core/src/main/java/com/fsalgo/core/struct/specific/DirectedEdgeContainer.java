package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.EdgeSetFactory;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/1/13 9:00
 * @Description:
 */
public class DirectedEdgeContainer<N> {

    Set<Edge<N>> incoming;
    Set<Edge<N>> outgoing;

    public DirectedEdgeContainer(EdgeSetFactory<N> edgeSetFactory, N node) {
        incoming = edgeSetFactory.createEdgeSet(node);
        outgoing = edgeSetFactory.createEdgeSet(node);
    }

    public void addIncomingEdge(Edge<N> edge) {
        incoming.add(edge);
    }

    public void addOutgoingEdge(Edge<N> edge) {
        outgoing.add(edge);
    }

    public void removeIncomingEdge(Edge<N> edge) {
        incoming.remove(edge);
    }

    public void removeOutgoingEdge(Edge<N> edge) {
        outgoing.remove(edge);
    }
}
