package com.lz.bmp.param.userTemplate;

import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 16:50
 */

public class UserTemplateDeleteParam {
    /**
     * 删除对应的code
     */
    private List<String> userTemplateCodeList;

    public List<String> getUserTemplateCodeList() {
        return userTemplateCodeList;
    }

    public void setUserTemplateCodeList(List<String> userTemplateCodeList) {
        this.userTemplateCodeList = userTemplateCodeList;
    }

    @Override
    public String toString() {
        return "UserTemplateDeleteParam{" +
                "userTemplateCodeList=" + userTemplateCodeList +
                '}';
    }
}
