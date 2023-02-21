package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.NodeSetFactory;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 2:42
 * @Description:
 */
public class NodeContainer<N> {

    Set<N> adjacent;
    Set<N> incoming;
    Set<N> outgoing;

    public NodeContainer(NodeSetFactory<N> nodeSetFactory, N node) {
        adjacent = nodeSetFactory.createNodeSet(node);
        incoming = nodeSetFactory.createNodeSet(node);
        outgoing = nodeSetFactory.createNodeSet(node);
    }

    public void setAdjacent(N node) {
        adjacent.add(node);
    }

    public void setIncoming(N node) {
        incoming.add(node);
    }

    public void setOutgoing(N node) {
        outgoing.add(node);
    }

    public Set<N> getAdjacent() {
        return adjacent;
    }

    public Set<N> getIncoming() {
        return incoming;
    }

    public Set<N> getOutgoing() {
        return outgoing;
    }
}
