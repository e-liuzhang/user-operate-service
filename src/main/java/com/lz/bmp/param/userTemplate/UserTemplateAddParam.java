package com.lz.bmp.param.userTemplate;

import com.lz.bmp.entity.BaseEntity;
import com.lz.bmp.entity.userTemplate.UserTemplateCommonAttr;
import com.lz.bmp.entity.userTemplate.UserTemplateInfo;
import com.lz.bmp.entity.userTemplate.UserTemplateTab;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:56
 */

public class UserTemplateAddParam extends BaseEntity {

    /**
     * 测站模板基本属性
     */
    private UserTemplateCommonAttr userTemplateCommonAttr;

    /**
     * 模板内容，一个测站对应多个页面
     */
    private Map<String, UserTemplateInfo> userTemplateDataMap;

    /**
     * 测站模板tab页
     */
    private List<UserTemplateTab> userTemplateTabList;

    /**
     * 模板的状态
     */
    private Integer status;

    public UserTemplateCommonAttr getUserTemplateCommonAttr() {
        return userTemplateCommonAttr;
    }

    public void setUserTemplateCommonAttr(UserTemplateCommonAttr userTemplateCommonAttr) {
        this.userTemplateCommonAttr = userTemplateCommonAttr;
    }

    public Map<String, UserTemplateInfo> getUserTemplateDataMap() {
        return userTemplateDataMap;
    }

    public void setUserTemplateDataMap(Map<String, UserTemplateInfo> userTemplateDataMap) {
        this.userTemplateDataMap = userTemplateDataMap;
    }

    public List<UserTemplateTab> getUserTemplateTabList() {
        return userTemplateTabList;
    }

    public void setUserTemplateTabList(List<UserTemplateTab> userTemplateTabList) {
        this.userTemplateTabList = userTemplateTabList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserTemplateAddParam{" +
                "userTemplateCommonAttr=" + userTemplateCommonAttr +
                ", userTemplateDataMap=" + userTemplateDataMap +
                ", userTemplateTabList=" + userTemplateTabList +
                ", status=" + status +
                '}';
    }
}
