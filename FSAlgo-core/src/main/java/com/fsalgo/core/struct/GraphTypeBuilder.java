package com.fsalgo.core.struct;

import com.fsalgo.core.struct.specific.DirectedGraph;
import com.fsalgo.core.struct.specific.UndirectedGraph;

/**
 * @Author: root
 * @Date: 2023/1/5 20:36
 * @Description:
 */
public final class GraphTypeBuilder<N> {

    private boolean directed;
    private boolean undirected;
    private boolean weighted;

    private GraphTypeBuilder(boolean directed, boolean undirected) {
        this.directed = directed;
        this.undirected = undirected;
        this.weighted = false;
    }

    public static <N> GraphTypeBuilder<N> directed() {
        return new GraphTypeBuilder<>(true, false);
    }

    public static <N> GraphTypeBuilder<N> undirected() {
        return new GraphTypeBuilder<>(false, true);
    }

    public static <N> GraphTypeBuilder<N> mixed() {
        return new GraphTypeBuilder<>(true, true);
    }

    public GraphTypeBuilder<N> weighted(boolean weighted) {
        this.weighted = weighted;
        return this;
    }

    public Graph<N> buildGraph() {
        if (directed) {
            return new DirectedGraph<>();
        }
        return new UndirectedGraph<>();
    }
}
