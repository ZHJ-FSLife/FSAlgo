package com.fsalgo;

import java.util.List;

/**
 * @Author: root
 * @Date: 2023/12/19 9:31
 * @Description:
 */
@FunctionalInterface
public interface NodeMapper<N> {

    List<N> getChildNode(N treeNode);

}
