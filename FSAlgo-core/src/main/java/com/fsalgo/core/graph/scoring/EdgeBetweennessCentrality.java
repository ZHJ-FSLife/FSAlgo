package com.fsalgo.core.graph.scoring;

import com.fsalgo.core.interfaces.score.EdgeScoringAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 10:31
 * @Description:
 */
public class EdgeBetweennessCentrality<N> implements EdgeScoringAlgorithm<N, Double> {

    private final Graph<N> graph;

    private Map<Edge<N>, Double> score;

    public EdgeBetweennessCentrality(Graph<N> graph) {
        this.graph = graph;
    }

    @Override
    public Map<Edge<N>, Double> getScores() {
        return score;
    }

    @Override
    public Double getEdgeScore(Edge<N> edge) {
        return score.get(edge);
    }

    @Override
    public String getName() {
        return "edge betweenness centrality";
    }
}
