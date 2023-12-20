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
package com.fsalgo.core.graph.scoring;

import com.fsalgo.core.interfaces.score.EdgeScoringAlgorithm;
import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 10:31
 * @Description: 复杂网络中边的中心性
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
