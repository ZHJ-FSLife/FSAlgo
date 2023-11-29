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
