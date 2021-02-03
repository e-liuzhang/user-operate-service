package com.lz.bmp.param.user;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/3 11:33
 */

public class UserUpdatePureJsonTabInfoParam {


    /**
     * 接收前端传递的用户基本属性映射值，核实一下这个里面的userCode与原来的userCode是否相同
     */
    private Map<String, String> pureJsonTabInfoMap;

    /**
     * 纯Json页面key值
     */
    private String tabKey;

    /**
     * 测站模板编码
     */
    private String userTemplateCode;

    /**
     * 站点编码，必传
     */
    private String userUuid;

    public Map<String, String> getPureJsonTabInfoMap() {
        return pureJsonTabInfoMap;
    }

    public void setPureJsonTabInfoMap(Map<String, String> pureJsonTabInfoMap) {
        this.pureJsonTabInfoMap = pureJsonTabInfoMap;
    }

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

    @Override
    public String toString() {
        return "UserUpdatePureJsonTabInfoParam{" +
                "pureJsonTabInfoMap=" + pureJsonTabInfoMap +
                ", tabKey='" + tabKey + '\'' +
                ", userTemplateCode='" + userTemplateCode + '\'' +
                ", userUuid='" + userUuid + '\'' +
                '}';
    }
}
