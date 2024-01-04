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

/**
 * @Author: root
 * @Date: 2024/1/4 15:53
 * @Description: 拓朴排序迭代器
 */
public class TopologicalOrderIterator<N> extends AbstractGraphIterator<N> {

    protected TopologicalOrderIterator(Graph<N> graph) {
        super(graph);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public N next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
