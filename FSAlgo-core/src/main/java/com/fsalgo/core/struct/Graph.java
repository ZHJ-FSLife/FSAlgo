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

import com.fsalgo.core.other.enums.GraphTypeEnum;
import com.fsalgo.core.interfaces.NameEntity;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 1:55
 * @Description:
 */
public interface Graph<N> extends NameEntity {

    /**
     * 添加结点
     *
     * @param node 节点
     */
    void addNode(N node);

    /**
     * 添加边
     *
     * @param edge 边
     */
    void addEdge(Edge<N> edge);

    /**
     * 添加边
     *
     * @param source 源节点
     * @param target 目标节点
     */
    void addEdge(N source, N target);

    /**
     * 添加边
     *
     * @param source 源节点
     * @param target 目标节点
     * @param weight 权重值
     */
    void addEdge(N source, N target, double weight);

    /**
     * 从图中移除指定节点（包含起相连的所有边）
     *
     * @param node 节点
     */
    void removeNode(N node);

    /**
     * 移除图中指定的边
     *
     * @param edge 边
     */
    void removeEdge(Edge<N> edge);

    /**
     * 移除图中两节点间所有的边
     *
     * @param source 源节点
     * @param target 目标节点
     */
    void removeEdge(N source, N target);

    /**
     * 移除图中两节点间指定的一条边
     *
     * @param source 源节点
     * @param target 目标节点
     * @param edge   边
     */
    void removeEdge(N source, N target, Edge<N> edge);

    /**
     * 获取源节点指向目标节点所有边
     *
     * @param source 源节点
     * @param target 目标节点
     * @return 返回源节点指向目标节点所有的边
     */
    Set<Edge<N>> getEdge(N source, N target);

    /**
     * 获取图中节点的数量
     *
     * @return 返回图中节点的总数量
     */
    int nodeSize();

    /**
     * 获取图中边的数量
     *
     * @return 返回图中边的总数量
     */
    int edgeSize();

    /**
     * 图中所有的节点
     *
     * @return 返回图中所有的节点
     */
    Set<N> nodes();

    /**
     * 图中所有的边
     *
     * @return 返回图中所有的边
     */
    Set<Edge<N>> edges();

    /**
     * 获取那些不可达的根节点、或孤儿节点
     *
     * @return 不可达的节点
     */
    Set<N> unattainableNodes();

    /**
     * 该节点相邻的所有节点
     *
     * @param node 节点
     * @return 返回相邻的所有节点
     */
    Set<N> adjacentNodes(N node);

    /**
     * 由相邻的边所指向该节点进来的节点
     *
     * @param node 节点
     * @return 返回所有所有指向该节点的相邻节点
     */
    Set<N> incomingNodes(N node);

    /**
     * 该节点由相邻的边所指向出去的节点
     *
     * @param node 节点
     * @return 返回所该节点指向的相邻节点
     */
    Set<N> outgoingNodes(N node);

    /**
     * 与该节点相邻的所有边
     *
     * @param node 节点
     * @return 返回与该节点所有相邻的边
     */
    Set<Edge<N>> adjacentEdges(N node);

    /**
     * 与该节点相连进来的边
     *
     * @param node 节点
     * @return 返回所有指向该节点相连的边
     */
    Set<Edge<N>> incomingEdges(N node);

    /**
     * 与该节点相连出去的边
     *
     * @param node 节点
     * @return 返回该节点指向出去的边
     */
    Set<Edge<N>> outgoingEdges(N node);

    /**
     * 源节点与目标节点之间是否存在至少一条边相连
     *
     * @param source 源节点
     * @param target 目标节点
     * @return true or false
     */
    boolean hasEdgeConnecting(N source, N target);

    /**
     * 源节点与目标节点之间是否存在一条指定的边连接两个节点
     *
     * @param source 源节点
     * @param target 目标节点
     * @param edge   指定的边
     * @return true or false
     */
    boolean hasEdgeConnecting(N source, N target, Edge<N> edge);

    /**
     * 图中是否包含由源节点到目标节点的边
     *
     * @param source 源节点
     * @param target 目标节点
     * @return 是否包含指定原点到目标节点的边
     */
    boolean containsEdge(N source, N target);

    /**
     * 图中是否包含该节点
     *
     * @param node 节点
     * @return 是否包含节点
     */
    boolean containsNode(N node);

    /**
     * 图中是否包含该边的存在
     *
     * @param edge 边
     * @return 是否包含边
     */
    boolean containsEdge(Edge<N> edge);

    /**
     * 获取当前图的类型
     *
     * @return 返回图类型
     */
    GraphTypeEnum getGraphType();

}
