package com.fsalgo.core.struct;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2022/12/21 20:19
 * @Description:
 */
public abstract class AbstractGraph<N> implements Graph<N>, Serializable {

    private static final long serialVersionUID = -1880246847230671650L;

    /**
     * 节点对应索引值
     */
    protected Map<N, Integer> nodeIndexMap = new HashMap<>();

    /**
     * 构建邻接表、或邻接多重表来表示图 - 节点与边之间的关系
     */
    protected Map<N, Set<Edge<N>>> graphMap = new HashMap<>();

    /**
     * 存储所有节点之间的边
     */
    protected Set<Edge<N>> edgeSet = new HashSet<>();

    /**
     * 获取节点索引map
     *
     * @return 结果索引
     */
    @Override
    public Map<N, Integer> getNodeIndexMap() {
        return nodeIndexMap;
    }

    /**
     * 获取图map
     *
     * @return 图map
     */
    @Override
    public Map<N, Set<Edge<N>>> getGraphMap() {
        return graphMap;
    }

    @Override
    public GraphType getType() {
        return null;
    }

    /**
     * 添加节点
     *
     * @param n 节点
     */
    @Override
    public void addNode(N n) {
        if (!graphMap.containsKey(n)) {
            graphMap.put(n, new HashSet<>());
            nodeIndexMap.put(n, graphMap.size() - 1);
        }
    }

    /**
     * 添加多个节点
     *
     * @param nodes 节点集
     */
    @Override
    public void addNodes(List<N> nodes) {
        for (N node : nodes) {
            addNode(node);
        }
    }

    /**
     * 获取所有节点
     *
     * @return 节点
     */
    @Override
    public Set<N> getAllNode() {
        return graphMap.keySet();
    }

    /**
     * 添加边
     *
     * @param edge 边
     */
    @Override
    public boolean addEdge(Edge<N> edge) {
        if (!edgeSet.contains(edge)) {
            return edgeSet.add(edge);
        }
        return false;
    }

    /**
     * 添加多条边
     *
     * @param edges 边
     */
    @Override
    public void addEdges(List<Edge<N>> edges) {
        for (Edge<N> edge : edges) {
            addEdge(edge);
        }
    }

    /**
     * 获取指定起始节点和目标节点之间的所有边
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 边
     */
    @Override
    public Set<Edge<N>> getEdge(N source, N target) {
        if (!graphMap.containsKey(source)) {
            return null;
        }
        Set<Edge<N>> edges = new HashSet<>();
        for (Edge<N> edge : graphMap.get(source)) {
            if (edge.getTarget() == target) {
                edges.add(edge);
            }
        }
        return edges;
    }

    @Override
    public Set<Edge<N>> getAllEdge() {
        return edgeSet;
    }

}
