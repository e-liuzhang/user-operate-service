package com.lz.bmp.entity.user;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:49
 */

public class UserCommonInfo {

    /**
     * 用户模板的ID
     */
    private String userTemplateCode;

    /**
     * 用户编码，全局唯一
     */
    private String userUuid;

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
        return "UserCommonInfo{" +
                "userTemplateCode='" + userTemplateCode + '\'' +
                ", userUuid='" + userUuid + '\'' +
                '}';
    }
}
