package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 14:45
 * @Description: 弗洛伊德多源最短路径
 */
public class FloydWarshallShortestPath<N> implements ShortestPathAlgorithm<N> {

    private final Graph<N> graph;

    private double[][] weight;

    private Map<N, Integer> indexs;

    public FloydWarshallShortestPath(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        weight = new double[graph.nodeSize()][graph.nodeSize()];
    }

    private void calcShortPath() {
        for (N node : graph.nodes()) {
            int nodeIndexs = indexs.get(node);
            for (Edge<N> edge : graph.outgoingEdges(node)) {
                int nextIndexs = indexs.get(edge.getTarget());
                weight[nodeIndexs][nextIndexs] = edge.getWeight();
            }
        }

        // for (N k : graph.nodes()) {
        //     for (N i : graph.nodes()) {
        //         for (N j : graph.nodes()) {
        //
        //         }
        //     }
        // }
    }

    @Override
    public GraphPath<N> getPath(N source, N target) {
        return null;
    }

    @Override
    public double getPathWeight(N source, N target) {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
