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
package com.fsalgo.core.other.util;

import com.fsalgo.core.interfaces.functional.NodeMapper;
import com.fsalgo.core.tree.Tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: root
 * @Date: 2024/6/21 19:51
 * @Description:
 */
public class TreeUtil {

    private TreeUtil() {
    }

    /**
     * 构建树型结构
     *
     * @param nodes  节点集合，包含所有需要构建为树的节点
     * @param self   函数式接口，用于获取本节点的唯一标识符
     * @param parent 函数式接口，用于获取父节点的唯一标识符
     * @param <K>    节点标识符类型
     * @param <N>    节点类型
     * @return 树结构构建后的根节点
     */
    public static <K, N> Tree<N> buildTree(List<N> nodes, NodeMapper<K, N> self, NodeMapper<K, N> parent) {
        return buildTree(Tree::new, nodes, self, parent);
    }

    /**
     * 构建树型结构
     *
     * @param constructor 用于构造 Tree 或其子类实例的方法引用或 lambda 表达式
     * @param nodes       节点集合，包含所有需要构建为树的节点
     * @param self        函数式接口，用于获取本节点的唯一标识符
     * @param parent      函数式接口，用于获取父节点的唯一标识符
     * @param <K>         节点标识符类型
     * @param <N>         节点类型
     * @param <T>         Tree 或其子类类型
     * @return 树结构构建后的根节点
     */
    public static <K, N, T extends Tree<N>> Tree<N> buildTree(Function<N, T> constructor, List<N> nodes, NodeMapper<K, N> self, NodeMapper<K, N> parent) {
        Map<K, N> nodeMap = new HashMap<>();
        Map<N, Tree<N>> treeMap = new HashMap<>();
        for (N node : nodes) {
            nodeMap.put(self.getKey(node), node);
            treeMap.put(node, constructor.apply(node));
        }

        Tree<N> root = constructor.apply(null);

        for (N node : nodes) {
            N parentNode = nodeMap.get(parent.getKey(node));
            if (parentNode == null) {
                root.addChild(treeMap.get(node));
                continue;
            }
            Tree<N> parentTreeNode = treeMap.get(parentNode);
            parentTreeNode.addChild(treeMap.get(node));
        }
        return root;
    }
}
