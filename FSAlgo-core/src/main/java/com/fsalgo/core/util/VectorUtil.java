package com.fsalgo.core.util;

/**
 * @Author: root
 * @Date: 2023/4/11 12:28
 * @Description:
 */
public class VectorUtil {

    private VectorUtil() {}

    private static void assessDim(final int a, final int b) {
        if (a != b) {
            throw new IllegalArgumentException("dimension mismatch!");
        }
    }

    public static void checkDims(final int[] a, final int[] b) {
        assessDim(a.length, b.length);
    }

    public static void checkDims(final double[] a, final double[] b) {
        assessDim(a.length, b.length);
    }

    public static void checkDims(final boolean[] a, final boolean[] b) {
        assessDim(a.length, b.length);
    }
}
