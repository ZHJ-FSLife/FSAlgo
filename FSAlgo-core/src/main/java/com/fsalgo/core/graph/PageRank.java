package com.fsalgo.core.graph;

import com.fsalgo.core.interfaces.NodeScoringAlgorithm;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2022/12/21 20:04
 * @Description: Page - 排名算法
 */
public class PageRank<N> implements NodeScoringAlgorithm<N, Double> {

    @Override
    public Map<N, Double> getScores() {
        return null;
    }

    @Override
    public Double getNodeScore(N n) {
        return null;
    }
}
