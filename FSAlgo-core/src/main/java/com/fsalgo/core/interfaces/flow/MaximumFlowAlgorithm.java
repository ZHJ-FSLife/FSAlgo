package com.fsalgo.core.interfaces.flow;

import com.fsalgo.core.struct.Edge;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/26 9:59
 * @Description:
 */
public interface MaximumFlowAlgorithm<N> extends FlowAlgorithm<N> {

    /**
     * 获取起始节点到目标节点的最大流
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 最大流
     */
    MaximumFlow<N> getMaximumFlow(N source, N target);

    /**
     * 获取起始节点source到目标节点target的最大流的值
     *
     * @param source 起始节点
     * @param target 目标节点
     * @return 最大流的值
     */
    default double getMaximumFlowValue(N source, N target) {
        return getMaximumFlow(source, target).getValue();
    }

    /**
     * 最大流
     *
     * @param <N>
     */
    interface MaximumFlow<N> extends Flow<N> {
        Double getValue();
    }

    /**
     * 最大流默认实现
     *
     * @param <N>
     */
    class MaximumFlowImpl<N> extends FlowImpl<N> implements MaximumFlow<N> {

        private Double value;

        public MaximumFlowImpl(Double value, Map<Edge<N>, Double> flow) {
            super(flow);
            this.value = value;
        }

        @Override
        public Double getValue() {
            return value;
        }
    }
}
