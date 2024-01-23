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
package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.EdgeSetFactory;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 3:44
 * @Description: 边容器，创建并存放与节点相邻的边
 */
public class EdgeContainer<N>{

    /**
     * 相邻边
     * key: 相邻节点
     * val: 相邻的边
     */
    private final Map<N, Set<Edge<N>>> adjacent;

    /**
     * 入边
     * key: 源节点
     * val: 两点之间的边
     */
    private final Map<N, Set<Edge<N>>> incoming;

    /**
     * 出边
     * key: 目标节点
     * val: 两点之间的边
     */
    private final Map<N, Set<Edge<N>>> outgoing;

    public EdgeContainer(EdgeSetFactory<N> edgeSetFactory, N node) {
        incoming = edgeSetFactory.createEdgeSet(node);
        outgoing = edgeSetFactory.createEdgeSet(node);
        adjacent = edgeSetFactory.createEdgeSet(node);
    }

    protected void setAdjacent(Edge<N> edge, N node) {
        adjacent.putIfAbsent(node, new LinkedHashSet<>());
        adjacent.get(node).add(edge);
    }

    public void setIncoming(Edge<N> edge) {
        N source = edge.getSource();

        incoming.putIfAbsent(source, new LinkedHashSet<>());
        incoming.get(source).add(edge);
        setAdjacent(edge, source);
    }

    public void setOutgoing(Edge<N> edge) {
        N target = edge.getTarget();

        outgoing.putIfAbsent(target, new LinkedHashSet<>());
        outgoing.get(target).add(edge);
        setAdjacent(edge, target);
    }

    public Map<N, Set<Edge<N>>> getAdjacent() {
        return adjacent;
    }

    public Map<N, Set<Edge<N>>> getIncoming() {
        return incoming;
    }

    public Map<N, Set<Edge<N>>> getOutgoing() {
        return outgoing;
    }

    public void removeAdjacent(N node) {
        adjacent.remove(node);
    }

    public void removeAdjacentAll(N node) {
        removeIncoming(node);
        removeOutgoing(node);
        removeAdjacent(node);
    }

    public void removeIncoming(N node) {
        incoming.remove(node);
    }

    public void removeOutgoing(N node) {
        outgoing.remove(node);
    }
}
