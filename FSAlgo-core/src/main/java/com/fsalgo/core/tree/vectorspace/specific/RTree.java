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

    /**
     * 度，2 * degree - 1
     */
    private final int degree;

    protected RTree(int degree, DistanceMetric distanceMetric) {
        super(distanceMetric);
        if (degree <= 1) {
            throw new IllegalArgumentException("The degree cannot be less than 2");
        }
        this.degree = degree;
    }

    public void add(SpacePoint<T> point) {
        if (root == null) {
            root = new LeafNode<>(degree);
            root.setBoundingBox(new BoundingBox(point.getCoord()));
            root.addPoint(point);
            return;
        }
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

        int currDimension;

        NonLeafNode<T> parent;

        BoundingBox boundingBox;

        protected Node(int degree) {
            this.degree = degree;
        }

        protected int maxDegree() {
            return 2 * degree - 1;
        }

        public void setBoundingBox(BoundingBox boundingBox) {
            this.boundingBox = boundingBox;
        }

        abstract public boolean isFull();

        abstract public void addPoint(SpacePoint<T> point);

        abstract public BoundingBox findBounding(Node<T> node);

        abstract public BoundingBox findBounding();
    }

    static class NonLeafNode<T> extends Node<T> {

        List<Node<T>> children;

        protected NonLeafNode(int degree) {
            super(degree);
            children = new ArrayList<>();
        }

        @Override
        public boolean isFull() {
            return children.size() >= maxDegree();
        }

        @Override
        public void addPoint(SpacePoint<T> point) {

        }

        @Override
        public BoundingBox findBounding(Node<T> node) {
            return null;
        }

        @Override
        public BoundingBox findBounding() {
            return null;
        }

        public NonLeafNode<T> split() {
            NonLeafNode<T> nextNonLeafNode = new NonLeafNode<T>(degree);
            if (parent == null) {
                parent = new NonLeafNode<T>(degree);
                parent.addChild(this);
            }
            parent.addChild(nextNonLeafNode);
            int dimension = boundingBox.largestGapDimension();
            for (int i = children.size() - 1; i >= 0; i--) {
                BoundingBox childBounding = children.get(i).boundingBox;
                if (childBounding.getMin()[dimension] > dimension) {
                    nextNonLeafNode.addChild(children.remove(i));
                }
            }
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

        List<SpacePoint<T>> points;

        protected LeafNode(int degree) {
            super(degree);
            points = new ArrayList<>();
        }

        @Override
        public boolean isFull() {
            return points.size() >= maxDegree();
        }

        @Override
        public void addPoint(SpacePoint<T> point) {
            points.add(point);
            for (int i = 0; i < points.size(); i++) {
                boundingBox.updateMin(i, Math.min(boundingBox.getMin()[i], point.getCoord()[i]));
                boundingBox.updateMax(i, Math.max(boundingBox.getMax()[i], point.getCoord()[i]));
            }
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
            nextLeafNode.boundingBox = findBounding(nextLeafNode);
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

        @Override
        public BoundingBox findBounding(Node<T> node) {
            return node.findBounding();
        }

        @Override
        public BoundingBox findBounding() {
            double[] min = points.get(0).getCoord();
            double[] max = points.get(0).getCoord();
            for (SpacePoint<T> point : points) {
                double[] current = point.getCoord();
                for (int i = 0; i < current.length; i++) {
                    min[i] = Math.min(min[i], current[i]);
                    max[i] = Math.max(max[i], current[i]);
                }
            }
            return new BoundingBox(min, max);
        }
    }
}
