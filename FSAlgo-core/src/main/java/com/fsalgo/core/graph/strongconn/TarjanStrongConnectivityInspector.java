package com.fsalgo.core.graph.strongconn;

import com.fsalgo.core.struct.Edge;
import com.fsalgo.core.struct.Graph;
import com.fsalgo.core.struct.Graphs;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/12/21 21:07
 * @Description: Tarjan - 强连通分量图算法
 */
public class TarjanStrongConnectivityInspector<N> {

    private final Graph<N> graph;
    /**
     * 节点被访问的最早时间
     */
    private int[] dfn;

    /**
     * 节点通过有向图可连通/回溯的最早时间点
     */
    private int[] low;

    /**
     * 节点访问标记
     */
    private boolean[] visited;

    /**
     * 节点索引位置
     */
    private Map<N, Integer> indexs;

    public TarjanStrongConnectivityInspector(Graph<N> graph) {
        this.graph = graph;
        initContainer();
    }
    
    private void initContainer() {
        this.indexs = Graphs.getNodeToIndexMapping(graph).getNodeMap();
        this.dfn = new int[indexs.size()];
        this.low = new int[indexs.size()];
        this.visited = new boolean[indexs.size()];
    }

    private int searchSort = 0;

    private final Deque<N> stack = new LinkedList<>();

    /**
     * 割点 - 结果集
     */
    private final Set<N> cutNodeRst = new HashSet<>();

    /**
     * 桥 - 结果集
     */
    private final Set<Edge<N>> connEdgeRst = new HashSet<>();

    /**
     * 连通子图结果集
     */
    private final List<List<N>> connGraphRst = new LinkedList<>();

    /**
     * 指定起始节点开始搜索图
     *
     * @param root 根节点
     */
    public void searchGraph(N root) {
        if (root == null) {
            throw new IllegalArgumentException("the access node cannot be empty!");
        }
        dfs(root);
    }

    /**
     * 所有节点都作为起始节点搜索一遍图
     * 已被访问过 或 无下一节点 的节点均略过
     */
    public void searchGraph() {
        for (Map.Entry<N, Integer> entry : indexs.entrySet()) {
            N node = entry.getKey();
            if (visited[indexs.get(node)] || graph.outgoingEdges(node).isEmpty()) {
                continue;
            }
            searchSort = 1;
            searchGraph(node);
        }
    }

    /**
     * 深度搜索筛查图节点
     *
     * @param root 起始节点
     */
    public void dfs(N root) {
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
        stack.push(root);

        if (!graph.outgoingEdges(root).isEmpty()) {

            isCutNodeByChildNum(index, graph.outgoingEdges(root).size(), root);

            // 深度搜索当前节点与下一节点，记录可回溯访问的最早时间点
            for (Edge<N> edge : graph.outgoingEdges(root)) {
                N child = edge.getTarget();

                dfs(child);

                int childIndex = indexs.get(child);

                // 当前节点与下一子节点可回溯的最早时间点，取最小值
                low[index] = Math.min(low[index], low[childIndex]);

                isCutNodeByIndex(index, childIndex, root);
                isConnEdge(index, childIndex, edge);
            }
        }

        // 取出栈中的连通子图
        if (dfn[index] == low[index]) {
            getConnSubGraph();
        }
    }

    /**
     * 通过子节点数判断当前节点是否为割点
     *
     * @param index    当前节点索引值
     * @param childNum 下一子节点数
     * @param node     节点
     */
    private void isCutNodeByChildNum(int index, int childNum, N node) {
        if (dfn[index] == 1 && childNum >= 2) {
            cutNodeRst.add(node);
        }
    }

    /**
     * 通过索引判断当前节点是否为割点
     *
     * @param index      当前节点索引值
     * @param childIndex 下一节点索引值
     * @param node       节点
     */
    private void isCutNodeByIndex(int index, int childIndex, N node) {
        if (dfn[index] > 1 && dfn[index] <= low[childIndex]) {
            cutNodeRst.add(node);
        }
    }

    /**
     * 判断当前边是否为桥
     *
     * @param index      当前节点索引值
     * @param childIndex 下一节点索引值
     * @param edge       当前节点与下一节点之间的边
     */
    private void isConnEdge(int index, int childIndex, Edge<N> edge) {
        if (dfn[index] < low[childIndex]) {
            connEdgeRst.add(edge);
        }
    }

    /**
     * 出栈处理，筛选连通子图结构
     */
    private void getConnSubGraph() {
        List<N> list = new LinkedList<>();
        while (!stack.isEmpty()) {
            int nodeIndex = indexs.get(stack.peek());
            if (dfn[nodeIndex] == low[nodeIndex]) {
                list.add(stack.pop());
                break;
            }
            list.add(stack.pop());
        }
        if (!list.isEmpty()) {
            connGraphRst.add(list);
        }
    }

    /**
     * 获取割点结果集
     */
    public Set<N> getCutNodeRst() {
        return cutNodeRst;
    }

    /**
     * 获取桥的结果集
     */
    public Set<Edge<N>> getConnEdgeRst() {
        return connEdgeRst;
    }

    /**
     * 获取连通子图的结果集
     */
    public List<List<N>> getConnGraphRst() {
        return connGraphRst;
    }

}
