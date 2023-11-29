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
package com.fsalgo.core.graph.strongconn;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2023/2/26 22:25
 * @Description:
 */
public class TarjanCutPointAndBridge<N> {

    private final Graph<N> graph;

    private int[] dfn;
    private int[] low;
    private boolean[] visited;
    private int searchSort = 0;
    private Map<N, Integer> indexs;

    private final Set<N> cutPoints = new HashSet<>();

    private final Set<Edge<N>> bridges = new HashSet<>();

    public TarjanCutPointAndBridge(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }

    private void initContainer() {
        this.indexs = Graphs.getNodeToIndexMapping(graph).getNodeMap();
        this.dfn = new int[indexs.size()];
        this.low = new int[indexs.size()];
        this.visited = new boolean[indexs.size()];
    }

    public void search(N root) {
        if (root == null) {
            throw new IllegalArgumentException("the access node cannot be empty!");
        }
        dfs(root);
    }

    public void search() {
        for (Map.Entry<N, Integer> entry : indexs.entrySet()) {
            N node = entry.getKey();
            if (visited[indexs.get(node)] || graph.outgoingEdges(node).isEmpty()) {
                continue;
            }
            searchSort = 1;
            search(node);
        }
    }

    private void dfs(N root) {
        if (root == null) {
            return;
        }
        int index = indexs.get(root);
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        dfn[index] = searchSort;
        low[index] = searchSort++;

        if (graph.outgoingEdges(root).isEmpty()) {
            return;
        }

        if (dfn[index] == 1 && graph.outgoingEdges(root).size() >= 2) {
            cutPoints.add(root);
        }

        for (Edge<N> edge : graph.outgoingEdges(root)) {
            N child = edge.getTarget();

            dfs(child);

            int childIndex = indexs.get(child);

            low[index] = Math.min(low[index], low[childIndex]);

            if (dfn[index] > 1 && dfn[index] <= low[childIndex]) {
                cutPoints.add(root);
            }

            if (dfn[index] < low[childIndex]) {
                bridges.add(edge);
            }
        }
    }

    public Set<N> getCutPoints() {
        return cutPoints;
    }

    public Set<Edge<N>> getBridges() {
        return bridges;
    }
}
