/*
 * FSAlgo
 * hhttps://github.com/ZHJ-FSLife/FSAlgo
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
package com.fsalgo.core.tree;

import java.util.Comparator;

/**
 * @Author: root
 * @Date: 2023/3/23 19:38
 * @Description: 红黑树
 * 1、每个节点都是红色或黑色
 * 2、根节点是黑色
 * 3、每个叶子节点（NIL节点）都是黑色的
 * 4、如果一个节点是红色的，则它的两个子节点都是黑色的
 * 5、对于每个节点，从该节点到其所有后代叶子节点的简单路径上，均包含相同数量的黑色节点
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    private final Comparator<? super K> comparator;

    public RedBlackTree() {
        this(Comparator.naturalOrder());
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }
}
