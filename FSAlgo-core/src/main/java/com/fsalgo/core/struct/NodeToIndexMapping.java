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
