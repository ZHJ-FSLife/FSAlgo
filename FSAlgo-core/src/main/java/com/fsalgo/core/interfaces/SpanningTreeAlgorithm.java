package com.fsalgo.core.interfaces;

import com.fsalgo.core.struct.Edge;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/23 21:18
 * @Description: 生成树算法
 */
public interface SpanningTreeAlgorithm<N> {

    SpanningTree<N> getSpanningTree();

    /**
     * 生成树
     *
     * @param <N>
     */
    interface SpanningTree<N> {

        double getWeight();

        Set<Edge<N>> getEdges();
    }

    /**
     * 生成树接口的默认实现
     *
     * @param <N>
     */
    class SpanningTreeImpl<N> implements SpanningTree<N>, Serializable {

        private static final long serialVersionUID = 1301715048436309711L;

        private final double weight;
        private final Set<Edge<N>> edges;

        public SpanningTreeImpl(Set<Edge<N>> edges, double weight) {
            this.edges = edges;
            this.weight = weight;
        }

        @Override
        public double getWeight() {
            return weight;
        }

        @Override
        public Set<Edge<N>> getEdges() {
            return edges;
        }
    }
}
