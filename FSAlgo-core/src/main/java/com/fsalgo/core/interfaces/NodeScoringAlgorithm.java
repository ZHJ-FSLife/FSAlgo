package com.fsalgo.core.interfaces;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2022/12/23 21:47
 * @Description: 节点评分算法
 */
public interface NodeScoringAlgorithm<N, D> {

    Map<N, D> getScores();

    D getNodeScore(N n);

}
