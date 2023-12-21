/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
 *
 * Copyright (C) [2023] [ZhongHongJie]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.fsalgo.core.struct.builder;

import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.specific.DirectedAcyclicGraph;
import com.fsalgo.core.struct.specific.UndirectedGraph;
import com.fsalgo.core.struct.specific.DirectedGraph;

/**
 * @Author: root
 * @Date: 2023/2/12 23:29
 * @Description:
 */
public final class GraphBuilder<N> {

    private final boolean directed;

    private final boolean undirected;

    private boolean weighted;

    private boolean allowsCycles;

    private final boolean allowingSelfLoops;

    private boolean allowingMultipleEdges;

    private GraphBuilder(boolean directed, boolean undirected) {
        this.directed = directed;
        this.undirected = undirected;
        this.weighted = false;
        this.allowsCycles = true;
        this.allowingSelfLoops = false;
        this.allowingMultipleEdges = false;
    }

    public static <N> GraphBuilder<N> directed() {
        return new GraphBuilder<>(true, false);
    }

    public static <N> GraphBuilder<N> undirected() {
        return new GraphBuilder<>(false, true);
    }

    public static <N> GraphBuilder<N> mixed() {
        return new GraphBuilder<>(true, true);
    }

    public GraphBuilder<N> weighted(boolean weighted) {
        this.weighted = weighted;
        return this;
    }

    public GraphBuilder<N> allowsCycles(boolean allowsCycles) {
        this.allowsCycles = allowsCycles;
        return this;
    }

    public Graph<N> build() {
        if (directed && undirected) {
            return null;
        }
        if (!directed) {
            return new UndirectedGraph<>();
        }
        if (allowingSelfLoops) {
            return new DirectedAcyclicGraph<>();
        }
        return new DirectedGraph<>();
    }
}
