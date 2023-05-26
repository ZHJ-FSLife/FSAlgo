package com.fsalgo.core.graph;

import com.fsalgo.core.struct.Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: root
 * @Date: 2023/5/26 9:14
 * @Description: 鲁汶算法，社区发现
 */
public class LouvainCommunityDetection<N> {

    private final Graph<N> graph;

    private Map<N, N> communityMap;

    public LouvainCommunityDetection(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        if (Objects.isNull(graph)) {
            return;
        }
        // 初始化每个节点所属的社区
        communityMap = new HashMap<>();
        for (N node : graph.nodes()) {
            communityMap.put(node, node);
        }
    }
}
