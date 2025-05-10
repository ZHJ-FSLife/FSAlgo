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

import java.util.ArrayList;
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
            boundingBoxes = new ArrayList<>();
            children = new ArrayList<>();
        }

        @Override
        public boolean isFull() {
            return children.size() >= maxDegree();
        }

        public NonLeafNode<T> split() {
            LeafNode<T> nextLeafNode = new LeafNode<T>(degree);
            return parent;
        }

        public void addChild(Node<T> child) {
            addChild(0, child);
        }

        public void addChild(int index, Node<T> child) {
            children.add(index, child);
        }
    }

    static class LeafNode<T> extends Node<T> {

        BoundingBox boundingBox;

        List<SpacePoint<T>> points;

        protected LeafNode(int degree) {
            super(degree);
            points = new ArrayList<>();
        }

        @Override
        public boolean isFull() {
            return points.size() >= maxDegree();
        }

        public NonLeafNode<T> split() {
            LeafNode<T> nextLeafNode = new LeafNode<T>(degree);
            if (parent == null) {
                parent = new NonLeafNode<T>(degree);
                parent.addChild(this);
            }
            parent.addChild(nextLeafNode);

            int dimension = boundingBox.largestGapDimension();
            double mid = mid(dimension);
            for (int i = points.size() - 1; i >= 0; i--) {
                if (points.get(i).getCoord()[dimension] > mid) {
                    nextLeafNode.points.add(points.remove(i));
                }
            }
            return parent;
        }

        private double mid(int dimension) {
            double min = points.get(0).getCoord()[dimension];
            double max = points.get(0).getCoord()[dimension];
            for (SpacePoint<T> point : points) {
                min = Math.min(min, point.getCoord()[dimension]);
                max = Math.max(max, point.getCoord()[dimension]);
            }
            return (max + min) / 2;
        }
    }
}
