package com.lz.bmp.entity.userTemplate;

import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/1/13 21:29
 */

public class UserTemplateInfo {

    /**
     * 测站对应的json值
     */
    private String jsonValue;

    /**
     * 表格栏的表头
     */
    private List<String> tabKeyLabelList;

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public List<String> getTabKeyLabelList() {
        return tabKeyLabelList;
    }

    public void setTabKeyLabelList(List<String> tabKeyLabelList) {
        this.tabKeyLabelList = tabKeyLabelList;
    }

    @Override
    public String toString() {
        return "UserTemplateInfo{" +
                "jsonValue='" + jsonValue + '\'' +
                ", tabKeyLabelList=" + tabKeyLabelList +
                '}';
    }
}
