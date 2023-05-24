package com.fsalgo.core.tree.vectorspace;

/**
 * @Author: root
 * @Date: 2023/5/24 10:19
 * @Description:
 */
public abstract class AbstractQuadOcTree<T> implements NearestNeighborSearch<T> {

    protected int dimension;
    protected int childNums;

    protected AbstractQuadOcTree() {
        this.dimension = getDimension();
        this.childNums = getChildNums();
    }

    protected abstract int getDimension();

    protected abstract int getChildNums();

    /**
     * 计算目标节点坐标位于子节点的具体坐标位置（二维：四象限；三维：八分体）
     *
     * @param center 原点坐标
     * @param target 目标点坐标
     * @return 索引
     */
    protected abstract int calcCoordinateIndex(double[] center, double[] target);

    public class Node<T extends Comparable<T>> {
        // 点坐标信息
        private final SpacePoint<T> point;

        // 是否为叶子节点
        private final boolean leaf;

        // 子节点，数组索引对应二维坐标系中的四象限，和三维坐标系中的八分体
        private Node<T>[] child;

        public Node(SpacePoint<T> point) {
            this(point, false);
        }

        public Node(SpacePoint<T> point, boolean leaf) {
            this(point, leaf, dimension);

        }

        public Node(SpacePoint<T> point, boolean leaf, int dimension) {
            this.point = point;
            this.leaf = leaf;
            if (!leaf) {
                child = new Node[childNums];
            }
        }

        public SpacePoint<T> getPoint() {
            return point;
        }

        public Node<T>[] getChild() {
            return child;
        }

        public boolean getLeaf() {
            return leaf;
        }
    }
}
