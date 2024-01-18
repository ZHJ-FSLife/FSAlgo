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

package com.fsalgo.core.struct.iterator;

import com.fsalgo.core.struct.Graph;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2024/1/4 15:48
 * @Description:
 */
public abstract class AbstractGraphIterator<N> implements GraphIterator<N> {

    protected final Set<N> visited = new HashSet<>();

    protected final Graph<N> graph;

    protected N source;

    protected AbstractGraphIterator(Graph<N> graph, N source) {
        this.graph = graph;
        this.source = source;
    }

    protected abstract N getNextNode();

    protected abstract N removeNextNode();

    protected abstract void addChildNode(N node);

    @Override
    public boolean hasNext() {
        while (visited.contains(getNextNode())) {
            removeNextNode();
        }
        return getNextNode() != null;
    }

    @Override
    public N next() {
        if (source == null) {
            throw new NullPointerException();
        }
        N nextNode;
        if (hasNext()) {
            nextNode = removeNextNode();
            visited.add(nextNode);

            addChildNode(nextNode);
        } else {
            throw new NoSuchElementException();
        }
        return nextNode;
    }
}
