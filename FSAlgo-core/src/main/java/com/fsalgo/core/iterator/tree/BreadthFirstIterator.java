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
package com.fsalgo.core.iterator.tree;


import com.fsalgo.core.iterator.AbstractTreeIterator;
import com.fsalgo.core.tree.Tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: root
 * @Date: 2024/6/24 14:22
 * @Description:
 */
public class BreadthFirstIterator<N> extends AbstractTreeIterator<N> {

    private final Deque<Tree<N>> queue = new LinkedList<>();

    public BreadthFirstIterator(Tree<N> root) {
        super(root);
        queue.addFirst(root);
    }

    @Override
    protected Tree<N> getNext() {
        return queue.peekFirst();
    }

    @Override
    protected Tree<N> removeNext() {
        root = queue.removeFirst();
        return root;
    }

    @Override
    protected void addChild(Tree<N> tree) {
        for (Tree<N> child : tree.getChild()) {
            queue.addLast(child);
        }
    }
}
