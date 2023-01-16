package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.AbstractGraph;
import com.fsalgo.core.struct.Edge;

/**
 * @Author: root
 * @Date: 2022/12/21 20:23
 * @Description:
 */
public class UndirectedGraph<N> extends AbstractGraph<N> {

    private static final long serialVersionUID = 6418124617932136812L;

    /**
     * 构建邻接多重表来表示无向图
     *
     * @param edge 边
     */
    @Override
    public boolean addEdge(Edge<N> edge) {

        addNode(edge.getSource());
        addNode(edge.getTarget());

        if (!edgeSet.contains(edge)) {
            edgeSet.add(edge);
            graphMap.get(edge.getSource()).add(edge);
        }
        
        Edge<N> reverseEdge = new Edge<>(edge.getTarget(), edge.getSource(), edge.getWeight());
        if (!edgeSet.contains(reverseEdge)) {
            edgeSet.add(reverseEdge);
            graphMap.get(reverseEdge.getSource()).add(reverseEdge);
        }
        return true;
    }
}
