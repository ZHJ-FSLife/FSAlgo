package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.AbstractBaseGraph;
import com.fsalgo.core.struct.Edge;

/**
 * @Author: root
 * @Date: 2023/3/26 23:09
 * @Description: 有向无环图
 */
public class DirectedAcyclicGraph<N> extends AbstractBaseGraph<N> {

    public DirectedAcyclicGraph() {

    }

    @Override
    public void addNode(N node) {

    }

    @Override
    public void addEdge(Edge<N> edge) {
        N source = edge.getSource();
        N target = edge.getTarget();
    }

}
