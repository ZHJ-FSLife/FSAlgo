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
package com.fsalgo.core.interfaces.score;

import com.fsalgo.core.interfaces.NameEntity;
import com.fsalgo.core.struct.Edge;

import java.util.Map;

/**
 * @Author: root
 * @Date: 2023/7/6 10:26
 * @Description: 边评分算法
 */
public interface EdgeScoringAlgorithm<N, D> extends NameEntity {

    /**
     * 获取所有边的评分分值
     *
     * @return 返回所有边的分值
     */
    Map<Edge<N>, D> getScores();

    /**
     * 获取指定边的评分分值
     *
     * @param edge 边
     * @return 分值
     */
    D getEdgeScore(Edge<N> edge);
}
