package com.lz.bmp.entity;

import java.util.Date;

/**
 * 基本类型类，用于数据库存储
 *
 * @author shangang_luo
 *
 */
public class BaseEntity {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public BaseEntity() {
        this.createTime = new Date();
        this.modifyTime = new Date();
    }
}
