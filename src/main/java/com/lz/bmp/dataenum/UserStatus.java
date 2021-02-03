package com.lz.bmp.dataenum;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 14:39
 */

public enum UserStatus {
    /**
     * 删除
     */
    DELETED(true),

    /**
     * 非删除
     */
    USING(false);

    /**
     * 用户数据的状态
     */
    Boolean isDel;

    UserStatus(Boolean isDel) {
        this.isDel = isDel;
    }

    public Boolean getDel() {
        return isDel;
    }
}
