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

package com.fsalgo.core.tree.vectorspace.specific;

import com.fsalgo.core.math.geometrical.DistanceMetric;
import com.fsalgo.core.tree.vectorspace.AbstractNearestNeighborSearch;
import com.fsalgo.core.tree.vectorspace.BoundingBox;
import com.fsalgo.core.tree.vectorspace.SpacePoint;

import java.util.List;

/**
 * @Author: root
 * @Date: 2024/4/13 20:59
 * @Description: Rectangle Tree
 */
public class RTree<T> extends AbstractNearestNeighborSearch<T> {

    private Node<T> root;

    protected RTree(DistanceMetric distanceMetric) {
        super(distanceMetric);
    }

    public void add(SpacePoint<T> point) {

    }

    private LeafNode<T> findLeafNode(SpacePoint<T> point) {
        return null;
    }

    @Override
    public SpacePoint<T> nearest(SpacePoint<T> point) {
        return null;
    }

    @Override
    public List<SpacePoint<T>> range(SpacePoint<T> point, double radius) {
        return List.of();
    }

    @Override
    public String getName() {
        return "Rectangle-Tree";
    }

    abstract static class Node<T> {

        final int degree;

        NonLeafNode<T> parent;

        protected Node(int degree) {
            this.degree = degree;
        }

        protected int maxDegree() {
            return 2 * degree - 1;
        }

        abstract public boolean isFull();
    }

    static class NonLeafNode<T> extends Node<T> {

        List<BoundingBox> boundingBoxes;

        List<Node<T>> children;

        protected NonLeafNode(int degree) {
            super(degree);
        }

        @Override
        public boolean isFull() {
            return false;
        }

        public NonLeafNode<T> split() {
            return null;
        }
    }

    static class LeafNode<T> extends Node<T> {

        List<SpacePoint<T>> point;

        protected LeafNode(int degree) {
            super(degree);
        }

        @Override
        public boolean isFull() {
            return false;
        }

        public NonLeafNode<T> split() {
            return null;
        }
    }
}
