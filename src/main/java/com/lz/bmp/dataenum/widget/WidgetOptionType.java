package com.lz.bmp.dataenum.widget;


/**
 * 控件选择
 *
 * @Author shangang_luo
 * @Date 2021/2/1 12:04
 */

public enum WidgetOptionType {


    /**
     * 标签显示值
     */
    LABEL("label"),

    /**
     * 标签code
     */
    VALUE("value"),

    /**
     * 标签id，用于树结构
     */
    ID("id"),


    /**
     * 标签id，用于树结构
     */
    CODE("code"),

    /**
     * 子节点，用于树结构
     */
    CHILDREN("children"),

    ;


    String optionType;

    WidgetOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getWidgetOptionTypeName() {
        return optionType;
    }
}
