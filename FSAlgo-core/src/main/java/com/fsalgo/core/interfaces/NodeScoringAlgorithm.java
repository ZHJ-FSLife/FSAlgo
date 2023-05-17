package com.fsalgo.core.interfaces;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2022/12/23 21:47
 * @Description: 节点评分算法
 */
public interface NodeScoringAlgorithm<N, D> extends NameEntity {

    /**
     * 获取所有节点的分值
     * @return 返回所有节点的分值
     */
    Map<N, D> getScores();

    /**
     * 获取指定节点的分值
     * @param n 节点
     * @return 分值
     */
    D getNodeScore(N n);

}
