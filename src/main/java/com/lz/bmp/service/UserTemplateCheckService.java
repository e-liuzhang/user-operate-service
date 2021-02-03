package com.lz.bmp.service;

import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.userTemplate.UserTemplate;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 15:15
 */

public interface UserTemplateCheckService {

    /**
     * 从前端的控件类型_字段结构中，提炼出字段到值的映射
     *
     * @param pureJsonTabInfoMap
     * @return
     * @throws Exception
     */
    PlainResult<Map<String, String>> getKeyValueInfo(Map<String, String> pureJsonTabInfoMap);

    /**
     * 获取模板中页面结构数据
     *
     * @param userTemplate
     * @param tabKey
     * @return
     */
    PlainResult<String> getPureJsonTabStr(UserTemplate userTemplate, String tabKey);

    /**
     * 解析json模板，得到fieldNo到具体空间信息的映射
     *
     * @param data
     * @return
     */
    Map<String, JSONObject> getWidgetsMap(String data);

    /**
     * 检查前端传入的值，是否满足模板格式要求
     *
     * @param widgetsMap
     * @param dataMap
     * @return
     */
    BaseResult checkPureJsonInfo(Map<String, JSONObject> widgetsMap, Map<String, String> dataMap);

}
