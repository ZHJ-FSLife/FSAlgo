package com.fsalgo.core.graph;

import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.impl.FibonacciHeap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最小生成树 - 普里姆算法
 */
public class PrimMinimumSpanningTree<N extends Comparable<N>> implements SpanningTreeAlgorithm<N> {

    private final Edge<N>[] incomingEdge;

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
        this.incomingEdge = new Edge[graphMap.size()];
        this.visited = new boolean[graphMap.size()];
    }

    @Override
    public SpanningTree<N> getSpanningTree() {

        if (graphMap.size() <= 1) {
            throw new IllegalArgumentException("there must be at least two nodes in the graph!");
        }

        double spanningTreeWeight = 0d;
        Set<Edge<N>> minimumSpanningTreeEdgeSet = new HashSet<>();

        // 使用斐波那契堆进行优化，相较于PriorityQueue，添加节点的时间复杂度从 O(logN) 优化到了 O(1)
        Heap<N> heap = new FibonacciHeap<>() {
            @Override
            public boolean compareTo(N n1, N n2) {
                int index1 = nodeIndexMap.get(n1);
                int index2 = nodeIndexMap.get(n2);
                return Double.compare(incomingEdge[index1].getWeight(), incomingEdge[index2].getWeight()) < 0;
            }
        };

        heap.add(graphMap.entrySet().iterator().next().getKey());
        while (!heap.isEmpty()) {
            N current = heap.remove();

            int currIndex = nodeIndexMap.get(current);

            if (!visited[currIndex] && incomingEdge[currIndex] != null) {
                minimumSpanningTreeEdgeSet.add(incomingEdge[currIndex]);
                spanningTreeWeight += incomingEdge[currIndex].getWeight();
            }

            if (minimumSpanningTreeEdgeSet.size() >= graphMap.size() - 1) {
                return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
            }

            visited[currIndex] = true;
            Set<Edge<N>> edges = graphMap.get(current);
            for (Edge<N> edge : edges) {
                N next = edge.getTarget();
                int nextIndex = nodeIndexMap.get(next);

                if (incomingEdge[nextIndex] == null || edge.getWeight() < incomingEdge[nextIndex].getWeight()) {
                    incomingEdge[nextIndex] = edge;
                    if (!visited[nextIndex]) {
                        heap.add(next);
                    }
                }
            }
        }

        return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
    }
}
