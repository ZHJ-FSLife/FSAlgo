package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.EdgeSetFactory;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 3:44
 * @Description:
 */
public class EdgeContainer<N> {

    /**
     * 入边
     * key: 源节点
     * val: 两点之间的边
     */
    Map<N, Set<Edge<N>>> incoming;

    /**
     * 出边
     * key: 目标节点
     * val: 两点之间的边
     */
    Map<N, Set<Edge<N>>> outgoing;

    public EdgeContainer(EdgeSetFactory<N> edgeSetFactory, N node) {
        incoming = edgeSetFactory.createEdgeSet(node);
        outgoing = edgeSetFactory.createEdgeSet(node);
    }

    public void setIncoming(Edge<N> edge) {
        N source = edge.getSource();
        if (!incoming.containsKey(source)) {
            incoming.put(source, new LinkedHashSet<>());
        }
        incoming.get(source).add(edge);
    }

    public void setOutgoing(Edge<N> edge) {
        N target = edge.getTarget();
        if (!outgoing.containsKey(target)) {
            outgoing.put(target, new LinkedHashSet<>());
        }
        outgoing.get(target).add(edge);
    }

    public Map<N, Set<Edge<N>>> getIncoming() {
        return incoming;
    }

    public Map<N, Set<Edge<N>>> getOutgoing() {
        return outgoing;
    }
}
