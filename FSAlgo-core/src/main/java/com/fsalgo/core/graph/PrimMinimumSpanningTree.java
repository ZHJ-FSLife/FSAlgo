package com.fsalgo.core.graph;

import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.FibonacciHeap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最小生成树 - 普里姆算法
 */
public class PrimMinimumSpanningTree<N extends Comparable<N>> implements SpanningTreeAlgorithm<N> {

    protected final int[] parent;

    protected final double[] distance;

    protected final boolean[] visited;

    /**
     * 图，构建邻接表来表示有向图，存储节点与边之间的关系
     */
    protected final Map<N, Set<Edge<N>>> graphMap;

    /**
     * 节点索引位置
     */
    protected final Map<N, Integer> nodeIndexMap;

    public PrimMinimumSpanningTree(Graph<N> graph) {
        this.graphMap = graph.getGraphMap();
        this.nodeIndexMap = graph.getNodeIndexMap();
        this.parent = new int[graphMap.size()];
        this.distance = new double[graphMap.size()];
        this.visited = new boolean[graphMap.size()];
    }

    @Override
    public SpanningTree<N> getSpanningTree() {
        double spanningTreeWeight = 0d;
        Set<Edge<N>> minimumSpanningTreeEdgeSet = new HashSet<>();

        // 使用斐波那契堆进行优化，相对于PriorityQueue，时间复杂度从 O(logN) 优化到 O(1)
        FibonacciHeap<N> heap = new FibonacciHeap<N>() {
            @Override
            public boolean compareTo(N n1, N n2) {
                return Double.compare(distance[nodeIndexMap.get(n1)], distance[nodeIndexMap.get(n2)]) < 0;
            }
        };

        while (!heap.isEmpty()) {
            N current = heap.remove();

        }

        return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
    }
}
