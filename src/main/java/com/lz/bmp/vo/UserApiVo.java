package com.lz.bmp.vo;

import com.lz.bmp.entity.user.UserCommonInfo;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:47
 */

public class UserApiVo {

    /**
     * 用户必填的4个属性
     */
    private UserCommonInfo userCommonInfo;

    /**
     * 用户基本属性
     */
    private Map<String, String> userBasicInfoMap;

    /**
     * 用户扩展属性
     */
    private Map<String, List<Map<String, String>>> userExtendInfoMap;

    public UserCommonInfo getUserCommonInfo() {
        return userCommonInfo;
    }

    public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
        this.userCommonInfo = userCommonInfo;
    }

    public Map<String, String> getUserBasicInfoMap() {
        return userBasicInfoMap;
    }

    public void setUserBasicInfoMap(Map<String, String> userBasicInfoMap) {
        this.userBasicInfoMap = userBasicInfoMap;
    }

    public Map<String, List<Map<String, String>>> getUserExtendInfoMap() {
        return userExtendInfoMap;
    }

    public void setUserExtendInfoMap(Map<String, List<Map<String, String>>> userExtendInfoMap) {
        this.userExtendInfoMap = userExtendInfoMap;
    }

    @Override
    public String toString() {
        return "UserApiVo{" +
                "userCommonInfo=" + userCommonInfo +
                ", userBasicInfoMap=" + userBasicInfoMap +
                ", userExtendInfoMap=" + userExtendInfoMap +
                '}';
    }
}
