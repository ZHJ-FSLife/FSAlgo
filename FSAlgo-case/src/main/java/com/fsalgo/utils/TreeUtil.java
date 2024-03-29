package com.fsalgo.utils;

import com.fsalgo.core.interfaces.functional.TreeNodeMapper;
import com.fsalgo.core.tree.RedBlackTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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
     *
     * @param root   根节点
     * @param mapper lambda表达式（获取不同TreeNode的子节点用）
     * @param <N>
     * @return
     */
    public static <N> String toMermaid(N root, TreeNodeMapper<N> mapper) {
        return toMermaid(root, mapper, "TD");
    }

    public static <N> String toMermaid(N root, TreeNodeMapper<N> mapper, String dire) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("```mermaid");
        sj.add("graph " + dire);

        Deque<N> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            N node = queue.pollFirst();
            List<N> childs = mapper.children(node);
            if (childs == null || childs.isEmpty()) {
                sj.add("style " + filtration(node) + " fill: green");
            }
            for (N tempNode : childs) {
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

    public static String rbtree2Mermaid(RedBlackTree.Node root, TreeNodeMapper<RedBlackTree.Node> mapper) {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("```mermaid");
        sj.add("graph " + "TD");

        Deque<RedBlackTree.Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            RedBlackTree.Node node = queue.pollFirst();
            List<RedBlackTree.Node> childs = mapper.children(node);
            if (node.isRed()) {
                sj.add("style " + filtration(node) + " fill: red");
            }
            if (childs == null || childs.isEmpty()) {
                sj.add("style " + filtration(node) + " fill: green");
            }
            for (RedBlackTree.Node tempNode : childs) {
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
