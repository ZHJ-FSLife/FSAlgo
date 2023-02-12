package com.fsalgo.core.struct;

import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/8 17:13
 * @Description:
 */
public interface EdgeSetFactory<N> {

    Set<Edge<N>> createEdgeSet(N node);
}
