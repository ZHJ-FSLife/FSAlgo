package com.fsalgo.core.struct;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 2:47
 * @Description:
 */
public interface NodeSetFactory<N> {

    /**
     * 为节点创建邻接节点的集合
     *
     * @param node 节点
     * @return 节点集合
     */
    Set<N> createNodeSet(N node);
}
