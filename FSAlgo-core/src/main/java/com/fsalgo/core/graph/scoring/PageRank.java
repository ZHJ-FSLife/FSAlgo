package com.fsalgo.core.graph.scoring;

import com.fsalgo.core.interfaces.NodeScoringAlgorithm;
import com.fsalgo.core.struct.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: root
 * @Date: 2022/12/21 20:04
 * @Description: Page - 排名算法
 */
public class PageRank<N> implements NodeScoringAlgorithm<N, Double> {

    /**
     * 最大迭代次数
     */
    private static final int MAX_ITERATIONS = 100;

    /**
     * 阻尼系数
     */
    private static final double DAMPING_FACTOR = 0.85d;

    private final Graph<N> graph;

    private Map<N, Double> score;

    public PageRank(Graph<N> graph) {
        this.graph = graph;
        initContainer();
        calcScore();
    }

    private void initContainer() {
        if (Objects.isNull(graph)) {
            return;
        }
        score = new HashMap<>();
        // 初始化米每个节点的PageRank值，（1.0 / 节点数）
        for (N node : graph.nodes()) {
            score.put(node, 1.0 / graph.nodeSize());
        }
    }

    /**
     * 对每个节点进行评分
     * 评分公式：
     * PR(i) = (1 - 阻尼系数) / 节点总数 + 阻尼系数 * sigma(PR(j) / 入节点出度值)
     */
    private void calcScore() {
        for (int i = 0; i < MAX_ITERATIONS; i++) {
            Map<N, Double> tempScore = new HashMap<>();
            for (N node : graph.nodes()) {
                double nums = 0.0d;
                for (N last : graph.incomingNodes(node)) {
                    nums += score.get(last) / graph.outgoingNodes(last).size();
                }
                tempScore.put(node, (1 - DAMPING_FACTOR) / graph.nodeSize() + DAMPING_FACTOR * nums);
            }
            score = tempScore;
        }
    }

    @Override
    public Map<N, Double> getScores() {
        return score;
    }

    @Override
    public Double getNodeScore(N n) {
        return score.get(n);
    }

    @Override
    public String getName() {
        return "page-rank";
    }
}
