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

import com.fsalgo.core.iterator.AbstractNodeIterator;
import com.fsalgo.core.struct.Graph;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: root
 * @Date: 2024/1/4 15:51
 * @Description: 深度优先迭代器
 */
public class DepthFirstIterator<N> extends AbstractNodeIterator<N> {

    private Deque<N> stack = new LinkedList<>();

    private final Graph<N> graph;

    public DepthFirstIterator(Graph<N> graph) {
        this(graph, null);
    }

    public DepthFirstIterator(Graph<N> graph, N source) {
        super(source);
        this.graph = graph;
        stack.addFirst(source);
    }

    @Override
    protected N getNextNode() {
        return stack.peekFirst();
    }

    @Override
    protected N removeNextNode() {
        source = stack.removeFirst();
        return source;
    }

    @Override
    protected void addChildNode(N node) {
        for (N child : graph.outgoingNodes(node)) {
            stack.addFirst(child);
        }
    }
}
