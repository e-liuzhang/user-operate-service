package com.lz.bmp.entity.datasource;

import java.io.Serializable;

/**
 * @Author yu_tao
 * @Date 2020/12/24
 */
public class MongoField implements Serializable {

    private static final long serialVersionUID = -2444504709387394671L;

    /**
     * findAllAndRemove字段的实体类必须含有id字段
     */
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
