package com.fsalgo.core.struct.specific;

import com.fsalgo.core.struct.AbstractGraph;
import com.fsalgo.core.struct.Edge;

/**
 * @Author: root
 * @Date: 2022/12/16 20:07
 * @Description:
 */
public class DirectedGraph<N> extends AbstractGraph<N> {

    private static final long serialVersionUID = -39572376289019248L;

    /**
     * 构建邻接表来表示有向图
     *
     * @param edge 边
     */
    @Override
    public boolean addEdge(Edge<N> edge) {
        addNode(edge.getSource());
        addNode(edge.getTarget());

        boolean modified = false;

        if (!edgeSet.contains(edge)) {
            modified = edgeSet.add(edge);
            modified |= graphMap.get(edge.getSource()).add(edge);
        }
        return modified;
    }

}
