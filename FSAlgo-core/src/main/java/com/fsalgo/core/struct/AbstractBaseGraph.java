package com.fsalgo.core.struct;

import com.fsalgo.core.struct.specific.EdgeContainer;
import com.fsalgo.core.util.TypeUtil;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/19 2:28
 * @Description: 图接口的基本结构与实现
 */
public abstract class AbstractBaseGraph<N> extends AbstractGraph<N> implements Graph<N> {

    protected int edgeSize = 0;

    /**
     * 图中节点与边的映射关系（邻接多重表）
     */
    protected Map<N, EdgeContainer<N>> graphMap;

    protected AbstractBaseGraph() {
        graphMap = new LinkedHashMap<>();
    }

    /**
     * 添加结点
     *
     * @param node 节点
     */
    @Override
    public void addNode(N node) {
        if (containsNode(node)) {
            return;
        }
        graphMap.put(node, new EdgeContainer<>(n -> new LinkedHashMap<>(), node));
    }

    /**
     * 添加边
     *
     * @param source 源节点
     * @param target 目标节点
     */
    @Override
    public void addEdge(N source, N target) {
        addEdge(new Edge<>(source, target));
    }

    /**
     * 添加边
     *
     * @param source 源节点
     * @param target 目标节点
     * @param weight 权重值
     */
    @Override
    public void addEdge(N source, N target, double weight) {
        addEdge(new Edge<>(source, target, weight));
    }

    /**
     * 从图中移除指定节点（包含起相连的所有边）
     *
     * @param node 节点
     */
    @Override
    public void removeNode(N node) {
        for (N incomingNode : incomingNodes(node)) {
            graphMap.get(incomingNode).removeOutgoing(node);
            graphMap.get(incomingNode).removeAdjacent(node);
        }
        for (N outgoingNode : outgoingNodes(node)) {
            graphMap.get(outgoingNode).removeIncoming(node);
            graphMap.get(outgoingNode).removeAdjacent(node);
        }
        graphMap.remove(node);
    }

    /**
     * 移除图中指定的边
     *
     * @param edge 边
     */
    @Override
    public void removeEdge(Edge<N> edge) {
        removeEdge(edge.getSource(), edge.getTarget(), edge);
    }

    /**
     * 移除图中两节点间所有的边
     *
     * @param source 源节点
     * @param target 目标节点
     */
    @Override
    public void removeEdge(N source, N target) {
        if (!hasEdgeConnecting(source, target)) {
            return;
        }
    }

    /**
     * 移除图中两节点间指定的一条边
     *
     * @param source 源节点
     * @param target 目标节点
     * @param edge   边
     */
    @Override
    public void removeEdge(N source, N target, Edge<N> edge) {
        if (!hasEdgeConnecting(source, target)) {
            return;
        }
        EdgeContainer<N> sourceEdgeContainer = graphMap.get(source);
        sourceEdgeContainer.getAdjacent().get(target).remove(edge);
        sourceEdgeContainer.getOutgoing().get(target).remove(edge);

        EdgeContainer<N> targetEdgeContainer = graphMap.get(target);
        targetEdgeContainer.getAdjacent().get(source).remove(edge);
        targetEdgeContainer.getIncoming().get(source).remove(edge);

        edgeSize--;

    }

    /**
     * 获取源节点指向目标节点所有边
     *
     * @param source 源节点
     * @param target 目标节点
     * @return 返回源节点指向目标节点所有的边
     */
    @Override
    public Set<Edge<N>> getEdge(N source, N target) {
        if (!hasEdgeConnecting(source, target)) {
            throw new IllegalArgumentException("The source node is not directly adjacent to the destination node！");
        }
        return graphMap.get(source).getOutgoing().get(target);
    }

    /**
     * 图中所有的节点
     *
     * @return 返回图中所有的节点
     */
    @Override
    public Set<N> nodes() {
        return new LinkedHashSet<>(graphMap.keySet());
    }

    /**
     * 图中所有的边
     *
     * @return 返回图中所有的边
     */
    @Override
    public Set<Edge<N>> edges() {
        Set<Edge<N>> allEdge = new LinkedHashSet<>();
        for (N node : graphMap.keySet()) {
            allEdge.addAll(outgoingEdges(node));
        }
        return allEdge;
    }

    /**
     * 获取图中节点的数量
     *
     * @return 返回图中节点的总数量
     */
    @Override
    public int nodeSize() {
        return graphMap.size();
    }

    /**
     * 获取图中边的数量
     *
     * @return 返回图中边的总数量
     */
    @Override
    public int edgeSize() {
        return edgeSize;
    }

    /**
     * 该节点相邻的所有节点
     *
     * @param node 节点
     * @return 返回相邻的所有节点
     */
    @Override
    public Set<N> adjacentNodes(N node) {
        if (!containsNode(node)) {
            return new LinkedHashSet<>();
        }
        return graphMap.get(node).getAdjacent().keySet();
    }

    /**
     * 由相邻的边所指向该节点进来的节点
     *
     * @param node 节点
     * @return 返回所有所有指向该节点的相邻节点
     */
    @Override
    public Set<N> incomingNodes(N node) {
        if (!containsNode(node)) {
            return new LinkedHashSet<>();
        }
        return graphMap.get(node).getIncoming().keySet();
    }

    /**
     * 该节点由相邻的边所指向出去的节点
     *
     * @param node 节点
     * @return 返回所该节点指向的相邻节点
     */
    @Override
    public Set<N> outgoingNodes(N node) {
        if (!containsNode(node)) {
            return new LinkedHashSet<>();
        }
        return graphMap.get(node).getOutgoing().keySet();
    }

    /**
     * 源节点与目标节点之间是否有一条边相连
     *
     * @param source 源节点
     * @param target 目标节点
     * @return true or false
     */
    @Override
    public boolean hasEdgeConnecting(N source, N target) {
        if (!containsNode(source) || !containsNode(target)) {
            throw new IllegalArgumentException("source node and target node must exist!");
        }
        return adjacentNodes(source).contains(target);
    }

    /**
     * 源节点与目标节点之间是否存在一条指定的边连接两个节点
     *
     * @param source 源节点
     * @param target 目标节点
     * @return true or false
     */
    @Override
    public boolean hasEdgeConnecting(N source, N target, Edge<N> edge) {
        return getEdge(source, target).contains(edge);
    }

    /**
     * 图中是否包含由源节点到目标节点的边
     *
     * @param source 源节点
     * @param target 目标节点
     * @return 是否包含指定原点到目标节点的边
     */
    @Override
    public boolean containsEdge(N source, N target) {
        if (!hasEdgeConnecting(source, target)) {
            return false;
        }
        return outgoingNodes(source).contains(target);
    }

    /**
     * 图中是否包含该节点
     *
     * @param node 节点
     * @return 是否包含节点
     */
    @Override
    public boolean containsNode(N node) {
        return graphMap.containsKey(node);
    }

    /**
     * 图中是否包含该边的存在
     *
     * @param edge 边
     * @return 是否包含边
     */
    @Override
    public boolean containsEdge(Edge<N> edge) {
        return hasEdgeConnecting(edge.getSource(), edge.getTarget(), edge);
    }

    /**
     * 与该节点相邻的所有边
     *
     * @param node 节点
     * @return 返回与该节点所有相邻的边
     */
    @Override
    public Set<Edge<N>> adjacentEdges(N node) {
        Set<Edge<N>> allAdjacentEdges = new LinkedHashSet<>();
        for (Set<Edge<N>> edgeSet : graphMap.get(node).getAdjacent().values()) {
            allAdjacentEdges.addAll(edgeSet);
        }
        return allAdjacentEdges;
    }

    /**
     * 与该节点相连进来的边
     *
     * @param node 节点
     * @return 返回所有指向该节点相连的边
     */
    @Override
    public Set<Edge<N>> incomingEdges(N node) {
        Set<Edge<N>> allIncomingEdges = new LinkedHashSet<>();
        for (Set<Edge<N>> edgeSet : graphMap.get(node).getIncoming().values()) {
            allIncomingEdges.addAll(edgeSet);
        }
        return allIncomingEdges;
    }

    /**
     * 与该节点相连出去的边
     *
     * @param node 节点
     * @return 返回该节点指向出去的边
     */
    @Override
    public Set<Edge<N>> outgoingEdges(N node) {
        Set<Edge<N>> allOutgoingEdges = new LinkedHashSet<>();
        for (Set<Edge<N>> edgeSet : graphMap.get(node).getOutgoing().values()) {
            allOutgoingEdges.addAll(edgeSet);
        }
        return allOutgoingEdges;
    }

    @Override
    public Object clone() {
        try {
            AbstractBaseGraph<N> newGraph = TypeUtil.uncheckedCase(super.clone());
            newGraph.edgeSize = this.edgeSize;
            newGraph.graphMap = this.graphMap;
            return newGraph;
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException("???");
        }
    }
}
