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

package com.fsalgo.core.interfaces.functional;

import java.util.List;

/**
 * @Author: root
 * @Date: 2024/1/18 21:05
 * @Description:
 */
@FunctionalInterface
public interface TreeNodeMapper<N> {

    /**
     * 获取树结构下指定节点的所有孩子节点
     *
     * @param root 根节点、指定节点
     * @return 所有的孩子节点
     */
    List<N> children(N root);

}
