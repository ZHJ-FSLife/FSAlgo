package com.fsalgo.core.interfaces.score;

import com.fsalgo.core.interfaces.NameEntity;
import com.fsalgo.core.struct.Edge;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 10:26
 * @Description: 边评分算法
 */
public interface EdgeScoringAlgorithm<N, D> extends NameEntity {

    /**
     * 获取所有边的评分分值
     *
     * @return 返回所有边的分值
     */
    Map<Edge<N>, D> getScores();

    /**
     * 获取指定边的评分分值
     *
     * @param edge 边
     * @return 分值
     */
    D getEdgeScore(Edge<N> edge);
}
