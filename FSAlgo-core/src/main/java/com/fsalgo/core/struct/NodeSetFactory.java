package com.fsalgo.core.struct;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/19 2:47
 * @Description:
 */
public interface NodeSetFactory<N> {

    Set<N> createNodeSet(N node);
}
