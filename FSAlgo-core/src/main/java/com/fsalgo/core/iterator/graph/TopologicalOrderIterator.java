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

package com.fsalgo.core.iterator.graph;

import com.fsalgo.core.other.enums.GraphTypeEnum;
import com.fsalgo.core.other.enums.exception.GraphBaseErrorEnum;
import com.fsalgo.core.iterator.AbstractNodeIterator;
import com.fsalgo.core.struct.Graph;

/**
 * @Author: root
 * @Date: 2024/1/4 15:53
 * @Description: 拓朴排序迭代器
 */
public class TopologicalOrderIterator<N> extends AbstractNodeIterator<N> {

    private final Graph<N> graph;

    public TopologicalOrderIterator(Graph<N> graph, N source) {
        super(source);
        if (!GraphTypeEnum.DIRECTED_ACYCLIC_GRAPH.equals(graph.getGraphType())) {
            throw new IllegalArgumentException(GraphBaseErrorEnum.NOT_DIRECTED_ACYCLIC_GRAPH.getDesc());
        }
        this.graph = graph;
    }

    @Override
    protected N getNextNode() {
        return null;
    }

    @Override
    protected N removeNextNode() {
        return null;
    }

    @Override
    protected void addChildNode(N node) {

    }
}
