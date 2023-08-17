package com.fsalgo.core.enums.exception;

/**
 * @Author: root
 * @Date: 2023/6/30 12:40
 * @Description:
 */
public enum GraphBaseErrorEnum implements BaseErrorEnum {

    NODE_NOT_EXIST("节点不存在!");

    private final String desc;

    GraphBaseErrorEnum(String desc) {
        this.desc = desc;
    }

    @Override
    public Object getCode() {
        return this.name();
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
