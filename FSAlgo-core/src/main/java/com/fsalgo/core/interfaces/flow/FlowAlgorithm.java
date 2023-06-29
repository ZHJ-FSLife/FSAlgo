package com.fsalgo.core.interfaces.flow;

import com.fsalgo.core.interfaces.NameEntity;
import com.fsalgo.core.struct.Edge;

import java.util.Collections;
import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/6/26 10:01
 * @Description:
 */
public interface FlowAlgorithm<N> extends NameEntity {

    /**
     * 返回流的实现类
     *
     * @return 流
     */
    default Flow<N> getFlow() {
        return new FlowImpl<>(this.getFlowMap());
    }

    /**
     * 获取流所经过边的映射值
     *
     * @return 流的映射值
     */
    Map<Edge<N>, Double> getFlowMap();

    /**
     * 流
     *
     * @param <N>
     */
    interface Flow<N> {

        default double getFlow(Edge<N> edge) {
            return getFlowMap().get(edge);
        }

        Map<Edge<N>, Double> getFlowMap();
    }

    /**
     * 流的默认实现
     *
     * @param <N>
     */
    class FlowImpl<N> implements Flow<N> {

        private final Map<Edge<N>, Double> flowMap;

        public FlowImpl(Map<Edge<N>, Double> flowMap) {
            this.flowMap = Collections.unmodifiableMap(flowMap);
        }

        @Override
        public Map<Edge<N>, Double> getFlowMap() {
            return flowMap;
        }
    }
}
