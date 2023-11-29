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
package com.fsalgo.core.struct;

import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/8 17:13
 * @Description:
 */
public interface EdgeSetFactory<N> {

    /**
     * 为节点创建邻近边的集合
     *
     * @param node 节点
     * @return 边集合
     */
    Map<N, Set<Edge<N>>> createEdgeSet(N node);
}
