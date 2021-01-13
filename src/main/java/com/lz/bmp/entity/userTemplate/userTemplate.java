package com.lz.bmp.entity.userTemplate;

import com.lz.bmp.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/11 16:39
 */

public class userTemplate extends BaseEntity {
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

}
