/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
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
package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;
import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.specific.FibonacciHeap;

import java.io.Serializable;
import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/20 20:14
 * @Description:
 */
public class DijkstraShortestPath<N extends Comparable<N>> implements ShortestPathAlgorithm<N>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Graph<N> graph;

    private Edge<N>[] incomingEdge;

    private double[] distance;

    private boolean[] visited;

    private Map<N, Integer> indexs;

    public DijkstraShortestPath(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        if (Objects.isNull(graph)) {
            return;
        }
        this.indexs = Graphs.getNodeToIndexMapping(graph).getNodeMap();
        this.distance = new double[indexs.size()];
        this.visited = new boolean[indexs.size()];
        this.incomingEdge = new Edge[indexs.size()];
    }

    @Override
    public GraphPath<N> getPath(N source, N target) {
        if (source == null || target == null) {
            throw new IllegalArgumentException("the source node and target node cannot be empty!");
        }

        if (indexs.size() <= 1 ||!indexs.containsKey(source) || !indexs.containsKey(target)) {
            throw new IllegalArgumentException("graph must contain the source node and target node!");
        }

        if (source.equals(target)) {
            return null;
        }

        Heap<N> heap = new FibonacciHeap<>(
                Comparator.comparingDouble(n -> distance[indexs.get(n)])
        );

        int sourceIndex = indexs.get(source);
        visited[sourceIndex] = true;
        incomingEdge[sourceIndex] = null;

        heap.add(source);
        while (!heap.isEmpty()) {
            N current = heap.remove();

            if (current == target) {
                break;
            }

            int currIndex = indexs.get(current);
            visited[currIndex] = true;

            for (Edge<N> edge : graph.outgoingEdges(current)) {
                N next = edge.getTarget();
                int nextIndex = indexs.get(next);
                if (visited[nextIndex]) {
                    continue;
                }
                if (distance[nextIndex] == 0 || distance[nextIndex] > distance[currIndex] + edge.getWeight()) {
                    distance[nextIndex] = distance[currIndex] + edge.getWeight();
                    incomingEdge[nextIndex] = edge;
                }
                heap.add(next);
            }

        }

        Deque<Edge<N>> edges = new LinkedList<>();
        N current = target;
        Deque<N> path = new LinkedList<>();
        while (current != source) {
            int currIndex = indexs.get(current);
            path.addFirst(current);
            edges.addFirst(incomingEdge[currIndex]);
            current = incomingEdge[currIndex].getSource();
        }
        path.addFirst(current);

        return new GraphPathImpl<>((List) path, (List) edges);
    }

    @Override
    public double getPathWeight(N source, N target) {
        return 0;
    }

    @Override
    public String getName() {
        return "dijkstra";
    }
}
