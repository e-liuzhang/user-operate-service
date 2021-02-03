package com.lz.bmp.param.user;

import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/2/3 19:25
 */

public class UserDeleteExtendTabInfoParam {
    /**
     * 页面key值
     */
    private String tabKey;

    /**
     * 用户模板编码
     */
    private String userTemplateCode;

    /**
     * 用户编码，基本信息的时候，必填，非基础信息的时候，也是必填
     */
    private String userUuid;
    /**
     * 获取额外信息唯一标识，dataUuid
     */
    private List<String> dataUuidList;

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    public String getUserTemplateCode() {
        return userTemplateCode;
    }

    public void setUserTemplateCode(String userTemplateCode) {
        this.userTemplateCode = userTemplateCode;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public List<String> getDataUuidList() {
        return dataUuidList;
    }

    public void setDataUuidList(List<String> dataUuidList) {
        this.dataUuidList = dataUuidList;
    }

    @Override
    public String toString() {
        return "UserDeleteExtendTabInfoParam{" +
                "tabKey='" + tabKey + '\'' +
                ", userTemplateCode='" + userTemplateCode + '\'' +
                ", userUuid='" + userUuid + '\'' +
                ", dataUuidList=" + dataUuidList +
                '}';
    }
}
