package com.lz.bmp.entity.userTemplate;

/**
 * @Author shangang_luo
 * @Date 2021/1/8 17:21
 */

public class UserTemplateCommonAttr {
    /**
     * 模板code
     */
    private String userTemplateCode;

    /**
     * 模板名称
     */
    private String userTemplateName;

    public String getUserTemplateCode() {
        return userTemplateCode;
    }

    public void setUserTemplateCode(String userTemplateCode) {
        this.userTemplateCode = userTemplateCode;
    }

    public String getUserTemplateName() {
        return userTemplateName;
    }

    public void setUserTemplateName(String userTemplateName) {
        this.userTemplateName = userTemplateName;
    }

    @Override
    public String toString() {
        return "UserTemplateCommonAttr{" +
                "userTemplateCode='" + userTemplateCode + '\'' +
                ", userTemplateName='" + userTemplateName + '\'' +
                '}';
    }
}
