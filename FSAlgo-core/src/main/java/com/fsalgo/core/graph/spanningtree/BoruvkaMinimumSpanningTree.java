package com.fsalgo.core.graph.spanningtree;

import com.fsalgo.core.interfaces.SpanningTreeAlgorithm;
import com.fsalgo.core.struct.Edge;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/23 19:28
 * @Description:
 */
public class BoruvkaMinimumSpanningTree<N> implements SpanningTreeAlgorithm<N> {

    @Override
    public SpanningTree<N> getSpanningTree() {
        double spanningTreeWeight = 0d;
        Set<Edge<N>> minimumSpanningTreeEdgeSet = new HashSet<>();

        return new SpanningTreeImpl<>(minimumSpanningTreeEdgeSet, spanningTreeWeight);
    }
}
