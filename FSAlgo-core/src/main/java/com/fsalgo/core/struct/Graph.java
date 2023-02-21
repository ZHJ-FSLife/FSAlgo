package com.fsalgo.core.struct;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 1:55
 * @Description:
 */
public interface Graph<N>{

    /**
     * 添加结点
     */
    void addNode(N node);

    /**
     * 添加边
     */
    void addEdge(Edge<N> edge);

    /**
     * 添加边
     */
    void addEdge(N source, N target);

    /**
     * 节点索引位置（需要注意可变图和不可变图的处理）
     */
    int index(N node);

    /**
     * 获取图中节点的数量
     */
    int nodeSize();

    /**
     * 获取图中边的数量
     */
    int edgeSize();

    /**
     * 图中所有的节点
     */
    Set<N> nodes();

    /**
     * 图中所有的边
     */
    Set<Edge<N>> edges();

    /**
     * 该节点相邻的所有节点
     */
    Set<N> adjacentNodes(N node);

    /**
     * 由相邻的边所指向该节点进来的节点
     */
    Set<N> incomingNodes(N node);

    /**
     * 该节点由相邻的边所指向出去的节点
     */
    Set<N> outgoingNodes(N node);

    /**
     * 与该节点相连进来的边
     */
    Set<Edge<N>> incomingEdges(N node);

    /**
     * 与该节点相连出去的边
     */
    Set<Edge<N>> outgoingEdges(N node);

    /**
     * 源节点与目标节点之间是否有一条边相连
     */
    boolean hasEdgeConnecting(N source, N target);

}
