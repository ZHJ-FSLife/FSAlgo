package com.fsalgo.core.struct;

import com.fsalgo.core.struct.specific.EdgeContainer;
import com.fsalgo.core.struct.specific.NodeContainer;
import com.fsalgo.core.util.TypeUtil;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 2:28
 * @Description:
 */
public abstract class AbstractBaseGraph<N> extends AbstractGraph<N> implements Graph<N> {

    protected int edgeSize = 0;

    protected Map<N, NodeContainer<N>> nodeMap;
    protected Map<N, EdgeContainer<N>> edgeMap;

    protected AbstractBaseGraph() {
        nodeMap = new LinkedHashMap<>();
        edgeMap = new LinkedHashMap<>();
    }

    /**
     * 添加结点
     *
     * @param node 节点
     */
    @Override
    public void addNode(N node) {
        if (nodeMap.containsKey(node)) {
            return;
        }
        nodeMap.put(node, new NodeContainer<>(n -> new HashSet<>(), node));
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
     * 图中所有的节点
     *
     * @return 返回图中所有的节点
     */
    @Override
    public Set<N> nodes() {
        return new HashSet<>(nodeMap.keySet());
    }

    /**
     * 图中所有的边
     *
     * @return 返回图中所有的边
     */
    @Override
    public Set<Edge<N>> edges() {
        Set<Edge<N>> allEdge = new HashSet<>();
        for (EdgeContainer<N> edgeContainer : edgeMap.values()) {
            allEdge.addAll(edgeContainer.getOutgoing());
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
        return nodeMap.size();
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
        if (!nodeMap.containsKey(node)) {
            return null;
        }
        return nodeMap.get(node).getAdjacent();
    }

    /**
     * 由相邻的边所指向该节点进来的节点
     *
     * @param node 节点
     * @return 返回所有所有指向该节点的相邻节点
     */
    @Override
    public Set<N> incomingNodes(N node) {
        if (!nodeMap.containsKey(node)) {
            return null;
        }
        return nodeMap.get(node).getIncoming();
    }

    /**
     * 该节点由相邻的边所指向出去的节点
     *
     * @param node 节点
     * @return 返回所该节点指向的相邻节点
     */
    @Override
    public Set<N> outgoingNodes(N node) {
        if (!nodeMap.containsKey(node)) {
            return null;
        }
        return nodeMap.get(node).getOutgoing();
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
        if (!nodeMap.containsKey(source) || !nodeMap.containsKey(target)) {
            throw new IllegalArgumentException("source node and target node must exist!");
        }
        return adjacentNodes(source).contains(target);
    }

    /**
     * 与该节点相连进来的边
     *
     * @param node 节点
     * @return 返回所有指向该节点相连的边
     */
    @Override
    public Set<Edge<N>> incomingEdges(N node) {
        return edgeMap.get(node).getIncoming();
    }

    /**
     * 与该节点相连出去的边
     *
     * @param node 节点
     * @return 返回该节点指向出去的边
     */
    @Override
    public Set<Edge<N>> outgoingEdges(N node) {
        return edgeMap.get(node).getOutgoing();
    }

    /**
     * 给新增的节点创建新的边容器
     *
     * @param node 节点
     */
    public void addEdgeContainer(N node) {
        if (edgeMap.containsKey(node)) {
            return;
        }
        edgeMap.put(node, new EdgeContainer<>(n -> new HashSet<>(), node));
    }

    @Override
    public Object clone() {
        try {
            AbstractBaseGraph<N> newGraph = TypeUtil.uncheckedCase(super.clone());
            newGraph.nodeMap = this.nodeMap;
            newGraph.edgeMap = this.edgeMap;
            return newGraph;
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException("???");
        }
    }
}
