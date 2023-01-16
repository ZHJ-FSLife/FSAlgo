package com.fsalgo.core.graph;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.GraphPath;
import com.fsalgo.core.tree.FibonacciHeap;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/21 20:03
 * @Description: 最短路径 - 迪杰斯特拉算法
 */
public class DijkstraShortestPath<N extends Comparable<N>> implements ShortestPathAlgorithm<N> {

    private N[] parents;

    private double[] distance;

    private boolean[] visited;

    private Map<N, Set<Edge<N>>> graphMap;

    private Map<N, Integer> nodeIndexMap;

    private Class<N> clazz;

    public DijkstraShortestPath(Class<N> clazz, Graph<N> graph) {
        this.clazz = clazz;
        init(graph);
    }

    public DijkstraShortestPath(Graph<N> graph) {
        this.clazz = (Class<N>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        init(graph);
    }

    private void init( Graph<N> graph) {
        graphMap = graph.getGraphMap();
        this.nodeIndexMap = graph.getNodeIndexMap();
        this.parents = (N[]) Array.newInstance(this.clazz, graphMap.size());
        this.visited = new boolean[graphMap.size()];
        this.distance = new double[graphMap.size()];
        // Arrays.fill(distance, Double.POSITIVE_INFINITY);
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

        FibonacciHeap<N> heap = new FibonacciHeap<N>() {
            public boolean compareTo(N n1, N n2) {
                return Double.compare(distance[nodeIndexMap.get(n1)], distance[nodeIndexMap.get(n2)]) < 0;
            }
        };

        int sourceIndex = nodeIndexMap.get(source);
        visited[sourceIndex] = true;
        parents[sourceIndex] = source;

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
                    parents[nextIndex] = current;
                }
                heap.add(next);
            }
        }

        N current = target;
        List<N> path = new LinkedList<>();
        while (current != source) {
            int currIndex = nodeIndexMap.get(current);
            path.add(current);
            current = parents[currIndex];
        }
        path.add(current);

        System.out.println(path);

        return null;
    }

    @Override
    public double getPathWeight(N source, N target) {
        return 0;
    }
}
