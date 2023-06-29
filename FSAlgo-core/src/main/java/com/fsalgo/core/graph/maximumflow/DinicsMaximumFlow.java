package com.fsalgo.core.graph.maximumflow;

import com.fsalgo.core.interfaces.flow.MaximumFlowAlgorithm;
import com.fsalgo.core.struct.Edge;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/25 11:30
 * @Description:
 */
public class DinicsMaximumFlow<N> implements MaximumFlowAlgorithm<N> {

    @Override
    public Map<Edge<N>, Double> getFlowMap() {
        return null;
    }

    @Override
    public MaximumFlow<N> getMaximumFlow(N source, N target) {
        return null;
    }

    @Override
    public String getName() {
        return "dinic's";
    }
}
