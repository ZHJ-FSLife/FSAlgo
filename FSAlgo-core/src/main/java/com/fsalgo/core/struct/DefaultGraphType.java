package com.fsalgo.core.struct;

import java.io.Serializable;

/**
 * @Author: root
 * @Date: 2022/12/23 20:06
 * @Description:
 */
public class DefaultGraphType implements GraphType, Serializable {

    private static final long serialVersionUID = 8126786850948847257L;

    private final boolean directed;
    private final boolean undirected;
    private final boolean weighted;

    private DefaultGraphType(boolean directed,
                             boolean undirected,
                             boolean weighted) {
        this.directed = directed;
        this.undirected = undirected;
        this.weighted = weighted;
    }

    @Override
    public boolean isDirected() {
        return directed && !undirected;
    }

    @Override
    public boolean isUndirected() {
        return !directed && undirected;
    }

    @Override
    public boolean isMixed() {
        return directed && undirected;
    }

    @Override
    public boolean isWeighted() {
        return weighted;
    }

    @Override
    public GraphType asDirected() {
        return new Builder(this).directed().build();
    }

    @Override
    public GraphType asUndirected() {
        return new Builder(this).undirected().build();
    }

    @Override
    public GraphType asMixed() {
        return new Builder(this).mixed().build();
    }

    @Override
    public GraphType asWeighted() {
        return new Builder().weighted(true).build();
    }

    @Override
    public GraphType asUnWeighted() {
        return new Builder(this).weighted(false).build();
    }

    public static class Builder {

        private boolean directed;
        private boolean undirected;
        private boolean weighted;

        public Builder() {
            this.directed = true;
            this.undirected = false;
            this.weighted = false;
        }

        public Builder(GraphType type) {
            this.directed = type.isDirected() || type.isMixed();
            this.undirected = type.isUndirected() || type.isMixed();
            this.weighted = type.isWeighted();
        }

        public Builder directed() {
            this.directed = true;
            this.undirected = false;
            return this;
        }

        public Builder undirected() {
            this.directed = false;
            this.undirected = true;
            return this;
        }

        public Builder mixed() {
            this.directed = true;
            this.undirected = true;
            return this;
        }

        public Builder weighted(boolean value) {
            this.weighted = value;
            return this;
        }

        public DefaultGraphType build() {
            return new DefaultGraphType(directed, undirected, weighted);
        }

    }
}
