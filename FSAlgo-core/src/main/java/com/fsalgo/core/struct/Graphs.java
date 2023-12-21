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
package com.fsalgo.core.struct;

import java.util.Collection;
import java.util.Objects;

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
