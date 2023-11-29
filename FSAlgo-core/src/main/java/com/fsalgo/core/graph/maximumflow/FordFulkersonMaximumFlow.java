/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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
package com.fsalgo.core.graph.maximumflow;

import com.fsalgo.core.interfaces.flow.MaximumFlowAlgorithm;
import com.fsalgo.core.struct.Edge;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/25 11:32
 * @Description:
 */
public class FordFulkersonMaximumFlow<N> implements MaximumFlowAlgorithm<N> {

    @Override
    public Map<Edge<N>, Double> getFlowMap() {
        return null;
    }

    @Override
    public MaximumFlow<N> getMaximumFlow(N source, N target) {
        return null;
    }

    @Override
    public String getName() {
        return "ford-fulkerson";
    }
}
