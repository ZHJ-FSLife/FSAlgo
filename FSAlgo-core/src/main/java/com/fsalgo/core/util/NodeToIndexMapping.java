package com.fsalgo.core.util;

import java.util.*;

/**
 * @Author: root
 * @Date: 2023/2/20 20:00
 * @Description:
 */
public class NodeToIndexMapping<N> {

    private final Map<N, Integer> nodeMap;

    public NodeToIndexMapping(List<N> nodes) {
        Objects.requireNonNull(nodes, "the input collection of nodes cannot be null");

        nodeMap = new LinkedHashMap<>(nodes.size());

        for (N node : nodes) {
            if (nodeMap.put(node, nodeMap.size()) != null) {
                throw new IllegalArgumentException("nodes are not distinct");
            }
        }
    }

    public NodeToIndexMapping(Collection<N> nodes) {
        this(
                new ArrayList<>(Objects.requireNonNull(nodes, "the input collection of nodes cannot be null"))
        );
    }

    public Map<N, Integer> getNodeMap() {
        return nodeMap;
    }

}
