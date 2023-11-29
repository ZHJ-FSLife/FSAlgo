/*
 * FSAlgo
 * https://github.com/H-f-society/FSAlgo
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
package com.fsalgo.core.struct;

import java.util.Objects;

/**
 * @Author: root
 * @Date: 2022/12/16 21:55
 * @Description:
 */
public class Edge<N> {

    private N source;

    private N target;

    private double weight = 0.0D;

    public Edge() {

    }

    public Edge(N source, N target) {
        this(source, target, 0.0D);
    }

    public Edge(N source, N target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public void setSource(N source) {
        this.source = source;
    }

    public void setTarget(N target) {
        this.target = target;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public N getSource() {
        return source;
    }

    public N getTarget() {
        return target;
    }

    public double getWeight() {
        return weight;
    }

    public Edge<N> inversion() {
        return new Edge<>(target, source, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, weight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Edge<?> edge = (Edge<?>) obj;
        return Objects.equals(source, edge.getSource()) && Objects.equals(target, edge.getTarget()) && (weight == edge.getWeight());
    }

    @Override
    public String toString() {
        return "(" + source + " -> " + target + " : " + weight + ")";
    }
}
