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
package com.fsalgo.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: root
 * @Date: 2024/6/21 19:42
 * @Description:
 */
public class Tree<N> {

    private N node;

    private List<Tree<N>> child;

    public Tree() {
        this(null);
    }

    public Tree(N node) {
        this(node, new ArrayList<>());
    }

    public Tree(N node, List<Tree<N>> child) {
        this.node = node;
        this.child = child;
    }

    public void setNode(N node) {
        this.node = node;
    }

    public N getNode() {
        return node;
    }

    public void setChild(List<Tree<N>> child) {
        this.child = child;
    }

    public void addChild(Tree<N> child) {
        this.child.add(child);
    }

    public void addChild(List<Tree<N>> child) {
        this.child.addAll(child);
    }

    public List<Tree<N>> getChild() {
        return child;
    }
}
