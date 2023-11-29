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
        FileUtil.writeUtf8String(content, GRAPH_VIEW_PATH + fileName + ".md");
    }
}
