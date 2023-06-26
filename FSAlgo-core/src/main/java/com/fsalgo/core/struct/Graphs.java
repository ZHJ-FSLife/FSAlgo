package com.fsalgo.core.struct;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/20 20:11
 * @Description:
 */
public abstract class Graphs {

    private Graphs() {}

    public static <N> NodeToIndexMapping<N> getNodeToIndexMapping(Graph<N> graph) {
        return new NodeToIndexMapping<>(Objects.requireNonNull(graph).nodes());
    }

    public static <N> void addGraph(Graph<N> destination, Graph<N> source) {
        addAllNode(destination, source.nodes());
        addAllEdge(destination, source.edges());
    }

    public static <N> void addAllNode(Graph<N> destination, Collection<N> nodes) {
        for (N node : nodes) {
            destination.addNode(node);
        }
    }

    public static <N> void addAllEdge(Graph<N> destination, Collection<Edge<N>> edges) {
        for (Edge<N> edge : edges) {
            destination.addEdge(edge);
        }
    }
}
