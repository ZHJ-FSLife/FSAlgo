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
package com.fsalgo.core.tree.heap;

import com.fsalgo.core.interfaces.NameEntity;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/1/17 13:06
 * @Description:
 */
public interface Heap<T> extends NameEntity {

    /**
     * 添加节点元素
     *
     * @param t 节点元素
     */
    void add(T t);

    /**
     * 泛型数组形式添加多元素
     *
     * @param keys 多元素
     */
    void addAll(T[] keys);

    /**
     * 泛型集合形式添加多元素
     *
     * @param keys 多元素
     */
    void addAll(List<T> keys);

    /**
     * 获取堆顶节点元素
     *
     * @return 堆顶节点元素
     */
    T peek();

    /**
     * 获取并移除堆顶节点元素
     *
     * @return 堆顶节点元素
     */
    T remove();

    /**
     * 获取堆大小
     *
     * @return 堆大小
     */
    int size();

    /**
     * 堆是否为空
     *
     * @return true or false
     */
    boolean isEmpty();

    /**
     * 比较堆中节点元素
     *
     * @param x 节点1
     * @param y 节点2
     * @return true or false
     */
    boolean compareTo(T x, T y);
}
