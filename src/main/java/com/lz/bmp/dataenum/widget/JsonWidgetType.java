package com.lz.bmp.dataenum.widget;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 17:07
 */

public enum JsonWidgetType {
    /**
     * 文本输入框
     */
    INPUT("input"),

    /**
     * 多行文本框
     */
    TEXTAREA("textarea"),

    /**
     * 下拉单选框(非树结构)
     */
    SELECTNONETREE("select1"),

    /**
     * 下拉单选框(树结构)
     */
    SELECTTREE("select2"),

    /**
     * 下拉多选框
     */
    SELECTMULTI("select3"),

    /**
     * 单选框
     */
    RADIO("radio"),

    /**
     * 多选框
     */
    CHECKBOX("checkbox"),

    /**
     * 年份选择
     */
    YEAR("year"),

    /**
     * 月份选择
     */
    MONTH("month"),

    /**
     * 日期选择
     */
    DATE("date"),

    /**
     * 经纬度选择
     */
    LATLNG("latLng"),

    /**
     * 图片上传
     */
    IMAGEUPLOAD("imageUpload");


    String widgetType;

    JsonWidgetType(String widgetType) {
        this.widgetType = widgetType;
    }

    public String getJsonWidgetTypeName() {
        return widgetType;
    }
}
