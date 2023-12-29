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

package com.fsalgo.core.tree.vectorspace.specific.bvh;

/**
 * @Author: root
 * @Date: 2023/12/29 13:34
 * @Description:
 */
public class BVHNode {

    private BoundingBox boundingBox;

    private BVHNode left;

    private BVHNode right;

    public BVHNode(BoundingBox boundingBox, BVHNode left, BVHNode right) {
        this.boundingBox = boundingBox;
        this.left = left;
        this.right = right;
    }
}
