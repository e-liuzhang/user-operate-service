package com.lz.bmp.param.user;

/**
 * @Author shangang_luo
 * @Date 2021/2/3 20:41
 */

public class UserQueryExtendInfoParam {
    /**
     * 用户数据
     */
    private String userUuid;

    /**
     * 用户模板
     */
    private String userTemplateCode;

    /**
     * 扩展属性对应的key
     */
    private String tabKey;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getUserTemplateCode() {
        return userTemplateCode;
    }

    public void setUserTemplateCode(String userTemplateCode) {
        this.userTemplateCode = userTemplateCode;
    }

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    @Override
    public String toString() {
        return "UserQueryExtendInfoParam{" +
                "userUuid='" + userUuid + '\'' +
                ", userTemplateCode='" + userTemplateCode + '\'' +
                ", tabKey='" + tabKey + '\'' +
                '}';
    }
}
