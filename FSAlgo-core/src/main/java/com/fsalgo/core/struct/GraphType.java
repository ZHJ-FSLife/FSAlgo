package com.fsalgo.core.struct;

/**
 * @Author: root
 * @Date: 2022/12/23 19:57
 * @Description:
 */
public interface GraphType {

    /**
     * 允许为有向图
     *
     * @return 允许为有向图返回true
     */
    boolean isDirected();

    /**
     * 允许为无向图
     *
     * @return 允许为无向图返回true
     */
    boolean isUndirected();

    /**
     * 允许为有向+无向的混合图
     *
     * @return 允许为有向+无向的混合图返回true
     */
    boolean isMixed();

    /**
     * 允许为带权图
     *
     * @return 允许为带权图返回true
     */
    boolean isWeighted();

    /**
     * 创建图类型为有向图
     *
     * @return 返回有向图类型
     */
    GraphType asDirected();

    /**
     * 创建图类型为无向图
     *
     * @return 返回无向图类型
     */
    GraphType asUndirected();

    /**
     * 创建图类型为有向+无向的混合图
     *
     * @return 返回混合图类型
     */
    GraphType asMixed();

    /**
     * 创建图类型为带权图
     *
     * @return 返回带权图类型
     */
    GraphType asWeighted();

    /**
     * 创建图类型为无权图
     *
     * @return 返回无权图类型
     */
    GraphType asUnWeighted();
}
