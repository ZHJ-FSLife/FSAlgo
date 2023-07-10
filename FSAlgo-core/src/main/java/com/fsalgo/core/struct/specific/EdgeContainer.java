package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.EdgeSetFactory;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 3:44
 * @Description: 边容器，创建并存放与节点相邻的边
 */
public class EdgeContainer<N>{

    /**
     * 相邻边
     * key: 相邻节点
     * val: 相邻的边
     */
    Map<N, Set<Edge<N>>> adjacent;

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
        adjacent = edgeSetFactory.createEdgeSet(node);
    }

    protected void setAdjacent(Edge<N> edge, N node) {
        adjacent.putIfAbsent(node, new LinkedHashSet<>());
        adjacent.get(node).add(edge);
    }

    public void setIncoming(Edge<N> edge) {
        N source = edge.getSource();

        incoming.putIfAbsent(source, new LinkedHashSet<>());
        incoming.get(source).add(edge);
        setAdjacent(edge, source);
    }

    public void setOutgoing(Edge<N> edge) {
        N target = edge.getTarget();

        outgoing.putIfAbsent(target, new LinkedHashSet<>());
        outgoing.get(target).add(edge);
        setAdjacent(edge, target);
    }

    public Map<N, Set<Edge<N>>> getAdjacent() {
        return adjacent;
    }

    public Map<N, Set<Edge<N>>> getIncoming() {
        return incoming;
    }

    public Map<N, Set<Edge<N>>> getOutgoing() {
        return outgoing;
    }

    public void removeAdjacent(N node) {
        adjacent.remove(node);
    }

    public void removeIncoming(N node) {
        incoming.remove(node);
    }

    public void removeOutgoing(N node) {
        outgoing.remove(node);
    }
}
