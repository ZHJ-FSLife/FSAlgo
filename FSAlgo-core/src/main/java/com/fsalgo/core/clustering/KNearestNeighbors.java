/*
 * FSAlgo
 * https://github.com/ZHJ-FSLife/FSAlgo
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
package com.fsalgo.core.clustering;

/**
 * @Author: root
 * @Date: 2023/3/6 9:20
 * @Description: KNN 算法 / 最邻近规则分类
 * 将给定的样本，划分到已分类好的簇中去
 * 但是目前写了的KDTree、BallTree都是静态数据结构，构建完毕后都没法动态自平衡
 * 后期研究一下 R-Tree、M-Tree 后在来写 KNN（虽然也可以不用动态添加进簇中，直接结合目前的DBSCAN、KMeans也能实现）
 */
public class KNearestNeighbors {
}
