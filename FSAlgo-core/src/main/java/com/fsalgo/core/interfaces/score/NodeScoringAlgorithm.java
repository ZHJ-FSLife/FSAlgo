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

import java.util.Map;

/**
 * @Author: root
 * @Date: 2022/12/23 21:47
 * @Description: 节点评分算法
 */
public interface NodeScoringAlgorithm<N, D> extends NameEntity {

    /**
     * 获取所有节点的评分分值
     *
     * @return 返回所有节点的分值
     */
    Map<N, D> getScores();

    /**
     * 获取指定节点的分值
     *
     * @param n 节点
     * @return 分值
     */
    D getNodeScore(N n);

}
