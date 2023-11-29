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
package com.fsalgo.core.interfaces;

import com.fsalgo.core.constant.BaseConstant;
import com.fsalgo.core.struct.Edge;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2022/12/23 21:18
 * @Description: 生成树算法
 */
public interface SpanningTreeAlgorithm<N> extends NameEntity {

    /**
     * 获取生成树
     *
     * @return 生成树
     */
    SpanningTree<N> getSpanningTree();

    /**
     * 生成树
     *
     * @param <N>
     */
    interface SpanningTree<N> {

        /**
         * 获取权重值
         *
         * @return 权重值
         */
        double getWeight();

        /**
         * 获取边集合
         *
         * @return 边集合
         */
        Set<Edge<N>> getEdges();
    }

    /**
     * 生成树接口的默认实现
     *
     * @param <N>
     */
    class SpanningTreeImpl<N> implements SpanningTree<N>, Serializable {

        private static final long serialVersionUID = BaseConstant.SERIAL_VERSION_UID;

        private final double weight;
        private final Set<Edge<N>> edges;

        public SpanningTreeImpl(Set<Edge<N>> edges, double weight) {
            this.edges = edges;
            this.weight = weight;
        }

        @Override
        public double getWeight() {
            return weight;
        }

        @Override
        public Set<Edge<N>> getEdges() {
            return edges;
        }
    }
}
