package com.fsalgo.core.tree;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2022/3/29 18:46
 * @Description: 树状数组 / 二叉索引数 / binary indexed tree
 * ========================================================
 * 将 [1, 2, 3, 4, 5, 6, 7, 8] 维护成下图所示的树形结构数组
 * ========================================================
 * len = 8 |                          36
 * len = 4 |            10
 * len = 2 |      3            11
 * len = 1 |   1     3     5       7
 * --------------------------------------------------------
 * 下标         0  1  2  3  4   5   6   7
 * ========================================================
 * ========================================================
 * --------------36
 * ----10   |    |
 * 3   |    11   |
 * |   |    |    |
 * 1   3    5    7
 */
public class BinaryIndexedTree {

    private final double[] tree;

    public BinaryIndexedTree(double[] data) {
        this.tree = new double[data.length];
        System.arraycopy(data, 0, tree, 0, data.length);
        buildTree();
    }

    public double[] getTree() {
        return this.tree;
    }

    /**
     * 构建树状数组
     * tree[i] 的父节点为 tree[i + lowBit(i + 1)]
     * 父节点的值 = 父节点 + 所有子节点
     */
    private void buildTree() {
        for (int i = 0; i < tree.length - 1; i++) {
            int temp = i + lowBit(i + 1);
            if (temp < tree.length) {
                tree[temp] += tree[i];
            }
        }
    }

    /**
     * 修改指定索引下的值，并向上更新其副节点的值
     *
     * @param index 索引
     * @param val   新值
     */
    public void updateNodeVal(int index, double val) {
        double tempVal = sumRange(index, index) - val;
        while (index < tree.length) {
            tree[index] -= tempVal;
            index += lowBit(index + 1);
        }
    }

    /**
     * 下标 0 ~ index 的和
     *
     * @param index 下标
     * @return 总和
     */
    public double sumRange(int index) {
        double sum = 0;
        index++;
        while (index > 0) {
            sum += tree[index - 1];
            index -= lowBit(index);
        }
        return sum;
    }

    /**
     * 计算指定区间的总和
     *
     * @param left  左下标
     * @param right 右下标
     * @return 总和
     */
    public double sumRange(int left, int right) {
        return sumRange(right) - sumRange(left - 1);
    }

    /**
     * 计算二进制中最低位的1
     *
     * @param n n
     * @return 二进制最低位的1
     */
    private int lowBit(int n) {
        return n & -n;
    }
}
