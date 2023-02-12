package com.fsalgo.core.graph;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.impl.FibonacciHeap;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最短路径 - 迪杰斯特拉算法
 */
public class DijkstraShortestPath<N extends Comparable<N>> implements ShortestPathAlgorithm<N> {

    private final Edge<N>[] incomingEdge;

    private final double[] distance;

    private final boolean[] visited;

    private final Map<N, Set<Edge<N>>> graphMap;

    private final Map<N, Integer> nodeIndexMap;

    public DijkstraShortestPath(Graph<N> graph) {
        graphMap = graph.getGraphMap();
        this.nodeIndexMap = graph.getNodeIndexMap();
        this.incomingEdge = new Edge[graphMap.size()];
        this.visited = new boolean[graphMap.size()];
        this.distance = new double[graphMap.size()];
    }

    @Override
    public GraphPath<N> getPath(N source, N target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("the source node and target node cannot be empty!");
        }

        if (!graphMap.containsKey(source) || !graphMap.containsKey(target)) {
            throw new IllegalArgumentException("graph must contain the source node and target node!");
        }

        if (source.equals(target)) {
            return null;
        }

        Heap<N> heap = new FibonacciHeap<>() {
            @Override
            public boolean compareTo(N n1, N n2) {
                return Double.compare(distance[nodeIndexMap.get(n1)], distance[nodeIndexMap.get(n2)]) < 0;
            }
        };

        int sourceIndex = nodeIndexMap.get(source);
        visited[sourceIndex] = true;
        incomingEdge[sourceIndex] = null;

        heap.add(source);
        while (!heap.isEmpty()) {
            N current = heap.remove();

            if (current == target) {
                break;
            }

            int currIndex = nodeIndexMap.get(current);
            Set<Edge<N>> edges = graphMap.get(current);
            visited[currIndex] = true;

            for (Edge<N> edge : edges) {
                N next = edge.getTarget();
                int nextIndex = nodeIndexMap.get(next);
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
            int currIndex = nodeIndexMap.get(current);
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
}
