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
package com.fsalgo.core.graph.shortestpath;

import com.fsalgo.core.interfaces.ShortestPathAlgorithm;

/**
 * @Author: root
 * @Date: 2022/12/21 19:03
 * @Description: 最短路径 - A星算法
 */
public class AStarShortestPath<N> implements ShortestPathAlgorithm<N> {

    @Override
    public GraphPath<N> getPath(N source, N target) {
        return null;
    }

    @Override
    public double getPathWeight(N source, N target) {
        return 0;
    }

    @Override
    public String getName() {
        return "a-start";
    }
}
