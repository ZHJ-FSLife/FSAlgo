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
package com.fsalgo.core.iterator;


import com.fsalgo.core.tree.Tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @Author: root
 * @Date: 2024/6/24 14:22
 * @Description:
 */
public abstract class AbstractTreeIterator<N> implements Iterator<Tree<N>> {

    /**
     * 虽然树结构的遍历不会死循环，但是为了以防万一出现环路行成图结构，
     * 加个visited确认哪些树节点已访问过了
     */
    protected final Set<Tree<N>> visited = new HashSet<>();

    protected Tree<N> root;

    protected AbstractTreeIterator(Tree<N> root) {
        this.root = root;
    }

    protected abstract Tree<N> getNext();

    protected abstract Tree<N> removeNext();

    protected abstract void addChild(Tree<N> tree);

    @Override
    public void remove() {
        removeNext();
    }

    @Override
    public boolean hasNext() {
        while (visited.contains(getNext())) {
            removeNext();
        }
        return getNext() != null;
    }

    @Override
    public Tree<N> next() {
        if (root == null) {
            throw new NullPointerException();
        }
        Tree<N> nextTree;
        if (hasNext()) {
            nextTree = removeNext();
            addChild(nextTree);
            visited.add(nextTree);
        } else {
            throw new NoSuchElementException();
        }
        return nextTree;
    }
}
