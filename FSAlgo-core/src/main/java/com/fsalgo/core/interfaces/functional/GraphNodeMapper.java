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

package com.fsalgo.core.interfaces.functional;

import com.fsalgo.core.struct.Graph;

import java.util.Collection;

/**
 * @Author: root
 * @Date: 2024/1/19 10:32
 * @Description:
 */
@FunctionalInterface
public interface GraphNodeMapper<N> {

    /**
     * 获取图结构下任意指定节点指向出去的所有相邻节点
     *
     * @param graph 图结构
     * @param node 任意指定节点
     * @return 向外指向的所有相邻节点
     */
    Collection<N> outgoingNodes(Graph<N> graph, N node);

}
