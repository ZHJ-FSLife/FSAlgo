/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.fsalgo.core.graph.spanningtree;

import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;
import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.specific.FibonacciHeap;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最小生成树 - 普里姆算法
 */
public class PrimMinimumSpanningTree<N extends Comparable<N>> implements SpanningTreeAlgorithm<N> {

    private final Graph<N> graph;

    protected boolean[] visited;

    private Edge<N>[] incomingEdge;

    private Map<N, Integer> indexs;


    public PrimMinimumSpanningTree(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        this.indexs = Graphs.getNodeToIndexMapping(graph).getNodeMap();
        this.incomingEdge = new Edge[indexs.size()];
        this.visited = new boolean[indexs.size()];
    }

    @Override
    public SpanningTree<N> getSpanningTree() {

        if (indexs.size() <= 1) {
            throw new IllegalArgumentException("there must be at least two nodes in the graph!");
        }

        double spanningTreeWeight = 0d;
        Set<Edge<N>> minimumSpanningTreeEdgeSet = new HashSet<>();

        // 使用斐波那契堆进行优化，相较于PriorityQueue，添加节点的时间复杂度从 O(logN) 优化到了 O(1)
        Heap<N> heap = new FibonacciHeap<>() {
            @Override
            public boolean compareTo(N n1, N n2) {
                return Double.compare(incomingEdge[indexs.get(n1)].getWeight(), incomingEdge[indexs.get(n2)].getWeight()) < 0;
            }
        };

        heap.add(indexs.entrySet().iterator().next().getKey());
        while (!heap.isEmpty()) {
            N current = heap.remove();

            int currIndex = indexs.get(current);

            if (!visited[currIndex] && incomingEdge[currIndex] != null) {
                minimumSpanningTreeEdgeSet.add(incomingEdge[currIndex]);
                spanningTreeWeight += incomingEdge[currIndex].getWeight();
            }

            if (minimumSpanningTreeEdgeSet.size() >= indexs.size() - 1) {
                return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
            }

            visited[currIndex] = true;
            for (Edge<N> edge : graph.outgoingEdges(current)) {
                N next = edge.getTarget();
                int nextIndex = indexs.get(next);

                if (incomingEdge[nextIndex] != null && edge.getWeight() > incomingEdge[nextIndex].getWeight()) {
                    continue;
                }

                incomingEdge[nextIndex] = edge;
                if (!visited[nextIndex]) {
                    heap.add(next);
                }
            }
        }

        return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
    }

    @Override
    public String getName() {
        return "prim";
    }
}
