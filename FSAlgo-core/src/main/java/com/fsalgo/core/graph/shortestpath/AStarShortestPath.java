package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;

/**
 * @Author: root
 * @Date: 2022/12/21 19:03
 * @Description: 最短路径 - A星算法
 */
public class AStarShortestPath<N> implements ShortestPathAlgorithm<N> {

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
        return "a-start";
    }
}
