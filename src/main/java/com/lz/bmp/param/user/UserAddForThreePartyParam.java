package com.lz.bmp.param.user;

import com.lz.bmp.entity.user.UserCommonInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 11:10
 */

public class UserAddForThreePartyParam {

    /**
     * 用户必填的2个属性
     */
    private UserCommonInfo userCommonInfo;

    /**
     * 用户基本属性
     */
    private Map<String, Map<String, String>> userBasicInfoMap;

    /**
     * 用户一层扩展属性
     */
    private Map<String, List<Map<String, String>>> userExtendInfoMap;

    /**
     * 数据是否删除，false表示存在
     */
    private Boolean isDel;

    private Date createTime;

    private Date modifyTime;

    public UserCommonInfo getUserCommonInfo() {
        return userCommonInfo;
    }

    public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
        this.userCommonInfo = userCommonInfo;
    }

    public Map<String, Map<String, String>> getUserBasicInfoMap() {
        return userBasicInfoMap;
    }

    public void setUserBasicInfoMap(Map<String, Map<String, String>> userBasicInfoMap) {
        this.userBasicInfoMap = userBasicInfoMap;
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
        return "UserAddForThreePartyParam{" +
                "userCommonInfo=" + userCommonInfo +
                ", userBasicInfoMap=" + userBasicInfoMap +
                ", userExtendInfoMap=" + userExtendInfoMap +
                ", isDel=" + isDel +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
