package com.lz.bmp.vo;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 20:03
 */

public class UserTemplateVo {

    /**
     * 污染源名称
     */
    private String userTemplateName;

    /**
     * 污染源code
     */
    private String userTemplateCode;

    public String getUserTemplateName() {
        return userTemplateName;
    }

    public void setUserTemplateName(String userTemplateName) {
        this.userTemplateName = userTemplateName;
    }

    public String getUserTemplateCode() {
        return userTemplateCode;
    }

    public void setUserTemplateCode(String userTemplateCode) {
        this.userTemplateCode = userTemplateCode;
    }

    @Override
    public String toString() {
        return "UserTemplateVo{" +
                "userTemplateName='" + userTemplateName + '\'' +
                ", userTemplateCode='" + userTemplateCode + '\'' +
                '}';
    }
}
