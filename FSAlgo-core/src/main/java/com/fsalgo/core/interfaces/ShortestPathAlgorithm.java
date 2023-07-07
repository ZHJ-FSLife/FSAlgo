package com.fsalgo.core.interfaces;

import com.fsalgo.core.struct.Edge;

import java.util.List;

/**
 * @Author: root
 * @Date: 2022/12/23 21:23
 * @Description: 最短路径算法
 */
public interface ShortestPathAlgorithm<N> extends NameEntity {

    /**
     * 起始节点到目标节点的最短路径
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 图节点间的最短路径
     */
    GraphPath<N> getPath(N source, N target);

    /**
     * 起始节点到目标节点的最小权重值
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 图节点间的最小权重值
     */
    double getPathWeight(N source, N target);

    /**
     * 单源最短路径
     *
     * @param <N>
     */
    interface SingleSourcePaths<N> {

    }

    interface GraphPath<N> {
        List<N> getNodes();

        List<Edge<N>> getEdges();
    }

    class GraphPathImpl<N> implements GraphPath<N> {

        private List<N> nodes;
        private List<Edge<N>> edges;

        public GraphPathImpl(List<N> nodes, List<Edge<N>> edges) {
            this.nodes = nodes;
            this.edges = edges;
        }

        @Override
        public List<N> getNodes() {
            return nodes;
        }

        @Override
        public List<Edge<N>> getEdges() {
            return edges;
        }
    }

}
