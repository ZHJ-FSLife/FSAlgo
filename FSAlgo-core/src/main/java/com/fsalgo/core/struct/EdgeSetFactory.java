package com.fsalgo.core.struct;

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
    Set<Edge<N>> createEdgeSet(N node);
}
