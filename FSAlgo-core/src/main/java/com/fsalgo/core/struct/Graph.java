package com.fsalgo.core.struct;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/16 19:55
 * @Description:
 */
public interface Graph<N> {

    /**
     * 默认边的权重值
     */
    double DEFAULT_EDGE_WEIGHT = 1.0;

    GraphType getType();

    /**
     * 获取节点索引map
     *
     * @return 结果索引
     */
    Map<N, Integer> getNodeIndexMap();

    /**
     * 获取图map
     *
     * @return 图map
     */
    Map<N, Set<Edge<N>>> getGraphMap();

    /**
     * 添加节点
     *
     * @param n 节点
     */
    void addNode(N n);

    /**
     * 添加多个节点
     *
     * @param nodes 节点集
     */
    void addNodes(List<N> nodes);

    /**
     * 获取所有节点
     *
     * @return 节点
     */
    Set<N> getAllNode();

    /**
     * 添加边
     *
     * @param edge 边
     */
    boolean addEdge(Edge<N> edge);

    /**
     * 添加多条边
     *
     * @param edges 边
     */
    void addEdges(List<Edge<N>> edges);

    /**
     * 获取指定起始节点和目标节点之间的所有边
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 边
     */
    Set<Edge<N>> getEdge(N source, N target);

    /**
     * 获取图中所有的边
     *
     * @return all edge in graph
     */
    Set<Edge<N>> getAllEdge();

}
