package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;
import com.fsalgo.core.tree.heap.Heap;
import com.fsalgo.core.tree.heap.impl.FibonacciHeap;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/20 20:14
 * @Description:
 */
public class DijkstraShortestPath<N extends Comparable<N>> implements ShortestPathAlgorithm<N> {

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

        Heap<N> heap = new FibonacciHeap<>() {
            @Override
            public boolean compareTo(N n1, N n2) {
                return Double.compare(distance[indexs.get(n1)], distance[indexs.get(n2)]) < 0;
            }
        };

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
            Set<Edge<N>> edges = graph.outgoingEdges(current);
            visited[currIndex] = true;

            for (Edge<N> edge : edges) {
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
}
