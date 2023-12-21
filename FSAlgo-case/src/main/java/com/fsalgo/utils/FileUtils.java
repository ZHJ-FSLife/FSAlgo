package com.fsalgo.utils;

import cn.hutool.core.io.FileUtil;

/**
 * @Author: root
 * @Date: 2023/11/29 14:43
 * @Description:
 */
public class FileUtils {

    private final static String GRAPH_VIEW_PATH = "../";

    public static void toMdFile(String content, String fileName) {
        toMdFile(content, fileName, false);
    }

    public static void toMdFile(String content, String fileName, boolean isAppend) {
        if (isAppend) {
            FileUtil.appendUtf8String("\n", GRAPH_VIEW_PATH + fileName + ".md");
            FileUtil.appendUtf8String(content, GRAPH_VIEW_PATH + fileName + ".md");
            return;
        }
        FileUtil.writeUtf8String(content, GRAPH_VIEW_PATH + fileName + ".md");
    }
}
