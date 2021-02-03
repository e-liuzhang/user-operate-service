package com.lz.bmp.dataenum.widget;

/**
 * 数据源类型类
 *
 * @Author shangang_luo
 * @Date 2021/2/1 12:04
 */

public enum WidgetSourceType {


    /**
     * 自定义数据源
     */
    PAGE_DEFINED(1),

    /**
     * 外部服务数据源
     */
    SYSTEM_DEFINED(2);


    Integer sourceType;

    WidgetSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getWidgetSourceType() {
        return sourceType;
    }
}
