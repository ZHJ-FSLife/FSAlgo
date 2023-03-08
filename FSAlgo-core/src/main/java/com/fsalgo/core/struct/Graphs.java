package com.fsalgo.core.struct;

import java.util.Objects;

/**
 * @Author: root
 * @Date: 2023/2/20 20:11
 * @Description:
 */
public abstract class Graphs {

    public static <N> NodeToIndexMapping<N> getNodeToIndexMapping(Graph<N> graph) {
        return new NodeToIndexMapping<>(Objects.requireNonNull(graph).nodes());
    }
}
