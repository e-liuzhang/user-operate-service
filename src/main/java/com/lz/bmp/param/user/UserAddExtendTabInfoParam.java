package com.lz.bmp.param.user;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/3 15:16
 */

public class UserAddExtendTabInfoParam {

    /**
     * 页面key值
     */
    private String tabKey;

    /**
     * 用户模板编码
     */
    private String userTemplateCode;

    /**
     * 用户编码
     */
    private String userUuid;

    /**
     * 设备跟随站点的信息
     */
    private Map<String, String> extendInfoMap;

    /**
     * 添加信息的唯一标识
     */
    private String dataUuid;

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

    public Map<String, String> getExtendInfoMap() {
        return extendInfoMap;
    }

    public void setExtendInfoMap(Map<String, String> extendInfoMap) {
        this.extendInfoMap = extendInfoMap;
    }

    public String getDataUuid() {
        return dataUuid;
    }

    public void setDataUuid(String dataUuid) {
        this.dataUuid = dataUuid;
    }

    @Override
    public String toString() {
        return "UserAddExtendTabInfoParam{" +
                "tabKey='" + tabKey + '\'' +
                ", userTemplateCode='" + userTemplateCode + '\'' +
                ", userUuid='" + userUuid + '\'' +
                ", extendInfoMap=" + extendInfoMap +
                ", dataUuid='" + dataUuid + '\'' +
                '}';
    }
}
