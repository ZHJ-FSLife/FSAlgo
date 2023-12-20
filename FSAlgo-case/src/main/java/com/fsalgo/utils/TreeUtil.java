package com.fsalgo.utils;

import com.fsalgo.NodeMapper;
import com.fsalgo.core.tree.vectorspace.AbstractQuadOcTree;
import com.fsalgo.core.tree.vectorspace.specific.QuadTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: root
 * @Date: 2023/12/19 9:35
 * @Description:
 */
public class TreeUtil {

    private static final String REGEX = "[\\s\\[\\]]";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public static <N> String filtration(N treeNode) {
        Matcher matcher = PATTERN.matcher(treeNode.toString());
        return matcher.replaceAll("");
    }

    /**
     * 生成mermaid树结构（方便debug校验树结构是否正确）
     * @param root 根节点
     * @param mapper lambda表达式（获取不同TreeNode的子节点用）
     * @return
     * @param <N>
     */
    public static <N> String toMermaid(N root, NodeMapper<N> mapper) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("```mermaid");
        sj.add("graph");

        Deque<N> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            N node = queue.pollFirst();
            for (N tempNode : mapper.getChildNode(node)) {
                if (tempNode == null) {
                    continue;
                }
                sj.add(filtration(node) + "-->" + filtration(tempNode));
                queue.push(tempNode);
            }
        }
        sj.add("```");
        return sj.toString();
    }
}
