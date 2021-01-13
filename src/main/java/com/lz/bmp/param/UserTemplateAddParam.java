package com.lz.bmp.param;

import com.lz.bmp.entity.userTemplate.UserTemplateCommonAttr;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:56
 */

public class UserTemplateAddParam {

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
     * 模版详情页
     */
    private UserTemplateInfo userTemplateDataInfo;

    /**
     * 模板的状态
     */
    private Integer status;
}
