package com.fsalgo.core.struct;

import com.fsalgo.core.interfaces.NameEntity;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 1:55
 * @Description:
 */
public interface Graph<N> extends NameEntity {

    /**
     * 添加结点
     *
     * @param node 节点
     */
    void addNode(N node);

    /**
     * 添加边
     *
     * @param edge 边
     */
    void addEdge(Edge<N> edge);

    /**
     * 添加边
     *
     * @param source 源节点
     * @param target 目标节点
     */
    void addEdge(N source, N target);

    /**
     * 添加边
     * @param source 源节点
     * @param target 目标节点
     * @param weight 权重值
     */
    void addEdge(N source, N target, double weight);

    /**
     * 获取图中节点的数量
     *
     * @return 返回图中节点的总数量
     */
    int nodeSize();

    /**
     * 获取图中边的数量
     *
     * @return 返回图中边的总数量
     */
    int edgeSize();

    /**
     * 图中所有的节点
     *
     * @return 返回图中所有的节点
     */
    Set<N> nodes();

    /**
     * 图中所有的边
     *
     * @return 返回图中所有的边
     */
    Set<Edge<N>> edges();

    /**
     * 该节点相邻的所有节点
     *
     * @param node 节点
     * @return 返回相邻的所有节点
     */
    Set<N> adjacentNodes(N node);

    /**
     * 由相邻的边所指向该节点进来的节点
     *
     * @param node 节点
     * @return 返回所有所有指向该节点的相邻节点
     */
    Set<N> incomingNodes(N node);

    /**
     * 该节点由相邻的边所指向出去的节点
     *
     * @param node 节点
     * @return 返回所该节点指向的相邻节点
     */
    Set<N> outgoingNodes(N node);

    /**
     * 与该节点相连进来的边
     *
     * @param node 节点
     * @return 返回所有指向该节点相连的边
     */
    Set<Edge<N>> incomingEdges(N node);

    /**
     * 与该节点相连出去的边
     *
     * @param node 节点
     * @return 返回该节点指向出去的边
     */
    Set<Edge<N>> outgoingEdges(N node);

    /**
     * 源节点与目标节点之间是否有一条边相连
     *
     * @param source 源节点
     * @param target 目标节点
     * @return true or false
     */
    boolean hasEdgeConnecting(N source, N target);

}
