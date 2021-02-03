package com.lz.bmp.entity.user;

import com.lz.bmp.entity.BaseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 15:03
 */

public class User  extends BaseEntity {

    /**
     * 用户必填的4个属性
     */
    private UserCommonInfo userCommonInfo;

    /**
     * 用户基本属性
     */
    private Map<String, Map<String, String>> sourceBasicInfoMap;

    /**
     * 用户扩展属性
     */
    private Map<String, List<Map<String, String>>> userExtendInfoMap;
    /**
     * 数据是否删除，false表示存在
     */
    private Boolean isDel;

    public UserCommonInfo getUserCommonInfo() {
        return userCommonInfo;
    }

    public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
        this.userCommonInfo = userCommonInfo;
    }

    public Map<String, Map<String, String>> getSourceBasicInfoMap() {
        return sourceBasicInfoMap;
    }

    public void setSourceBasicInfoMap(Map<String, Map<String, String>> sourceBasicInfoMap) {
        this.sourceBasicInfoMap = sourceBasicInfoMap;
    }

    public Map<String, List<Map<String, String>>> getUserExtendInfoMap() {
        return userExtendInfoMap;
    }

    public void setUserExtendInfoMap(Map<String, List<Map<String, String>>> userExtendInfoMap) {
        this.userExtendInfoMap = userExtendInfoMap;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCommonInfo=" + userCommonInfo +
                ", sourceBasicInfoMap=" + sourceBasicInfoMap +
                ", userExtendInfoMap=" + userExtendInfoMap +
                ", isDel=" + isDel +
                '}';
    }
}
