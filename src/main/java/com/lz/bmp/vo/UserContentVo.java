package com.lz.bmp.vo;

import com.lz.bmp.entity.userTemplate.UserTemplateCommonAttr;
import com.lz.bmp.entity.userTemplate.UserTemplateInfo;
import com.lz.bmp.entity.userTemplate.UserTemplateTab;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/4 15:03
 */

public class UserContentVo {

    private Map<String, UserDataInfo> userDataMap;

    /**
     * 测站模板基本属性
     */
    private UserTemplateCommonAttr userTemplateCommonAttr;

    /**
     * 模板内容，一个用户对应多个页面
     */
    private Map<String, UserTemplateInfo> userTemplateDataMap;

    /**
     * 测站模板tab页
     */
    private List<UserTemplateTab> userTemplateTabList;

    public Map<String, UserDataInfo> getUserDataMap() {
        return userDataMap;
    }

    public void setUserDataMap(Map<String, UserDataInfo> userDataMap) {
        this.userDataMap = userDataMap;
    }

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

    @Override
    public String toString() {
        return "UserContentVo{" +
                "userDataMap=" + userDataMap +
                ", userTemplateCommonAttr=" + userTemplateCommonAttr +
                ", userTemplateDataMap=" + userTemplateDataMap +
                ", userTemplateTabList=" + userTemplateTabList +
                '}';
    }
}
