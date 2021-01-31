package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 17:00
 */

public enum UserTemplateStatus {
    /**
     * 上架
     */
    SHELVE(0),

    /**
     * 下架
     */
    UN_SHELVE(1),

    /**
     * 删除
     */
    DELETE(2);

    /**
     * 模板的状态
     */
    Integer type;

    UserTemplateStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
